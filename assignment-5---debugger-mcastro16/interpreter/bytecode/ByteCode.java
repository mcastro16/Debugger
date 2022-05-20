package interpreter.bytecode;
import java.util.*;
import interpreter.VirtualMachine;

public abstract class ByteCode {

  public abstract void init(Vector<String> args);
  public abstract void execute(VirtualMachine vm);
  public abstract String getArgs();
}
