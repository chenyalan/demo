package com.andlinks.mybatis.utils;

import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈亚兰 on 2018/3/31.
 * poi 针对Excel
 */
public class ExcelUtils {
    private static final String excel2003=".xls";
    private static final String excel2007=".xlsx";

    //指定一个目录，将本目录及子目录下的所有文件放在同一个文件夹里
    public static void putAllFilesInOneDirectory(String path,String toPath) throws IOException {
        List<File> listFiles=new ArrayList<>();
        File file=new File(path);
        File[] files=file.listFiles();// listFiles会把这个目录下的所有罗列，包括子文件夹
        if(files==null){
            return;
        }
        //将所有文件存放在listFiles数组里
        for(File f:files){
            if(f.isFile()){
                if(f.getName().toLowerCase().endsWith(".xlsx")||f.getName().toLowerCase().endsWith(".xls")){
                    listFiles.add(f);
                }
            }else if(f.isDirectory()){
                putAllFilesInOneDirectory(f.getAbsolutePath(),toPath);//递归
            }
        }
        for(File f:listFiles){
            InputStream in=new FileInputStream(f);
            Workbook workbook=getWorkBook(in,f.getName());
            FileOutputStream fo=new FileOutputStream(toPath+f.getName());
            workbook.write(fo);
        }
    }

    public static void getFiles(String filePath) throws IOException {
        File root=new File(filePath);
        File[] files=root.listFiles();
        for(File file:files){
            InputStream in=new FileInputStream(file);
            Workbook workbook=getWorkBook(in,file.getName());
            String fileType=file.getName().substring(file.getName().lastIndexOf("."));
            workbook=readSheet(workbook);
        }
    }

    public static Workbook readSheet(Workbook workbook) {
        Sheet sheet=workbook.getSheetAt(0);
        //某一行字段
        Row row=sheet.getRow(0);
        for(Cell c:row){
            System.out.print(getCellValue(c));
        }
        return workbook;
    }

    /**根据后缀名生成WorkBook**/
    public static Workbook getWorkBook(InputStream in,String name) throws IOException {
        String fileType=name.substring(name.lastIndexOf("."));//.xls Or .xlsx
        Workbook wb=null;
        if(fileType.endsWith(excel2003)){
            wb=new HSSFWorkbook(in);
        }else if(fileType.endsWith(excel2007)){
            wb=new XSSFWorkbook(in);
        }
        return wb;
    }

    public static String getCellValue(Cell cell){

        if(cell == null) return "";

        if(cell.getCellType() == Cell.CELL_TYPE_STRING){

            return cell.getStringCellValue();

        }else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){

            return String.valueOf(cell.getBooleanCellValue());

        }else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){

            return cell.getCellFormula() ;

        }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){

            return String.valueOf(cell.getNumericCellValue());

        }
        return "";
    }

    //apache poi 插入图片至Excel
    public static Workbook createPictureInExcel(String picturePath,Workbook workbook,Sheet sheet) throws IOException {
        InputStream is=new FileInputStream(picturePath);
        byte[] bytes= IOUtils.toByteArray(is);
        int picture=workbook.addPicture(bytes,Workbook.PICTURE_TYPE_JPEG);
        CreationHelper helper=workbook.getCreationHelper();
        Drawing drawing=sheet.createDrawingPatriarch();
        ClientAnchor anchor=helper.createClientAnchor();
        //图片插入坐标
        anchor.setCol1(1);
        anchor.setRow1(1);
        anchor.setCol2(9);
        anchor.setRow2(19);
        //插入图片
        Picture pict=drawing.createPicture(anchor,picture);
        pict.resize();
        return workbook;
    }
    public static void main(String[] args) throws IOException {
        File file=new File("C:\\Users\\Administrator\\Desktop\\picture.xlsx");
        InputStream in=new FileInputStream(file);
        Workbook workbook=getWorkBook(in,file.getName());
        Sheet sheet=workbook.getSheetAt(0);
        workbook=ExcelUtils.createPictureInExcel("C:\\Users\\Administrator\\Desktop\\test.jpg",workbook,sheet);
        FileOutputStream fo=new FileOutputStream("C:\\Users\\Administrator\\Desktop\\picture.xlsx");
        workbook.write(fo);
    }

}
