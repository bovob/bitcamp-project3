package bitcamp.project3.vo;

import bitcamp.project3.DummyData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book {
    public static int seqNo = 0;
    private String title;
    private String author;
    private String bookCategory;
    private int no;
    private String mbti="istj";
    private boolean check;

    public Book() {
        this.no = ++seqNo;
    }

    public Book(int no){
        this.no = no;
        if (no > seqNo){
            seqNo = no;
        }
    }

    public Book(String[] data){
        this.title = data[0];
        this.author = data[1];
        this.bookCategory = data[2];
        this.mbti = data[3];
    }

    //더미생성
    public static List<Book> generateDummyData() {
        List<Book> booklist = new ArrayList<>();
        String[] categories = {"소설", "과학", "역사", "자기계발", "철학"};
        String[] authors = {"김작가", "이저자", "박문학", "최과학", "정역사"};

        booklist.addAll(DummyData.addDummyBook());

        setSeqNo(32);
        return booklist;
    }
    //더미seqNo 이후 설정
    public static void setSeqNo(int no) {
        seqNo = no;
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

    public void setNextSeqNo(){ this.seqNo += 1; }

    public static int getNextSeqNo() {
        return ++seqNo;
    }


    public String getMbti() {
        return mbti;
    }

    public void setMbti(String mbti) {
        this.mbti = mbti;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
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
