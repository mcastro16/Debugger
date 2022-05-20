package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

public class GoToCode extends ByteCode {
  private String label;
  int address;

  public void setAddress(int i) {
    address = i;
  }

  @Override
  public void init(Vector<String> args) {
    label = (String) args.firstElement();
  }

  @Override
  public void execute(VirtualMachine vm) {
    vm.setPC(address);
  }

  public int getAddress() {
    return address;
  }

  @Override
  public String getArgs() {
    return label;
  }
}
