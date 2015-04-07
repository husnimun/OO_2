import java.util.*;
/**
 *
 * @author husnimun
 */
public class Arithmetic extends Expression {
    public Arithmetic(String ekspresi) {
        super(ekspresi);
    }
    
    public float calculate() {
        Stack<Float> calc = new Stack<>();
        String word_temp;
        float operan1, operan2;
        
        Scanner sc = new Scanner(ekspresi);
        
        while (sc.hasNext()) {
            word_temp = sc.next();
            if (isOperan(word_temp)) {
                calc.push(Float.parseFloat(word_temp));
            } else {
                operan2 = calc.pop();
                operan1 = calc.pop();
                calc.push(binaryOpt(word_temp, operan1, operan2));
            }
        }
        return calc.peek();
    }
    
    @Override
    public boolean isOperan(String opr) {
        return (!isOperator(opr)) && (!opr.equals("(")) && (!opr.equals(")"));
    }

    @Override
    public boolean isOperator(String oprt) {
        return (oprt.equals("*")  ||
                oprt.equals("/")  ||
                oprt.equals("+")  ||
                oprt.equals("-")  ||
                oprt.equals(">")  || 
                oprt.equals("<")  || 
                oprt.equals(">=") || 
                oprt.equals("<=") || 
                oprt.equals("=")  ||
                oprt.equals("<>")
                );
    }

    @Override
    public boolean higherPrecedence(String o1, String o2) {
       return (getOperatorWeight(o1) > getOperatorWeight(o2)); 
    }

    @Override
    public int getOperatorWeight(String op) {
        switch(op) {
            case "*":
                return 4;
            case "/":
                return 3;
            case "+":
                return 2;
            case "-":
                return 1;
            default:
                return 0;
        }
    }

    public float binaryOpt(String oprt, float a, float b) {
        switch(oprt) {
            case "*":
                return a * b;
            case "/":
                return a / b;
            case "+":
                return a + b;
            case "-":
                return a - b;
            default: 
                return 0;
        }
    }


    @Override
    public boolean isUnaryOpt(String oprt) {
        return false;
    }
}