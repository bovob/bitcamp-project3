package bitcamp.project3.vo;

import bitcamp.project3.util.Prompt;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Book {
    private static int seqNo;
    private String title;
    private String author;
    private String bookCategory;
    private int count;//미구현
    private int no;
    private int M;
    private int B;
    private int T;
    private int I;
    private boolean check;

    public Book() {}

    public Book(int no) {
        this.no = no;
    }

    //더미생성
    public static List<Book> generateDummyData(int count) {
        List<Book> booklist = new ArrayList<>();
        String[] categories = {"소설", "과학", "역사", "자기계발", "철학"};
        String[] authors = {"김작가", "이저자", "박문학", "최과학", "정역사"};

        for (int i = 0; i < count; i++) {
            Book book = new Book();
            book.setNo(i+1);
            book.setTitle("책 제목 " + (i + 1));
            book.setAuthor(authors[i]);
            book.setBookCategory(categories[i]);
            book.setM(i);
            book.setB(i);
            book.setT(i);
            book.setI(i);
            booklist.add(book);
        }
        return booklist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return no == book.no;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(no);
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


    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }


}
