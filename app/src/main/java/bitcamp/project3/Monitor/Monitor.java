package bitcamp.project3.Monitor;

import bitcamp.project3.controller.BorrowCommand;
import bitcamp.project3.controller.ReturnCommand;
import bitcamp.project3.controller.UserCommand;
import bitcamp.project3.vo.Book;
import java.util.List;

public class Monitor {

    // 도서 Dummy 생성
    List<Book> bookList = Book.generateDummyData(5);

    public UserCommand userCommand = UserCommand.getInstance();
    public BorrowCommand borrowCommand;
    public ReturnCommand returnCommand;

    // 메뉴 검증
    protected static boolean isValidateMenu(int menuNo, String[] menus) {
        return menuNo >= 1 && menuNo <= menus.length;
    }

    // 메뉴 타이틀 출력
    public static String getMenuTitle(int menuNo, String[] menus) {
        return isValidateMenu(menuNo, menus) ? menus[menuNo - 1] : null;
    }
}
