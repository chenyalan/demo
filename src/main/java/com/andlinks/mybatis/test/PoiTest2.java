package com.andlinks.mybatis.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 陈亚兰 on 2017/12/27.
 */
public class PoiTest2 {
    public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream("F:\\第二版2.xls");
        FileOutputStream os = new FileOutputStream("F:\\第二版ps2.xls");
        Workbook workbook = new HSSFWorkbook(in);
        Sheet sheet = workbook.getSheetAt(0);
        Row row=null;
        Workbook wbNext=new HSSFWorkbook();
        int j=0;
        Sheet wbSheet=wbNext.createSheet();
        Row rowNext=null;
        StringBuffer tot=new StringBuffer();
        for (int i = 0; i <=sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            StringBuffer sb=new StringBuffer();
            Cell first=row.getCell(0);
            for (Cell c : row) {
                sb.append(getCellValue(c));
            }
            if(sb.toString().equals(getCellValue(first))){
                System.out.println(getCellValue(first)+":"+i);
            }else{
                rowNext=wbSheet.createRow(j++);
                int k=0;
                for(Cell c:row){
                    Cell cNext=rowNext.createCell(k++);
                    cNext.setCellValue(getCellValue(c));
                }
            }
        }
        wbNext.write(os);

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
