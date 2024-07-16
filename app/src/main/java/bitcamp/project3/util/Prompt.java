package bitcamp.project3.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static bitcamp.project3.util.SystemMsg.errorProgramExit;

public class Prompt {

    //width length
    public static final int SMALL = 4;
    public static final int MIDDLE = 15;
    public static final int LARGE = 20;
    public static final int HUGE = 30;

    static Scanner keyboardScanner = new Scanner(System.in);

    // String 값 Input
    public static String input(String format, Object... args) {
        System.out.printf(format + " ", args);
        return keyboardScanner.nextLine();
    }

    // Int 값 Input
    public static int inputInt(String format, Object... args) {
        return Integer.parseInt(input(format, args));
    }

    // Date 값 Input
    public static LocalDate inputDate(String format, Object... args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            try {
                String dateString = input(format, args);
                return LocalDate.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("적절한 데이트 타입이 아닙니다. ");
            }
        }
    }

    // MBTI
    public static String inputMBTI(String Mbti, String options) {
        while (true) {
            String input = input(Mbti +" ("+ options+" )?").toUpperCase();
            if (input.length() == 1 && options.contains(input)){
                return input;
            }
            System.out.printf("잘못된 입력입니다." +options+" 중 하나를 입력해주세요.");
        }
    }


    public static void close() {
        errorProgramExit();
        keyboardScanner.close();
    }
}