package com.andlinks.mybatis.utils;

import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 陈亚兰 on 2018/4/16.
 * apache-poi word
 */
public class WordUtils {
    public static void createDocument() throws IOException {
        XWPFDocument document=new XWPFDocument();
        FileOutputStream fo=new FileOutputStream(new File("ccc.docx"));
        XWPFParagraph paragraph=document.createParagraph();
        paragraph.setBorderLeft(Borders.BASIC_BLACK_DASHES);
        XWPFRun run=paragraph.createRun();
        run.setText("fjkdfjdk飞起来了");
        paragraph=document.createParagraph();
        run=paragraph.createRun();
        run.setText("我是不是第二行");

        System.out.print("create:");
        //表格

        //create first row
        //create table
        XWPFTable table = document.createTable();
        //create first row
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("col one, row one");
        tableRowOne.addNewTableCell().setText("col two, row one");
        tableRowOne.addNewTableCell().setText("col three, row one");
        //create second row
        XWPFTableRow tableRowTwo = table.getRow(1);
        tableRowTwo.getCell(0).setText("col one, row two");
        tableRowTwo.getCell(1).setText("col two, row two");
        tableRowTwo.getCell(2).setText("col three, row two");
        //create third row
        XWPFTableRow tableRowThree = table.getRow(2);
        tableRowThree.getCell(0).setText("col one, row three");
        tableRowThree.getCell(1).setText("col two, row three");
        tableRowThree.getCell(2).setText("col three, row three");
        document.write(fo);
        fo.close();
    }
    public static void main(String[] args) throws IOException {
        createDocument();
    }
}
