package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

public class StoreCode extends ByteCode {
  int n;
  String id = "";

  @Override
  public void init(Vector<String> args) {
    if (args.size() == 1) {
      n = Integer.parseInt((String) args.firstElement());
    } else {
      id = args.get(args.size() - 1);
      n = Integer.parseInt((String) args.firstElement());
    }
  }

  @Override
  public void execute(VirtualMachine vm) {
    vm.store(n);
  }

  @Override
  public String getArgs() {
    return id;
  }
}
