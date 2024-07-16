package bitcamp.project3.util;

import static bitcamp.project3.util.Prompt.lightSkyBlueColorCode;
import static bitcamp.project3.util.Prompt.resetColorCode;

public class SystemMsg {
    public static void setClearCmd(){
        int top = 5;    //높이

//        loading(2000);
        for(int i=0;i<top;i++){
            System.out.print("\n");
        }

    }


    // [ERROR message] if system.in doesn't get Number
    public static void errorNumberFormatException(){
        System.out.print( lightSkyBlueColorCode+"[숫자로 메뉴 번호를 입력해주세요.]\n\n"+resetColorCode);
    }

    // [ERROR message] if system.in get over Number
    public static void errorNumberLimitException() {
        System.out.print( lightSkyBlueColorCode+"[유효한 메뉴 번호를 입력해주세요.]\n\n"+resetColorCode);
    }

    // [ERROR message] delete admin
    public static void errorDeleteAdmin(){
        System.out.print( lightSkyBlueColorCode+"[삭제할 수 없는 유저입니다.]\n\n"+resetColorCode);
    }

    // Program Exit
    public static void errorProgramExit() {
        System.out.print(lightSkyBlueColorCode+"[프로그램을 종료합니다...]\n\n"+resetColorCode);
    }

    // [ERROR message]  Disaccord LogIn
    public static void errorDisaccordLogin() {
        System.out.print(lightSkyBlueColorCode+"[ID PW를 다시 확인해주세요.]\n\n"+resetColorCode);
    }

    //Success LogIn
    public static void successLogin() {
        System.out.print(lightSkyBlueColorCode+"[로그인 되었습니다.]\n\n"+resetColorCode);
    }

    //Success Join
    public static void successJoin() {
        System.out.print(lightSkyBlueColorCode+"[회원가입 되었습니다.]\n\n"+resetColorCode);
    }

    //Success Update
    public static void successUpdate() {
        System.out.print(lightSkyBlueColorCode+"[변경 되었습니다.]\n\n"+resetColorCode);
    }

    //move login Monitor
    public static void successGotoLogin() {
        System.out.print(lightSkyBlueColorCode+"[회원가입 화면으로 돌아갑니다.]\n\n"+resetColorCode);
    }

    // [ERROR message] can't find user
    public static void errorUserNo() { System.out.print(lightSkyBlueColorCode+"[올바른 번호를 입력해주세요.]\n\n"+resetColorCode); }

    // [ERROR message] can't find book
    public static void errorNotHereBook(){ System.out.print(lightSkyBlueColorCode+"[존재하지 않는 도서입니다.]\n\n"+resetColorCode); }

    // [ERROR message] can't accord book
    public static void errorAccordBook(){ System.out.print(lightSkyBlueColorCode+"[관련 도서가 없습니다.]\n\n"+resetColorCode); }

    // [ERROR message] Already lend
    public static void errorNothingLend(){ System.out.print(lightSkyBlueColorCode+"[현재 대출 중인 도서가 없습니다.]\n\n"+resetColorCode); }

    // [ERROR message] Already lend
    public static void errorAlreadyLend(){ System.out.print(lightSkyBlueColorCode+"[이미 대출 중인 도서입니다.]\n\n"+resetColorCode); }

    //success lend
    public static void successLendBook(){ System.out.print(lightSkyBlueColorCode+"[도서가 성공적으로 대출되었습니다.]\n\n"+resetColorCode); }

    // [ERROR message] return
    public static void errorReturnBook(){ System.out.print(lightSkyBlueColorCode+"[반납이 취소되었습니다.]\n\n"+resetColorCode); }

    //[ERROR]
    private static String printError(){ return "[ERROR) "; }

    //loading (...)
    public static void loading(long time){
        long sz = time/1000;
        try {
            for(int i=0;i<sz;i++) {
                System.out.print(".");
                Thread.sleep(time/sz);
            }
            System.out.print("\n");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }
}
