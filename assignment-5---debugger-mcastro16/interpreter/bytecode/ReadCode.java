package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.*;

public class ReadCode extends ByteCode {
  Scanner scanner = new Scanner(System.in);

  @Override
  public void init(Vector<String> args) {}

  @Override
  public void execute(VirtualMachine vm) {
    System.out.print("input number ");
    int input = scanner.nextInt();
    vm.Push(input);
  }

  @Override
  public String getArgs() {
    return "";
  }
}
