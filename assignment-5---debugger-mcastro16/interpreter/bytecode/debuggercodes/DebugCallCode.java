package interpreter.bytecode.debuggercodes;

import interpreter.*;
import interpreter.bytecode.*;
import interpreter.debugger.*;

public class DebugCallCode extends CallCode {

  public void execute(VirtualMachine vm) {
    DebuggerVirtualMachine virtM = (DebuggerVirtualMachine) vm;
    super.execute(vm);
    virtM.pushfuncRecordIntoStack();
  }
}
