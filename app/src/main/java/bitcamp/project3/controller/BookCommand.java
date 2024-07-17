package bitcamp.project3.controller;

import bitcamp.project3.Monitor.AdminMonitor;
import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.Borrow;
import java.util.ArrayList;
import java.util.List;

import static bitcamp.project3.util.MenuFormat.*;
import static bitcamp.project3.util.Prompt.*;
import static bitcamp.project3.util.SystemMsg.*;
import static bitcamp.project3.util.TableFormat.*;

public class BookCommand implements Command {

    //0->1번(-1) process
    //"도서 관리"
    String menuTitle = AdminMonitor.getAdminMenus()[0][0];
//    {"도서등록","도서목록","도서수정","도서삭제"}   //1~

    // 도서 Dummy 생성
    static ArrayList<Book> bookList;
    List<Borrow> borrowList;

    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////
    public BookCommand() {
    }

    public BookCommand(List<Borrow> borrowList) {
        this.borrowList = borrowList;
    }


    ///////////////////////////////////////////////////////////
    ////////////////////// getInstance() //////////////////////
    ///////////////////////////////////////////////////////////
    private static BookCommand bc;

    // setup BookCommand Instance
    public static BookCommand getInstance() {

        if (bc == null) {
            bc = new BookCommand();
        }

        return bc;
    }// Method getInstance END

    // reset BookCommand Instance
    public static void freeInstance() {
        bc = null;
    }// Method freeInstance END





    ///////////////////////////////////////////////////////////
    ///////////////////////// Method //////////////////////////
    ///////////////////////////////////////////////////////////
    // 메인실행
    public void adminExecute() {

        while (processMenu()) {
            try {

            } catch (NumberFormatException e) {
                errorNumberFormatException();
            }
        }
    }//Method execute END

    //Menu Process
    //[0] 종료 입력시에만 process 종료
    private boolean processMenu(){
//        System.out.print("[도서 관리]\n");
        printMenuTUI();
        int ans = inputInt(String.format("메인/%s>", menuTitle));

        return switch (ans) {
            case 1 -> {
                create();
                yield true; //도서 등록
            }
            case 2 -> {
                read();
                yield true; //도서 확인
            }
            case 3 -> {
                update();
                yield true; //도서 수정
            }
            case 4 -> {
                delete();
                yield true; //도서 삭제
            }
            case 0 -> false; //종료
            default -> {
                errorNumberLimitException();
                yield true;
            }
        };
        
    }//Method bookMenuProcess END

    private void printMenuTUI(){
        setClearCmd();

        System.out.print(printAdminMenu(1));
    }

    // 도서등록
    @Override
    public void create() {
//        System.out.println("도서등록 입니다.");
        Book book = new Book();

        book.setBookCategory(Prompt.input("카테고리"));
        book.setTitle(Prompt.input("책 이름?"));
        book.setAuthor(Prompt.input("책 저자?"));
        book.setMbti("MBTI? ");

        bookList.add(book);

    }

    // 목록조회
    @Override
    public void read() {
        String[] calm = {"번호","카테고리","도서명","저자","MBTI"};
        int[] width   = {SMALL, LARGE,       HUGE,  LARGE, MIDDLE};
        int i=0;


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
        for (Object obj : bookList.toArray()) {
            Book book = (Book) obj;

            System.out.print(printTableDataFormat( width[0], String.format("%s", book.getNo())) );
            System.out.print(printTableDataFormat( width[1], String.format("%s", book.getBookCategory())) );
            System.out.print(printTableDataFormat( width[2], String.format("%s", book.getTitle())) );
            System.out.print(printTableDataFormat( width[3], String.format("%s", book.getAuthor())) );
            System.out.print(printTableDataFormat( width[4], String.format("%s", book.getMbti())));
            System.out.print(":\n");
        }

        //END line
        System.out.print(printTableLine(width)+resetColorCode);
        //////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////
    }


    // 도서수정
    @Override
    public void update() {
//        System.out.println("도서수정 입니다.");
        int bookNo = inputInt(yellowColorCode+"도서번호?"+resetColorCode);
        Book bookToUpdate = null;

        for (Book book : bookList) {
            if (book.getNo() == bookNo) {
                bookToUpdate = book;
                break;
            }
        }

        if (bookToUpdate == null) {
            errorNotHereBook();
            return;
        }

        bookToUpdate.setBookCategory(Prompt.input(yellowColorCode+"카테고리"+resetColorCode));
        bookToUpdate.setTitle(Prompt.input(yellowColorCode+"책 이름?"+resetColorCode));
        bookToUpdate.setAuthor(Prompt.input(yellowColorCode+"책 저자?"+resetColorCode));
        bookToUpdate.setMbti(Prompt.input(yellowColorCode+"MBTI ?"+resetColorCode));

        System.out.println(lightSkyBlueColorCode+"변경되었습니다."+resetColorCode);
    }

    //도서삭제
    @Override
    public void delete() {
        int bookNo = inputInt(yellowColorCode+"책번호?"+resetColorCode);
        Book bookToRemove = null;

        for (Book book : bookList) {
            if (book.getNo() == bookNo) {
                bookToRemove = book;
                break;
            }
        }

        if (bookToRemove != null) {
            bookList.remove(bookToRemove);
            System.out.printf(lightSkyBlueColorCode+"%d번 %s를 삭제했습니다.\n"+resetColorCode, bookToRemove.getNo(), bookToRemove.getTitle());
        } else {
            errorNotHereBook();
        }
    }


    @Override
    public void cmd() {

    }

    @Override
    public void printTUI() {

    }

    public ArrayList<Book> getBookList() {
        return this.bookList;
    }

    public static void setBookList(ArrayList<Book> bookList) {
        BookCommand.bookList = bookList;
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


}
