package interpreter.bytecode;

import interpreter.*;
import java.util.*;

public class BopCode extends ByteCode {
  String operator;

  @Override
  public void init(Vector<String> args) {
    operator = (String) args.firstElement();
  }

  public void execute(VirtualMachine vm) {
    int valueOne = vm.pop();
    int valueTwo = vm.pop();
    int sum;
    switch (operator) {
      case "+":
        sum = valueTwo + valueOne;
        break;
      case "-":
        sum = valueTwo - valueOne;
        break;
      case "*":
        sum = valueTwo * valueOne;
        break;
      case "/":
        sum = valueTwo / valueOne;
        break;
      case "==":
        if (valueTwo == valueOne) {
          sum = 1;
        } else {
          sum = 0;
        }
        break;
      case "!=":
        if (valueTwo != valueOne) {
          sum = 1;
        } else {
          sum = 0;
        }
        break;
      case ">=":
        if (valueTwo >= valueOne) {
          sum = 1;
        } else {
          sum = 0;
        }
        break;
      case "<=":
        if (valueTwo <= valueOne) {
          sum = 1;
        } else {
          sum = 0;
        }
        break;
      case ">":
        if (valueTwo > valueOne) {
          sum = 1;
        } else {
          sum = 0;
        }
        break;
      case "<":
        if (valueTwo < valueOne) {
          sum = 1;
        } else {
          sum = 0;
        }
        break;
      case "|":
        if (valueTwo == 1 || valueOne == 1) {
          sum = 1;
        } else {
          sum = 0;
        }
        break;
      case "&":
        if (valueTwo == 1 && valueOne == 1) {
          sum = 1;
        } else {
          sum = 0;
        }
        break;
      default:
        sum = 0;
        break;
    }
    vm.push(sum);
  }

  @Override
  public String getArgs() {
    return operator;
  }
}
