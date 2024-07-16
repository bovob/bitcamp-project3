package bitcamp.project3.util;

public class SystemMsg {
    public static void setClearCmd(){
        int top = 5;    //높이

//        loading(2000);
        for(int i=0;i<top;i++){
            System.out.print("\n");
        }

    }


    // [ERROR message] if system.in doesn't get Number
    public static void printNumberFormatException(){
        System.out.println( "[숫자로 메뉴 번호를 입력해주세요.]");
    }

    // [ERROR message] if system.in get over Number
    public static void printNumberLimitException() {
        System.out.println( "[유효한 메뉴 번호를 입력해주세요.]");
    }

    // Program Exit
    public static void printProgramExit() {
        System.out.println("[프로그램을 종료합니다...]");
    }

    // Disaccord LogIn
    public static void printDisaccordLogin() {
        System.out.println("[ID PW를 다시 확인해주세요.]\n\n");
    }

    //Success LogIn
    public static void printSuccessLogin() {
        System.out.print("[로그인 되었습니다.]\n\n");
    }

    //Success Join
    public static void printSuccessJoin() {
        System.out.print("[회원가입 되었습니다.]\n\n");
    }

    //move login Monitor
    public static void printGotoLogin() {
        System.out.print("[회원가입 화면으로 돌아갑니다.]\n\n");
    }

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
