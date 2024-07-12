package bitcamp.project3.vo;

import java.util.Objects;

public class Book {
    private static int seqNo;
    private String title;
    private String author;
    private String bookCategory;
    private int count;
    private int no;
    private int M;
    private int B;
    private int T;
    private int I;
    private String id;

    public Book() {
    }

    //@Override
    //public boolean equals(Object o) {
    //    if (this == o) {
    //        return true;
    //    }
    //    if (o == null || getClass() != o.getClass()) {
    //        return false;
    //    }
    //    Book book = (Book) o;
    //    return no == book.no;
    //}
    //
    //@Override
    //public int hashCode() {
    //    return Objects.hashCode(no);
    //}

    public Book(int no) {
        this.no = no;
    }

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
        this.title = title;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
