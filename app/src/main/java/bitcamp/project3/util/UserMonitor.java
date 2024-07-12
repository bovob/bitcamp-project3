package bitcamp.project3.util;

import bitcamp.project3.controller.UserCommand;
import bitcamp.project3.util.Monitor;

public class UserMonitor extends Monitor  {
        public static String[][] userMenu ={
            {"대출","반납","회원 정보 수정"}, //0~
            {"추천 도서 대출", "도서 검색"},  //1~
            {"제목", "저자"},                 //2~
            {"PW 수정", "MBTI 재검사"}        //3~
    };




    public static void userExecute() {
        String command;
        UserCommand uc = UserCommand.getInstance();


        System.out.println("유저");
        printUserMenu();

        uc.cmd();

        while (true) {
            try {
                command = Prompt.input("메인> ");
                if (command.equals("menu")) {
                    printUserMenu();
                    continue;
                }
                int menuNo = Integer.parseInt(command);
                String menuTitle = getMenuTitle(menuNo, userMenu[0]);
                if (menuTitle != null) {
                    processUserMenu(menuTitle);
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

    // 유저 메뉴 출력
    static void printUserMenu() {
        String line = "----------------------------------";
        String adminTitle = "[유저메뉴]";
        System.out.println(adminTitle);

        // 메뉴 목록출력
        for (int i = 0; i < userMenu[0].length; i++) {
            if (userMenu[0][i].equals("종료")) {
                System.out.printf("%d. %s\n", (i + 1), userMenu[0][i]);
            } else {
                System.out.printf("%d. %s\n", (i + 1), userMenu[0][i]);
            }
        }
        System.out.println(line);
    }

    // 유저 메뉴 프로세스
    static void processUserMenu(String menuTitle) {
        switch (menuTitle) {
            case "도서대출":
                System.out.println("도서대출 메뉴입니다.");
                //bookCommand.execute();
                break;
            case "도서반납":
                System.out.println("대출반납 메뉴입니다.");
                //bookLoanCommand.execute();
                break;
            case "회원정보수정":
                System.out.println("회원정보수정 메뉴입니다.");
                //userCommand.execute();
                break;
            default:
                System.out.printf("%s 메뉴의 명령을 처리할 수 없습니다.\n", menuTitle);
        }
    }
}
