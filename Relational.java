import java.util.*;

/**
 *
 * @author 13513022 / Husni Munaya
 */
public class Relational extends Expression {
    public Relational(String ekspresi) {
        super(ekspresi);
    }
    
    public boolean calculate() {
        float operan1, operan2;
        String oprt;

        Scanner sc = new Scanner(ekspresi);
        
        operan1 = Float.parseFloat(sc.next());
        operan2 = Float.parseFloat(sc.next());
        oprt = sc.next();
        
        return binaryOpt(oprt, operan1, operan2);
    }
    public boolean binaryOpt(String oprt, float a, float b) {
        switch(oprt) {
            case "<":
                return a < b;
            case ">":
                return a > b;
            case "<=":
                return a <= b;
            case ">=":
                return a >= b;
            case "=":
                return a == b;
            case "<>": 
                return a != b;
            default:
                return false;
        }
    }
    @Override
    public boolean isOperan(String opr) {
        return (!isOperator(opr)) && (!opr.equals("(")) && (!opr.equals(")"));
    }

    @Override
    public boolean isOperator(String oprt) {
        return (oprt.equals(">")  || 
                oprt.equals("<")  || 
                oprt.equals(">=") || 
                oprt.equals("<=") || 
                oprt.equals("=")  ||
                oprt.equals("<>")
                );
    }

    @Override
    public boolean isUnaryOpt(String oprt) {
        return false;
    }

    @Override
    public boolean higherPrecedence(String o1, String o2) {
        return (getOperatorWeight(o1) > getOperatorWeight(o2));
    }

    @Override
    public int getOperatorWeight(String op) {
        return 0;
    }
    
}
