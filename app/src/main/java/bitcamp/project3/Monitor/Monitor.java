package bitcamp.project3.Monitor;

public class Monitor {
    // 메뉴 검증
    protected static boolean isValidateMenu(int menuNo, String[] menus) {
        return menuNo >= 1 && menuNo <= menus.length;
    }

    // 메뉴 타이틀 출력
    public static String getMenuTitle(int menuNo, String[] menus) {
        return isValidateMenu(menuNo, menus) ? menus[menuNo - 1] : null;
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

}
