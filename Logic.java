import java.util.*;

/**
 *
 * @author 13513006 / Rahman Adianto, 13513022 / Husni Munaya
 */
public class Logic extends Expression {
    
    /**
     * A constructor.
     * Membuat objek Arithmetic
     * @param ekspresi
     */
    public Logic(String ekspresi) {
        super(ekspresi);
    }
    
    /**
     * Mengkalkulasi ekspresi
     * @return hasil perhitungan
     */
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
    
    /**
     * Method yang mengembalikan hasil dari operasi binary
     * Menghitung dua buah integer 
     * @param oprt sebuah string
     * @param a sebuah float
     * @param b sebuah float
     * @return hasil perhitungan operasi binary
     */
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
    
    /**
     * Method yang mengembalikan hasil dari operasi unary
     * Menghitung satu buah integer 
     * @param oprt sebuah string
     * @param a sebuah boolean
     * @return boolean yang merupakan hasil operasi unary
     */
    public boolean unaryOpt(String oprt, boolean a) {
        return oprt.equals("not") ? !a : false;
    }
    
    
    /**
     * Fungsi yang mengecek apakah string berupa operan
     * @param opr yang berupa operan
     * @return boolean yang bernilai true jika string == operan
     */
    @Override
    public boolean isOperan(String opr) {
        return opr.equals("true") || opr.equals("false");
    }

    /**
     * Fungsi yang mengecek apakah string berupa operator
     * @param oprt yang berupa operator
     * @return boolean yang bernilai true jika string == operator
     */
    @Override
    public boolean isOperator(String oprt) {
        return oprt.equals("and") || oprt.equals("or") || oprt.equals("xor") || oprt.equals("not");
    }

    
    /**
     * Fungsi yang mengecek apakah operator o1 mempunyai prioritas lebih dibanding o2
     * @param o1 operator yang berupa string
     * @param o2 operator yang berupa string
     * @return boolean yang bernilai true jika o1 memiliki prioritas lebih dibanding o2
     */
    @Override
    public boolean higherPrecedence(String o1, String o2) {
        return (getOperatorWeight(o1) > getOperatorWeight(o2));
    }

    
    /**
     * Fungsi yang menghasilkan berat operator
     * @param op operator yang berupa string
     * @return integer yang merupakan bobot dari operator
     */
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
    
    /**
     * Method yang mengecek apakah string berupa operator unary
     * @param oprt sebuah string
     * @return boolean yang bernilai true jika oprt berupa operator unary
     */
    @Override
    public boolean isUnaryOpt(String oprt) {
        return oprt.equals("not");
    }
    
}
