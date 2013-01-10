package d.t.u.x;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class IO {

    public static Properties loadProperties(InputStream in) {
        try {
            Properties p = new Properties();

            p.load(in);

            return p;
        } catch (IOException e) {
            return null;
        }
    }
}
