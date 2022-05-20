package interpreter.debugger.ui;

import interpreter.debugger.*;
import java.util.*;
import java.io.*;

public class DebuggerShell {
    int line;
    private BufferedReader in;
    Scanner scanner;
    private ArrayList<Integer> lineBreak;
    private Vector<SourceLine> breakContainer;
    String userInput;
    static DebuggerVirtualMachine dvm;

    public DebuggerShell(DebuggerVirtualMachine n) {
      line = 1;
      this.dvm = n;
      scanner = new Scanner(System.in);
      breakContainer = dvm.breakpointContainer;
      lineBreak = new ArrayList();
    }

    public void help() {
      System.out.println("Type ? for help");

      System.out.println("> ");
      userInput = scanner.next();
      switch (userInput) {
        case "?":
          System.out.println("Command:\tUse:");
          System.out.println("set\t\tsets breakpoints");
          System.out.println("source\t\tprints code");
          System.out.println("continue\tcontinues");
          System.out.println("exit\t\texits");
          System.out.println("locals\t\tdisplay variables");
          System.out.println("step\t\tsteps into");
          System.out.println("list\t\tshows breakpoints");
          break;

        case "set":
          setBreak();
          break;
        case "source":
          printCode();
          break;
        case "continue":
          cont();
          break;
        case "exit":
          quit();
          break;
        case "locals":
          displayVar();
          break;
        case "step":
          stepIn();
          break;
        case "list":
          showBreak();
          break;
        default:
          System.out.println("Incorrect syntax or unknown command");
          break;
      }
    }

    public void printCode() {//prints whole code
      int sourceSize = breakContainer.size();
      String source;
      for (int i = 0; i < sourceSize; i++) {
        SourceLine sLine = breakContainer.get(i);
        if (sLine.isBreakptSet) {
          source = "*";
        } else {
          source = "";
        }
        source = source.concat(" " + sLine.getSourceLine());
        System.out.println((i + 1) + ": " + source);
      }
    }

    public void setBreak() {
      ArrayList<Integer> point = new ArrayList();
      boolean breakStatus = true;
      userInput = scanner.next();
      StringTokenizer tokenizer = new StringTokenizer(userInput);
      while (tokenizer.hasMoreTokens()) {
        int buffer = Integer.parseInt(tokenizer.nextToken());
        point.add(buffer);
        lineBreak.add(buffer);
      }
      if (dvm.setBreak(point)) {
        System.out.println("Breakpoints set at lines: ");
        for (int lines : point) {
          System.out.print(point + " ");
        }
      } else {
        System.out.println("Error could not set breakpoints");
      }
    }

    public void showBreak() {
      if (breakContainer.isEmpty()) {
        System.out.println("No break points set");
        return;
      } else {
        System.out.println("Breakpoints set at: ");
      }
      System.out.println(lineBreak.size());
      String buffer;
      Iterator<Integer> iterator = lineBreak.iterator();
      while (iterator.hasNext()) {
        buffer = (Integer.toString(iterator.next()));
        System.out.print(buffer + " ");
      }
      System.out.println("");
    }

    public void cont() {//continue is a used word
      dvm.executeProgram();
      printCode();
    }

    public void stepIn() {
      dvm.stepIn();
      cont();
    }

    public void quit() {
      System.out.println("EXECUTION STOPPED");
      dvm.setRun(false);
    }

    public void displayVar() {
      dvm.displayVar();
    }
}
