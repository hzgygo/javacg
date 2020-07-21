package com.hzgy.core.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;

public class PicUtils {

    private String rootPath;
    private String destFile;
    private int width;
    private int height;
    private int scaleWidth;
    private int scaleHeight;
    private BufferedImage img;
    private String ext;

    //缩放图片工具的构造函数
    public PicUtils(String rootPath,String srcFile) throws IOException {
        this.rootPath = rootPath;
        //得到最后一个.的位置
        int index = srcFile.lastIndexOf(".");
        //获取被缩放的图片的格式
        this.ext = srcFile.substring(index + 1);
        //获取目标路径(和原始图片路径相同,在文件名后添加了一个_s)
        if (index >= 0) {
            this.destFile = srcFile.substring(0, index) + "_s." + ext;
        }
        String realPath = rootPath + File.separator + srcFile;
        System.out.println("srcFile:" + realPath);
        File file = new File(realPath);
        if (file.exists()) {
            //读取图片,返回一个BufferedImage对象
            this.img = ImageIO.read(file);
            //获取图片的长和宽
            this.width = img.getWidth();
            this.height = img.getHeight();
        }
    }

    /**
     * 计算压缩比例
     * @param swidth 参考缩放宽度
     * @param sheight 参考缩放高度
     * @return 压缩比例
     */
    public double getPercent(double swidth,double sheight) {
        if (this.width <= swidth && this.height <= sheight){
            this.scaleHeight = height;
            this.scaleWidth = width;
            return 0.00;
        }
        double p2 = 0.00;
        //如果时高图，按照高度缩放
        //否则按照宽度缩放
        if (this.height >= this.width) {
            p2 = (sheight / this.height) * 100.00;
        }
        else{
            p2 = (swidth / this.width) * 100.00;
        }
        return (p2 / 100.00);
    }

    /**
     * 按比例对图片进行缩放.
     * @param scale 缩放比例
     * @throws IOException 异常
     */
    public void zoomByScale(double scale) throws IOException {
        //获取缩放后的长和宽
        int _width = (int) (scale * width);
        int _height = (int) (scale * height);
        //设置缩放后的高和宽
        this.scaleHeight = _height;
        this.scaleWidth = _width;
        //获取缩放后的Image对象
        Image _img = img.getScaledInstance(_width, _height, Image.SCALE_DEFAULT);
        //新建一个和Image对象相同大小的画布
        BufferedImage image = new BufferedImage(_width, _height, BufferedImage.TYPE_INT_RGB);
        //获取画笔
        Graphics2D graphics = image.createGraphics();
        //将Image对象画在画布上,最后一个参数,ImageObserver:接收有关 Image 信息通知的异步更新接口,没用到直接传空
        graphics.drawImage(_img, 0, 0, null);
        //释放资源
        graphics.dispose();
        //使用ImageIO的方法进行输出,记得关闭资源
        String realPath = this.rootPath + File.separator + destFile;
        OutputStream out = new FileOutputStream(realPath);
        ImageIO.write(image, ext, out);
        out.close();
    }

    /**
     * 指定长和宽对图片进行缩放
     * @param width 长
     * @param height 宽
     * @throws IOException
     */
    public void zoomBySize(int width, int height) throws IOException {
        //与按比例缩放的不同只在于,不需要获取新的长和宽,其余相同.
        Image _img = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.drawImage(_img, 0, 0, null);
        graphics.dispose();
        String realPath = this.rootPath + File.separator + destFile;
        OutputStream out = new FileOutputStream(realPath);
        ImageIO.write(image, ext, out);
        out.close();
    }

    public String getDestFile() {
        return destFile;
    }

    public void setDestFile(String destFile) {
        this.destFile = destFile;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public int getScaleWidth(double scale) {
        if (scale > 0){
            return (int) (scale * width);
        }
        return scaleWidth;
    }

    public void setScaleWidth(int scaleWidth) {
        this.scaleWidth = scaleWidth;
    }

    public int getScaleHeight(double scale) {
        if (scale > 0){
            return (int) (scale * height);
        }
        return scaleHeight;
    }

    public void setScaleHeight(int scaleHeight) {
        this.scaleHeight = scaleHeight;
    }
}
