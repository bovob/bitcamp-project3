package bitcamp.project3.util;

import bitcamp.project3.vo.Book;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static bitcamp.project3.util.SystemMsg.errorProgramExit;
import static bitcamp.project3.util.TableFormat.koreanFormat;
import static bitcamp.project3.util.TableFormat.printTableDataFormat;

public class Prompt {

    //width length
    public static final int SMALL = 4;
    public static final int MIDDLE = 20;
    public static final int LARGE = 30;
    public static final int HUGE = 40;

    public static String boldTextCode = "\u001B[1m";
    public static String lightSkyBlueColorCode = "\u001B[38;2;135;206;250m";
    public static String yellowColorCode = "\u001B[38;2;255;255;153m";
    public static String pinkColorCode = "\u001B[35m";
    public static String resetColorCode = "\u001B[0m";

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
            String input = input(Mbti + " (" + options + " )?").toUpperCase();
            if (input.length() == 1 && options.contains(input)) {
                return input;
            }
            System.out.printf("잘못된 입력입니다." + options + " 중 하나를 입력해주세요.");
        }
    }


    public static String valid(Book book, int width){
        return book.isCheck()?
                "|"+pinkColorCode+String.format(koreanFormat(width, "대출 중"), "대출 중")+lightSkyBlueColorCode
                : printTableDataFormat(width, String.format("%s", "대출 가능"));
    }


    public static void close() {
        errorProgramExit();
        keyboardScanner.close();
    }
}