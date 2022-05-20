package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.*;

public class FalseBranchCode extends ByteCode {
  private String label;
  int address;

  public void setAddress(int i) {
    address = i;
  }

  public int getAddress(){
    return address;
  }

  public void init(Vector<String> args) {
    label = (String) args.firstElement();
  }

  @Override
  public void execute(VirtualMachine vm) {
    int i = vm.pop();
    if (i == 0) {
      vm.setPC(address);
    }
  }

  @Override
  public String getArgs() {
    return label;
  }
}
