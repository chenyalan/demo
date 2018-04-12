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
public class PoiTest3 {
    public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream("F:\\pop.xls");
        List<String> title= Arrays.asList(new String[]{"干部姓名","干部身份证","户主","户id","家庭成员","成员身份id","车牌号","车型","登记状态"});
        Workbook workbook = new HSSFWorkbook(in);
        Sheet sheet = workbook.getSheetAt(0);
        Row currentRow = null;
        StringBuffer tot=new StringBuffer();
        int j=1;
        //
        Workbook wb=new HSSFWorkbook();
        String excelPath="F:\\exportover52.xls";
        File file=new File(excelPath);
        Sheet wbSheet=wb.createSheet();
        OutputStream outputStream=new FileOutputStream(excelPath);
        Row wbRow=wbSheet.createRow(0);
        for(int i=0;i<title.size();i++){
            Cell cell=wbRow.createCell(i);
            cell.setCellValue(title.get(i));
        }
        Cell cellThird;
        for(int i=0;i<=3508;i++){
            currentRow=sheet.getRow(i);
            Cell first;
            first=currentRow.getCell(0);
            if(getCellValue(first).contains("干部")){
                continue;
            }else{
                int nextIndex=i;
                Row nextRow=sheet.getRow(nextIndex);
                Cell cellNext=nextRow.getCell(0);

                //取出干部  户主姓名  户主身份证号
                String ganBu=getCellValue(nextRow.getCell(0));
                String ganBuId=null;
                String hostName=getCellValue(nextRow.getCell(1));
                String hostId=getCellValue(nextRow.getCell(2));

                int ttt=nextIndex;
                Row tttRow=sheet.getRow(ttt);
                Cell tttCell=cellNext;
                while(!getCellValue(tttCell).contains("干部")) {
                    String a=getCellValue(tttRow.getCell(0));
                    String b=getCellValue(tttRow.getCell(1));
                    if (a.equals(b)) {
                        ganBuId = getCellValue(tttRow.getCell(2));
                        break;
                    }
                    ttt++;
                    tttRow=sheet.getRow(ttt);
                    try{
                        tttCell=tttRow.getCell(0);
                    }catch (Exception e){

                    }

                }
                while(!getCellValue(cellNext).contains("干部")) {

                    wbRow=wbSheet.createRow(j++);
                    cellThird=wbRow.createCell(0);
                    cellThird.setCellValue(ganBu);
                    cellThird=wbRow.createCell(1);
                    cellThird.setCellValue(ganBuId);
                    cellThird=wbRow.createCell(2);
                    cellThird.setCellValue(hostName);
                    cellThird=wbRow.createCell(3);
                    cellThird.setCellValue(hostId);

                    for (int n=4;n<=8;n++){
                        cellThird=wbRow.createCell(n);
                        cellThird.setCellValue(getCellValue(nextRow.getCell(n-3)));
                    }
//                    cellThird=wbRow.createCell(4);
//                    //这里面有可能是自己
//                    cellThird.setCellValue(getCellValue(nextRow.getCell(1)));
//                    cellThird=wbRow.createCell(5);
//                    cellThird.setCellValue(getCellValue(nextRow.getCell(2)));
//                    cellThird=wbRow.createCell(6);
//                    cellThird.setCellValue(getCellValue(nextRow.getCell(3)));
//                    cellThird=wbRow.createCell(7);
//                    cellThird.setCellValue(getCellValue(nextRow.getCell(4)));
//                    cellThird=wbRow.createCell(8);
//                    cellThird.setCellValue(getCellValue(nextRow.getCell(5)));
                    nextIndex++;
                    nextRow=sheet.getRow(nextIndex);
                    try{
                        cellNext=nextRow.getCell(0);
                    }catch (Exception e){

                    }
//
//                    System.out.println(ganBu+" "+ganBuId+" "+hostName+" "+hostName+" "+getCellValue(nextRow.getCell(1))+" "+
//                            getCellValue(nextRow.getCell(2))+" "+getCellValue(nextRow.getCell(3))+" "+
//                                    getCellValue(nextRow.getCell(4))+" "+getCellValue(nextRow.getCell(5)));
                }
                i=nextIndex-1;
            }

        }
        wb.write(outputStream);
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
