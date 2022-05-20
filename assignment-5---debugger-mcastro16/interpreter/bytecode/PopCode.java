package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

public class PopCode extends ByteCode {
  int n;

  @Override
  public void init(Vector<String> args) {
    n = Integer.parseInt((String) args.firstElement());
  }

  @Override
  public void execute(VirtualMachine vm) {
    for (int i = 1; i < n; i++) {
      vm.pop();
    }
  }

  public int getPop() {
    return n;
  }

  public String getArgs() {
    return Integer.toString(n);
  }
}
