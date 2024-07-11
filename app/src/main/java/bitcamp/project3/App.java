package bitcamp.project3;

import bitcamp.project3.util.Prompt;

public class App {

    // 관리자 메뉴
    static String[] adminMenus = new String[]{"도서관리", "대출관리", "유저관리", "종료"};
    // 유저 메뉴
    static String[] userMenus = new String[]{"도서대출", "도서반납", "회원정보수정", "종료"};

    // Main
    public static void main(String[] args) {
        App app = new App();
        int check;
        check = Prompt.inputInt("[임시]관리자 1/유저 2");
        switch (check) {
            case 1:
                app.adminExecute();
                break;
            case 2:
                app.executeUser();
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
                String menuTitle = getMenuTitle(menuNo, adminMenus);
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

    // 유저 메뉴 실행
    void executeUser() {
        String command;
        System.out.println("유저");
        printUserMenu();
        while (true) {
            try {
                command = Prompt.input("메인> ");
                if (command.equals("menu")) {
                    printUserMenu();
                    continue;
                }
                int menuNo = Integer.parseInt(command);
                String menuTitle = getMenuTitle(menuNo, userMenus);
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

    // 유저 메뉴 출력
    static void printUserMenu() {
        String line = "----------------------------------";
        String adminTitle = "[유저메뉴]";
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
                //bookCommand.execute();
                break;
            case "대출관리":
                System.out.println("대출관리 메뉴입니다.");
                //bookLoanCommand.execute();
                break;
            case "유저관리":
                System.out.println("유저관리 메뉴입니다.");
                //userCommand.execute();
                break;
            default:
                System.out.printf("%s 메뉴의 명령을 처리할 수 없습니다.\n", menuTitle);
        }
    }

    // 유저 메뉴 프로세스
    void processUserMenu(String menuTitle) {
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

    // 메뉴 검증
    static boolean isValidateMenu(int menuNo, String[] menus) {
        return menuNo >= 1 && menuNo <= menus.length;
    }

    // 메뉴 타이틀 출력
    static String getMenuTitle(int menuNo, String[] menus) {
        return isValidateMenu(menuNo, menus) ? menus[menuNo - 1] : null;
    }

}

