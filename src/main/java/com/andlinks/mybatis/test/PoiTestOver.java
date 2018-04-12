package com.andlinks.mybatis.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 陈亚兰 on 2017/12/27.
 */
public class PoiTestOver {
    public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream("F:\\over.xls");
        //FileOutputStream os = new FileOutputStream("F:\\over-tip.xls");
        Workbook workbook = new HSSFWorkbook(in);
        Sheet sheet = workbook.getSheetAt(0);
        Row currentRow = sheet.getRow(0);
        Row f=sheet.createRow(0);
        //写入文件
        Workbook wb=new HSSFWorkbook();
        String excelPath="F:\\over-tips.xls";
        File file=new File(excelPath);
        Sheet wbSheet=wb.createSheet();
        OutputStream outputStream=new FileOutputStream(excelPath);
        Row wbRow=wbSheet.createRow(0);
        int wbIndex=1;

        List<String> list= Arrays.asList(new String[]{"干部姓名","干部身份证号","户主姓名","户主身份证号","家庭住址","家庭成员姓名","身份证号","机动车牌号","车型","登记状态"});
        for(int i=0;i<list.size();i++){
            Cell cell=wbRow.createCell(i);
            cell.setCellValue(list.get(i));
        }
        for(int i=0;i<sheet.getLastRowNum()-1;i++){
            currentRow=sheet.getRow(i);
            Cell first=currentRow.getCell(0);
               if(getCellValue(first).contains("干部")){
                   int j=i+1;
                   currentRow=sheet.getRow(j++);
                   String ganbuName=getCellValue(currentRow.getCell(0));
                   String hostName=getCellValue(currentRow.getCell(1));
                   String hostId=getCellValue(currentRow.getCell(2));

                   String ganbuId=null;
                   Row row1=currentRow;
                   int j1=j;
                   List<String> chenyuan=new ArrayList<>();
                   List<String> shenId=new ArrayList<>();
                   while(!getCellValue(row1.getCell(0)).contains("干部姓名")&&getCellValue(row1.getCell(0))!=""){
                       if(getCellValue(row1.getCell(1))==ganbuName){
                           ganbuId=getCellValue(row1.getCell(2));
                           if(ganbuId=="")ganbuId="无";
                           System.out.println(ganbuName+" :"+j1);
                       }else{
                           chenyuan.add(getCellValue(row1.getCell(1)));
                           shenId.add(getCellValue(row1.getCell(2)));
                       }
                       row1=sheet.getRow(j1++);
                   }
                   int jihao=j1;
                   for(int k=0;k<chenyuan.size();k++){
                       wbRow=wbSheet.createRow(wbIndex++);//每次自增
                       Cell wbCell=wbRow.createCell(0);
                       wbCell.setCellValue(ganbuName);
                       wbCell=wbRow.createCell(1);
                       wbCell.setCellValue(ganbuId);
                       wbCell=wbRow.createCell(2);
                       wbCell.setCellValue(hostName);
                       wbCell.setCellValue(hostId);
                       wbCell=wbRow.createCell(5);
                       wbCell.setCellValue(chenyuan.get(k));
                       wbCell=wbRow.createCell(6);
                       wbCell.setCellValue(shenId.get(k));
                   }

               }


        }
        workbook.write(outputStream);
    }

    private static String getMergedRegionValue(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();

        for(int i = 0 ; i < sheetMergeCount ; i++){
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if(row >= firstRow && row <= lastRow){

                if(column >= firstColumn && column <= lastColumn){
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell) ;
                }
            }
        }

        return null ;
    }

    //获取单元格的值
    private static String getCellValue(Cell cell) {
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

    private static boolean isMergedRegion(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }

}
