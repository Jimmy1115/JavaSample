package SampleCode;

import java.util.Scanner;

public class SampleScanner {
    SampleScanner(int type){
        switch (type){
            case 1:
                ex_Scanner1();
                break;
            case 2:
                break;
            default:
                System.out.println("呼叫的參數錯誤!!!");
                break;
        }
    }
    void ex_Scanner1(){
        // Scanner sc = new Scanner(System.in);
        // Scanner sc = new Scanner("100 java");
        Scanner sc = new Scanner("100,java");
        sc.useDelimiter(",");   // 設定分隔符號
        System.out.println("請輸入數字:");
        int i = sc.nextInt();
        System.out.println("請輸入字串:");
        String str = sc.next();
        System.out.println("輸入的數字是:" + i);
        System.out.println("輸入的字串是:" + str);
        sc.close();
    }
}
