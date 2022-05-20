package interpreter.debugger;

public class SourceLine {
    private  String sourceLine;
    public Boolean isBreakptSet;

    public SourceLine(String i, Boolean isBreakptSet) {
      sourceLine = i;
      this.isBreakptSet = isBreakptSet;
    }

    public void breakptSet(boolean isBreakptSet) {
      this.isBreakptSet = isBreakptSet;
    }

    public boolean breakptGet() {
      return isBreakptSet;
    }

    public String getSourceLine() {
      return sourceLine;
    }
}
