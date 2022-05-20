package interpreter;

import java.util.*;

public class RunTimeStack {
    private final Stack<Integer> framePointers;
    private final Vector<Integer> runStack;

    public RunTimeStack() {
        framePointers = new Stack<Integer>();
        runStack = new Vector<Integer>();
        framePointers.push(0);
    }

    public int peek() {
        return runStack.lastElement();
    }

    public int pop() {
        int pop = runStack.lastElement();
        runStack.remove(runStack.size() - 1);
        return pop;
    }

    public int push(int i) {
        runStack.add(i);
        return i;
    }

    public void newFrameAt(int offset) {
        framePointers.push(runStack.size() - offset);
    }

    public int peekFrame() {
        return framePointers.peek();
    }

    public void popFrame() {
        int returnValue = runStack.lastElement();
        while (runStack.size() != framePointers.peek()) {
            runStack.remove(runStack.size() - 1);
        }
        framePointers.pop();
        runStack.add(returnValue);
    }

    public int store(int offset) {
        int storeValue = runStack.get(runStack.size() - 1);
        runStack.remove(runStack.size() - 1);
        runStack.set(framePointers.peek() + offset, storeValue);
        return storeValue;
    }

    public int load(int offset) {
        int loadValue = runStack.get(framePointers.peek() + offset);
        runStack.add(loadValue);
        return loadValue;
    }

    public Integer push(Integer i) {
        runStack.add(i);
        return i;
    }

    public int getRunStackSize() {
        return runStack.size();
    }
    
    public int getValue(int n) {
        return runStack.get(n);
    }
}
