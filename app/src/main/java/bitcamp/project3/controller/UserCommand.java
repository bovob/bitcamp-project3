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
        read();
        //SubMenu 출력
        System.out.print(printMenu(0));
    }//Method printTUI END


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

        userData[0]=Prompt.input("이름: ");
        userData[1]=Prompt.input("ID: ");
        userData[2]=Prompt.input("PW: ");

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
        int[] width={4, 15, 15, 15};
        //no, name, id, mbti
        int no=0;   //user num(priKey X)

        //+----+---------------+---------------+---------------+
        str.append(printLine(width));
        //|1   |root           |root           |0000           |
        for(User user :userList) {
            str.append(printUser(width, user, ++no));
        }
        //+----+---------------+---------------+---------------+
        str.append(printLine(width));

        System.out.print(str);
    }//Method read END

    //User 정보 출력
    //|1   |root           |root           |0000           |
    //width: 가로 너비
    private String printUser(int[] width, User user, int no){
        String str = "";

        str = printUserFormat(width[0], String.format("%d", no))+
              printUserFormat(width[1], user.getName())+
              printUserFormat(width[2], user.getId())+
              printUserFormat(width[3], printMbti(user.getMbti()))+
              "|"+
              "\n";

        return str;
    }//Method printUser END


    //가로선 출력
    //너비 배열 입력 시 가로선 출력 =>Prompt 이동 예정
    //  +----+---------------+---------------+---------------+
    //width: 가로 너비
    private String printLine(int[] width){
        StringBuilder str;

        //start "+"
        str = new StringBuilder("+");
        // str += "----+"
        for(int i=0 ; i<width.length ; i++) {
            str.append("-".repeat(width[i]));
            str.append("+");
        }
        //end "\n"
        str.append("\n");

        return str.toString();
    }//Method printLine END

    //표 내용 출력
    //너비 배열과 데이터 입력 시 표에 맞게 출력
    //for(printUserFormat)사용하여 한줄 출력 가능 =>Prompt 이동 예정
    //  "|root           "
    //width: 가로길이
    //data(String): 정보
    private String printUserFormat(int width, String data){
       String str = "";

        //start "|"
       str = "|" +
             String.format("%-" + width + "s", data);

       return str;
    }//Method printUserFormat END


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
    }//Method updateMenuCommand END

    //회원 정보 출력
    //    'user'님의 회원 정보
    //    ID: user
    //    PW: 0000
    //    MBTI: istj
    private String printUserData(User user){
        String str = "";

                //'user'님의 회원 정보
        str = "'"       + user.getName() +"'님의 회원 정보\n"+
                //ID: user
              "ID: "    + user.getId()                  +"\n"+
                //PW: 0000
              "PW: "    + user.getPw()                  +"\n"+
                //MBTI: istj
//                "MBTI: "  + printMbti(user.getMbti())     +"\n\n";
              "MBTI: "  + user.getMbti().getMbti()     +"\n\n";

        //sub Menu 출력
        //        [1] PW 수정
        //        [2] MBTI 재검사
        //        [0] 이전 메뉴
        str += printMenu(3);


        return str;
    }//Method printUserData END

    //print Menu
    //index=> 메뉴 배열 번호(main:0)             =>Prompt 이동 예정
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
    }//Method printMenu END


    //PW update
    private void setUserPw(){
        String pw = Prompt.input(String.format("새 PW(이전: %s) ",currentUser.getPw()));
        currentUser.setPw(pw);
        System.out.print("변경 되었습니다.\n");
    }//Method setUserPw END

    //mbti update
    private void setMbti(){
//        System.out.print("setMbti function\n");
        currentUser.setMbti(createMbtiData());
        System.out.print("변경 되었습니다.\n");
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

    public static void setCurrentUser(User currentUser) {
        UserCommand.currentUser = currentUser;
    }
}
