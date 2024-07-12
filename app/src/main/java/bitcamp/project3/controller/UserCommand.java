package bitcamp.project3.controller;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.Monitor.UserMonitor;
import bitcamp.project3.vo.User;
import java.util.ArrayList;

public class UserCommand implements Command{

    public static ArrayList<User> userList = new ArrayList<>();
    private static User currentUser;    //TEST USER1


    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////
    UserCommand(){
        create(new String[]{"root", "root", "0000"},new int[]{0,0,0,0});
        create(new String[]{"user", "user", "0000"},new int[]{0,0,0,0});
        currentUser = userList.get(1);
    }



    ///////////////////////////////////////////////////////////
    ////////////////////// getInstance() //////////////////////
    ///////////////////////////////////////////////////////////
    private static UserCommand uc;

    // setup UserCommand Instance
    public static UserCommand getInstance() {

        if (uc == null) {
            uc = new UserCommand();
        }

        return uc;
    }// Method getInstance END

    // reset UserCommand Instance
    public static void freeInstance() {
        uc = null;
    }// Method freeInstance END



    ///////////////////////////////////////////////////////////
    ///////////////////////// Method //////////////////////////
    ///////////////////////////////////////////////////////////
    @Override
    public void cmd(){
        printTUI();

    }


    @Override
    public void printTUI(){
        System.out.print("'"+currentUser.getName() + "'"+"님 환영합니다!\n");
        read();
        System.out.print(printMenu(0));
    }


    
    @Override
    public void create(){
        userList.add( new User(createUserData(), createMbtiData()) );
    }

    public void create(String[] user, int[] mbti){
        userList.add( new User(user, mbti) );
    }


//    private User createUser(){
//        User user = new User(createUserData(), createMbtiData());
//        return user;
//    }


    private String[] createUserData(){
        String[] userData = new String[3];

        userData[0]=Prompt.input("이름: ");
        userData[1]=Prompt.input("ID: ");
        userData[2]=Prompt.input("PW: ");

        return userData;
    }


    private int[] createMbtiData(){
        int[] mData = new int[4];

        mData[0] = 1;
        mData[1] = 2;
        mData[2] = 3;
        mData[3] = 4;

        return mData;
    }




    @Override
    public void read(){
        StringBuilder str = new StringBuilder();
        int[] width={4, 15, 15, 15};
        //no, name, id, mbti
        int no=0;

        str.append(printLine(width));
        for(User user :userList) {
            str.append(printUser(width, user, ++no));
        }
        str.append(printLine(width));

        System.out.print(str);
    }

    private String printUser(int[] width, User user, int no){
        String str = "";

        str = printUserFormat(width[0], String.format("%d", no))+
              printUserFormat(width[1], user.getName())+
              printUserFormat(width[2], user.getId())+
              printUserFormat(width[3], printMbti(user.getMbti()))+
              "|"+
              "\n";

        return str;
    }

    private String printLine(int[] width){
        StringBuilder str;

        str = new StringBuilder("+");
        for(int i=0 ; i<width.length ; i++) {
            str.append("-".repeat(width[i]));
            str.append("+");
        }
        str.append("\n");

        return str.toString();
    }


    private String printUserFormat(int width, String data){
       String str = "";

       str = "|" +
             String.format("%-" + width + "s", data);

       return str;
    }


    private String printMbti(User.Mbti mbti){
        return String.format("%s%s%s%s", mbti.getBe(), mbti.getPw(), mbti.getSo(), mbti.getHm());
    }




    @Override
    public void update(){
        while(updateMenuCommand()){

        };
    }

    private boolean updateMenuCommand(){
        int ans = 0;

        System.out.print(printUserData(currentUser));
        ans = Prompt.inputInt("> ");

        return switch (ans) {
            case 1 -> {
                setUserPw();
                yield true;
            }
            case 2 -> {
                setMbti();
                yield true;
            }
            case 0 -> false;
            default -> {
                System.out.print("올바른 메뉴 번호를 입력해주세요.\n");
                yield true;
            }
        };
    }

    private String printUserData(User user){
        String str = "";

        str = "'"       + user.getName() +"'님의 회원 정보\n"+
              "ID: "    + user.getId()                  +"\n"+
              "PW: "    + user.getPw()                  +"\n"+
              "MBTI: "  + printMbti(user.getMbti())     +"\n\n";

        str += printMenu(3);


        return str;
    }

    private String printMenu(int index){
        String str = "";
        UserMonitor um = UserMonitor.getInstance();
        String[] userMenu = um.getUserMenu()[index];

        for(int no = 0 ; no<userMenu.length ; no++){
            str += String.format("[%-1d] %s\n", no+1, userMenu[no]);
        }
        if(index==0){
            str += String.format("[0] %s\n", "종료");
        }else{
            str += String.format("[0] %s\n", "이전 메뉴");
        }

        return str;
    }

    private void setUserPw(){
        String pw = Prompt.input(String.format("새 PW(이전: %s) ",currentUser.getPw()));
        currentUser.setPw(pw);
        System.out.print("변경 되었습니다.\n");
    }


    private void setMbti(){
        System.out.print("setMbti function\n");
    }



    @Override
    public void delete(){

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

    public static ArrayList<User> getUserList() {
        return userList;
    }

    public static void setUserList(ArrayList<User> userList) {
        UserCommand.userList = userList;
    }

    public static User getCurrentUser() {
        return currentUser;
    }
}
