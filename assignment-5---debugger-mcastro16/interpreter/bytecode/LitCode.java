package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

public class LitCode extends ByteCode {
  int n;
  public String id = "";

  @Override
  public void init(Vector<String> args) {
    n = Integer.parseInt(args.firstElement());
    if (args.size() > 1) {
      id = args.get(args.size() - 1);
    }
  }

  @Override
  public void execute(VirtualMachine vm) {
    vm.Push(n);
  }

  @Override
    public String getArgs() {
    return id;
  }
}
