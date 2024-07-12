package bitcamp.project3.controller;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import java.util.LinkedList;

public class BookCommand implements Command {

    String menuTitle;
    String[] menus = {"도서등록", "도서조회", "도서수정", "도서삭제"};

    LinkedList bookList = new LinkedList();

    // 생성자
    public BookCommand() {
    }

    public BookCommand(String title) {
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
        System.out.println("도서등록 입니다.");
        Book book = new Book();

        book.setBookCategory(Prompt.input("카테고리"));
        book.setTitle(Prompt.input("책 이름?"));
        book.setAuthor(Prompt.input("책 저자?"));
        book.setM(Prompt.inputInt("M ?"));
        book.setB(Prompt.inputInt("B ?"));
        book.setT(Prompt.inputInt("T ?"));
        book.setI(Prompt.inputInt("I ?"));
        book.setNo(Book.getNextSeqNo());
        bookList.add(book);

    }

    // 목록조회
    @Override
    public void read() {
        System.out.println("도서목록 입니다.");
        System.out.println("번호 | 카테고리 | 도서명 저자 M B T I 점수");
        for (Object obj : bookList.toArray()){
            Book book = (Book) obj;
            System.out.printf("%d  |   %s  | %s  %s  %d %d %d %d\n",
            book.getNo(), book.getBookCategory() , book.getTitle(), book.getAuthor(),
            book.getM(), book.getB(), book.getT(), book.getI());
        }
    }

    // 도서수정
    @Override
    public void update() {
        System.out.println("도서수정 입니다.");
        int bookNo = Prompt.inputInt("도서번호?");
        int index = bookList.indexOf(new Book(bookNo)); // Book의 인스턴스를 생성하여 인덱스 찾기

        if (index == -1) {
            System.out.println("해당 도서를 찾을 수 없습니다.");
        }

        Book book = (Book) bookList.get(index);
        book.setBookCategory(Prompt.input("카테고리"));
        book.setTitle(Prompt.input("책 이름?"));
        book.setAuthor(Prompt.input("책 저자?"));
        book.setM(Prompt.inputInt("M ?"));
        book.setB(Prompt.inputInt("B ?"));
        book.setT(Prompt.inputInt("T ?"));
        book.setI(Prompt.inputInt("I ?"));
        System.out.println("변경되었습니다.");
    }

    //도서삭제
    @Override
    public void delete() {
        System.out.println("도서삭제 입니다.");

        int bookNo = Prompt.inputInt("책번호?");
        int index = bookList.indexOf(new Book(bookNo));

        Book book = (Book) bookList.get(index);
        if (book != null){
            bookList.remove(bookList.indexOf(index));
            System.out.printf("%d번 %s를 삭제했습니다.\n", book.getNo(), book.getTitle());
        } else{
            System.out.printf("없는 도서번호 입니다.");
        }
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
