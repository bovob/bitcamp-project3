package bitcamp.project3.vo;

public class User {
    String name = "OREO";
    String id = "OREO";
    String pw = "0000";
    Mbti mbti;
    int takeCnt;

    public static class Mbti{
        int ei;
        int ns;
        int ft;
        int pj;

        public Mbti(){
            this.ei = 0;
            this.ns = 0;
            this.ft = 0;
            this.pj = 0;
        }

        public Mbti(int ei, int ns, int ft, int pj){
            this.ei = ei;
            this.ns = ns;
            this.ft = ft;
            this.pj = pj;
        }

        public Mbti(int[] data){
            this.ei = data[0];
            this.ns = data[1];
            this.ft = data[2];
            this.pj = data[3];
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

        public int getEi() {
            return ei;
        }

        public void setEi(int ei) {
            this.ei += ei;
        }

        public int getNs() {
            return ns;
        }

        public void setNs(int ns) {
            this.ns += ns;
        }

        public int getFt() {
            return ft;
        }

        public void setFt(int ft) {
            this.ft += ft;
        }

        public int getPj() {
            return pj;
        }

        public void setPj(int pj) {
            this.pj += pj;
        }

        public String getMbti(){
            String str = "";

            if(this.ei>0){
                str += "e";
            }else{
                str += "i";
            }

            if(this.ns>1){
                str += "n";
            }else{
                str += "s";
            }

            if(this.ft>1){
                str += "f";
            }else{
                str += "t";
            }

            if(this.pj>0){
                str += "p";
            }else{
                str += "j";
            }
            return str;
        }
    }//Class Mbti END

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

    public User(String[] data, Mbti mbti){
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


    @Override
    public String toString() {
        return "[ID : " + name + ']';
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

    public User.Mbti getMbti() {
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
