package com.andlinks.mybatis.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 陈亚兰 on 2017/12/27.
 */
public class PoiTest {
    public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream("F:\\第一版.xls");

        Workbook workbook = new HSSFWorkbook(in);
        //写入文件
        Workbook wb=new HSSFWorkbook();
        String excelPath="F:\\第二版2.xls";
        File file=new File(excelPath);
        Sheet wbSheet=wb.createSheet();
        OutputStream outputStream=new FileOutputStream(excelPath);
        Row wbRow=wbSheet.createRow(0);
        int rowIndex=1;
        wbRow.setHeight((short)540);
        List<String> wbList= Arrays.asList("姓名","户主姓名","户主身份号","机动车牌号","车型","登记状态");
        for(int i=0;i<wbList.size();i++){
            Cell cell=wbRow.createCell(i);
            cell.setCellValue(wbList.get(i));
        }
//        wb.write(outputStream);

        //
        Sheet sheet = workbook.getSheetAt(1);
        Row row = null;
        StringBuffer tot=new StringBuffer();
        for (int i = 0; i <=sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            StringBuffer sb=new StringBuffer();
            for (Cell c : row) {
                boolean isMearge = isMergedRegion(sheet, i, c.getColumnIndex());
                //判断是否具有合并单元格
                if(isMearge){
                }else{
                    sb.append(getCellValue(c));
                }
            }
            if(sb.toString().contains("家庭成员姓名")){
                tot.append("A"+i+"A,");
            }
        }
        for (int j= 0; j < sheet.getLastRowNum()-1; j++) {
            row = sheet.getRow(j);
            StringBuffer sb=new StringBuffer();
            String t="A"+j+"A";
            if(tot.toString().contains(t))continue;
            wbRow=wbSheet.createRow(rowIndex++);//每次自增
            int cellIndex=0;
            for (Cell c : row) {
                boolean isMearge = isMergedRegion(sheet, j, c.getColumnIndex());
                //判断是否具有合并单元格
                if(isMearge){
                    String rs=getMergedRegionValue(sheet,row.getRowNum(),c.getColumnIndex());
                        Cell cell=wbRow.createCell(cellIndex++);
                    cell.setCellValue(rs);
                }else{
                    Cell cell=wbRow.createCell(cellIndex++);
                    cell.setCellValue(getCellValue(c));
//                    System.out.print(getCellValue(c)+" ");
                }
            }
            System.out.println();
        }
        wb.write(outputStream);
        outputStream.close();

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
