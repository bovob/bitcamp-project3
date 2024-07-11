package bitcamp.project3.vo;

public class User {
    String name;
    String id;
    String pw;
    Mbti mbti;
    int takeCnt;

    public class Mbti{
        int be;
        int pw;
        int so;
        int hm;
    }



    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////

    User(){
        this.takeCnt = 0;
    }

    User(String name, String id, String pw, Mbti mbti){
        this.name = name;
        this.id = id;
        this.pw = pw;
        this.mbti = mbti;
        this.takeCnt = 0;
    }

    User(String[] data, Mbti mbti){
        this.name = data[0];
        this.id = data[1];
        this.pw = data[2];
        this.mbti = mbti;
        this.takeCnt = 0;
    }



    ///////////////////////////////////////////////////////////
    ////////////////////// getInstance() //////////////////////
    ///////////////////////////////////////////////////////////
    private static User u;

    // setup User Instance
    public static User getInstance() {

        if (u == null) {
            u = new User();
        }

        return u;
    }// Method getInstance END

    // reset User Instance
    public static void freeInstance() {
        u = null;
    }// Method freeInstance END





    ///////////////////////////////////////////////////////////
    ///////////////////////// Method //////////////////////////
    ///////////////////////////////////////////////////////////
    public void userMain(){

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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public Mbti getMbti() {
        return mbti;
    }

    public void setMbti(Mbti mbti) {
        this.mbti = mbti;
    }

    public int getTakeCnt() {
        return takeCnt;
    }

    public void setTakeCnt(int takeCnt) {
        this.takeCnt = takeCnt;
    }
}
