package interpreter.bytecode;

import interpreter.*;
import java.util.*;

public class ArgsCode extends ByteCode {
  int n;

  public void init(Vector<String> args) {
    n = Integer.parseInt((String) args.firstElement());
  }

  public void execute(VirtualMachine vm) {
    vm.newFrameAt(n);
  }

  public String getArgs() {
    return Integer.toString(n);
  }
}
