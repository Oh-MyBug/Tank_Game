import java.io.IOException;
import java.util.Properties;

public class TestConfig {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(TestConfig.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = (String) properties.get("initTankCount");
        System.out.println(str);
    }
}
