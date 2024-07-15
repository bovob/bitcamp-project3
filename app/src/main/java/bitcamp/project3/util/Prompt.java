package bitcamp.project3.util;

import bitcamp.project3.Monitor.AdminMonitor;
import bitcamp.project3.Monitor.UserMonitor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Prompt {
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

    //print User Menu
    //index=> 메뉴 배열 번호(main:0)
    //    private String[][] userMenus ={
    //            //Menu Num
    //            {"대출","반납","회원 정보 수정"}, //0~
    //            {"추천 도서 대출", "도서 검색"},  //1~
    //            {"제목", "저자"},                 //2~
    //            {"PW 수정", "MBTI 재검사"}        //3~
    //    };
    public static String printUserMenu(int index){
        String str = "";
        UserMonitor um = UserMonitor.getInstance();
        String[] userMenu = um.getUserMenus()[index];

        for(int no = 0 ; no<userMenu.length ; no++){
            str += String.format("[%-1d] %s\n", no+1, userMenu[no]);
        }
        if(index==0){
            str += String.format("[0] %s\n", "종료");
        }else{
            str += String.format("[0] %s\n", "이전 메뉴");
        }

        return str;
    }//Method printMenu END


    //print Admin Menu
    //index=> 메뉴 배열 번호(main:0)
    public static String printAdminMenu(int index){
        String str = "";
        AdminMonitor am = AdminMonitor.getInstance();
        String[] adminMenu = am.getAdminMenus()[index];

        for(int no = 0 ; no<adminMenu.length ; no++){
            str += String.format("[%-1d] %s\n", no+1, adminMenu[no]);
        }
        if(index==0){
            str += String.format("[0] %s\n", "종료");
        }else{
            str += String.format("[0] %s\n", "이전 메뉴");
        }

        return str;
    }//Method printMenu END


    public static void setClearCmd(){
        int top = 3;    //높이


        for(int i=0;i<top;i++){
            System.out.print("\n");
        }
    }

    ///////////////////////////////////////////////////////////
    ///////////////////////// print ///////////////////////////
    ///////////////////////////////////////////////////////////

    // [ERROR message] if system.in doesn't get Number
    public static void printNumberFormatException(){
        System.out.println( "숫자로 메뉴 번호를 입력해주세요.");
    }

    // [ERROR message] if system.in get over Number
    public static void printNumberLimitException() {
        System.out.println( "유효한 메뉴 번호를 입력해주세요.");
    }

    // Program Exit
    public static void printProgramExit() {
        System.out.println("[프로그램을 종료합니다...]");
    }

    // Disaccord ID
    public static void printDisaccordID() {
        System.out.println("[존재하지 않는 사용자입니다.]");
    }

    // Disaccord LogIn
    public static void printDisaccordLogin() {
        System.out.println("[ID PW를 다시 확인해주세요.]\n\n");
    }

    //Success LogIn
    public static void printSuccessLogin() {
        System.out.print("[로그인 되었습니다.]\n\n");
    }

    //[ERROR]
    private static String printError(){ return "[ERROR) "; }




    public static void close() {
        printProgramExit();
        keyboardScanner.close();
    }
}