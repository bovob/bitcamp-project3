package bitcamp.project3.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class GetHtml {
    static final String urlToRead = "https://www.readersnews.com/news/articleView.html?idxno=105991";

    public static void getDescription(String data){
        String[] des = stringSplitPeriod(data);
        for(String str : des){
            System.out.printf("%s\n", str);
        }
    }

    public static void getTitle(String data){
        System.out.printf("::  %s  ::\n",data);
    }

    public static void getMbtiDescription(String mbti){
        String[] data = getTypeDescriptuon(mbti);

        getTitle(Objects.requireNonNull(data)[0]);
        getDescription(data[1]);
    }

    private static String[][] printData(){
        String url = getHttpHTML(urlToRead);
        String[] mbti = stringSplitStartP(url);  //1~16
        String[][] mbtiFormat = new String[mbti.length][];
        String[][] data = new String[mbtiFormat.length][];
        int i = 0;

        for(String s : mbti){
            mbtiFormat[i++] = stringSpliteEndP(s);
        }

        i=0;
        for(String[] s: mbtiFormat){
            data[i++] = stringSplitTitle(s[0]);
        }

        //data 1st index: 0~15 type
        //  1: intj     5: infj     09: istj    13: istp
        //  2: intp     6: infp     10: isfj    14: isfp
        //  3: entj     7: enfj     11: estj    15: estp
        //  4: entp     8: enfp     12: esfj    16: esfp
        //     2st index: 0 = title
        //                1 = data
        return data;
    }

    private static String getHttpHTML(String urlToRead) {
        URL url;
        HttpURLConnection conn;
        BufferedReader rd;
        String line;
        String result = "";
        try {
            url = new URL(urlToRead);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((line = rd.readLine()) != null) {
                result += line + "\n";
            }
            rd.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    private static String[] stringSplitStartP(String str){
        return str.split("<p><strong>");
    }

    private static String[] stringSpliteEndP(String str){
        return str.split("</p>");
    }

    private static String[] stringSplitTitle(String str){
        return str.split("</strong><br />");
    }

    private static String[] stringSplitPeriod(String str){
        return str.split("\\. ");
    }


    //  1: intj     5: infj     09: istj    13: istp
    //  2: intp     6: infp     10: isfj    14: isfp
    //  3: entj     7: enfj     11: estj    15: estp
    //  4: entp     8: enfp     12: esfj    16: esfp
    private static String[] getTypeDescriptuon(String mbti){
        String[][] data = printData();

        switch (mbti){
            case"intj":
                return data[1];
            case"intp":
                return data[2];
            case"entj":
                return data[3];
            case"entp":
                return data[4];
            case"infj":
                return data[5];
            case"infp":
                return data[6];
            case"enfj":
                return data[7];
            case"enfp":
                return data[8];
            case"istj":
                return data[9];
            case"isfj":
                return data[10];
            case"estj":
                return data[11];
            case"esfj":
                return data[12];
            case"istp":
                return data[13];
            case"isfp":
                return data[14];
            case"estp":
                return data[15];
            case"esfp":
                return data[16];
            default:
                return null;
        }
    }


}
