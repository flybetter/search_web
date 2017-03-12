import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA
 * Created By Bingyu wu
 * Date: 2017/3/11
 * Time: 下午10:27
 */
public class JsoupTest {

    public String readAll(String url) {

        String result="";

        Document document= null;
        try {
            document = Jsoup.connect(url).timeout(120000).get();
        } catch (IOException e) {
            return "readAll Exception:"+e.getMessage();
        }

        Elements elements=document.select("div.im");

        for (Element element:elements){

            result+=element.text()+"\n\n";
        }

        return result;
    };


    public ResultType readDetail(String jarName){

        String url="http://mvnrepository.com/search?q="+jarName;

        String result="";

        ResultType resultType=new ResultType();

        Document document= null;
        try {
            document = Jsoup.connect(url).timeout(120000).get();
        } catch (IOException e) {
            resultType.setType(4);
            resultType.setResult("readDetail Exception:"+e.getMessage());
        }

        Elements elements=document.select("p.im-subtitle");

        Boolean detailFlag=false;

        if ( elements.isEmpty()){

            resultType.setType(3);
            resultType.setResult("maven page not information");

            return resultType;

        }


        for(Element element:elements){

            Elements elements2=element.select("a");

            for (Element element2:elements2){

                if (element2.text().equalsIgnoreCase(jarName)){

                    String elem2Href=element2.attr("href");

//                    System.out.println("elem2Href:"+elem2Href);

                    result=this.readSecond(elem2Href,jarName);

                    resultType.setType(1);
                    resultType.setResult(resultType.getResult()+result);

                    detailFlag=true;

                }

            }

        }

        if(!detailFlag){

            result=this.readAll(url);
            resultType.setType(2);
            resultType.setResult(result);

        }

        return  resultType;
    }





    public  String readSecond(String url,String jarName) {

        url="http://mvnrepository.com"+url;

//        System.out.println(url);

        Document document= null;
        try {
            document = Jsoup.connect(url).timeout(120000).get();
        } catch (IOException e) {

            return "readSecond Exception:"+e.getMessage();
        }

        String description=document.select("div.im-description").get(0).text();

        String title=document.select("h2.im-title").get(0).text();

        String body="";
        try {
            body=document.select("table.grid").get(0).text();
        } catch (Exception e) {
            body="error";
        }

//        System.out.println("document:"+document.toString());

//        System.out.println("title:"+title);
//        System.out.println("description:"+description);
//        System.out.println("body:"+body);

        String result="";

        result="title:"+title+"\n";
        result+="description:"+description+"\n";
        result+="body:"+body+"\n\n";


        return result;
    }

    public static void main(String[] args) throws Exception {
        JsoupTest jsoupTest=new JsoupTest();

        ResultType result=jsoupTest.readDetail("crimson");

        System.out.println(result.toString());
//        jsoupTest.readSecond("artifact/org.jsoup/jsoup","jsoup");

    }
}
