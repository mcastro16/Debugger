package interpreter.bytecode;

import java.util.*;
import interpreter.*;

public class CallCode extends ByteCode {
  String label;
  int address;

  @Override
  public void init(Vector<String> args) {
    label = (String) args.firstElement();
  }

  public String getArgs() {
    return label;
  }

  public void setAddress(int i) {
    address = i;
  }

  @Override
  public void execute(VirtualMachine vm) {
    vm.pushAddrs(vm.getPC());
    vm.setPC(address);
  }

  public int getAddress() {
    return address;
  }
}
