package com.github.hui.quick.plugin.test;

import com.github.hui.quick.plugin.phantom.Html2ImageByJsWrapper;

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by yihui on 2018/3/26.
 */
public class Html2ImgTest {

    @Test
    public void testRender() throws IOException {
        try {
            BufferedImage img = null;
            String url = "https://seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/WebDriver.Window.html";
            long start = System.currentTimeMillis();
            int width = 3840;
            int heght = 2560;
            img = Html2ImageByJsWrapper.renderHtml2Image1(url,width,heght);
            long end = System.currentTimeMillis();
            System.out.println("cost:  " + (end - start));
            //haha(img);

            writePNGImage(img,"/Users/quyixiao/Desktop/aaabb/"+width+"*"+heght+".png");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGoogleRender() throws IOException {
        WebDriver driver =null;
        BufferedImage img = null;
        try {

            String url = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%E6%9D%8E%E8%BF%9E%E6%9D%B0&rsv_pq=af3fc8d3000243be&rsv_t=7e99qhg2XvX6JOdHNpLGBG6eVmWgyGhldcU1ITXJUqxAucdiL21BKN7lGzs&rqlang=cn&rsv_enter=1&rsv_dl=tb&rsv_sug3=34&rsv_sug1=5&rsv_sug7=100&rsv_sug2=0&inputT=13922&rsv_sug4=14943";
            long start = System.currentTimeMillis();
            int width = 24340;
            int heght = 1880;



            //打开网址过后，设置隐性等待,等待10秒
            // chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            //打开网址后，窗口最大化
            //chromeDriver.manage().window().maximize();

            System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
            driver = new ChromeDriver();
            System.out.println("11111111111111111="+(System.currentTimeMillis()-start));
           // driver.manage().window().setSize(new Dimension(width,heght));
            driver.manage().window().fullscreen();
            System.out.println("22222222222222222="+(System.currentTimeMillis()-start));
            driver.get(url);
            System.out.println("3333333333333333="+(System.currentTimeMillis()-start));
            Thread.sleep(3000);
           // driver.manage().window().setSize(new Dimension(width,heght));
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            System.out.println("44444444444444444="+(System.currentTimeMillis()-start));



            img =  ImageIO.read(scrFile);



            long end = System.currentTimeMillis();
            System.out.println("cost:  " + (end - start));
            //haha(img);

            writePNGImage(img,"/Users/quyixiao/Desktop/aaabb/"+width+"*"+heght+".png");


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
           driver.quit();
        }
    }


    public static void haha(BufferedImage bf){

        try {



            File file = new File("/Users/quyixiao/Desktop/aaabb/aa.jpeg");
            mkDir(file);
            ImageIO.write(bf, "jpeg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 递归创建文件夹
     *
     * @param file 由目录创建的file对象
     * @throws FileNotFoundException
     */
    public static void mkDir(File file) throws FileNotFoundException {
        if (file.getParentFile() == null) {
            file = file.getAbsoluteFile();
        }

        boolean ans;
        if (file.getParentFile().exists()) {
            modifyFileAuth(file);
            if (!file.exists() && !file.mkdir()) {
                throw new FileNotFoundException();
            }
        } else {
            mkDir(file.getParentFile());
            modifyFileAuth(file);
            if (!file.exists() && !file.mkdir()) {
                throw new FileNotFoundException();
            }
        }
    }

    /**
     * 修改文件权限，设置为可读写
     *
     * @param file
     */
    private static void modifyFileAuth(File file) {
        boolean ans = file.setExecutable(true, false);
        ans = file.setReadable(true, false) && ans;
        ans = file.setWritable(true, false) && ans;

    }




    public static boolean writeImage(RenderedImage im, String formatName, String fileName) {
        boolean result = false;
        try {
            result = ImageIO.write(im,formatName,new File(fileName));
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }



    public static boolean writeJPEGImage(RenderedImage im, String fileName) {
        return writeImage(im,"JPEG",fileName);
    }
    public static boolean writeGIFImage(RenderedImage im, String fileName) {
        return writeImage(im,"GIF",fileName);
    }
    public static boolean writePNGImage(RenderedImage im, String fileName) {
        return writeImage(im,"PNG",fileName);
    }
    public static boolean writeBMPImage(RenderedImage im, String fileName) {
        return writeImage(im,"BMP",fileName);
    }

}
