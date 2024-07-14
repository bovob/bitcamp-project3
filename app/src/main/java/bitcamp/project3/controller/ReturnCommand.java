package bitcamp.project3.controller;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.Borrow;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ReturnCommand {
        String menuTitle = "반납";
        String[] menus = {"도서반납"};

        String currentUser = "user";

        private List<Book> bookList;
        private List<Borrow> borrowList = new LinkedList<>();

    public ReturnCommand(List<Book> bookList, List<Borrow> borrowList) {
        this.bookList = bookList;
        this.borrowList = borrowList;
    }

    public ReturnCommand() {
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
                        case "도서반납":
                            bookReturn();
                            break;
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("숫자로 메뉴 번호를 입력하세요.");
                }
            }
        }

    private void bookReturn() {
        System.out.println("도서반납 입니다.");

        // 현재 사용자가 대출한 책 목록 출력
        List<Borrow> userBorrows = new ArrayList<>();
        for (Borrow borrow : borrowList) {
            if (borrow.getUser().equals(currentUser)) {
                userBorrows.add(borrow);
            }
        }

        if (userBorrows.isEmpty()) {
            System.out.println("현재 대출 중인 도서가 없습니다.");
            return;
        }

        System.out.println("현재 대출 중인 도서 목록:");
        System.out.println("번호 | 도서명 | 대출일 | 반납예정일");
        for (int i = 0; i < userBorrows.size(); i++) {
            Borrow borrow = userBorrows.get(i);
            System.out.printf("%d | %s | %s | %s\n",
                i + 1, borrow.getTitle(), borrow.getStartDate(), borrow.getEndDate());
        }

        int selectNo = Prompt.inputInt("반납할 도서의 번호를 입력하세요 (취소: 0): ");
        if (selectNo == 0) {
            System.out.println("반납이 취소되었습니다.");
            return;
        }

        if (selectNo < 1 || selectNo > userBorrows.size()) {
            System.out.println("잘못된 번호입니다.");
            return;
        }

        Borrow selectedBorrow = userBorrows.get(selectNo - 1);

        // 해당 도서를 bookList에서 찾아 대출 상태 변경
        for (Book book : bookList) {
            if (book.getTitle().equals(selectedBorrow.getTitle())) {
                book.setCheck(false);
                break;
            }
        }

        // borrowList에서 해당 대출 정보 제거
        borrowList.remove(selectedBorrow);

        System.out.printf("%s 도서가 성공적으로 반납되었습니다.\n", selectedBorrow.getTitle());
    }

        private void printBookList() {
            System.out.println("도서목록 입니다.");
            System.out.println("번호 | 카테고리 | 도서명 | 저자 | 대출 상태");
            for (Book book : bookList) {
                System.out.printf("%d  |   %s  |  %s  |  %s  |  %s\n",
                    book.getNo(), book.getBookCategory(), book.getTitle(),
                    book.getAuthor(), book.isCheck() ? "대출 중" : "대출 가능");
            }
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
