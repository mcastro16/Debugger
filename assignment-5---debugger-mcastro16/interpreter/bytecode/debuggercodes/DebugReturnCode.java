package interpreter.bytecode.debuggercodes;

import interpreter.*;
import interpreter.bytecode.*;
import interpreter.debugger.*;

public class DebugReturnCode extends ReturnCode {

  @Override
  public void execute(VirtualMachine vm) {//CURRENTLY POPS STACK
    DebuggerVirtualMachine virtM = (DebuggerVirtualMachine) vm;
    super.execute(virtM);
    virtM.end();
  }
}
