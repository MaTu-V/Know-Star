package com.wd.zykt.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

@Component
public class PDFUtil {


    /**
     * 将PDF按页数每页转换成一个jpg图片
     * 返回最终文件目录
     * @param filePath
     * @return
     */
    public static String pdfToImagePath(String filePath,String uploadPath) {
        LinkedList<String> list = new LinkedList<>();
        File file = new File(filePath);
        try {
            File f = new File(uploadPath); // 创建文件上传目录
            if (!f.exists()) {
                f.mkdirs();
            }
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                // 方式1,第二个参数是设置缩放比(即像素)
                // BufferedImage image = renderer.renderImageWithDPI(i, 296);
                // 方式2,第二个参数是设置缩放比(即像素)
                BufferedImage image = renderer.renderImage(i, 5f);  //第二个参数越大生成图片分辨率越高，转换时间也就越长
                String imagePath = uploadPath + "/" + i + ".jpg";
                ImageIO.write(image, "PNG", new File(imagePath));
                list.add(imagePath);
            }
            doc.close();              //关闭文件,不然该pdf文件会一直被占用。
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploadPath;
    }

    public static void main(String[] args) {
//        String filePath = "G://testPDF/test/test01.pdf";
//        String imagePath = "C://zykt/zykt_mater_test/";
//        LinkedList<String> imageList = pdfToImagePath(filePath,imagePath);
//        System.out.println("true");
//        pdfToImagePath(filePath);
    }
}
