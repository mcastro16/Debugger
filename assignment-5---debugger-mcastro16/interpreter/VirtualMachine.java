package interpreter;

import java.util.*;
import interpreter.bytecode.*;

public class VirtualMachine {
    protected RunTimeStack runStack;
    public int pc;
    protected Stack<Integer> returnAddrs;
    public boolean isRunning;
    protected Program program;

    public VirtualMachine(Program program) {
        this.program = program;
        runStack = new RunTimeStack();
        returnAddrs = new Stack<Integer>();
    }

    public void executeProgram() {
        pc = 0;
        isRunning = true;
        while (isRunning) {
            ByteCode code = program.getCode(pc);
            code.execute(this);
            pc++;
        }
    }

    public void setPC(int i) {
        pc = i;
    }

    public int getPC() {
        return pc;
    }

    public int peek() {
        return runStack.peek();
    }

    public int pop() {
        return runStack.pop();
    }

    public int Push(int i) {
        return runStack.push(i);
    }

    public void pushAddrs(int addrs) {
        returnAddrs.push(addrs);
    }

    public void newFrameAt(int i) {
        runStack.newFrameAt(i);
    }

    public void popFrame() {
        runStack.popFrame();
    }

    public int getStackSize() {
        return runStack.getRunStackSize();
    }

    public int store(int i) {
        return runStack.store(i);
    }

    public int load(int i) {
        return runStack.load(i);
    }

    public Integer push(Integer i) {
        return runStack.push(i);
    }

    public Integer popAddress() {
        return returnAddrs.pop();
    }

    public void setRun(boolean status) {
        isRunning = status;
    }

    public Boolean getRun() {
        return isRunning;
    }

    public void stopRun() {
        isRunning = false;
    }
}
