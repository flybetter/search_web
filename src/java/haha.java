import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA
 * Created By Kinsey
 * Date: 2017/3/11
 * Time: 上午9:59
 */
public class haha {
    public static void main(String[] args) {

        String url = "http://www.baidu.com";

        String result = "";

        BufferedReader in=null;

        try {
            URL realUrl = new URL(url);

            URLConnection connection = realUrl.openConnection();

            connection.connect();

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = "";

            while ((line = in.readLine()) != null) {
                result+=line;

            }
        } catch (IOException e) {
            System.out.println("发出get请求出现异常："+e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("result:"+result);
    }
}
