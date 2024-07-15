package bitcamp.project3.controller;

import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.Borrow;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static bitcamp.project3.util.MenuFormat.*;
import static bitcamp.project3.util.Prompt.*;
import static bitcamp.project3.util.SystemMsg.*;
import static bitcamp.project3.util.TableFormat.*;


public class BorrowCommand implements Command {

    String menuTitle = "대출";
    String currentUser = "user";

    List<Borrow> borrowList = new LinkedList<>();






    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////
    public BorrowCommand() {
    }

    public BorrowCommand(String title, List<Book> bookList) {
        this.menuTitle = title;
        this.bookList = bookList;
    }

//    public BorrowCommand(String title, LinkedList bookList) {
//        this.menuTitle = title;
//        this.bookList = bookList;
//    }





    ///////////////////////////////////////////////////////////
    ////////////////////////// Method /////////////////////////
    ///////////////////////////////////////////////////////////
    // 메인실행
    public void execute() {
/*            String command = input(String.format("메인/%s>", menuTitle));
            if (command.equals("menu")) {
                System.out.print(printUserMenu(1));
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
                switch (menuNo){
                    case 1:
                        bookBorrow();
                        break;
                    case 2:
                        bookSearch();
                        break;
                    default:
                        printNumberLimitException();
                }*/
        while (processMenu()) {
            try {

                } catch (NumberFormatException ex) {
                    printNumberFormatException();
                }
        }
    }//Method execute END

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
                printNumberLimitException();
                yield true;
            }
        };
    }//Method borrowMenuProcess END


    private void printMenuTUI(){
        setClearCmd();

        System.out.printf("안녕하세요 '%s' 님\n", currentUser);
        System.out.println("++대출도서 목록예정++\n");
        read();

        System.out.print(printUserMenu(1));
    }




    private void bookBorrow() {
        System.out.println("도서대출 입니다.");
        int bookNo = inputInt("도서번호를 입력하세요: ");

        // bookList에서 해당 도서 찾기
        Book selectedBook = null;
        for (Book book : bookList) {
            if (book.getNo() == bookNo) {
                selectedBook = book;
                break;
            }
        }

        if (selectedBook == null) {
            System.out.println("해당 번호의 도서를 찾을 수 없습니다.");
            return;
        }

        if (selectedBook.isCheck()) {
            System.out.println("이미 대출 중인 도서입니다.");
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

        System.out.println("도서가 성공적으로 대출되었습니다.");
        System.out.printf("대출 도서: %s\n", selectedBook.getTitle());
        System.out.printf("대출 기간: %s ~ %s\n", borrow.getStartDate(), borrow.getEndDate());
    }





    @Override
    public void read() {
        String[] calm={"No", "카테고리", "도서명", "저자", "대출상태"};
        int[] width={4, 20, 20, 20, 15};
        //no, cate, title, writer, status
        int i=0;


        System.out.println("도서목록 입니다.");

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



    private void bookSearch() {
        String[] searchMenu = {"카테고리로 검색", "도서명으로 검색", "저자로 검색"};
        String[] calm={"No", "카테고리", "도서명", "저자", "대출상태"};
        int[] width={4, 20, 20, 20, 15};
        //no, cate, title, writer, status
        int i=0;

        setClearCmd();
        System.out.println("도서 검색");
        System.out.print(printCustomMenu(searchMenu));
        int searchOption = inputInt("검색 옵션을 선택하세요: ");

        String searchKeyword;
        List<Book> searchResults = new ArrayList<>();

        switch (searchOption) {
            case 1:
                searchKeyword = input("검색할 카테고리를 입력하세요: ");
                for (Book book : bookList) {
                    if (book.getBookCategory().toLowerCase().contains(searchKeyword.toLowerCase())) {
                        searchResults.add(book);
                    }
                }
                break;
            case 2:
                searchKeyword = input("검색할 도서명을 입력하세요: ");
                for (Book book : bookList) {
                    if (book.getTitle().toLowerCase().contains(searchKeyword.toLowerCase())) {
                        searchResults.add(book);
                    }
                }
                break;
            case 3:
                searchKeyword = input("검색할 저자를 입력하세요: ");
                for (Book book : bookList) {
                    if (book.getAuthor().toLowerCase().contains(searchKeyword.toLowerCase())) {
                        searchResults.add(book);
                    }
                }
                break;
            case 0:
                break;
            default:
                System.out.println("잘못된 옵션을 선택하셨습니다.");
                return;
        }

        if (searchResults.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
        } else {
            System.out.println("검색 결과:");


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
            for (Book book : searchResults) {
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
    }

//    public void cmd() {
//        System.out.printf("[%s]\n", menuTitle);
//        for (int i = 0; i < menus.length; i++) {
//            System.out.printf("[%d] %s\n", (i + 1), menus[i]);
//        }
//        System.out.println("[0] 이전메뉴");
//    }


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
    //원래 북리스트
    //LinkedList bookList;
    public List<Borrow> getBorrowList() {
        return this.borrowList;
    }

    //더미 북 리스트
    List<Book> bookList = Book.generateDummyData(5);
    public List<Book> getBookList() {
        return this.bookList;
    }
}

