package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;

public class LabelCode extends ByteCode {
  private String label;

  @Override
  public void init(Vector<String> args) {
    label = (String) args.firstElement();
  }

  @Override
  public void execute(VirtualMachine vm) {
    String dumpString = "LABEL ";
    dumpString = dumpString.concat(label);
  }

  public String getArgs() {
    return label;
  }
}
