package com.github.hui.quick.plugin.phantom;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 通过phantomjs 来实现html转图片的功能，需要先安装phantomjs
 *
 * {@link /doc/image/html2img.md}
 *
 * <p>
 * Created by yihui on 2017/12/1.
 */

public class Html2ImageByJsWrapper {

    private static PhantomJSDriver webDriver;



    private static ChromeDriver chromeDriver;

    static {
        try {
            webDriver = getPhantomJs();
            //chromeDriver = getChromeDriver();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getPhantomJsPath() {
        String path = System.getProperty("phantomjs.binary.path");


        System.out.println("===========path==========="+path);
        if (path == null || "".endsWith(path)) {
            return "/usr/local/bin/phantomjs/";
        } else {
            return path;
        }
    }


    private static String getChromePath() {
        String path = System.getProperty("phantomjs.binary.path");


        // 如果不设置将搜索环境变量
        System.setProperty("webdriver.chrome.driver", "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");


        System.out.println("===========path==========="+path);
        if (path == null || "".endsWith(path)) {
            return "/usr/local/bin/phantomjs/";
        } else {
            return path;
        }
    }

    private static PhantomJSDriver getPhantomJs() {
        //设置必要参数
        DesiredCapabilities dcaps = new DesiredCapabilities();
        //ssl证书支持
        dcaps.setCapability("acceptSslCerts", true);
        //截屏支持
        dcaps.setCapability("takesScreenshot", true);
        //css搜索支持
        dcaps.setCapability("cssSelectorsEnabled", true);
        //js支持
        dcaps.setJavascriptEnabled(true);
        //驱动支持（第二参数表明的是你的phantomjs引擎所在的路径，which/whereis phantomjs可以查看）
        // fixme 这里写了执行， 可以考虑判断系统是否有安装，并获取对应的路径 or 开放出来指定路径
        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, getPhantomJsPath());
        //创建无界面浏览器对象
        return new PhantomJSDriver(dcaps);
    }


    private static ChromeDriver getChromeDriver() {
        //设置必要参数
        DesiredCapabilities dcaps = new DesiredCapabilities();
        //ssl证书支持
        dcaps.setCapability("acceptSslCerts", true);
        //截屏支持
        dcaps.setCapability("takesScreenshot", true);
        //css搜索支持
        dcaps.setCapability("cssSelectorsEnabled", true);
        //js支持
        dcaps.setJavascriptEnabled(true);
        //驱动支持（第二参数表明的是你的phantomjs引擎所在的路径，which/whereis phantomjs可以查看）
        // fixme 这里写了执行， 可以考虑判断系统是否有安装，并获取对应的路径 or 开放出来指定路径
        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, getChromePath());
        //创建无界面浏览器对象
        return new ChromeDriver(dcaps);
    }


    public static BufferedImage renderHtml2Image(String url) throws IOException {
        if (webDriver != null) {
            //打开网址过后，设置隐性等待,等待10秒
            webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            //打开网址后，窗口最大化
            webDriver.manage().window().maximize();
            float width = webDriver.manage().window().getSize().width;
            float height = webDriver.manage().window().getSize().height;
            System.out.println("pre with="+width + ",height="+height);

            Dimension dimension = new Dimension(1000,800);
            webDriver.manage().window().setSize(dimension);

            width = webDriver.manage().window().getSize().width;

            height = webDriver.manage().window().getSize().height;
            System.out.println("set propety with="+width + ",height="+height);




            webDriver.get(url);


            File file = webDriver.getScreenshotAs(OutputType.FILE);





            return ImageIO.read(file);
        } else {
            return null;
        }
    }



    public static BufferedImage renderHtml2Image1(String url,int widthNew,int heightNew) throws IOException {
        if (webDriver != null) {
            //打开网址过后，设置隐性等待,等待10秒
            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            //打开网址后，窗口最大化
            webDriver.manage().window().maximize();
            int width = webDriver.manage().window().getSize().width;
            int height = webDriver.manage().window().getSize().height;
            System.out.println("pre with="+width + ",height="+height);
            webDriver.get(url);
            File file = webDriver.getScreenshotAs(OutputType.FILE);
            return ImageIO.read(file);
        } else {
            return null;
        }
    }




    public static BufferedImage renderHtml2ImageByChromeDriver(String url,int widthNew,int heightNew) throws IOException {



        return null;
    }


}
