package com.andlinks.mybatis.utils;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlToken;
import org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps;
import org.openxmlformats.schemas.drawingml.x2006.main.CTPositiveSize2D;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 陈亚兰 on 2018/4/16.
 */
public class CustomXWPFDocumentNoxml extends XWPFDocument{
    public CustomXWPFDocumentNoxml(InputStream in) throws IOException {
        super(in);
    }

    /**
     *
     */
    public CustomXWPFDocumentNoxml() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param pkg
     * @throws IOException
     */
    public CustomXWPFDocumentNoxml(OPCPackage pkg) throws IOException {
        super(pkg);
        // TODO Auto-generated constructor stub
    }  // picAttch 图片后面追加的字符串 可以是空格
    public void createPicture(XWPFParagraph paragraph,int id, int width, int height,String blipId) throws FileNotFoundException, InvalidFormatException {
        final int EMU = 9525;
        width *= EMU;
        height *= EMU;
        if (paragraph == null) {
            paragraph = createParagraph();
        }
        CTInline inline = paragraph.createRun().getCTR().addNewDrawing()
                .addNewInline();
        String picXml = ""
                + "<a:graphic xmlns:a=\"http://schemas.openxmlformats.org/drawingml/2006/main\">"
                + "   <a:graphicData uri=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">"
                + "      <pic:pic xmlns:pic=\"http://schemas.openxmlformats.org/drawingml/2006/picture\">"
                + "         <pic:nvPicPr>" + "            <pic:cNvPr id=\""
                + id
                + "\" name=\"img_"
                + id
                + "\"/>"
                + "            <pic:cNvPicPr/>"
                + "         </pic:nvPicPr>"
                + "         <pic:blipFill>"
                + "            <a:blip r:embed=\""
                + blipId
                + "\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"/>"
                + "            <a:stretch>"
                + "               <a:fillRect/>"
                + "            </a:stretch>"
                + "         </pic:blipFill>"
                + "         <pic:spPr>"
                + "            <a:xfrm>"
                + "               <a:off x=\"0\" y=\"0\"/>"
                + "               <a:ext cx=\""
                + width
                + "\" cy=\""
                + height
                + "\"/>"
                + "            </a:xfrm>"
                + "            <a:prstGeom prst=\"rect\">"
                + "               <a:avLst/>"
                + "            </a:prstGeom>"
                + "         </pic:spPr>"
                + "      </pic:pic>"
                + "   </a:graphicData>" + "</a:graphic>";
        // CTGraphicalObjectData graphicData =
        // inline.addNewGraphic().addNewGraphicData();
        XmlToken xmlToken = null;
        try {
            xmlToken = XmlToken.Factory.parse(picXml);
        } catch (XmlException xe) {
            xe.printStackTrace();
        }
        inline.set(xmlToken);
        // graphicData.set(xmlToken);
        inline.setDistT(10);
        inline.setDistB(0);
        inline.setDistL(0);
        inline.setDistR(0);
        CTPositiveSize2D extent = inline.addNewExtent();
        extent.setCx(width);
        extent.setCy(height);
        CTNonVisualDrawingProps docPr = inline.addNewDocPr();
        docPr.setId(id);
        docPr.setName("docx_img_ " + id);
        docPr.setDescr("docx Picture");
    }
}
