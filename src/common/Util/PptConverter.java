package common.Util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

public class PptConverter {
	
	String fileName;
	String filePath;
	
	public PptConverter(String fileName, String filePath) {
		this.fileName = fileName;
		this.filePath = filePath;
	}

	public int convert() throws IOException {
		//creating an empty presentation
		int numPpt = 0;
		System.out.println("current dir: " + System.getProperty("user.dir"));
		File inputFile = new File(filePath + "/" + fileName);
		FileInputStream is = new FileInputStream(inputFile);

        XMLSlideShow ppt = new XMLSlideShow(is);
        is.close();

        double zoom = 4; // magnify it by 2
        AffineTransform at = new AffineTransform();
        at.setToScale(zoom, zoom);

        Dimension pgsize = ppt.getPageSize();

        XSLFSlide[] slide = ppt.getSlides();
        numPpt = slide.length;
        for (int i = 0; i < slide.length; i++) {
            BufferedImage img = new BufferedImage((int)Math.ceil(pgsize.width*zoom), (int)Math.ceil(pgsize.height*zoom), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = img.createGraphics();
            graphics.setTransform(at);

            graphics.setPaint(Color.white);
            graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
            slide[i].draw(graphics);
            
            File outFile = new File(filePath + "\\" + String.format("%02d.JPG", (i+1)));
            outFile.createNewFile();
            FileOutputStream out = new FileOutputStream(outFile);
            
            javax.imageio.ImageIO.write(img, "JPG", out);
            out.close();
        }
        
        return numPpt;
	}
}
