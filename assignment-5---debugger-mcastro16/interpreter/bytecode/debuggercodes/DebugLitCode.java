package interpreter.bytecode.debuggercodes;

import interpreter.*;
import interpreter.bytecode.*;
import interpreter.debugger.*;
import java.util.*;

public class DebugLitCode extends LitCode {

  @Override
  public void execute(VirtualMachine vm) {
    DebuggerVirtualMachine virtM = (DebuggerVirtualMachine) vm;
    super.execute(virtM);
    if (!(id.isEmpty())) {
      int buffer = virtM.getStackSize();
      virtM.funcRecEnter(id, buffer);
    }
  }
}
