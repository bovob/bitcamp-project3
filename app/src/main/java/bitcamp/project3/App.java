package bitcamp.project3;

import bitcamp.project3.Monitor.AdminMonitor;
import bitcamp.project3.Monitor.Membership;
import bitcamp.project3.controller.BookCommand;
import bitcamp.project3.Monitor.UserMonitor;
import bitcamp.project3.controller.BorrowCommand;
import bitcamp.project3.vo.Book;

import java.util.ArrayList;

import static bitcamp.project3.util.Prompt.close;

//                       Monitor, util.*
//                              |
//  App: Membership -----[0]AdminMonitor    --[1]BookCommand
//                                          --[2]BorrowCommand
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
//      Class Mbti {                        }                                           String mbti
//          int ei;                                                                     boolean check;
//          int ns;                                                                 }
//          int ft;
//          int pj;
//      }
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
        //더미 생성
        BookCommand.setBookList(new ArrayList<>(Book.generateDummyData()));

        // 로그인 후 회원SeqNo get
        // 관리자SeqNo : 0
        // 유저SeqNo: 1~
        // 종료 시 -1
        while(true) {
            int check = Membership.getInstance().cmd();

            //종료
            if(check==-1){
                close();
                return;
            }

            //관리자모드
            if(check==0){
                rm.adminExecute();
                continue;
            }

            //user
            um.execute(check);

        }
    }
}

