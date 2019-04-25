package SampleCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SampleRegex {
    SampleRegex(int type){
        switch (type){
            case 1:
                // ex_Matcher1();
                // ex_Matcher2();
                ex_Matcher3();
                break;
            case 2:
                break;
            default:
                System.out.println("呼叫的參數錯誤!!!");
                break;
        }
    }
    void ex_Matcher1(){
        Pattern ptn = Pattern.compile("Java [EMS]{2}.*",Pattern.CASE_INSENSITIVE);

        Matcher mch1 = ptn.matcher("Java SE 5.0");
        Matcher mch2 = ptn.matcher("Java eE 1.4.2");
        Matcher mch3 = ptn.matcher("Java me 1.0");
        Matcher mch4 = ptn.matcher("Java S 9.0");
        Matcher mch5 = ptn.matcher("Java AB 8.0");
        System.out.println(mch1.matches());
        System.out.println(mch2.matches());
        System.out.println(mch3.matches());
        System.out.println(mch4.matches());
        System.out.println(mch5.matches());
    }
    // find() replaceAll()
    void ex_Matcher2(){
        Pattern ptn = Pattern.compile("saw",Pattern.CASE_INSENSITIVE);

        Matcher mch = ptn.matcher("I saw a saw");
        while (mch.find()){
             System.out.println(mch.start() + "-" + (mch.end() - 1));
        }
        String newStr = mch.replaceAll("yyy");
        System.out.println(newStr);
    }
    // split() : limit是回傳陣列的上限數
    void ex_Matcher3(){
        Pattern ptn = Pattern.compile("[:/.]+");
        String[] token1, token2, token3;
        token1 = ptn.split("http://vincentjava.idv.tw");
        for (String token: token1) {
            System.out.println(token);
        }
        System.out.println("--------------------------------------");
        token2 = ptn.split("http://vincentjava.idv.tw",2);
        for (String token: token2) {
            System.out.println(token);
        }
        System.out.println("--------------------------------------");
        token3 = ptn.split("http://vincentjava.idv.tw",100);
        for (String token: token3) {
            System.out.println(token);
        }
    }
}
