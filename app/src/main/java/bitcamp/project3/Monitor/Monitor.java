package bitcamp.project3.Monitor;

import bitcamp.project3.controller.BookCommand;
import bitcamp.project3.controller.BorrowCommand;
import bitcamp.project3.controller.ReturnCommand;
import bitcamp.project3.controller.UserCommand;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.Borrow;
import java.util.ArrayList;
import java.util.List;

public class Monitor {

    // 도서 Dummy 생성
    List<Book> bookList = new ArrayList<>(Book.generateDummyData(5));
    List<Borrow> borrowList = new ArrayList<>();


    public UserCommand userCommand = UserCommand.getInstance();
    public BorrowCommand borrowCommand = BorrowCommand.getInstance();
    public ReturnCommand returnCommand = ReturnCommand.getInstance();
    public BookCommand bookCommand;

    // 메뉴 검증
    protected static boolean isValidateMenu(int menuNo, String[] menus) {
        return menuNo >= 1 && menuNo <= menus.length;
    }

    // 메뉴 타이틀 출력
    public static String getMenuTitle(int menuNo, String[] menus) {
        return isValidateMenu(menuNo, menus) ? menus[menuNo - 1] : null;
    }
}
