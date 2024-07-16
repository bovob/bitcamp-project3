package bitcamp.project3.Monitor;

import bitcamp.project3.controller.BookCommand;
import bitcamp.project3.controller.BorrowCommand;
import bitcamp.project3.controller.ReturnCommand;
import bitcamp.project3.controller.UserCommand;
import bitcamp.project3.vo.Borrow;

import java.util.List;

import static bitcamp.project3.util.MenuFormat.*;
import static bitcamp.project3.util.Prompt.*;
import static bitcamp.project3.util.SystemMsg.*;

public class AdminMonitor extends Monitor {
    // 관리자 메뉴
    static String[][] adminMenus = new String[][]{
                                                            //Menu Num
            {"도서관리", "대출관리", "유저관리"},           //0~
            {"등록","조회","수정","삭제"},                  //1~
            {"전체현황"}                        //2~
    };


    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////
    public AdminMonitor(){
        this.bookCommand = new BookCommand(this.borrowCommand.getBorrowList());
        this.borrowCommand = new BorrowCommand(bookCommand.getBookList(), userCommand.getCurrentUser());
        this.returnCommand = new ReturnCommand(bookCommand.getBookList(), borrowCommand.getBorrowList(), userCommand.getCurrentUser());
    }


    public AdminMonitor(List<Borrow> borrowList){
        this.bookCommand = new BookCommand(borrowList);
    }

    private List<Borrow> borrowList;


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
                    errorNumberLimitException();
                }
            } catch (NumberFormatException ex) {
                errorNumberFormatException();
            }

            //restart Cmd TUI
            printAdminMonitorTUI();
        }

        successGotoLogin();
    }//Method adminExecute END

    //RootMonitor
    void printAdminMonitorTUI(){
        setClearCmd();
        System.out.print(printAdminMenu(0));;
    }//Method printAdminMonitorTUI END


    // 관리자 메뉴 프로세스
    void processAdminMenu(int ans) {
        BookCommand bc = BookCommand.getInstance();
        ReturnCommand rc = ReturnCommand.getInstance();
        switch (ans) {
            case 1: //도서 관리
//                System.out.println("도서관리 메뉴로 접속합니다.");

//                String menuTitle = Monitor.getMenuTitle(ans, adminMenus[0]);
//                System.out.print(printAdminMenu(ans));;
                bc.adminExecute();
                break;
            case 2: //대출 관리
//                System.out.println("대출관리 메뉴로 접속합니다.");
                this.borrowList = BorrowCommand.getInstance().getBorrowList();
                updateBorrowList(this.borrowList);
                rc.adminExecute();
                break;
            case 3: //유저 관리
                UserCommand uc = UserCommand.getInstance(0);

//                System.out.println("유저관리 메뉴로 접속합니다.");
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

    public void updateBorrowList(List<Borrow> updatedList) {
        this.borrowList = updatedList;
    }
}
