package bitcamp.project3.vo;

public class Book {
    private static int seqNo;

    private String title;
    private String author;
    private int count;
    private int no;
    private int M;
    private int B;
    private int T;
    private int I;

    public static int getNextSeqNo() {
        return ++seqNo;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    public int getT() {
        return T;
    }

    public void setT(int t) {
        T = t;
    }


    public int getI() {
        return I;
    }

    public void setI(int i) {
        I = i;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

    }
}
