import java.util.HashMap;
import org.apache.commons.lang3.builder.*;
import static java.lang.System.out;

public class SampleCode {
    public static void main(String[] args) {

        System.out.println("Hello World!");
        new SampleEquals();
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
                        appendSuper(super.equals(obj)). // 這一行可以不用，但差異就不知道了(可以傳遞比較到父類別，不懂什麼狀況)
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