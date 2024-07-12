package bitcamp.project3.util;

import bitcamp.project3.controller.UserCommand;
import bitcamp.project3.vo.User;

import java.util.Iterator;


public class Membership {
    String id;
    String pw;
    UserCommand uc = UserCommand.getInstance();

    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////
    Membership(){
        this.id = "user";       //default
        this.pw = "0000";   //default
    }




    ///////////////////////////////////////////////////////////
    ////////////////////// getInstance() //////////////////////
    ///////////////////////////////////////////////////////////
    private static Membership m;

    // setup Membership Instance
    public static Membership getInstance() {

        if (m == null) {
            m = new Membership();
        }

        return m;
    }// Method getInstance END

    // reset Membership Instance
    public static void freeInstance() {
        m = null;
    }// Method freeInstance END







    ///////////////////////////////////////////////////////////
    ///////////////////////// Method //////////////////////////
    ///////////////////////////////////////////////////////////
    public int cmd() {
        return membershipMenu();
    }

    private int getLoginUserNo(){
        Iterator<User> iter = uc.getUserList().iterator();
        User currentUser = null;
        int userNo = 0;
        while(iter.hasNext()) {
            currentUser = iter.next();

            if (this.id.equals(currentUser.getId()))
                return userNo;
            userNo++;
        }
        return -1;
    }

    private String membershipGuide(){
        String str = "";

        str += printMembershipTUI();
        str += "[1] 로그인\n[2] 회원가입\n[0] 종료\n";

        return str;
    }

    private String printMembershipTUI(){
        String str = "";

        str = "[관리자] ID:root PW:0000\n"+
                "[유저] ID:user PW:0000\n";
        return str;
    }

    private int membershipMenu(){
        int ans; // user answer

        for (;;) {
            System.out.print(membershipGuide());
            ans = Prompt.inputInt("");

            switch (ans){
                case 1: //login
                    if(login()){
                        return getLoginUserNo();
                    }else{
                        continue;
                    }
                case 2: //join
                    uc.create();
                    continue;
                case 0:
                    return -1;
                default:
            }
        }
    }//Method Menu END


    private boolean login(){
        String id = ""; // user answer id
        String pw = ""; // user answer pw

        id = Prompt.input("ID : ");
        if(EqualUserID(id)){
            pw = Prompt.input("PW : ");

            if(EqualUserPW(pw)){
                this.id = id;
                this.pw = pw;

                return true;
            }
        }

        this.id = "";
        this.pw = "";
        return false;
    }//Method login END


    private boolean EqualUserID(String id) {
        Iterator<User> iter = uc.getUserList().iterator();
        User currentUser = null;
        while(iter.hasNext()) {
            currentUser = iter.next();

            if (id.equals(currentUser.getName()))
                return true;
        }
        return false;
    }//Method EqualUserID END


    private boolean EqualUserPW(String pw){
        Iterator<User> iter = uc.getUserList().iterator();
        User currentUser = null;
        while(iter.hasNext()) {
            currentUser = iter.next();

            if (pw.equals(currentUser.getPw()))
                return true;
        }
        return false;
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
}
