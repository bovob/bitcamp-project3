package bitcamp.project3.vo;

public class User {
    String name = "OREO";
    String id = "OREO";
    String pw = "0000";
    Mbti mbti;
    int takeCnt;

    public class Mbti{
        int be;
        int pw;
        int so;
        int hm;

        public Mbti(){
            this.be = 0;
            this.pw = 0;
            this.so = 0;
            this.hm = 0;
        }

        public Mbti(int be, int pw, int so, int hm){
            this.be = be;
            this.pw = pw;
            this.so = so;
            this.hm = hm;
        }

        public Mbti(int[] data){
            this.be = data[0];
            this.pw = data[1];
            this.so = data[2];
            this.hm = data[3];
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

        public int getBe() {
            return be;
        }

        public void setBe(int be) {
            this.be = be;
        }

        public int getPw() {
            return pw;
        }

        public void setPw(int pw) {
            this.pw = pw;
        }

        public int getSo() {
            return so;
        }

        public void setSo(int so) {
            this.so = so;
        }

        public int getHm() {
            return hm;
        }

        public void setHm(int hm) {
            this.hm = hm;
        }
    }



    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////

    public User(){
        this.mbti = new Mbti();
        this.takeCnt = 0;
    }

    public User(String name, String id, String pw, int[] m){
        this.name = name;
        this.id = id;
        this.pw = pw;
        this.mbti = new Mbti(m);
        this.takeCnt = 0;
    }

    public User(String[] data, int[] m){
        this.name = data[0];
        this.id = data[1];
        this.pw = data[2];
        this.mbti = new Mbti(m);
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
