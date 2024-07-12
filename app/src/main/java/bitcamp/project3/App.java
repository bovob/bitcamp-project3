package bitcamp.project3;

import bitcamp.project3.controller.BookCommand;
import bitcamp.project3.Monitor.Membership;
import bitcamp.project3.Monitor.Monitor;
import bitcamp.project3.controller.BorrowCommand;
import bitcamp.project3.util.Prompt;
import bitcamp.project3.Monitor.UserMonitor;

public class App {

    // 관리자 메뉴
    static String[] adminMenus = new String[]{"도서관리", "대출관리", "유저관리", "종료"};

    BookCommand bookCommand = new BookCommand("도서관리");
    BorrowCommand borrowCommand = new BorrowCommand("대출관리");

    // Main
    public static void main(String[] args) {
        App app = new App();

        // 로그인 후 회원SeqNo get
        // 관리자SeqNo : 0
        // 유저SeqNo: 1~
        // 로그인 실패 시 -1, INPUT으로 따로 1/2 관리자 유저 접속
        int check = Membership.getInstance().cmd();

        if(check==0){
            check = 1;
        }
        else if(check>0){
            check = 2;
        }
        else {
            check = Prompt.inputInt("[임시]관리자 1/유저 2");
        }

        switch (check) {
            case 1:
                app.adminExecute();
                break;
            case 2:
                UserMonitor um = UserMonitor.getInstance();
                um.userExecute();
                break;
        }
    }

    // 관리자 메뉴 실행
    void adminExecute() {
        String command;
        System.out.println("관리자");
        printAdminMenu();
        while (true) {
            try {
                command = Prompt.input("메인> ");
                if (command.equals("menu")) {
                    printAdminMenu();
                    continue;
                }
                int menuNo = Integer.parseInt(command);
                String menuTitle = Monitor.getMenuTitle(menuNo, adminMenus);
                if (menuTitle != null) {
                    processAdminMenu(menuTitle);
                } else if(menuTitle.equals("종료")) {
                    break;
                } else {
                    System.out.println("유효한 메뉴 번호가 아닙니다.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }
        System.out.println("종료합니다.");
        Prompt.close();
    }



    // 관리자 메뉴 출력
    static void printAdminMenu() {
        String line = "----------------------------------";
        String adminTitle = "[관리자메뉴]";
        System.out.println(adminTitle);

        // 메뉴 목록출력
        for (int i = 0; i < adminMenus.length; i++) {
            if (adminMenus[i].equals("종료")) {
                System.out.printf("%d. %s\n", (i + 1), adminMenus[i]);
            } else {
                System.out.printf("%d. %s\n", (i + 1), adminMenus[i]);
            }
        }
        System.out.println(line);
    }


    // 관리자 메뉴 프로세스
    void processAdminMenu(String menuTitle) {
        switch (menuTitle) {
            case "도서관리":
                System.out.println("도서관리 메뉴입니다.");
                bookCommand.execute(menuTitle);
                break;
            case "대출관리":
                System.out.println("대출관리 메뉴입니다.");
                borrowCommand.execute(menuTitle);
                break;
            case "유저관리":
                System.out.println("유저관리 메뉴입니다.");
                //userCommand.execute();
                break;
            default:
                System.out.printf("%s 메뉴의 명령을 처리할 수 없습니다.\n", menuTitle);
        }
    }


}

