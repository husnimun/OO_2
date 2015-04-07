import java.util.*;

/**
 *
 * @author 13513022 / Husni Munaya
 */
public class Main {
    public static void main(String[] args) {
        Scanner in;
        String command;
        
        System.out.println("Masukkan ekspresi aritmatika: ");
        in = new Scanner(System.in);
        command = in.nextLine();
        
        Arithmetic ekspresi1 = new Arithmetic(command);
        float resultArit = ekspresi1.calculate();
        System.out.println(resultArit);
        
        System.out.println("Masukkan ekspresi logika: ");
        in = new Scanner(System.in);
        command = in.nextLine();
        
        Logic ekspresi2 = new Logic(command);
        boolean resultBool = ekspresi2.calculate();
        System.out.println(resultBool);
        
        System.out.println("Masukkan ekspresi relasional: ");
        in = new Scanner(System.in);
        command = in.nextLine();
        
        Relational ekspresi3 = new Relational(command);
        boolean resultRelational = ekspresi3.calculate();
        System.out.println(resultRelational);
        
    }
}
