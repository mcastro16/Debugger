package interpreter.debugger;

import interpreter.ByteCodeLoader.*;
import java.util.*;
import interpreter.*;
import java.io.*;
import interpreter.bytecode.*;
import interpreter.bytecode.debuggercodes.*;

public class DebuggerVirtualMachine extends VirtualMachine {
    public Stack<FunctionEnvironmentRecord> environment;
    FunctionEnvironmentRecord fer;
    ArrayList<String> source;
    static ArrayList lineBreak, breakPoint;
    private BufferedReader in;
    Scanner scanner;
    Stack<Integer> functSt, functEnd;
    String userInput;
    ArrayList<String> printTrace;
    public boolean stepin, stepover;
    boolean trace = false;

    public Vector<SourceLine> breakpointContainer;

    public DebuggerVirtualMachine(Program program, String sourceFile) throws FileNotFoundException, IOException {
      super(program);
      printTrace = new ArrayList();
      stepin = false;
      functSt = new Stack();
      functEnd = new Stack();
      fer = new FunctionEnvironmentRecord();
      environment = new Stack();
      scanner = new Scanner(System.in);
      isRunning = true;
      source = new ArrayList();
      breakPoint = new ArrayList();
      breakpointContainer = new Vector();
      in = new BufferedReader(new FileReader(sourceFile));
      String lineHolder = in.readLine();
      while (lineHolder != null) {
        System.out.println(lineHolder);
        source.add(lineHolder);
        lineHolder = in.readLine();
        breakpointContainer.add(new SourceLine(source.get(source.size() - 1), false));
      }
    }

    public boolean setBreak(ArrayList<Integer> breakArray) {
      try {
        for (Integer compare : breakArray) {
          String buffer = breakpointContainer.get(compare - 1).getSourceLine();
          if (!(buffer.contains("{") || buffer.contains("int") || buffer.contains("boolean") || buffer.contains("while") || buffer.contains("return") || buffer.contains("=") || buffer.contains("if"))) {
            return false;
          }
        }
        for (Integer compare : breakArray) {
          breakpointContainer.get(compare - 1).breakptSet(true);
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        return false;
      }
      return true;
    }

    public void clearBreak(ArrayList<Integer> breakArray) {
      try {
        for (Integer compare : breakArray) {
          breakpointContainer.get(compare - 1).breakptSet(false);
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("This shouldn't have happened clearBreak failed");
      }
    }

    public int sourceSize() {
      return source.size();
    }

    public void runTrue() {
      isRunning = true;
    }

    public void stepIn() {
      stepin = true;
    }

    @Override
    public void executeProgram() {
      int envsize = environment.size();
      while (isRunning) {
        ByteCode code = program.getCode(pc);
        code.execute(this);
        pc++;

        if (code instanceof LineCode) {
          LineCode linecode = (LineCode) code;

          if (linecode.getLine() > 0 && breakpointContainer.get(linecode.getLine() - 1).breakptGet()) {
            stepin = false;
            code = program.getCode(pc);

            if (code instanceof FunctionCode) {//if its a function code increase counter and execute code
              code.execute(this);
              pc++;
              code = program.getCode(pc);

              while (code instanceof FormalCode) {//while the code is a formal code, execute formal code
                code.execute(this);
                pc++;
                code = program.getCode(pc);
              }
            }
            break;
          }
        }

        if (stepin && environment.size() == envsize + 1) {//same for step in but it'll break if there's an instance of a line or new entry in stack as said in reader
          if (code instanceof FunctionCode) {
            ByteCode nextcode = program.getCode(pc);
            if (nextcode instanceof LineCode || nextcode instanceof ReadCode
                                || nextcode instanceof WriteCode) {
              stepin = false;
              break;
            }
          }
          else if (code instanceof LineCode && ((LineCode) code).getLine() > 0) {
            stepin = false;
            break;
          }
        }
      }
    }

    public void debugPop(int pop) {//pops top of functionRecord
      fer.pop(pop);
    }

    public ByteCode getCode(int pc) {
      return program.getCode(pc);
    }

    public void end() {//pops top of functionenvironmentrecord stack
      FunctionEnvironmentRecord n = environment.pop();
    }

    public void stackPeek() {//checks the top of the enviromental stack
      FunctionEnvironmentRecord n = environment.peek();
    }

    public void setFunct(String s, int x, int y) {//sets the name, start, and end of the function
      fer.setFunctionInfo(s, x, y);
    }

    public int getFunctStackSize() {//returns function stack size
      return environment.size();
    }

    public void trace() {
      if (trace) {
        trace = false;
        System.out.println("Trace turned off");
      } else {
        trace = true;
        System.out.println("Trace turned on");
      }
    }

    public boolean getTraceBool() {//returns trace boolean
      return trace;
    }


    public void setTrace(String n) {//adds to the trace arraylist
      printTrace.add(n);
    }

    public ArrayList<Integer> getEnd() {//gets end of function
      if (functEnd.isEmpty()) {
        return null;
      }
      ArrayList<Integer> end = new ArrayList();
      end.add(functEnd.peek());
      return end;
    }


    public void funcRecEnter(String n, Integer i) {//enters new function record
      fer.enter(n, i);
    }

    public void currentLine(int n) {
      fer.setCurrentLineNumber(n);
    }

    public ArrayList<Integer> getStart() {
      if (functSt.isEmpty()) {
        return null;
      }
      ArrayList<Integer> start = new ArrayList();
      start.add(functSt.peek());
      return start;
    }

    public String getStack() {
      String callst = "";
      ListIterator iterator = environment.listIterator(environment.size());
      while (iterator.hasPrevious()) {
        FunctionEnvironmentRecord buffer = (FunctionEnvironmentRecord) iterator.previous();
        callst += buffer.getName() + ":" + buffer.getCurrent() + "\n";
      }
      return callst;
    }

    public String getName() {
      return fer.getName();
    }

    public void pushfuncRecordIntoStack() {
      environment.push(fer);
      fer = new FunctionEnvironmentRecord();
      fer.beginScope();
    }

    public Integer getCurrent() {//gets current int of the function
      return fer.getCurrent();
    }

    public void displayVar() {//display variable
      String displayedvar;
      Integer val, offset;
      Set<String> table = fer.getKey();
      Iterator iterator = table.iterator();
      try {
        while (iterator.hasNext()) {
          displayedvar = (String) (iterator.next());
          offset = fer.getOffset(displayedvar);
          val = runStack.getValue(offset);
          System.out.println(displayedvar + "=" + val);
        }
      } catch (ArrayIndexOutOfBoundsException e) {
          System.out.println("no variable to input yet");
      }
    }
}
