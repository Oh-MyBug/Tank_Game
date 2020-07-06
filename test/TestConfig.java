import com.ohmybug.tank.Direction;
import com.ohmybug.tank.PropertyMgr;

public class TestConfig {
    public static final int w = Integer.parseInt(PropertyMgr.get("gameHeight"));
    public static void Main(){
        System.out.println(w);
    }
    public static void main(String[] args) {
        System.out.println(Direction.values()[0]);
        System.out.println(Direction.values()[1]);
        System.out.println(Direction.values()[2]);
        System.out.println(Direction.values()[3]);
    }
}
