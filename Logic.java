import java.util.*;

/**
 *
 * @author 13513006 / Rahman Adianto, 13513022 / Husni Munaya
 */
public class Logic extends Expression {
    public Logic(String ekspresi) {
        super(ekspresi);
    }
    
    public boolean calculate() {
        Stack<Boolean> calc = new Stack<>();
        
        boolean operan1, operan2;
        String word_temp;
        
        Scanner sc = new Scanner(ekspresi);
        while (sc.hasNext()) {
            word_temp = sc.next();
            if (isOperan(word_temp)) {
                calc.push(Boolean.parseBoolean(word_temp));
            } else {
                if (word_temp.equals("not")) {
                    operan1 = calc.pop();
                    calc.push(unaryOpt(word_temp, operan1));
                } else {
                    operan2 = calc.pop();
                    operan1 = calc.pop();
                    calc.push(binaryOpt(word_temp, operan1, operan2));
                }
            }
        }
        return calc.peek();
    }
    
    @Override
    public boolean isOperan(String opr) {
        return opr.equals("true") || opr.equals("false");
    }

    @Override
    public boolean isOperator(String oprt) {
        return oprt.equals("and") || oprt.equals("or") || oprt.equals("xor") || oprt.equals("not");
    }

    @Override
    public boolean higherPrecedence(String o1, String o2) {
        return (getOperatorWeight(o1) > getOperatorWeight(o2));
    }

    @Override
    public int getOperatorWeight(String op) {
        switch(op) {
            case "not":
                return 4;
            case "and":
                return 3;
            case "xor":
                return 2;
            case "or":
                return 1;
            default: 
                return 0;
        }
    }
    
    public boolean binaryOpt(String oprt, boolean a, boolean b) {
        switch(oprt) {
            case "and":
                return a && b;
            case "xor":
                return a ^ b;
            case "or":
                return a || b;
            default: 
                return false;
        }
    }
    public boolean unaryOpt(String oprt, boolean a) {
        return oprt.equals("not") ? !a : false;
    }

    @Override
    public boolean isUnaryOpt(String oprt) {
        return oprt.equals("not");
    }
    
}
