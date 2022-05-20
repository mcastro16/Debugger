package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

public class LoadCode extends ByteCode {
  int n;
  String id;

  @Override
  public void init(Vector<String> args) {
    n = Integer.parseInt((String) args.firstElement());
    id = (String) args.firstElement();
  }

  @Override
  public void execute(VirtualMachine vm) {
    vm.load(n);
  }

  @Override
  public String getArgs() {
    return id;
  }
}
