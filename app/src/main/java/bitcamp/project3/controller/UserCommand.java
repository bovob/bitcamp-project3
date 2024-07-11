package bitcamp.project3.controller;

import bitcamp.project3.vo.User;
import java.util.ArrayList;

public class UserCommand implements Command{

    public static ArrayList<User> userList = new ArrayList<>();

    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////
    UserCommand(){

    }




    ///////////////////////////////////////////////////////////
    ////////////////////// getInstance() //////////////////////
    ///////////////////////////////////////////////////////////




    ///////////////////////////////////////////////////////////
    ///////////////////////// Method //////////////////////////
    ///////////////////////////////////////////////////////////
    @Override
    public void cmd(){
        printTUI();
    }

    @Override
    public void printTUI(){

    }


    
    @Override
    public void create(){
        userList.add(createUser());
    }


    private User createUser(){
        User user = new User(createUserData(), createMbtiData());
        return user;
    }


    private String[] createUserData(){
        String[] userData = new String[3];

        System.out.print("이름: ");
        userData[0] = "a";

        System.out.print("ID: ");
        userData[1] = "b";

        System.out.print("PW: ");
        userData[2] = "c";

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
        String str = "";
        User user = userList.getFirst();

        int[] width={4, 15, 15, 15};
        //no, name, id, mbti
        str += printLine(width);
        str += printUser(width, user);
        str += printLine(width);

        System.out.print(str);
    }

    private String printUser(int[] width, User user){
        String str = "";

        str = printUserFormat(width[0], String.format("%d", 1))+
              printUserFormat(width[1], user.getName())+
              printUserFormat(width[2], user.getId())+
              printUserFormat(width[3], printMbti(user.getMbti()))+
              ":\n";

        return str;
    }

    private String printLine(int[] width){
        StringBuilder str;

        str = new StringBuilder("+");
        for(int i:width) {
            str.append("-".repeat(Math.max(0, width[i])));
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
}
