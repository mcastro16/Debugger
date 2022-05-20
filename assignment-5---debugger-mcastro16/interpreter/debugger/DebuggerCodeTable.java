package interpreter.debugger;

import java.util.*;

import interpreter.CodeTable;

public class DebuggerCodeTable extends Object{
  private static HashMap<String, String> codeMap = new HashMap<String, String>();

  public static String get(String code) {
    String result = codeMap.get(code.trim().toUpperCase());

    if(result == null) {
      return CodeTable.get(code);
    } else {
      return result;
    }
  }

  public static void init() {
    CodeTable.init();
    codeMap.put("CALL", "debuggercodes.DebugCallCode");
    codeMap.put("POP", "debuggercodes.DebugPopCode");
    codeMap.put("RETURN", "debuggercodes.DebugReturnCode");
    codeMap.put("LIT", "debuggercodes.DebugLitCode");
    codeMap.put("LINE", "debuggercodes.LineCode");
    codeMap.put("FUNCTION", "debuggercodes.FunctionCode");
    codeMap.put("FORMAL", "debuggercodes.FormalCode");
  }
}
