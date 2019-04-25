package SampleCode;

import java.io.File;
import java.io.IOException;
import java.util.Formatter;

public class SampleFormatter {
    SampleFormatter(int type){
        switch (type){
            case 1:
                // ex_format1();
                // ex_format2();
                ex_format3();
                break;
            case 2:
                break;
            default:
                System.out.println("呼叫的參數錯誤!!!");
                break;
        }
    }

    void ex_format1(){
        Formatter f = new Formatter(System.out);
        f.format("圓週率: %f" ,Math.PI);
    }
    void ex_format2(){
        StringBuffer sb = new StringBuffer("圓週");
        Formatter f = new Formatter(sb);
        f.format("率: %f" ,Math.PI);
        System.out.println(sb);
    }
    void ex_format3(){
        try{
            Formatter f = new Formatter(new File("Foo.txt"));
            f.format("圓週率: %f" ,Math.PI);
            f.close();
        }
        catch (IOException e){}
    }
}
