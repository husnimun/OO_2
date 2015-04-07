import java.util.*;

/**
 *
 * @author 13513006 / Rahman Adianto, 13513022 / Husni Munaya, 13513078 Gazandi Cahyadarma
 */
public class Arithmetic extends Expression {
    
    /**
     * A constructor.
     * Membuat objek Arithmetic
     * @param ekspresi
     */
    public Arithmetic(String ekspresi) {
        super(ekspresi);
    }
    
    /**
     * Mengkalkulasi ekspresi
     * @return hasil perhitungan
     */
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
    
    /**
     * Method yang mengembalikan hasil dari operasi binary
     * Menghitung dua buah integer 
     * @param oprt sebuah string
     * @param a sebuah float
     * @param b sebuah float
     * @return hasil perhitungan operasi binary
     */
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
    
    /**
     * Fungsi yang mengecek apakah string berupa operan
     * @param opr yang berupa operan
     * @return boolean yang bernilai true jika string == operan
     */
    @Override
    public boolean isOperan(String opr) {
        return (!isOperator(opr)) && (!opr.equals("(")) && (!opr.equals(")"));
    }

    /**
     * Fungsi yang mengecek apakah string berupa operator
     * @param oprt yang berupa operator
     * @return boolean yang bernilai true jika string == operator
     */
    @Override
    public boolean isOperator(String oprt) {
        return (oprt.equals("*")  ||
                oprt.equals("/")  ||
                oprt.equals("+")  ||
                oprt.equals("-")
                );
    }

    
    /**
     * Fungsi yang mengecek apakah string berupa operator unary
     * @param oprt yang berupa string
     * @return boolean yang bernilai true jika string == operator unary
     */
    @Override
    public boolean isUnaryOpt(String oprt) {
        return false;
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

}