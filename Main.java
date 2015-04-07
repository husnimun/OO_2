
import java.io.*;
import java.util.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author husnimun
 */
public class Main {
    public static void main(String[] args) {
        Scanner in;
        String command;
        
        System.out.println("Masukkan ekspresi aritmatika: ");
        in = new Scanner(System.in);
        command = in.nextLine();
        
        Arithmetic bil = new Arithmetic(command);
        float resultArit = bil.calculate();
        System.out.println(resultArit);
        
        System.out.println("Masukkan ekspresi logika: ");
        in = new Scanner(System.in);
        command = in.nextLine();
        
        Logic bil2 = new Logic(command);
        boolean resultBool = bil2.calculate();
        System.out.println(resultBool);
        
    }
}
