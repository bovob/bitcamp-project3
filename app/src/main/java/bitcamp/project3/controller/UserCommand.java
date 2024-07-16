package bitcamp.project3.controller;

import bitcamp.project3.vo.User;
import java.util.ArrayList;

import static bitcamp.project3.util.MenuFormat.*;
import static bitcamp.project3.util.Prompt.*;
import static bitcamp.project3.util.SystemMsg.*;
import static bitcamp.project3.util.TableFormat.*;


public class UserCommand implements Command{

    public static ArrayList<User> userList = new ArrayList<>();
    private static User currentUser;    //TEST USER1


    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////
    UserCommand(){
        create(new String[]{"root", "root", "0000"},new int[]{0,0,0,0});    //default:0
        create(new String[]{"user", "user", "0000"},new int[]{0,0,0,0});
    }



    ///////////////////////////////////////////////////////////
    ////////////////////// getInstance() //////////////////////
    ///////////////////////////////////////////////////////////
    private static UserCommand uc;

    // setup UserCommand Instance
    public static UserCommand getInstance(int userNo) {

        if (uc == null) {
            uc = new UserCommand();
        }
        currentUser = userList.get(userNo);

        return uc;
    }// Method getInstance END

    // reset UserCommand Instance
    public static void freeInstance() {
        uc = null;
    }// Method freeInstance END



    ///////////////////////////////////////////////////////////
    ///////////////////////// Method //////////////////////////
    ///////////////////////////////////////////////////////////
    // cmd main Method
    @Override
    public void cmd(){
        printTUI();
    }//Method cmd END


    // cmd TUI 출력 main Method
    @Override
    public void printTUI(){
        //User 환영 메세지
        System.out.print("'"+currentUser.getName() + "'"+"님 환영합니다!\n");
        //(TEST)User List
        //read();
        ReturnCommand rc = ReturnCommand.getInstance();
        rc.printBorrowStatusByUser();
        //SubMenu 출력
        System.out.print(printUserMenu(0));
    }//Method printTUI END


    public void printAdminTUI(){
        String[] calm = {"유저 삭제"};

        //(TEST)User List
        read();
        //SubMenu 출력
        System.out.print(printCustomMenu(calm));
    }








    // User 생성 main Method(NEW User+프로필/MBTI user 입력)
    @Override
    public void create(){
        userList.add( new User(createUserData(), createMbtiData()) );
    }//Method create END

    // User 생성 main Method(기존 유저/Dummy 유저)
    public void create(String[] user, int[] mbti){
        userList.add( new User(user, mbti) );
    }//Method create END

    //user 프로필 입력
    //  Class User
    //  -public User("String[] data", User.Mbti mbti)
    private String[] createUserData(){
        String[] userData = new String[3];

        userData[0]=input("이름: ");
        userData[1]=input("ID: ");
        userData[2]=input("PW: ");

        return userData;
    }//Method createUserData END

    //user MBTI 입력
    //  Class User
    //  -public User(String[] data, "User.Mbti mbti")
    private User.Mbti createMbtiData(){
        MbtiCommand mc = MbtiCommand.getInstance();
        User.Mbti mbti = new User.Mbti();

        //MBTI 검사지 출력
        mc.printTUI(mbti);

        return mbti;
    }//Method createMbtiData END









    //User List 출력 main Method
    //    +----+---------------+---------------+---------------+
    //    |1   |root           |root           |0000           |
    //    |2   |user           |user           |0000           |
    //    +----+---------------+---------------+---------------+
    @Override
    public void read(){
        StringBuilder str = new StringBuilder();

        String[] calm = {"No", "이름", "ID", "MBTI"};
        int[] width={4, 15, 15, 15};
        //no, name, id, mbti
        int no=0;   //user num(priKey X)
        int i=0;

        //table title
        str.append(printTableLine(width));
        for(String data: calm){
            str.append(printTableDataFormat(width[i++], data));
        }
        str.append(":\n");




        //+----+---------------+---------------+---------------+
        str.append(printTableLine(width));
        //|1   |root           |root           |0000           |
        for(User user :userList) {
            str.append(printUser(width, user, ++no));
        }
        //+----+---------------+---------------+---------------+
        str.append(printTableLine(width));

        System.out.print(lightSkyBlueColorCode+str+resetColorCode);
    }//Method read END

    //User 정보 출력
    //|1   |root           |root           |0000           |
    //width: 가로 너비
    private String printUser(int[] width, User user, int no){
        String str = "";

        str = printTableDataFormat(width[0], String.format("%d", no))+
              printTableDataFormat(width[1], user.getName())+
              printTableDataFormat(width[2], user.getId())+
              printTableDataFormat(width[3], user.getMbti().getMbti())+
              "|"+
              "\n";

        return str;
    }//Method printUser END



    //mbti int->String 한줄 출력
    private String printMbti(User.Mbti mbti){
        return String.format("%s%s%s%s", mbti.getEi(), mbti.getNs(), mbti.getFt(), mbti.getPj());
    }//Method printMbti END










    //User data 수정 main Method
    @Override
    public void update(){
        while(updateMenuCommand()){

        };
    }//Method update END

    //User data 수정 메뉴 선택
    //while( (boolean)updateMenuCommand )
    //[0]종료 입력시에만 return false=>Method update()종료
    private boolean updateMenuCommand(){
        int ans = 0;

        System.out.print(printUserData(currentUser));
        ans = inputInt("> ");

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
                errorNumberLimitException();
                yield true;
            }
        };
    }//Method updateMenuCommand END

    //회원 정보 출력
    //    'user'님의 회원 정보
    //    ID: user
    //    PW: 0000
    //    MBTI: istj
    private String printUserData(User user){
        String str = "";

                //'user'님의 회원 정보
        str = lightSkyBlueColorCode+
                "'"       + user.getName() +"'님의 회원 정보\n"+
                //ID: user
              "ID: "    + user.getId()                  +"\n"+
                //PW: 0000
              "PW: "    + user.getPw()                  +"\n"+
                //MBTI: istj
              "MBTI: "  + user.getMbti().getMbti()     +"\n\n"
                +resetColorCode;

        //sub Menu 출력
        str += printUserMenu(3);


        return str;
    }//Method printUserData END



    //PW update
    private void setUserPw(){
        String pw = input(String.format(yellowColorCode+"새 PW(이전: %s) "+resetColorCode,currentUser.getPw()));
        currentUser.setPw(pw);
        successUpdate();
    }//Method setUserPw END

    //mbti update
    private void setMbti(){
//        System.out.print("setMbti function\n");
        currentUser.setMbti(createMbtiData());
        successUpdate();
    }









    @Override
    public void delete(){
        deleteUser();
    }

    private void deleteUser(){
        int ans=-1;

        while(true) {
            try {

                System.out.print(yellowColorCode+"삭제할 유저 번호?(이전: 0)\n");
                ans = inputInt(">"+resetColorCode);

                if(ans == 0){
                    return;
                }

                if(ans ==1){
                    errorDeleteAdmin();
                    continue;
                }

                if(isValidateUserNum(ans)) {
                    System.out.printf(lightSkyBlueColorCode+"'%s'님이 삭제되었습니다.\n"+resetColorCode, userList.get(ans - 1).getName());
                    userList.remove(ans - 1);
                    return;
                }else{
                    errorUserNo();
                }

            } catch (NumberFormatException e) {

            }
        }
    }

    private boolean isValidateUserNum(int ans){
        return ans > 0 && ans <= (userList.size());
    }


    ///////////////////////////////////////////////////////////
    ///////////////////// Admin Process ///////////////////////
    ///////////////////////////////////////////////////////////
    public void adminExcute(){
        uc.processAdminUser();
    }

    public void processAdminUser(){
        while(adminMenuCommand()){

        };
    }//Method update END

    //User data 수정 메뉴 선택
    //while( (boolean)adminMenuCommand )
    //[0]종료 입력시에만 return false=>Method 종료
    private boolean adminMenuCommand(){
        int ans = 0;

        uc.printAdminTUI();
        ans = inputInt("> ");

        return switch (ans) {
            case 1 -> {
                delete();
                yield true;
            }
            case 0 -> false;
            default -> {
                errorNumberLimitException();
                yield true;
            }
        };
    }//Method updateMenuCommand END



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

    public static void setCurrentUser(User currentUser) {
        UserCommand.currentUser = currentUser;
    }
}
