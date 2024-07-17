package bitcamp.project3.controller;

import bitcamp.project3.util.GetHtml;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.Borrow;
import bitcamp.project3.vo.User;
import bitcamp.project3.Monitor.AdminMonitor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static bitcamp.project3.util.MenuFormat.*;
import static bitcamp.project3.util.Prompt.*;
import static bitcamp.project3.util.SystemMsg.*;
import static bitcamp.project3.util.TableFormat.*;


public class BorrowCommand implements Command {

    String menuTitle = "대출";
    static User currentUser;
        //= "user";

    //List<Book> bookList;
    static List<Borrow> borrowList = new ArrayList<>();
    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////
    public BorrowCommand(User user) {
        currentUser = user;
    }

    public BorrowCommand(List<Book> bookList) {
//        this.bookList = bookList;
    }


    ///////////////////////////////////////////////////////////
    ////////////////////// getInstance() //////////////////////
    ///////////////////////////////////////////////////////////
    private static BorrowCommand mc;

    // setup BookCommand Instance
    public static BorrowCommand getInstance(User user) {

        if (mc == null) {
            mc = new BorrowCommand(user);
        }
        currentUser = user;

        return mc;
    }// Method getInstance END

    // reset BookCommand Instance
    public static void freeInstance() {
        mc = null;
    }// Method freeInstance END



    ///////////////////////////////////////////////////////////
    ////////////////////////// Method /////////////////////////
    ///////////////////////////////////////////////////////////
    // 메인실행
    public void execute() {
            while (processMenu()) {
                try {

                } catch (NumberFormatException ex) {
                    errorNumberFormatException();
                }
            }
    }//Method execute END

    //Menu Process
    //[0] 종료 입력시에만 process 종료
    private boolean processMenu(){
        printMenuTUI();
        int menuNo = inputInt(String.format("메인/%s>", menuTitle));

        return switch (menuNo) {
            case 1 -> {
                bookBorrow();
                yield true;
            }
            case 2 -> {
                bookSearch();
                yield true;
            }
            case 0 -> false;
            default -> {
                errorNumberLimitException();
                yield true;
            }
        };
    }//Method borrowMenuProcess END


    private void printMenuTUI(){
        setClearCmd();

        System.out.printf("'%s' 님의 추천 도서\n", currentUser.getName());
        read();

        System.out.print(printUserMenu(1));
    }




    private void bookBorrow() {
        int bookNo = inputInt(yellowColorCode+"도서번호를 입력하세요: "+resetColorCode);

        // bookList에서 해당 도서 찾기
        Book selectedBook = null;
        for (Book book : BookCommand.getInstance().getBookList()) {
            if (book.getNo() == bookNo) {
                selectedBook = book;
                break;
            }
        }

        if (selectedBook == null) {
            errorNotHereBook();
            return;
        }

        if (selectedBook.isCheck()) {
            errorAlreadyLend();
            return;
        }

        // 새로운 Borrow 객체 생성
        Borrow borrow = new Borrow(LocalDate.now());
        borrow.setTitle(selectedBook.getTitle());
        borrow.setUser(currentUser);

        // borrowList에 대출 정보 추가
        borrowList.add(borrow);

        // 도서를 대출 중으로 표시
        selectedBook.setCheck(true);

        successLendBook();
        System.out.printf("대출 도서: %s\n", selectedBook.getTitle());
        System.out.printf("대출 기간: %s ~ %s\n", borrow.getStartDate(), borrow.getEndDate());

        updateBorrowListInOtherClasses();
    }

    // 대출리스트 Update
    private void updateBorrowListInOtherClasses() {
//        ReturnCommand.getInstance(currentUser).setBorrowList(this.borrowList);
//        AdminMonitor.getInstance().updateBorrowList(this.borrowList);
    }


    @Override
    public void read() {
        String[] calm={"No", "카테고리", "도서명", "저자", "대출상태"};
        int[] width={SMALL, LARGE, HUGE, LARGE, MIDDLE};
        //no, cate, title, writer, status
        int i=0;

        String userMbti = currentUser.getMbti().getMbti();

        //Mbti 설명
        GetHtml.printMbtiDescription(userMbti);
        bookMbti(userMbti);
//        //////////////////////////////////////////////////////////////
//        ////////////////////////result table//////////////////////////
//        //////////////////////////////////////////////////////////////
//        //table title
//        System.out.print(printTableLine(width));
//        for(String data: calm){
//            System.out.print(printTableDataFormat(width[i++], data));
//        }
//        System.out.print(":\n");
//        System.out.print(printTableLine(width));
//
//        //table data
//        for (Book book : bookList) {
//            System.out.print(printTableDataFormat( width[0], String.format("%s", book.getNo())) );
//            System.out.print(printTableDataFormat( width[1], String.format("%s", book.getBookCategory())) );
//            System.out.print(printTableDataFormat( width[2], String.format("%s", book.getTitle())) );
//            System.out.print(printTableDataFormat( width[3], String.format("%s", book.getAuthor())) );
//            System.out.print(valid(book, width[4]));
//            System.out.print(":\n");
//        }
//
//        //END line
//        System.out.print(printTableLine(width));
//        //////////////////////////////////////////////////////////////
//        //////////////////////////////////////////////////////////////

    }

    // 전체 도서 출력
    private void allBookPrint(){
        String[] calm={"No", "카테고리", "도서명", "저자", "대출상태"};
        int[] width={SMALL, LARGE, HUGE, LARGE, MIDDLE};
                System.out.print(lightSkyBlueColorCode+printTableLine(width));
//        for(String data: calm){
//            System.out.print(printTableDataFormat(width[i++], data));
//        }
        for(int i = 0; i < calm.length; i++){
            System.out.print(printTableDataFormat(width[i], calm[i]));
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


    }



    private void bookMbti(String type){
        String[] calm={"No", "카테고리", "도서명", "저자", "대출상태"};
        int[] width={SMALL, LARGE, HUGE, LARGE, MIDDLE};
        //no, cate, title, writer, status
        int i=0;
        List<Book> resultList = new ArrayList<>();

        for (Book book : BookCommand.getInstance().getBookList()) {
            if (book.getMbti().toLowerCase().contains(type.toLowerCase())) {
                resultList.add(book);
            }
        }

        if (resultList.isEmpty()) {
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
        for (Book book : resultList) {
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


    private void bookSearch() {
        String[] searchMenu = {"카테고리", "도서명", "저자"};
        String[] calm={"No", "카테고리", "도서명", "저자", "대출상태"};
        int[] width={SMALL, LARGE, HUGE, LARGE, MIDDLE};
        //no, cate, title, writer, status
        int i=0;



        setClearCmd();
        System.out.println("                                  +-- 전체 도서 목록 --+");
        allBookPrint();

        System.out.print(printCustomMenu(searchMenu));
        int searchOption = inputInt("검색 옵션을 선택하세요: ");

        String searchKeyword;
        List<Book> searchResults = new ArrayList<>();

        switch (searchOption) {
            case 1:
                System.out.print("카테고리: 소설, 과학, 역사, 자기계발, 철학\n");
                searchKeyword = input(yellowColorCode+"검색할 카테고리를 입력하세요: "+resetColorCode);
                for (Book book : BookCommand.getInstance().getBookList()) {
                    if (book.getBookCategory().toLowerCase().contains(searchKeyword.toLowerCase())) {
                        searchResults.add(book);
                    }
                }
                break;
            case 2:
                searchKeyword = input(yellowColorCode+"검색할 도서명을 입력하세요: "+resetColorCode);
                for (Book book : BookCommand.getInstance().getBookList()) {
                    if (book.getTitle().toLowerCase().contains(searchKeyword.toLowerCase())) {
                        searchResults.add(book);
                    }
                }
                break;
            case 3:
                searchKeyword = input(yellowColorCode+"검색할 저자를 입력하세요: "+resetColorCode);
                for (Book book : BookCommand.getInstance().getBookList()) {
                    if (book.getAuthor().toLowerCase().contains(searchKeyword.toLowerCase())) {
                        searchResults.add(book);
                    }
                }
                break;
            case 0:
                break;
            default:
                errorNumberLimitException();
                return;
        }

        if (searchResults.isEmpty()) {
            errorAccordBook();
        } else {


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
            for (Book book : searchResults) {
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
            bookBorrow();
        }
    }


    @Override
    public void cmd() {

    }

    @Override
    public void printTUI() {

    }

    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }



    ///////////////////////////////////////////////////////////
    ///////////////// public getter, setter ///////////////////
    ///////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////
    //////////////////////////// -- ///////////////////////////
    //////////////////////////// -- ///////////////////////////
    //////////////////////////// -- ///////////////////////////
    //////////////////////// ---------- ///////////////////////
    ////////////////////////// ------ /////////////////////////
    //////////////////////////// -- ///////////////////////////
    ///////////////////////////////////////////////////////////

    public static List<Borrow> getBorrowList() {
        return borrowList;
    }

    public static void setBorrowList(List<Borrow> borrowList) {
        BorrowCommand.borrowList = borrowList;
    }

    public static void removeBorrowList(Borrow b) {
        borrowList.remove(b);
    }

    public static User getCurrentUser() {
        return currentUser;
    }

}

