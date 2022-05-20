package interpreter.debugger;

import java.util.*;

class Binder {
    private Object value;
    private String prevtop;
    private Binder tail;

    Binder(Object v, String p, Binder t) {
        value = v;
        prevtop = p;
        tail = t;
    }

    Object getValue() {
        return value;
    }

    String getPrevtop() {
        return prevtop;
    }

    Binder getTail() {
        return tail;
    }
}

class Table {

    private java.util.HashMap<String, Binder> symbols = new java.util.HashMap<String, Binder>();
    private String top;
    private Binder marks;

    public Table() {}

    public Object get(String key) {
        Binder e = symbols.get(key);
        return e.getValue();
    }

    public void put(String key, Object value) {
        symbols.put(key, new Binder(value, top, symbols.get(key)));
        top = key;
    }

    public void beginScope() {
        marks = new Binder(null, top, marks);
        top = null;
    }

    public void pop(int i) {
        for (int counter = 0; counter < i; counter++) {
            Binder e = symbols.get(top);
            if (e.getTail() != null) {
                symbols.put(top, e.getTail());
            } else {
                symbols.remove(top);
            }
            top = e.getPrevtop();
        }
    }

    public void endScope() {
        while (top != null) {
            Binder e = symbols.get(top);
            if (e.getTail() != null) {
                symbols.put(top, e.getTail());
            } else {
                symbols.remove(top);
            }
            top = e.getPrevtop();
        }
        top = marks.getPrevtop();
        marks = marks.getTail();
    }

    public java.util.Set<String> keys() {
        return symbols.keySet();
    }
}

public class FunctionEnvironmentRecord {
    String prevtop = "";
    Binder tail;
    String name;
    Integer startLine, endLine;
    Integer currentLine = 0;
    Table table;

    public FunctionEnvironmentRecord() {
        table = new Table();
        name = new String();
        startLine = endLine = currentLine = 0;
    }

    public static void main(String args[]) {
        FunctionEnvironmentRecord record = new FunctionEnvironmentRecord();
        record.beginScope();
        System.out.println(record);

        record.setFunctionInfo("g", 1, 20);
        System.out.println(record);

        record.setCurrentLineNumber(5);
        System.out.println(record);

        record.enter("a", 4);
        System.out.println(record);

        record.enter("b", 2);
        System.out.println(record);

        record.enter("c", 7);
        System.out.println(record);

        record.enter("a", 1);
        System.out.println(record);

        record.pop(2);
        System.out.println(record);

        record.pop(1);
        System.out.println(record);
    }

    void beginScope() {
        table.beginScope();
    }

    void endScope() {
        table.endScope();
    }

    void pop(int i) {
        table.pop(i);
    }

    public void setCurrentLineNumber(int n) {
        currentLine = n;
    }

    public void setFunctionInfo(String n, Integer s, Integer e) {
        name = n;
        startLine = s;
        endLine = e;
    }

    public Set<String> getKey() {
        return table.keys();
    }
    public String getName(){
        return name;
    }

    public Integer getStart() {
        return startLine;
    }

    public Integer getEnd() {
        return endLine;
    }

    public Integer getCurrent() {
        return currentLine;
    }

    public Integer getOffset(String n) {
        return (Integer) (table.get(n));
    }

    public void enter(String var, Integer value) {
        table.put(var, value);
    }
}
