package bitcamp.project3.controller;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import java.util.LinkedList;

public class BorrowCommand{

    String menuTitle;
    String[] menus = {"대출목록", "대출조회", "날짜수정"};

    LinkedList borrowList = new LinkedList();
    LinkedList bookList = new LinkedList();

    // 생성자
    public BorrowCommand() {
    }

    public BorrowCommand(String title) {
        this.menuTitle = title;
    }

    // 메인실행
    public void execute(String menuTitle) {
        cmd();
        while (true) {
            String command = Prompt.input(String.format("메인/%s>", menuTitle));
            if (command.equals("menu")) {
                cmd();
                continue;
            } else if (command.equals("9")) { // 이전 메뉴 선택
                return;
            } try {
                int menuNo = Integer.parseInt(command);
                String menuName = getMenuTitle(menuNo, menus);
                if (menuName == null) {
                    System.out.println("유효한 메뉴 번호가 아닙니다.");
                    continue;
                }
                switch (menuName){
                    case "대출목록":
                        displayBorrowList();
                        break;
                    case "대출조회":
                        searchBorrowList();
                        break;
                    case "날짜수정":
                        updateBorrowDate();
                        break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }
    }

    // 대출목록
    public void displayBorrowList() {
        System.out.println("대출목록 입니다.");
    }

    // 대출조회
    public void searchBorrowList() {
        System.out.println("대출조회 입니다.");
    }

    // 날짜수정
    public void updateBorrowDate() {
        System.out.println("날짜수정 입니다.");

    }

    public void cmd() {
        System.out.printf("[%s]\n", menuTitle);
        for (int i = 0; i < menus.length; i++) {
            System.out.printf("%d. %s\n", (i + 1), menus[i]);
        }
        System.out.println("9. 이전");
    }

    public void printTUI() {
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

