package interpreter.bytecode.debuggercodes;

import interpreter.*;
import interpreter.bytecode.*;
import interpreter.debugger.*;

public class DebugPopCode extends PopCode {

  @Override
  public void execute(VirtualMachine vm) {
    DebuggerVirtualMachine virtM = (DebuggerVirtualMachine) vm;
    super.execute(vm);
  }
}
