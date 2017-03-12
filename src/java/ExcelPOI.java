import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA
 * Created By Bingyu wu
 * Date: 2017/3/11
 * Time: 下午9:21
 */
public class ExcelPOI {

    public List<String> read(InputStream in) throws  Exception{

        List<String> result=new ArrayList<String>();

        HSSFWorkbook workbook=new HSSFWorkbook(in);

        HSSFSheet sheet=workbook.getSheetAt(0);

        int rowNum=sheet.getPhysicalNumberOfRows();

        for (int i = 1; i < rowNum; i++) {

            HSSFRow row=sheet.getRow(i);

            String text =row.getCell(1).getStringCellValue().replaceAll(".jar","");

            String [] tempSplit=text.split("-");

            if(tempSplit.length>1){
                Boolean flag=Pattern.matches("^\\d[\\d\\.]+\\d$", tempSplit[tempSplit.length-1]);

                if (flag){
                    text=text.replaceAll("-"+tempSplit[tempSplit.length-1],"");
                }

            }

            result.add(text);
        }

        return  result;
    }


    public void write(String path,ResultType resultType,int rowNum) throws Exception{

        InputStream in=new FileInputStream(path);

        HSSFWorkbook workbook=new HSSFWorkbook(in);

        in.close();

        FileOutputStream out=new FileOutputStream(path);

        HSSFSheet sheet=workbook.getSheetAt(0);

        sheet.getRow(1).getCell(4).setCellValue("haha");

        workbook.write(out);

        out.close();

    }

    public static void main(String[] args) throws Exception {

        InputStream in=new FileInputStream("//Users/wubingyu/Downloads/after_review４.xls");

        ExcelPOI excelPOI=new ExcelPOI();
//        JsoupTest jsoupTest =new JsoupTest();
//
//        List<String> result=excelPOI.read(in);
//
//        for (int i = 0; i <result.size() ; i++) {
//
//            System.out.println("行数"+i+"："+result.get(i));
//            ResultType jsoupResult=jsoupTest.readDetail(result.get(i));
//            System.out.println(jsoupResult.toString());
//
//            System.out.println();
//
//
//        }
        String path="//Users/wubingyu/Downloads/after_review４.xls";

        excelPOI.write(path,null,4);


    }


}
