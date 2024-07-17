package bitcamp.project3.controller;

import bitcamp.project3.util.Prompt;
import bitcamp.project3.vo.User;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

import static bitcamp.project3.util.Prompt.lightSkyBlueColorCode;
import static bitcamp.project3.util.Prompt.resetColorCode;
import static bitcamp.project3.util.SystemMsg.*;



public class MbtiCommand implements Command{
    private static final String PATH = "C:\\Users\\BIT\\git\\bitcamp-project3\\app\\doc\\mbtiTest.txt";
//    private static final String PATH = "C:\\Users\\BITCAMP\\git\\bitcamp-project3\\app\\doc\\mbtiTest.txt";
    private static final File F = new File(PATH);
    private static ArrayList<String> F_list = new ArrayList<>();
    private static LinkedList<Score> scoreList = new LinkedList<>();



    class Score{
        private String sort;
        private int score;

        Score(String sort, int score){
            this.sort = sort;
            this.score = score;
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

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }





    ///////////////////////////////////////////////////////////
    ////////////////////// Constructor ////////////////////////
    ///////////////////////////////////////////////////////////
    public MbtiCommand(){
        cmd();
    }




    ///////////////////////////////////////////////////////////
    ////////////////////// getInstance() //////////////////////
    ///////////////////////////////////////////////////////////
    private static MbtiCommand mc;

    // setup MbtiCommand Instance
    public static MbtiCommand getInstance() {

        if (mc == null) {
            mc = new MbtiCommand();
        }

        return mc;
    }// Method getInstance END

    // reset MbtiCommand Instance
    public static void freeInstance() {
        mc = null;
    }// Method freeInstance END






    ///////////////////////////////////////////////////////////
    ///////////////////////// Method //////////////////////////
    ///////////////////////////////////////////////////////////
    @Override
    public void cmd() {
        File[] contents = F.listFiles();
        try{
            BufferedReader br = new BufferedReader(new FileReader(F));

            String str = br.readLine();
            while(str != null){
                F_list.add(str);
                str = br.readLine();
            }

            br.close();
        } catch (NullPointerException e){ // null이 있을 경우
            e.getStackTrace();
        } catch (FileNotFoundException e){ // 파일을 찾을 수 없는 경우
            e.getStackTrace();
        } catch (IOException e){ // 파일 읽기 중 에러가 발생한 경우
            e.getStackTrace();
        }
    }

    @Override
    public void printTUI() {

    }

    public void printTUI(User.Mbti mbti) {
        int ans = -1;
        for(int i=0; i<F_list.size();i+=3){

            while(ans==-1){

                System.out.print(printQuestionTUI(i));
                ans = getUserIndex();
                if(ans>0) {
                    scoreList.add(getScore(i, ans));
                }else{
                errorNumberLimitException();
                }

            }
            ans = -1;
        }

        for(Score score : scoreList){
            setScore(mbti, score);
        }
    }

    private String printQuestionTUI(int index){
        return String.format( "Q%-2d ", (index/3)+1 )+F_list.get(index)+"\n"+
              "[1] "+F_list.get(index+1).substring(3)+"\n"+
              "[2] "+F_list.get(index+2).substring(3)+"\n";
    }

    private int getUserIndex(){
        try {
            int ans = Prompt.inputInt("> ");

            return (ans==1||ans==2) ? ans:-1;
        }catch (NumberFormatException e){
//            printNumberFormatException();
        }
        return -1;
    }

    private Score getScore(int index, int userAns){
        String[] ans = {F_list.get(index+1), F_list.get(index+2)};
        String sort;
        int score;
        try {
            sort = ans[userAns-1].substring(0,1);
            score = Integer.parseInt(ans[userAns-1].substring(1,2));

            return new Score(sort, score);

        } catch(StringIndexOutOfBoundsException e) {
            System.out.println("[Error] "+e.getMessage());
        }
        return null;
    }

    private void setScore(User.Mbti mbti, Score score){
        String sort = score.getSort();
        int result = score.getScore();

        switch (sort){
            case "e":
                mbti.setEi(result);
                break;
            case "n":
                mbti.setNs(result);
                break;
            case "f":
                mbti.setFt(result);
                break;
            case "p":
                mbti.setPj(result);
                break;
            default:
                System.out.print(lightSkyBlueColorCode+"Check Read File Function...\n"+resetColorCode);
        }
    }


    @Override
    public void create() {

    }

    @Override
    public void read() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {


    }


    private boolean isExistsFile(){
        if(!F.exists()){
            System.out.print(lightSkyBlueColorCode+"해당 경로에 아래 파일을 다운받아주세요.\n" +
                    PATH+resetColorCode);
            return false;
        }
        return true;
    }
}//Class MbtiCommand END
