package bitcamp.project3.controller;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import java.util.LinkedList;
import java.util.List;

public class BorrowCommand {

    String menuTitle = "대출";
    String[] menus = {"도서대출", "도서검색"};

    String currentUser = "user";

    LinkedList borrowList = new LinkedList();
    //원래 북리스트
    //LinkedList bookList;
    
    //더미 북 리스트
    List<Book> bookList = Book.generateDummyData(10);

    // 생성자
    public BorrowCommand() {
    }

    public BorrowCommand(String title, LinkedList bookList) {
        this.menuTitle = title;
        this.bookList = bookList;
    }

    // 메인실행
    public void execute() {

        System.out.printf("안녕하세요 '%s' 님\n", currentUser);
        System.out.println("++대출도서 목록예정++\n");
        printBookList();
        cmd();

        while (true) {
            String command = Prompt.input(String.format("메인/%s>", menuTitle));
            if (command.equals("menu")) {
                cmd();
                continue;
            } else if (command.equals("0")) { // 이전 메뉴 선택
                return;
            } try {
                int menuNo = Integer.parseInt(command);
                String menuName = getMenuTitle(menuNo, menus);
                if (menuName == null) {
                    System.out.println("유효한 메뉴 번호가 아닙니다.");
                    continue;
                }
                switch (menuName){
                    case "도서대출":
                        bookBorrow();
                        break;
                    case "도서검색":
                        bookSearch();
                        break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }
    }

    private void bookBorrow() {
        System.out.println("도서대출 입니다.");
        System.out.println("[도서번호?]");
        Prompt.inputInt(">");



    }

    private void printBookList() {
        System.out.println("도서목록 입니다.");
        System.out.println("번호 | 카테고리 | 도서명 | 저자");
        for (Object obj : bookList.toArray()){
            Book book = (Book) obj;
            System.out.printf("%d  |   %s  |  %s  |  %s\n",
                book.getNo(), book.getBookCategory() , book.getTitle(), book.getAuthor());
        }
    }

    private void bookSearch() {

    }

    public void cmd() {
        System.out.printf("[%s]\n", menuTitle);
        for (int i = 0; i < menus.length; i++) {
            System.out.printf("[%d] %s\n", (i + 1), menus[i]);
        }
        System.out.println("[0] 이전메뉴");
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

