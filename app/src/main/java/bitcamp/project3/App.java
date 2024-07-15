package bitcamp.project3;

import bitcamp.project3.Monitor.AdminMonitor;
import bitcamp.project3.Monitor.Membership;
import bitcamp.project3.controller.BookCommand;
import bitcamp.project3.controller.BorrowCommand;
import bitcamp.project3.controller.ReturnCommand;
import bitcamp.project3.util.Prompt;
import bitcamp.project3.Monitor.UserMonitor;
import bitcamp.project3.vo.Book;
import bitcamp.project3.vo.Borrow;
import bitcamp.project3.vo.User;
import java.util.LinkedList;
import java.util.List;

import java.time.LocalDate;

//                       Monitor, util.*
//                              |
//  App: Membership -----[0]AdminMonitor    --[1]BookCommand
//                                          --[2]
//                                          --[3]UserCommand
//
//
//                              |
//                  -----[1]UserMonitor     --[1]BorrowCommand
//                                          --[2]ReturnCommand
//                                          --[3]UserCommand        --MbtiCommand
//
//
//
//  Class User {                            Class Borrow {                          Class Book {
//      String name;                            int no;                                 static int seqNo;
//      String id;                              static int seqNo;                       String title;
//      String pw;                              LocalDate startDate;                    String author;
//      Mbti mbti;                              LocalDate endDate;                      String bookCategory;
//      int takeCnt;                            String title;                           int count;
//                                              String user;                            int no;
//      Class Mbti {                        }                                           int M;
//          int ei;                                                                     int B;
//          int ns;                                                                     int T;
//          int ft;                                                                     int I;
//          int pj;                                                                     boolean check;
//      }                                                                           }
//  }
//

public class App {

    static AdminMonitor rm = AdminMonitor.getInstance();
    static UserMonitor um = UserMonitor.getInstance();
    static BookCommand bc = BookCommand.getInstance();

    public App(){
        //rm.borrowCommand = new BorrowCommand("대출관리", bc.getBookList());

    }

    // Main
    public static void main(String[] args) {
        App app = new App();

        // 로그인 후 회원SeqNo get
        // 관리자SeqNo : 0
        // 유저SeqNo: 1~
        // 로그인 실패 시 -1, INPUT으로 따로 1/2 관리자 유저 접속
        int check = Membership.getInstance().cmd();

        if(check==0){
            check = 1;
        }
        else if(check>0){
            check = 2;
        }
        else {
            check = Prompt.inputInt("[임시]관리자 1/유저 2");
        }

        switch (check) {
            case 1:
                rm.adminExecute();
                break;
            case 2:
                um.execute();
                break;
        }
    }
}

