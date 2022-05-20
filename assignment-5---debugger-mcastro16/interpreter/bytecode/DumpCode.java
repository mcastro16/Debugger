package interpreter.bytecode;

import interpreter.*;
import java.util.Vector;

public class DumpCode extends ByteCode {
  String label;

  @Override
  public void execute(VirtualMachine vm) {}

  @Override
  public void init(Vector<String> args) {
    label = (String) args.firstElement();
  }

  @Override
  public String getArgs() {
    throw new UnsupportedOperationException("shouldn't be seeing this"); //To change body of generated methods, choose Tools | Templates.
  }
}
