package interpreter;

import java.util.*;

public class CodeTable extends Object {
    private static HashMap<String, String> table = new HashMap<String, String>();

    public static String get(String code) {
        return table.get(code);
    }

    public static void init() {
        table.put("HALT", "HaltCode");
        table.put("POP", "PopCode");
        table.put("FALSEBRANCH", "FalseBranchCode");
        table.put("GOTO", "GoToCode");
        table.put("STORE", "StoreCode");
        table.put("LOAD", "LoadCode");
        table.put("LIT", "LitCode");
        table.put("ARGS", "ArgsCode");
        table.put("CALL", "CallCode");
        table.put("RETURN", "ReturnCode");
        table.put("BOP", "BopCode");
        table.put("READ", "ReadCode");
        table.put("WRITE", "WriteCode");
        table.put("LABEL", "LabelCode");
        table.put("DUMP", "DumpCode");
    }
}
