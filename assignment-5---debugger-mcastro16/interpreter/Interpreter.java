package interpreter;

import java.io.*;
import interpreter.debugger.*;

public class Interpreter {
    ByteCodeLoader bcl;

    public Program getProg() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Program program = bcl.loadCodes();
        return program;
    }

    public Interpreter(String codeFile, boolean debug) {
        try {
            if (!debug) {
                CodeTable.init();
            } else {
                DebuggerCodeTable.init();
            }
            bcl = new ByteCodeLoader(codeFile);
        } catch (IOException e) {
            System.out.println("**** " + e);
        }
    }

    void run() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Program program = bcl.loadCodes();
        VirtualMachine vm = new VirtualMachine(program);
        vm.executeProgram();
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (args.length == 0) {
            System.out.println("***Incorrect usage, try: java interpreter.Interpreter <file>");
            System.exit(1);
        } else if (args.length == 2 && args[0].equals("-d")) {
            (new Debugger(args[1] + ".x.cod", args[1] + ".x")).run();
        }
    }
}
