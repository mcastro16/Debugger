package interpreter;

import interpreter.bytecode.*;

import java.util.*;

public class Program extends Object {
    static HashMap<String, Integer> address;
    private final ArrayList<Object> container;

    Program () {
        container = new ArrayList();
        address = new HashMap<String, Integer>();
    }

    public void push(ByteCode byteCode) {
        if(byteCode instanceof LabelCode) {
            LabelCode labelBranch = (LabelCode)byteCode;
            addLabel(labelBranch.getArgs(),container.size());
        }
        container.add(byteCode);
    }

    public void resolveAddress() {
        for (Object content : container) {
            if (content instanceof GoToCode) {
                GoToCode changeBranch = (GoToCode) content;
                changeBranch.setAddress(address.get(changeBranch.getArgs()));
            } else if (content instanceof FalseBranchCode) {
                FalseBranchCode changeBranch = (FalseBranchCode) content;
                changeBranch.setAddress(address.get(changeBranch.getArgs()));
            } else if (content instanceof CallCode) {
                CallCode changeBranch = (CallCode) content;
                changeBranch.setAddress(address.get(changeBranch.getArgs()));
            }
        }
    }

    static public void addLabel(String key, int branch) {
        address.put(key, branch);
    }

    static public String getBranch(String branch) {
        return address.get(branch).toString();
    }

    public ByteCode getCode(int index) {
        return (ByteCode)container.get(index);
    }

    public int codeListSize() {
        return container.size();
    }

}
