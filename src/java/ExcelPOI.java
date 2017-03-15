import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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


    public void write(ResultType resultType,String jarName,Integer rowNum) throws Exception{

        InputStream in=new FileInputStream("//Users/wubingyu/Downloads/after_review４.xls");

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook(in);

        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.getSheet("sheet");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment((Short) CellStyle.ALIGN_CENTER); // 创建一个居中格式
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("jar包名");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        cell.setCellValue("jar包上级文件名");
        cell.setCellStyle(style);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，
//	    List list = CreateSimpleExcelToDisk.getStudent();
        //
//	    for (int i = 0; i < list.size(); i++)
//	    {
//	        row = sheet.createRow((int) i + 1);
//	        Student stu = (Student) list.get(i);
//	        // 第四步，创建单元格，并设置值
//	        row.createCell((short) 0).setCellValue((double) stu.getId());
//	        row.createCell((short) 1).setCellValue(stu.getName());
//	        row.createCell((short) 2).setCellValue((double) stu.getAge());
//	        cell = row.createCell((short) 3);
//	        cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(stu
//	                .getBirth()));
//	    }

//        for(int i=0;i<list.size();i++) {
//            row = sheet.createRow((int) i + 1);
//            row.createCell(0).setCellValue(list.get(i).getName());
//            String aa[]=list.get(i).getPath().split("\\\\");
//            row.createCell(1).setCellValue(aa[aa.length-2]);
//            boolean flag=false;
//            for(int j=0;j<aa.length;j++){
//                if(aa[j].equalsIgnoreCase("sw_comps_Nms")){
//                    flag=true;
//                }
//                if(flag){
//                    row.createCell(j+2).setCellValue(aa[j]);
//                }
//            }
//        }
//        System.out.println("完成了！");

        HSSFRow rowTemp=sheet.createRow(rowNum+1);
//        rowTemp.createCell(0).setCellValue(jarName);
        if (resultType.getType()==1){
            rowTemp.createCell(3).setCellValue(resultType.getResult());
        }else{
            rowTemp.createCell(3).setCellValue("");
        }

        if (resultType.getType()==2){
            rowTemp.createCell(4).setCellValue(resultType.getResult());
        }else{
            rowTemp.createCell(4).setCellValue("");
        }

        if (resultType.getType()==3){
            rowTemp.createCell(5).setCellValue(resultType.getResult());
        }else{
            rowTemp.createCell(5).setCellValue("");
        }

        if (resultType.getType()==4){
            rowTemp.createCell(6).setCellValue(resultType.getResult());
        }else{
            rowTemp.createCell(6).setCellValue("");
        }

        // 第六步，将文件存到指定位置
        try
        {
            FileOutputStream fout = new FileOutputStream("//Users/wubingyu/Downloads/after_review４.xls");
            wb.write(fout);
            fout.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }

    }

    public static void main(String[] args) throws Exception {

        InputStream in=new FileInputStream("//Users/wubingyu/Downloads/after_review４.xls");

        ExcelPOI excelPOI=new ExcelPOI();
        JsoupTest jsoupTest =new JsoupTest();

        List<String> result=excelPOI.read(in);

        for (int i = 0; i <result.size() ; i++) {

            System.out.println("行数"+i+"："+result.get(i));
            ResultType jsoupResult=jsoupTest.readDetail(result.get(i));
            excelPOI.write(jsoupResult,result.get(i),i);
            System.out.println(jsoupResult.toString());
            System.out.println();

        }

    }


}
