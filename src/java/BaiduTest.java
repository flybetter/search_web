import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA
 * Created By Kinsey
 * Date: 2017/3/11
 * Time: 上午10:11
 */
public class BaiduTest {

    public String send(String url){

        String result="";

        BufferedReader in=null;

        try {
            URL realUrl=new URL(url);

           URLConnection connection =realUrl.openConnection();

           connection.connect();

           in=new BufferedReader(new InputStreamReader(connection.getInputStream()));

           String line=null;

           while((line=in.readLine())!=null){

               result+=line;
           }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (in!=null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    };


    public String regexString(String targetStr,String patternStr){
        Pattern pattern=Pattern.compile(patternStr);

        Matcher matcher=pattern.matcher(targetStr);

        if (matcher.find()){
            return matcher.group(0);
        }else{
            return "not find";
        }

    }

    public static void main(String[] args) {
//        String url="http://mvnrepository.com/search?q=spring";
//        BaiduTest test=new BaiduTest();
//        String result=test.send(url);

      //  String imgSrc = test.regexString(result, "src=(.+?)>");
      //  System.out.println(imgSrc);

        boolean flag=Pattern.matches("^\\d[\\d\\.]+\\d$","2.1");

        System.out.println(flag);

    }
}
