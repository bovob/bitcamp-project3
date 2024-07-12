package bitcamp.project3.vo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Borrow {
    private int no;
    private static int seqNo;
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;

    public Borrow(){};

    public Borrow(int no){
        this.no = no;
    };

    public Borrow(LocalDate startDate) {
        this.startDate = startDate;
        this.endDate = startDate.plusDays(7);
    }

    public static int getNextSeqNo() {
        return ++seqNo;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // 대출 기간을 연장하는 메서드
    public void extendBorrow(int days) {
        this.endDate = this.endDate.plusDays(days);
    }

    // 현재 대출 기간을 계산하는 메서드
    public long getBorrowPeriod() {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
}