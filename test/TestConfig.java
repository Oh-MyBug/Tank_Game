import com.ohmybug.tank.PropertyMgr;

public class TestConfig {
    public static final int w = Integer.parseInt(PropertyMgr.get("gameHeight"));
    public static void Main(){
        System.out.println(w);
    }
    public static void main(String[] args) {
        TestConfig.Main();
    }
}
