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
    public static void errorNumberFormatException(){
        System.out.println( "[숫자로 메뉴 번호를 입력해주세요.]\n\n");
    }

    // [ERROR message] if system.in get over Number
    public static void errorNumberLimitException() {
        System.out.println( "[유효한 메뉴 번호를 입력해주세요.]\n\n");
    }

    // Program Exit
    public static void errorProgramExit() {
        System.out.println("[프로그램을 종료합니다...]\n\n");
    }

    // Disaccord LogIn
    public static void errorDisaccordLogin() {
        System.out.println("[ID PW를 다시 확인해주세요.]\n\n");
    }

    //Success LogIn
    public static void successLogin() {
        System.out.print("[로그인 되었습니다.]\n\n");
    }

    //Success Join
    public static void successJoin() {
        System.out.print("[회원가입 되었습니다.]\n\n");
    }

    //Success Update
    public static void successUpdate() {
        System.out.print("[변경 되었습니다.]\n\n");
    }

    //move login Monitor
    public static void successGotoLogin() {
        System.out.print("[회원가입 화면으로 돌아갑니다.]\n\n");
    }

    //can't find user
    public static void errorUserNo() { System.out.print("[올바른 번호를 입력해주세요.]\n\n"); }

    //can't find book
    public static void errorNotHereBook(){ System.out.println("[존재하지 않는 도서입니다.]\n\n"); }

    //can't accord book
    public static void errorAccordBook(){ System.out.println("[관련 도서가 없습니다.]\n\n"); }

    //Already lend
    public static void errorNothingLend(){ System.out.println("[현재 대출 중인 도서가 없습니다.]\n\n"); }

    //Already lend
    public static void errorAlreadyLend(){ System.out.println("[이미 대출 중인 도서입니다.]\n\n"); }

    //success lend
    public static void successLendBook(){ System.out.println("[도서가 성공적으로 대출되었습니다.]\n\n"); }

    //Already lend
    public static void errorReturnBook(){ System.out.println("[반납이 취소되었습니다.]\n\n"); }

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
