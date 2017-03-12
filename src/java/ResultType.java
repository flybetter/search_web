/**
 * Created with IntelliJ IDEA
 * Created By Bingyu wu
 * Date: 2017/3/12
 * Time: 上午11:15
 */
public class ResultType {


    //1 detail information
    //2 no information
    //3 maven page no result
    //4 error
    public Integer type;

    public String result="";

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResultType{" +
                "type=" + type +
                ", result='" + result + '\'' +
                '}';
    }
}
