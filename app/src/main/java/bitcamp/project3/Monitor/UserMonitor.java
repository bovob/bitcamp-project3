package bitcamp.project3.Monitor;

import bitcamp.project3.controller.BorrowCommand;
import bitcamp.project3.controller.ReturnCommand;
import bitcamp.project3.controller.UserCommand;
import bitcamp.project3.vo.User;

import static bitcamp.project3.util.Prompt.*;
import static bitcamp.project3.util.SystemMsg.*;

public class UserMonitor extends Monitor {
    private String[][] userMenus ={
                                              //Menu Num
            {"대출","반납","회원 정보 수정"}, //0~
            {"추천 도서 대출", "도서 검색"},  //1~
            {"제목", "저자"},                 //2~
            {"PW 수정", "MBTI 재검사"}        //3~
    };
    int userNo=0;



    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////
    public UserMonitor(){
//        BorrowCommand bc = new BorrowCommand( bookCommand.getBookList());
//        returnCommand = new ReturnCommand( bookCommand.getBookList(), bc.getBorrowList(), UserCommand.getCurrentUser());
    }



    ///////////////////////////////////////////////////////////
    ////////////////////// getInstance() //////////////////////
    ///////////////////////////////////////////////////////////
    private static UserMonitor um;

    // setup UserMonitor Instance
    public static UserMonitor getInstance() {

        if (um == null) {
            um = new UserMonitor();
        }

        return um;
    }// Method getInstance END

    // reset UserMonitor Instance
    public static void freeInstance() {
        um = null;
    }// Method freeInstance END








    ///////////////////////////////////////////////////////////
    ////////////////////////// Method /////////////////////////
    ///////////////////////////////////////////////////////////
    public void execute(int ans) {
        int menus = userMenus[0].length;
        int menuNo;

        setUserNo(ans);
        User currentUser = UserCommand.getUserList().get(userNo);
        UserCommand.setCurrentUser(currentUser);

        //userMenu Main Menu Start
        printUserMonitorTUI();

        //Input Menu Num
        while (true) {
            try {
                menuNo = inputInt("메인> ");

                //Valid Menu Num
                if (menuNo>0 && menuNo<=menus) {
                    processUserMenu(menuNo);
                }
                //Exit
                else if(menuNo == 0) {
                    break;
                }
                //defualt(범위 외 번호)
                else {
                    errorNumberLimitException();
                }

            } catch (NumberFormatException ex) {
                errorNumberFormatException();
            }

            //restart Cmd TUI
            printUserMonitorTUI();
        }

        successGotoLogin();
    }

    //userMonitor
    void printUserMonitorTUI(){
        UserCommand uc = UserCommand.getInstance(getUserNo());

        setClearCmd();
        uc.cmd();
    }


    // Valid Menu Num
    // 유저 메뉴 프로세스
    void processUserMenu(int ans) {
        User current = UserCommand.getCurrentUser();

        switch (ans) {
            case 1: //도서 대출
//                System.out.println("도서대출 메뉴입니다.");
                BorrowCommand bc = BorrowCommand.getInstance(current);
                bc.execute();
                break;
            case 2: //대출 반납
//                System.out.println("대출반납 메뉴입니다.");
                ReturnCommand rc = ReturnCommand.getInstance(current);
                rc.execute();
                break;
            case 3: //회원 정보 수정
                UserCommand uc = UserCommand.getInstance(getUserNo());
                uc.update();
                break;
        }
    }//Method processUserMenu END





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


    public String[][] getUserMenus() {
        return userMenus;
    }

    public void setUserMenus(String[][] userMenus) {
        this.userMenus = userMenus;
    }

    public int getUserNo() {
        return userNo;
    }

    public void setUserNo(int userNo) {
        this.userNo = userNo;
    }
}//Class UserMonitor END
