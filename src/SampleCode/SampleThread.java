package SampleCode;

public class SampleThread {

    SampleThread(int type){
        if (type == 1) {        // 實作 thread 類別
            TestThread t1 = new TestThread();
            TestThread t2 = new TestThread();
            t1.setName("T1");
            t2.setName("T2");
            t1.start();
            t2.start();
        }
        else if(type == 2) {    // 實作 Runnable 介面
            TestRunnable r1 = new TestRunnable();
            TestRunnable r2 = new TestRunnable();
            Thread t1 = new Thread(r1,"R1");
            Thread t2 = new Thread(r2,"R2");
            t1.start();
            t2.start();
        }
        else if (type == 3) {   // 例子1
            Thread father = new Thread(new FatherRunnable());
            father.start();
        }
        else if (type == 4) {   // 例子2
            Account ac = new Account(10000);
            System.out.println("帳戶原始金額:" + ac.checkAccount() + " 元");

            WithDraw r1 = new WithDraw(ac, 5000);
            WithDraw r2 = new WithDraw(ac, 2000);
            WithDraw r3 = new WithDraw(ac, 4000);

            Thread t1 = new Thread(r1,"T1");
            Thread t2 = new Thread(r2,"T2");
            Thread t3 = new Thread(r3,"T3");

            t1.start();
            t2.start();
            t3.start();
        }
        else if(type == 5){
            Cookies cookies = new Cookies();
            Put put = new Put(cookies);
            Eat eat = new Eat(cookies);
            Thread dog = new Thread(eat);
            Thread master = new Thread(put);
            dog.start();
            master.start();
        }
    }
}

// 實作 thread 類別
class TestThread extends Thread{
    public void run(){
        for (int i = 1; i < 1000; i++) {
            String tName = Thread.currentThread().getName();
            System.out.println(tName + " : " + i);
        }
    }
}

// 實作 Runnable 介面
class TestRunnable implements Runnable{
    public void run(){
        for (int i = 1; i < 1000; i++) {
            String tName = Thread.currentThread().getName();
            System.out.println(tName + " : " + i);
        }
    }
}

// 例子1
class FatherRunnable implements Runnable{
    public void run(){
        System.out.println("爸爸下班回家.");
        System.out.println("爸爸準備洗澡");
        System.out.println("發現沒瓦斯了");
        System.out.println("打電話請瓦斯工人送瓦斯");
        System.out.println("等待瓦斯工人...");

        Thread worker = new Thread(new WorkRunnable());
        worker.start();
        try {
            worker.join();
        }catch (InterruptedException ie){
            System.out.println("爸爸不洗澡了!!!");
        }
        System.out.println("爸爸開始洗澡");
        System.out.println("爸爸洗完澡了");
    }
}

class WorkRunnable implements  Runnable{
    public  void run(){
        System.out.println("工人送瓦斯中......");
        try{
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000);
                System.out.println(i + " 分鐘,");
            }
        }catch (InterruptedException ie){
            System.out.println("瓦斯工人送瓦斯途中發生意外!");
        }
        System.out.println("瓦斯工人將瓦斯送到家了");
        System.out.println("瓦斯工人將瓦斯安裝完畢");
    }
}

// 例子2(提款範例)
class WithDraw implements Runnable{
    private Account account;
    private double withdrawMoney;
    public WithDraw(Account account, double withdrawMoney){
        this.account = account;
        this.withdrawMoney = withdrawMoney;
    }

    @Override
    public void run() {
        account.withDraw(account, withdrawMoney);   // 開始提款
    }
}

class Account{
    private double balance;
    public Account(double balance){
        this.balance = balance;
    }
    public synchronized void withDraw(Account account, double withdrawMoney){
        String tName = Thread.currentThread().getName();
        System.out.println(tName + " 開始提款......");
        double tmpBalance = balance;
        // 模擬提款時系統所花的時間
        for ( double i = 0; i < 99999999; i++) ;

        tmpBalance -= withdrawMoney;
        if (tmpBalance <= 0){
            System.out.println("................");
            System.out.println("餘額不足........");
        }else {
            balance = tmpBalance;
        }
        System.out.println("列印交易單: \n欲提款金額:" + withdrawMoney + " 元 " + "帳戶餘額:" + account.checkAccount());
        System.out.println(tName + " 完成提款......");
        System.out.println("................");
    }

    public double checkAccount() {
        return balance;
    }
}

// 例子3(餅乾範例)
class Cookies{
    private int cookiesNo;
    private static volatile boolean empty = true;   // volatile:重讀該成員變數的值
    public synchronized void put(int cNo){
        while (!empty){
            try {
                wait();
            }
            catch (InterruptedException e){}
        }
        System.out.println("主人放了第 " + cNo + " 塊餅乾");
        cookiesNo = cNo;
        empty = false;
        notify();
    }

    public synchronized void eat(int cNo){
        while (empty){
            try {
                wait();
            }
            catch (InterruptedException e){}
        }
        System.out.println("小白狗吃了第 " + cNo + " 塊餅乾");
        empty = true;
       notify();
    }
}

class Eat implements Runnable{
    Cookies cookies;
    Eat(Cookies cookies){
        this.cookies = cookies;
    }
    public void run(){
        for (int i = 1; i <= 10; i++) {
            cookies.eat(i);
        }
    }
}

class Put implements Runnable{
    Cookies cookies;
    Put(Cookies cookies){
        this.cookies = cookies;
    }
    public void run(){
        for (int i = 1; i <= 10; i++) {
            cookies.put(i);
        }
    }
}


