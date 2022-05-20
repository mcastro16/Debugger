package interpreter.bytecode;

import java.util.Vector;
import interpreter.VirtualMachine;

public class ReturnCode extends ByteCode {
  String label = "";
  public int topValue;

  @Override
  public void init(Vector<String> args) {
    if (!args.isEmpty()) {
      label = args.firstElement();
    }
  }

  @Override
  public void execute(VirtualMachine vm) {
    vm.popFrame();
    vm.setPC(vm.popAddress());
    topValue = vm.peek();
  }

  @Override
  public String getArgs() {
    return label;
  }
}
