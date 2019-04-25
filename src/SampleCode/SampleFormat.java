package SampleCode;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Locale;

public class SampleFormat {
    SampleFormat(int type){
        switch (type){
            case 1:
                // ex_DecimaFormat();
                ex_DateTimeFormat();
                break;
            case 2:
                break;
            default:
                System.out.println("呼叫的參數錯誤!!!");
                break;
        }
    }

    void ex_DecimaFormat(){
        int apple = 96;
        int tatal = apple * 986 * 867;
        System.out.println("總共: " + tatal + " 元");
        DecimalFormat df1 = new DecimalFormat();
        DecimalFormat df2 = new DecimalFormat("####,####,####");
        DecimalFormat df3 = new DecimalFormat("0000,0000,0000");
        System.out.println("總共: "+ df1.format(tatal) + " 元");
        System.out.println("總共: "+ df2.format(tatal) + " 元");
        System.out.println("總共: "+ df3.format(tatal) + " 元");
    }

    private static Date date = new Date();
    void ex_DateTimeFormat(){
        show(DateFormat.SHORT,DateFormat.SHORT,Locale.TAIWAN);
        show(DateFormat.MEDIUM,DateFormat.MEDIUM,Locale.TAIWAN);
        show(DateFormat.LONG,DateFormat.LONG,Locale.TAIWAN);
        show(DateFormat.FULL,DateFormat.FULL,Locale.TAIWAN);
    }
    private static void show(int dateFormat, int timeFormat, Locale locale){
        DateFormat df = DateFormat.getDateTimeInstance(dateFormat, timeFormat, locale);
        System.out.println(df.format(date));
    }
}
