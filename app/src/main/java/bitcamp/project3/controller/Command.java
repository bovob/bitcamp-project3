package bitcamp.project3.controller;

public interface Command {
    void cmd();
    void printTUI();
    void create();
    void read();
    void update();
    void delete();

    // 메뉴 검증
    static boolean isValidateMenu(int menuNo, String[] menus) {
        return menuNo >= 1 && menuNo <= menus.length;
    }

    // 메뉴 타이틀 출력
    static String getMenuTitle(int menuNo, String[] menus) {
        return isValidateMenu(menuNo, menus) ? menus[menuNo - 1] : null;
    }
}
