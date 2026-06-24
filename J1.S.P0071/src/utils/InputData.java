/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class InputData {
    private static final Scanner SCANNER = new Scanner(System.in);

    public String getString(String message, String regex) {
        return inputString(message, regex, false);
    }
    
    public String inputString(String message, String regex, boolean allowEmpty) {
        System.out.print(message);
        while(true){
            String value = SCANNER.nextLine().trim();
            if(value.matches(regex) || (allowEmpty && value.isEmpty())){
                return value;
            }
            System.out.println("Please input valid string.");
        }
    }
    
    public int inputInteger(String message, String regex) {
        System.out.print(message);
        while (true) {
            String number = SCANNER.nextLine().trim();
            if (number.matches(regex)) {
                return Integer.parseInt(number);
            }
            System.out.println("Please input valid integer.");
        }
    }

    public double inputDouble(String message, String regex) {
        System.out.print(message);
        while (true) {
            String number = SCANNER.nextLine().trim();
            if (number.matches(regex)) {
                return Double.parseDouble(number);
            }
            System.out.println("Please input valid number.");
        }
    }
    
    public Date getDate(String message, String regex){
        System.out.print(message);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        formatter.setLenient(false);
        while (true) {
            String date = SCANNER.nextLine().trim();
            if (date.matches(regex)) {
                try {
                    return formatter.parse(date);
                } catch (ParseException e) {
                    System.out.println("Please input valid date.");
                    continue;
                }
            }
            System.out.println("Please input valid date.");
        }
    }
}
