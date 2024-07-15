package bitcamp.project3.controller;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.Borrow;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static bitcamp.project3.util.MenuFormat.*;
import static bitcamp.project3.util.Prompt.*;
import static bitcamp.project3.util.SystemMsg.*;
import static bitcamp.project3.util.TableFormat.*;

public class ReturnCommand {
        String menuTitle = "반납";
        String[] menus = {"도서반납"};

        String currentUser = "user";

        List<Book> bookList;
        private List<Borrow> borrowList = new LinkedList<>();










    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////
    public ReturnCommand(List<Book> bookList, List<Borrow> borrowList) {
        this.bookList = bookList;
        this.borrowList = borrowList;
    }







    ///////////////////////////////////////////////////////////
    ///////////////////////// Method //////////////////////////
    ///////////////////////////////////////////////////////////
    public ReturnCommand() {
        this.bookList = new ArrayList<>();
    }

    // 메인실행
    public void execute() {
/*System.out.printf("안녕하세요 '%s' 님\n", currentUser);
        System.out.println("++대출도서 목록예정++\n");
        printBookList();
        cmd();
        String command = Prompt.input(String.format("메인/%s>", menuTitle));
        if (command.equals("menu")) {
            cmd();
            continue;
        } else if (command.equals("0")) { // 이전 메뉴 선택
            return;
        }
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
        }**/
        while (processMenu()) {
            try {

                } catch (NumberFormatException ex) {
                    printNumberFormatException();
                }
        }
    }

    //Menu Process
    //[0] 종료 입력시에만 process 종료
    private boolean processMenu() {
        printMenuTUI();
        int menuNo = inputInt(String.format("메인/%s>", menuTitle));


        return switch (menuNo) {
            case 1 -> {
                bookReturn();
                yield true;
            }
            case 0 -> false;
            default -> {
                printNumberLimitException();
                yield true;
            }
        };
    }




    private void printMenuTUI(){
        setClearCmd();

        System.out.printf("안녕하세요 '%s' 님\n", currentUser);
        System.out.println("++대출도서 목록예정++\n");
        printBookList();
//        cmd();

        System.out.print(printCustomMenu(menus));
    }




    private void bookReturn() {
        String[] calm={"No", "도서명", "대출일", "반납예정일"};
        int[] width={4, 20, 20, 20};
        //no, title, borrowDate, RetueDate
        int i=0;
        
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

        //////////////////////////////////////////////////////////////
        ////////////////////////result table//////////////////////////
        //////////////////////////////////////////////////////////////
        //table title
        System.out.print(printTableLine(width));
        for(String data: calm){
            System.out.print(printTableDataFormat(width[i++], data));
        }
        System.out.print(":\n");
        System.out.print(printTableLine(width));

        //table data
        for (Borrow borrow : userBorrows) {
            System.out.print(printTableDataFormat( width[0], String.format("%s", i++)) );
            System.out.print(printTableDataFormat( width[1], String.format("%s", borrow.getTitle())) );
            System.out.print(printTableDataFormat( width[2], String.format("%s", borrow.getStartDate())) );
            System.out.print(printTableDataFormat( width[3], String.format("%s", borrow.getEndDate())) );
            System.out.print(":\n");
        }

        //END line
        System.out.print(printTableLine(width));
        //////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////


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
            String[] calm={"No", "카테고리", "도서명", "저자", "대출상태"};
            int[] width={4, 20, 20, 20, 15};
            //no, cate, title, writer, status
            int i=0;

/*            System.out.println("번호 | 카테고리 | 도서명 | 저자 | 대출 상태");

            for (Book book : bookList) {
                System.out.printf("%d  |   %s  |  %s  |  %s  |  %s\n",
                    book.getNo(), book.getBookCategory(), book.getTitle(),
                    book.getAuthor(), book.isCheck() ? "대출 중" : "대출 가능");
            }**/

            System.out.println("도서목록 입니다.");

            if (bookList == null){
                System.out.println("도서 목록이 없습니다.");
                return;
            }



            //////////////////////////////////////////////////////////////
            ////////////////////////result table//////////////////////////
            //////////////////////////////////////////////////////////////
            //table title
            System.out.print(printTableLine(width));
            for(String data: calm){
                System.out.print(printTableDataFormat(width[i++], data));
            }
            System.out.print(":\n");
            System.out.print(printTableLine(width));

            //table data
            for (Book book : bookList) {
                System.out.print(printTableDataFormat( width[0], String.format("%s", book.getNo())) );
                System.out.print(printTableDataFormat( width[1], String.format("%s", book.getBookCategory())) );
                System.out.print(printTableDataFormat( width[2], String.format("%s", book.getTitle())) );
                System.out.print(printTableDataFormat( width[3], String.format("%s", book.getAuthor())) );
                System.out.print(printTableDataFormat( width[4], String.format("%s", book.isCheck() ? "대출중" : "대출가능") ));
                System.out.print(":\n");
            }

            //END line
            System.out.print(printTableLine(width));
            //////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////
        }

//        public void cmd() {
//            System.out.printf("[%s]\n", menuTitle);
//            for (int i = 0; i < menus.length; i++) {
//                System.out.printf("[%d] %s\n", (i + 1), menus[i]);
//            }
//            System.out.println("[0] 이전메뉴");
//        }

}
