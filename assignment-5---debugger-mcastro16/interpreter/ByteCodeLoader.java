package interpreter;

import java.io.*;
import java.util.*;
import interpreter.bytecode.*;
import interpreter.debugger.DebuggerCodeTable;

public class ByteCodeLoader {
    private BufferedReader reader;

    public ByteCodeLoader(String programFile) throws IOException {
        reader = new BufferedReader(new FileReader(programFile));
    }

    public Program loadCodes() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        Program program = new Program();
        Vector<String> codeList = new Vector<String>();
        String newLine = reader.readLine();

        while (newLine != null) {
            StringTokenizer tokenizer = new StringTokenizer(newLine);
            codeList.clear();
            String hashKey = tokenizer.nextToken();
            String hashVal = DebuggerCodeTable.get(hashKey);
            ByteCode bytecoder = (ByteCode) (Class.forName("interpreter.bytecode." + hashVal).newInstance());
            System.out.println(hashVal);

            while (tokenizer.hasMoreTokens()) {

                codeList.add(tokenizer.nextToken());
            }
            bytecoder.init(codeList);
            program.push(bytecoder);

            newLine = reader.readLine();
        }
        program.resolveAddress();
        return program;
    }
}
