import java.util.HashMap;
import org.apache.commons.lang3.builder.*;
import static java.lang.System.out;

public class SampleCode {
    public static void main(String[] args) {

        System.out.println("Hello World!");
        new SampleEquals();
        new SampleThrows(1);
    }
}

// 覆寫equals() 和 HashCode()
class SampleEquals {
    String tradeMake;
    String kind;
    String color;
    SampleEquals(){
        sampleEqualsRun();
    }
    SampleEquals(String t, String k, String c){
        tradeMake = t;
        kind = k;
        color = c;
    }

    void sampleEqualsRun(){
        SampleEquals se1 = new SampleEquals("斯伯通","籃球", "紅");
        SampleEquals se2 = new SampleEquals("斯伯通","籃球", "紅");

        out.println("se1.equals(se2) = " + se1.equals(se2));
        out.println("se1.hashCode() = " + se1.hashCode());
        out.println("se2.hashCode() = " + se2.hashCode());
        HashMap h = new HashMap();
        h.put(new SampleEquals("斯伯通","籃球", "紅"), "ABC");
        out.println("HashMap 元素內容:");
        out.println(h.get(new SampleEquals("斯伯通","籃球", "紅")));
    }

    // 覆寫equals()
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj != null && getClass() ==obj.getClass()){
            if (obj instanceof  SampleEquals){
                SampleEquals se = (SampleEquals)obj;
                // 要import org.apache.commons.lang3.builder.EqualsBuilder;
                return new EqualsBuilder().
                        //appendSuper(super.equals(obj)). // 這一行有問題，可以不用，但差異就不知道了(可以傳遞比較到父類別，不懂什麼狀況)
                        append(tradeMake,se.tradeMake).
                        append(kind,se.kind).
                        append(color,se.color).
                        isEquals();
                // 可以不 import
                /*if (tradeMake.equals(se.tradeMake) &&
                        kind.equals(se.kind) &&
                        color.equals(se.color)){
                    return true;
                }*/
            }
        }
        return false;
    }

    // 覆寫HashCode
    // 要import org.apache.commons.lang3.builder.HashCodeBuilder;
    public int hashCode(){
        return new HashCodeBuilder(17, 37).
                append(tradeMake).      // 要計算的變數
                append(kind).
                append(color).
                toHashCode();
    }
}

class MemberIDException extends Exception{
    public MemberIDException(String mID){
        super("會員證號 " + mID + " 驗證失敗 !");
    }
    public void contactWith(){
        out.println("請連絡服務人員: ");
    }
}
// throw自定例外方法
class SampleThrows{
    static int numerator = 20;
    static int[] denominator = {0, 2, 4};
    static String answer;

    SampleThrows(int type){
        if (type == 1) {
            try {
                calc(1);
            } catch (Exception e) {
                out.println("錯誤訊息: " + e.getMessage());
            }
            out.println(numerator + " / " + denominator[0] + " = " + answer);
            out.println("計算完畢");
        }else if (type == 2) {
            try{
                checkMemberID("14566789");
            }
            catch (MemberIDException e){
                out.println("錯誤訊息: " + e.getMessage());
                e.contactWith();
            }
        }
    }

    public static void calc(int index) throws Exception{
        double ans = 0;
        if (index == 0 || index >= denominator.length){
            answer = "無法計算";
            throw new Exception("denominator[] 的索引值" + "不得為 " + index);
        }
        ans = numerator / denominator[index];
        answer = String.valueOf(ans);
    }
    public static void checkMemberID(String mID) throws MemberIDException {
        if (mID.length() != 5) {
            throw new MemberIDException(mID);
        }
    }
}