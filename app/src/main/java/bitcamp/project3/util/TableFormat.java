package bitcamp.project3.util;

import java.io.UnsupportedEncodingException;
import java.util.regex.Pattern;

public class TableFormat {
    //가로선 출력
    //너비 배열 입력 시 가로선 출력 =>Prompt 이동 예정
    //  +----+---------------+---------------+---------------+
    //width: 가로 너비
    public static String printTableLine(int[] width){
        StringBuilder str;

        //start "+"
        str = new StringBuilder("+");
        // str += "----+"
        for(int i=0 ; i<width.length ; i++) {
            str.append("-".repeat(width[i]));
            str.append("+");
        }
        //end "\n"
        str.append("\n");

        return str.toString();
    }//Method printLine END



    //표 내용 출력
    //너비 배열과 데이터 입력 시 표에 맞게 출력
    //for(printUserFormat)사용하여 한줄 출력 가능 =>Prompt 이동 예정
    //  "|root           "
    //width: 가로길이
    //data(String): 정보
    public static String printTableDataFormat(int width, String data){
        String str = "";
        //start "|"
        str = "|" +
                String.format(koreanFormat(width, data), data);

        return str;
    }//Method printUserFormat END


    //format(koean)
    public static String koreanFormat(int width, String data){
        int titleLen = getlengthWord(data);
        int cnt = getCountWord(data);
        String str ="";

        str = "%-"+ (width-cnt) +"s";
        return str;

    }

    public static int getCountWord(String word){
        char[] arr = word.toCharArray();
        int cnt = 0;

        for(char w : arr){
            if(Pattern.matches("^[가-힣]*$", String.format("%c", w) ) ){
                cnt++;
            }
        }

        return cnt;
    }

    //check byte(korean)
    public static int getlengthWord(String word){
        if(!getType(word)) {
            try {
                return word.getBytes("euc-kr").length;
            } catch (UnsupportedEncodingException e) {
                return 0;
            }
        }
        return 0;
    }

    //get type(korean?)
    public static boolean getType(String word){
        return !Pattern.matches("^[가-힣]*$", word);
    }

}
