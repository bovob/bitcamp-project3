package bitcamp.project3.Monitor;

import bitcamp.project3.controller.BookCommand;
import bitcamp.project3.controller.BorrowCommand;
import bitcamp.project3.controller.UserCommand;

import static bitcamp.project3.util.MenuFormat.*;
import static bitcamp.project3.util.Prompt.*;
import static bitcamp.project3.util.SystemMsg.*;

public class AdminMonitor {
    // 관리자 메뉴
    static String[][] adminMenus = new String[][]{
                                                            //Menu Num
            {"도서관리", "대출관리", "유저관리"},           //0~
            {"등록","조회","수정","삭제"}   //1~
    };


//    public BookCommand bookCommand = new BookCommand("도서관리");
    public BorrowCommand borrowCommand;






    ///////////////////////////////////////////////////////////
    ////////////////////// getInstance() //////////////////////
    ///////////////////////////////////////////////////////////
    private static AdminMonitor rm;

    // setup AdminMonitor Instance
    public static AdminMonitor getInstance() {

        if (rm == null) {
            rm = new AdminMonitor();
        }

        return rm;
    }// Method getInstance END

    // reset AdminMonitor Instance
    public static void freeInstance() {
        rm = null;
    }// Method freeInstance END





    
    ///////////////////////////////////////////////////////////
    ////////////////////////// Method /////////////////////////
    ///////////////////////////////////////////////////////////
    // 관리자 메뉴 실행
    public void adminExecute() {
        int menus = adminMenus[0].length;
        int menuNo;

        //RootMenu Main Menu Start
        printAdminMonitorTUI();

        //Input Menu Num
        while (true) {
            try {
                menuNo = inputInt("메인> ");

                //Valid Menu Num
                if (menuNo>0 && menuNo<=menus) {
                    processAdminMenu(menuNo);
                }
                //Exit
                else if(menuNo == 0) {
                    break;
                }
                //default(범위 외 번호)
                else {
                    printNumberLimitException();
                }
            } catch (NumberFormatException ex) {
                printNumberFormatException();
            }

            //restart Cmd TUI
            printAdminMonitorTUI();
        }

        printGotoLogin();
    }//Method adminExecute END

    //RootMonitor
    void printAdminMonitorTUI(){
        setClearCmd();
        System.out.print(printAdminMenu(0));;
    }//Method printAdminMonitorTUI END


    // 관리자 메뉴 프로세스
    void processAdminMenu(int ans) {
        BookCommand bc = BookCommand.getInstance();
        switch (ans) {
            case 1: //도서 관리
                System.out.println("도서관리 메뉴로 접속합니다.");

//                String menuTitle = Monitor.getMenuTitle(ans, adminMenus[0]);
//                System.out.print(printAdminMenu(ans));;
                bc.execute();
                break;
            case 2: //대출 관리
                System.out.println("대출관리 메뉴로 접속합니다.");
                bc.execute();
                break;
            case 3: //유저 관리
                UserCommand uc = UserCommand.getInstance();
                System.out.println("유저관리 메뉴로 접속합니다.");
                uc.adminExcute();

                break;
                }

    }//Method processAdminMenu END








    ///////////////////////////////////////////////////////////
    ///////////////// public getter, setter ///////////////////
    ///////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////
    //////////////////////////// -- ///////////////////////////
    //////////////////////////// -- ///////////////////////////
    //////////////////////////// -- ///////////////////////////
    //////////////////////// ---------- ///////////////////////
    ////////////////////////// ------ /////////////////////////
    //////////////////////////// -- ///////////////////////////
    ///////////////////////////////////////////////////////////


    public static String[][] getAdminMenus() {
        return adminMenus;
    }

    public static void setAdminMenus(String[][] adminMenus) {
        AdminMonitor.adminMenus = adminMenus;
    }
}
