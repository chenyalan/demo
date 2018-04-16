package com.andlinks.mybatis.utils;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.*;
import java.util.List;

/**
 * Created by 陈亚兰 on 2018/4/16.
 */
public class GetWord2 {
    public static void getWord(String path) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        File file=new File(path);
        FileInputStream fi=new FileInputStream(file);
        CustomXWPFDocument document=new CustomXWPFDocument(fi);
        XWPFTable table=document.getTables().get(0);
        XWPFTableRow row;
        XWPFTableCell cell;
        XWPFTableCell cellNew;
        int hang=0;
        for(XWPFTableRow r:table.getRows()){
            int lie=0;
            for(XWPFTableCell c:r.getTableCells()){
                System.out.print(hang+"行"+lie+"列:"+c.getText()+ "\t");
                lie++;
            }
            System.out.print("\n");
            hang++;
        }
        row=table.getRow(0);
        cell=row.getCell(1);
        cell.setText("陈亚兰");
        cell=row.getCell(3);cell.setText("女");
        cell=row.getCell(5);cell.setText("1994年4月");
        //照片
        cell=row.getCell(6);
//        cell=shuiPing(cell);
        cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
                byte[] byteArray=inputStreamToByteArray(new FileInputStream("C:\\Users\\Administrator\\Desktop\\zhengjian.jpg"),true);
                ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArray);
                String ind=document.addPictureData(byteArrayInputStream,XWPFDocument.PICTURE_TYPE_JPEG);
                XWPFParagraph p=cell.addParagraph();
                document.createPicture(p,document.getAllPictures().size()-1,90,120,"");



        //
        row=table.getRow(1);
        cell=row.getCell(1);cell.setText("汉");
        cell=row.getCell(3);cell.setText("江苏涟水");
        cell=row.getCell(5);cell.setText("江苏");
        row=table.getRow(2);
        cell=row.getCell(3);cell.setText("2016年7月");
        cell=row.getCell(5);cell.setText("良好");
        row=table.getRow(3);
        cell=row.getCell(1);cell.setText("大幅度大幅度反对法");
        cellNew=row.createCell();
        mergeCellsHorizontal(table,3,1,2);
        cell=row.getCell(3);cell.setText("几十块的积分打孔积分抵扣");
        row=table.getRow(4);
        cell=row.getCell(2);cell.setText("金陵科技学院");
        cell=row.getCell(4);cell.setText("计算机科学与技术");
        row=table.getRow(5);
        cell=row.getCell(2);cell.setText("无");
        cell=row.getCell(4);cell.setText("无");
        row=table.getRow(6);
        cell=row.getCell(1);cell.setText("java工程师");
        row=table.getRow(9);
        cell=row.getCell(1);cell.setText("2012年9月-2016年6月 金陵科技学院 \n 2016年7月-2017年3月 南京YK电气有限公司 \n 2017年4月-至今 南京安链数据科技有限公司 ");

        row=table.getRow(10);
        cell=row.getCell(1);cell.setText("无");
        row=table.getRow(11);
        cell=row.getCell(1);cell.setText("2016年 优秀 "+"\n"+"2017年 优秀");
        row=table.getRow(12);
        cell=row.getCell(1);cell.setText("dd");
        for(int i=0;i<5;i++){
            row=table.getRow(14+i);
            cell=row.getCell(1);
            cell.setText("父亲"+i);
            cell=row.getCell(2);cell.setText("张三"+i);
            cell=row.getCell(3);cell.setText("24"+i+1);
            cell=row.getCell(4);cell.setText("党员");
            cell=row.getCell(5);cell.setText("警察"+i);
        }
        FileOutputStream fo=new FileOutputStream("C:\\Users\\Administrator\\Desktop\\dkk.docx");
        document.write(fo);
        fi.close();
    }
    public static XWPFTableCell shuiPing(XWPFTableCell cell){
        CTTc cttc = cell.getCTTc();
        CTP ctp = cttc.getPList().get(0);
        CTPPr ctppr = ctp.getPPr();
        if (ctppr == null) {
            ctppr = ctp.addNewPPr();
        }
        CTJc ctjc = ctppr.getJc();
        if (ctjc == null) {
            ctjc = ctppr.addNewJc();
        }
        ctjc.setVal(STJc.CENTER);
        return cell;
    }

    public static void mergeCellsHorizontal(XWPFTable table, int row, int fromCell, int toCell) {
        for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {
            XWPFTableCell cell = table.getRow(row).getCell(cellIndex);
            if ( cellIndex == fromCell ) {
                // The first merged cell is set with RESTART merge value
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
            } else {
                // Cells which join (merge) the first one, are set with CONTINUE
                cell.getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);
            }
        }
    }
    public static byte[] inputStreamToByteArray(InputStream in,boolean isClose){
        byte[] byteArray=null;
        try {
            int total = in.available();
            byteArray = new byte[total];
            in.read(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(isClose){
                try {
                    in.close();
                } catch (Exception e2) {
                    System.out.println("关闭流失败");
                }
            }
        }
        return byteArray;
    }
    public static void main(String[] args) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        getWord("C:\\Users\\Administrator\\Desktop\\at.docx");
    }
}
