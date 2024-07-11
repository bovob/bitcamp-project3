package bitcamp.project3.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Prompt {
    static Scanner keyboardScanner = new Scanner(System.in);

    public static String input(String format, Object... args) {
        System.out.printf(format + " ", args);
        return keyboardScanner.nextLine();
    }

    public static int inputInt(String format, Object... args) {
        return Integer.parseInt(input(format, args));
    }

    // 아직 사용 여부 불확실
    //public static LocalDate inputDate(String format, Object... args) {
    //    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    //    while (true) {
    //        try {
    //            String dateString = input(format, args);
    //            return LocalDate.parse(dateString, formatter);
    //        } catch (DateTimeParseException e) {
    //            System.out.println("적절한 데이트 타입이 아닙니다. ");
    //        }
    //    }
    //}


    public static void close() {
        keyboardScanner.close();
    }
}