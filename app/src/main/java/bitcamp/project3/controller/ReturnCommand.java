package bitcamp.project3.controller;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.Borrow;
import bitcamp.project3.vo.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static bitcamp.project3.util.MenuFormat.*;
import static bitcamp.project3.util.Prompt.*;
import static bitcamp.project3.util.SystemMsg.*;
import static bitcamp.project3.util.TableFormat.*;

public class ReturnCommand {
        String menuTitle = "반납";
        String[] menus = {"도서반납"};

        static User currentUser;

//        List<Book> bookList;
//        List<Borrow> borrowList;

    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////

    ReturnCommand(User user){

    }

//    public ReturnCommand(List<Borrow> borrowList) {
//        this.bookList = bookList;
//    }


    ///////////////////////////////////////////////////////////
    ////////////////////// getInstance() //////////////////////
    ///////////////////////////////////////////////////////////
    private static ReturnCommand rc;

    // setup BookCommand Instance
    public static ReturnCommand getInstance(User user) {

        if (rc == null) {
            rc = new ReturnCommand(user);
        }
        currentUser = user;

        return rc;
    }// Method getInstance END

    // reset BookCommand Instance
    public static void freeInstance() {
        rc = null;
    }// Method freeInstance END


    ///////////////////////////////////////////////////////////
    ///////////////////////// Method //////////////////////////
    ///////////////////////////////////////////////////////////
//    public ReturnCommand() {
//        this.bookList = new ArrayList<>();
//    }

    // 메인실행
    public void execute() {
        while (processMenu()) {
            try {

                } catch (NumberFormatException ex) {
                    errorNumberFormatException();
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
                errorNumberLimitException();
                yield true;
            }
        };
    }




    private void printMenuTUI(){
        setClearCmd();

        System.out.printf("안녕하세요 '%s' 님\n", currentUser.getName());
        printBorrowBookList();

        System.out.print(printCustomMenu(menus));
    }






    private void bookReturn() {
        // 현재 사용자가 대출한 책 목록 출력
        List<Borrow> userBorrows = new ArrayList<>();
        for (Borrow borrow : BorrowCommand.getBorrowList()) {
            if (borrow.getUser().equals(currentUser)) {
                userBorrows.add(borrow);
            }
        }


        int selectNo = Prompt.inputInt(yellowColorCode+"반납할 도서의 번호를 입력하세요 (취소: 0): "+resetColorCode);
        if (selectNo == 0) {
            errorReturnBook();
            return;
        }

        if (selectNo < 1 || selectNo > userBorrows.size()) {
            errorNumberLimitException();
            return;
        }

        Borrow selectedBorrow = userBorrows.get(selectNo - 1);

        // 해당 도서를 bookList에서 찾아 대출 상태 변경
        for (Book book : BookCommand.getInstance().getBookList()) {
            if (book.getTitle().equals(selectedBorrow.getTitle())) {
                book.setCheck(false);
                break;
            }
        }

        // borrowList에서 해당 대출 정보 제거
        BorrowCommand.removeBorrowList(selectedBorrow);

        System.out.printf(lightSkyBlueColorCode+"%s 도서가 성공적으로 반납되었습니다.\n"+resetColorCode, selectedBorrow.getTitle());
    }

        private void printBorrowBookList() {
            String[] calm={"No", "도서명", "대출일", "반납예정일"};
            int[] width={4, 20, 20, 20};
            //no, title, borrowDate, RetueDate
            int i=0;


            // 현재 사용자가 대출한 책 목록 출력
            List<Borrow> userBorrows = new ArrayList<>();
            for (Borrow borrow : BorrowCommand.getBorrowList()) {
                if (borrow.getUser().equals(currentUser)) {
                    userBorrows.add(borrow);
                }
            }

            if (userBorrows.isEmpty()) {
                errorNothingLend();
                return;
            }
            //////////////////////////////////////////////////////////////
            ////////////////////////result table//////////////////////////
            //////////////////////////////////////////////////////////////
            //table title
            System.out.println("현재 대출 중인 도서 목록:");
            System.out.print(lightSkyBlueColorCode+printTableLine(width));
            for(String data: calm){
                System.out.print(printTableDataFormat(width[i++], data));
            }
            System.out.print(":\n");
            System.out.print(printTableLine(width));

            //table data
            int borrowIndex = 1;
            for (Borrow borrow : userBorrows) {
                System.out.print(printTableDataFormat( width[0], String.format("%s", borrowIndex)) );
                System.out.print(printTableDataFormat( width[1], String.format("%s", borrow.getTitle())) );
                System.out.print(printTableDataFormat( width[2], String.format("%s", borrow.getStartDate())) );
                System.out.print(printTableDataFormat( width[3], String.format("%s", borrow.getEndDate())) );
                System.out.print(":\n");
                borrowIndex++;
            }

            //END line
            System.out.print(printTableLine(width)+resetColorCode);
        }

        private void printBookList() {
            String[] calm={"No", "카테고리", "도서명", "저자", "대출상태"};
            int[] width={SMALL, LARGE, HUGE, LARGE, MIDDLE};
            //no, cate, title, writer, status
            int i=0;


            if (BookCommand.getInstance().getBookList() == null){
                errorAccordBook();
                return;
            }



            //////////////////////////////////////////////////////////////
            ////////////////////////result table//////////////////////////
            //////////////////////////////////////////////////////////////
            //table title
            System.out.print(lightSkyBlueColorCode+printTableLine(width));
            for(String data: calm){
                System.out.print(printTableDataFormat(width[i++], data));
            }
            System.out.print(":\n");
            System.out.print(printTableLine(width));

            //table data
            for (Book book : BookCommand.getInstance().getBookList()) {
                System.out.print(printTableDataFormat( width[0], String.format("%s", book.getNo())) );
                System.out.print(printTableDataFormat( width[1], String.format("%s", book.getBookCategory())) );
                System.out.print(printTableDataFormat( width[2], String.format("%s", book.getTitle())) );
                System.out.print(printTableDataFormat( width[3], String.format("%s", book.getAuthor())) );
                System.out.print(valid(book, width[4]));
                System.out.print(":\n");
            }

            //END line
            System.out.print(printTableLine(width)+resetColorCode);
            //////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////
        }

    ///////////////////////////////////////////////////////////
    ////////////////////// Admin Process //////////////////////
    ///////////////////////////////////////////////////////////
    public void adminExecute(){
        rc.processAdminUser();
    }

    private void printAdminMenuTUI(){
        setClearCmd();

        System.out.print(printAdminMenu(2));
    }

    public void processAdminUser() {
        while (adminMenuCommand()) {
        }
    }//Method update END

    private boolean adminMenuCommand(){

        printAdminMenuTUI();

        int ans = inputInt("메인/대출관리> ");

        return switch (ans) {
            case 1 -> {
                rc.printAllBorrowStatus();
                yield true;
            }
            case 2 -> {
                rc.printBorrowStatusByUser();
                yield true;
            }
            case 0 -> false;
            default -> {
                errorNumberLimitException();
                yield true;
            }
        };
    }//Method updateMenuCommand END

    private void printAllBorrowStatus() {
        System.out.println("\n[전체 대출 현황]");
        if (BorrowCommand.getBorrowList() == null || BorrowCommand.getBorrowList().isEmpty()) {
            errorNothingLend();
            return;
        }

        String[] columns = {"No", "사용자", "도서명", "대출일", "반납예정일"};
        int[] widths = {SMALL, LARGE, HUGE, LARGE, MIDDLE};
        printTableHeader(columns, widths);

        int index = 1;
        for (Borrow borrow : BorrowCommand.getBorrowList()) {
            System.out.print(printTableDataFormat(widths[0], String.valueOf(index)));
            System.out.print(printTableDataFormat(widths[1], borrow.getUser().getName()));
            System.out.print(printTableDataFormat(widths[2], borrow.getTitle()));
            System.out.print(printTableDataFormat(widths[3], String.format("%s",borrow.getStartDate())));
            System.out.print(printTableDataFormat(widths[4], String.format("%s",borrow.getEndDate())));
            System.out.print(":\n");
            index++;
        }
        System.out.print(printTableLine(widths)+resetColorCode);
    }

    protected void printBorrowStatusByUser() {
        // User 대출 리스트
        // .getBorrowList() 리스트 Get
        // .stream() 스트림 변환
        // .filter(  borrow .getUser() 유저정보를
        //                  .equals(currentUser) 와 일치하는지 비교)
        //                                                          .collect(모아진 데이터를
        //                                                                   .toList() 리스트저장);
        List<Borrow> userBorrows = BorrowCommand.getBorrowList().stream()
            .filter(borrow -> borrow.getUser().equals(currentUser)).collect(Collectors.toList());

        //if (BorrowCommand.getBorrowList() == null || BorrowCommand.getBorrowList().isEmpty()) {
        //    errorNothingLend();
        //    return;
        //}

        if (userBorrows.isEmpty()) {
            errorNothingLend();
            return;
        }

        String[] columns = {"No", "도서명", "대출일", "반납예정일"};
        int[] widths = {SMALL, HUGE, LARGE, LARGE};
        printTableHeader(columns, widths);

        int index = 1;
        for (Borrow borrow : userBorrows) {
            System.out.print(printTableDataFormat(widths[0], String.valueOf(index)));
            System.out.print(printTableDataFormat(widths[1], borrow.getTitle()));
            System.out.print(printTableDataFormat(widths[2], String.format("%s", borrow.getStartDate())));
            System.out.print(printTableDataFormat(widths[3], String.format("%s", borrow.getEndDate())));
            System.out.print(":\n");
            index++;
        }
        System.out.print(printTableLine(widths)+resetColorCode);
    }

    /**    Map<User, List<Borrow>> borrowsByUser = new HashMap<>();
    //    for (Borrow borrow : BorrowCommand.getBorrowList()) {
    //        borrowsByUser.computeIfAbsent(borrow.getUser(), k -> new ArrayList<>()).add(borrow);
    //    }
    //
    //    for (Map.Entry<User, List<Borrow>> entry : borrowsByUser.entrySet()) {
    //        User user = entry.getKey();
    //        List<Borrow> userBorrows = entry.getValue();
    //
    //        String[] columns = {"No", "도서명", "대출일", "반납예정일"};
    //        int[] widths = {SMALL, HUGE, LARGE, LARGE};
    //        printTableHeader(columns, widths);
    //
    //        int index = 1;
    //        for (Borrow borrow : userBorrows) {
    //            System.out.print(printTableDataFormat(widths[0], String.valueOf(index)));
    //            System.out.print(printTableDataFormat(widths[1], borrow.getTitle()));
    //            System.out.print(printTableDataFormat(widths[2], String.format("%s",borrow.getStartDate())));
    //            System.out.print(printTableDataFormat(widths[3], String.format("%s",borrow.getEndDate())));
    //            System.out.print(":\n");
    //            index++;
    //        }
    //        System.out.print(printTableLine(widths));
    //    }
    //}
     */

    private void printTableHeader(String[] columns, int[] widths) {
        System.out.print(lightSkyBlueColorCode+printTableLine(widths));
        for (int i = 0; i < columns.length; i++) {
            System.out.print(printTableDataFormat(widths[i], columns[i]));
        }
        System.out.print(":\n");
        System.out.print(printTableLine(widths));
    }
//
//    public void setBorrowList(List<Borrow> borrowList) {
//        this.borrowList = borrowList;
//    }
//
//    public List<Borrow> getBorrowList() {
//        return this.borrowList;
//    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
