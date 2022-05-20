package interpreter.bytecode.debuggercodes;

import interpreter.*;
import interpreter.bytecode.*;
import interpreter.debugger.*;
import java.util.Vector;

public class LineCode extends ByteCode {
  int line;

  @Override
  public void init(Vector<String> args) {
    line = Integer.parseInt(args.get(0));
  }

  @Override
  public void execute(VirtualMachine vm) {
    DebuggerVirtualMachine virtM = (DebuggerVirtualMachine) vm;
    virtM.currentLine(line);
  }

  @Override
  public String getArgs() {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  public int getLine(){
    return line;
  }

  public int get(int n){
    line = n;
    return line;
  }
}
