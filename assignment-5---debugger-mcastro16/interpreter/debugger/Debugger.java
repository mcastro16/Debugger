package interpreter.debugger;

import interpreter.debugger.ui.*;
import java.io.*;
import interpreter.*;

public class Debugger {
  Interpreter interpreter;
  DebuggerVirtualMachine dvm;
  DebuggerShell ds;
  String file, file2;
  static boolean status = true;

  public Debugger(String codeFile, String sourceFile) {
    file = codeFile;
    file2 = sourceFile;
  }

  public void run() throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
    Interpreter interpreter = new Interpreter(file, true);
    dvm = new DebuggerVirtualMachine(interpreter.getProg(), file2);
    ds = new DebuggerShell(dvm);
    ds.printCode();
    while(dvm.getRun()){
      ds.help();
    }
    System.out.println("Debugging is Done");
  }
}
