package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

public class WriteCode extends ByteCode {
  int n;

  @Override
  public void init(Vector<String> args) {}

  @Override
  public void execute(VirtualMachine vm) {
    n = vm.peek();
  }

  public String getArgs() {
    return Integer.toString(n);
  }
}
