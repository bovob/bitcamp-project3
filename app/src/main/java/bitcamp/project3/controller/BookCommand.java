package bitcamp.project3.controller;

import bitcamp.project3.Monitor.AdminMonitor;
import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.Book;
import java.util.LinkedList;
import java.util.List;

import static bitcamp.project3.util.MenuFormat.*;
import static bitcamp.project3.util.Prompt.*;
import static bitcamp.project3.util.SystemMsg.*;

public class BookCommand implements Command {

    //0->1번(-1) process
    //"도서 관리"
    String menuTitle = AdminMonitor.getAdminMenus()[0][0];
//    {"도서등록","도서목록","도서수정","도서삭제"}   //1~
//    String[] menus = AdminMonitor.getAdminMenus()[1];
    LinkedList<Book> bookList = new LinkedList<>();



    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////
    public BookCommand() {
    }

    public BookCommand(String title) {
        this.menuTitle = title;
    }







    ///////////////////////////////////////////////////////////
    ////////////////////// getInstance() //////////////////////
    ///////////////////////////////////////////////////////////
    private static BookCommand mc;

    // setup BookCommand Instance
    public static BookCommand getInstance() {

        if (mc == null) {
            mc = new BookCommand();
        }

        return mc;
    }// Method getInstance END

    // reset BookCommand Instance
    public static void freeInstance() {
        mc = null;
    }// Method freeInstance END





    ///////////////////////////////////////////////////////////
    ///////////////////////// Method //////////////////////////
    ///////////////////////////////////////////////////////////
    // 메인실행
    public void execute() {
/*        cmd();
        if (command.equals("menu")) {
                cmd();
                continue;
            } else if (command.equals("9")) { // 이전 메뉴 선택
                return;
            }

                int menuNo = Integer.parseInt(command);
                String menuName = getMenuTitle(menuNo, menus);
                if (menuName == null) {
                    System.out.println("유효한 메뉴 번호가 아닙니다.");
                    continue;
                }
                switch (menuNo){
                    case 1: //도서 등록
                        create();
                        break;
                    case 2: //도서 삭제
                        read();
                        break;
                    case 3: //도서 수정
                        update();
                        break;
                    case 4: //도서 삭제
                        delete();
                        break;
                    case 0:
                        break;
                    default:
                        printNumberLimitException();
                }
                bookMenuProcess(menuNo);**/
        while (processMenu()) {
            try {

            } catch (NumberFormatException e) {
                printNumberFormatException();
            }
        }
    }//Method execute END

    //Menu Process
    //[0] 종료 입력시에만 process 종료
    private boolean processMenu(){
        System.out.print("[도서 관리]\n");
        printMenuTUI();
        int menuNo = inputInt(String.format("메인/%s>", menuTitle));

        return switch (menuNo) {
            case 1 -> {
                create();
                yield true; //도서 등록
            }
            case 2 -> {
                read();
                yield true; //도서 삭제
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
                printNumberLimitException();
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
        System.out.println("도서등록 입니다.");
        Book book = new Book();

        book.setBookCategory(Prompt.input("카테고리"));
        book.setTitle(Prompt.input("책 이름?"));
        book.setAuthor(Prompt.input("책 저자?"));

        book.setM(inputInt("M ?"));
        book.setB(inputInt("B ?"));
        book.setT(inputInt("T ?"));
        book.setI(inputInt("I ?"));
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
        int bookNo = inputInt("도서번호?");
        Book book = (Book) bookList.get(bookList.indexOf(new Book(bookNo)));
        if (book == null) {
            System.out.printf("없는 책입니다.");
        }
        book.setBookCategory(Prompt.input("카테고리"));
        book.setTitle(Prompt.input("책 이름?"));
        book.setAuthor(Prompt.input("책 저자?"));
        book.setM(inputInt("M ?"));
        book.setB(inputInt("B ?"));
        book.setT(inputInt("T ?"));
        book.setI(inputInt("I ?"));
        System.out.println("변경되었습니다.");
    }



    //도서삭제
    @Override
    public void delete() {
        System.out.println("도서삭제 입니다.");

        int bookNo = inputInt("책번호?");
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
//        System.out.printf("[%s]\n", menuTitle);
//        for (int i = 0; i < menus.length; i++) {
//            System.out.printf("%d. %s\n", (i + 1), menus[i]);
//        }
//        System.out.println("9. 이전");
    }

    @Override
    public void printTUI() {

    }

    public List getBookList() {
        return this.bookList;
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
