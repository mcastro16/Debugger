package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

public class HaltCode extends ByteCode {

  @Override
  public void init(Vector<String> args) {
  }

  @Override
  public void execute(VirtualMachine vm) {
    vm.stopRun();
  }

  @Override
  public String getArgs() {
    return "";
  }
}
