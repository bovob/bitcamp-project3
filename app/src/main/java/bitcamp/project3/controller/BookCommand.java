package bitcamp.project3.controller;

import bitcamp.project3.util.Prompt;
import java.util.LinkedList;

public class BookCommand implements Command {

    String menuTitle;
    String[] menus = {"도서등록", "도서조회", "도서수정", "도서삭제"};

    LinkedList bookList = new LinkedList();

    public BookCommand() {
    }

    public BookCommand(String title) {
        this.menuTitle = title;
    }

    public void execute(String menuTitle) {

        cmd();
        while (true) {
            String command = Prompt.input(String.format("메인/%s>", menuTitle));
            if (command.equals("menu")) {
                cmd();
                continue;
            } else if (command.equals("9")) { // 이전 메뉴 선택
                return;
            }

            try {
                int menuNo = Integer.parseInt(command);
                String menuName = getMenuTitle(menuNo, menus);
                if (menuName == null) {
                    System.out.println("유효한 메뉴 번호가 아닙니다.");
                    continue;
                }
                switch (menuTitle){
                    case "도서등록":
                        create();
                        break;
                    case "도서조회":
                        read();
                        break;
                    case "도서수정":
                        update();
                        break;
                    case "도서삭제":
                        delete();
                        break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }
    }

    // 도서등록
    @Override
    public void create() {

    }

    // 목록조회
    @Override
    public void read() {

    }

    // 도서수정
    @Override
    public void update() {

    }

    //도서삭제
    @Override
    public void delete() {

    }
    

    @Override
    public void cmd() {
        System.out.printf("[%s]\n", menuTitle);
        for (int i = 0; i < menus.length; i++) {
            System.out.printf("%d. %s\n", (i + 1), menus[i]);
        }
        System.out.println("9. 이전");
    }

    @Override
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
