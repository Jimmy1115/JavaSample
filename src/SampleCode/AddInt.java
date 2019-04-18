package SampleCode;

import java.io.Serializable;

public class AddInt implements Serializable{
    private static final long serialVersionUID = 1;
    private int sum = 0;    // private transient int sum = 0; // 不會加入序列中
    public void calc(int... c){
        for (int i = 0; i < c.length; i++) {
            sum += c[i];
        }
    }
    public int getSum(){
        return sum;
    }
}
