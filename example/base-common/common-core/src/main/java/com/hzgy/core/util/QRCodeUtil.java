package com.hzgy.core.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author chenz
 *
 */
public class QRCodeUtil {

    private static Logger logger = LoggerFactory.getLogger(QRCodeUtil.class);
    /**
     * 二维码宽度
     */
    private static int width = 300;
    /**
     * 二维码高度
     */
    private static int height = 300;
    /**
     * 前景色
     */
    private static int black = 0xFF000000;
    /**
     * 背景色
     */
    private static int white = 0xFFFFFFFF;
    /**
     * 白边大小，取值范围0~4
     */
    private static int margin = 0;
    /**
     * 二维码容错率
     */
    private static ErrorCorrectionLevel level = ErrorCorrectionLevel.L;

    /**
     * 生成二维码
     *
     * @param text 二维码内容
     * @param w    宽度 默认 300
     * @param h    高度 默认 300
     * @param m    白边大小，取值范围0~4 默认 1
     * @return
     * @throws WriterException
     */
    public static BufferedImage getQRImg(String text, int w, int h, int m) throws WriterException {
        HashMap<EncodeHintType, Object> hints = new HashMap<>(1);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, m == -1 ? margin : m);
        // 指定纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, level);
        BitMatrix bm = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, w == 0 ? width : w, h == 0 ? height : h, hints);
        return toBufferedImage(bm);
    }

    /**
     * 生成默认二维码
     *
     * @param text
     * @return
     * @throws WriterException
     */
    public static BufferedImage getQRImg(String text) throws WriterException {
        return getQRImg(text, 0, 0, -1);
    }

    /**
     * 生成默认二维码
     *
     * @param text
     * @return
     * @throws WriterException
     */
    public static BufferedImage getQRImg(String text, String logoPath) throws WriterException {
        BufferedImage image = getQRImg(text, 0, 0, -1);
        try {
            image = writeLogo(image, logoPath);
        } catch (IOException e) {
            logger.error("com.hzgy.openim.common.util.QRcodeUtil.getQRImg", "二维码添加Logo出错");
        }
        return image;
    }


    /**
     * 二维码添加LOGO
     *
     * @param matrix   二维码矩阵相关
     * @param logoPath logo路径
     * @throws IOException
     */
    public static BufferedImage writeLogo(BitMatrix matrix, String logoPath) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        return writeLogo(image, logoPath);
    }

    /**
     * 二维码添加LOGO
     *
     * @param image
     * @param logoPath
     * @return
     * @throws IOException
     */
    public static BufferedImage writeLogo(BufferedImage image, String logoPath) throws IOException {
        if (StringUtil.isEmpty(logoPath)) {
            return image;
        }
        int ratioWidth = image.getWidth() * 2 / 10;
        int ratioHeight = image.getHeight() * 2 / 10;
        Graphics2D gs = image.createGraphics();
        //载入logo
        Image img = setRadius(ImageIO.read(new File(logoPath)), ratioWidth / 3, 0, ratioWidth / 10);
        int logoWidth = img.getWidth(null) > ratioWidth ? ratioWidth : img.getWidth(null);
        int logoHeight = img.getHeight(null) > ratioHeight ? ratioHeight : img.getHeight(null);

        int x = (image.getWidth() - logoWidth) / 2;
        int y = (image.getHeight() - logoHeight) / 2;

        gs.drawImage(img, x, y, logoWidth, logoHeight, null);
        gs.setColor(Color.black);
        gs.setBackground(Color.WHITE);
        gs.dispose();
        img.flush();
        return image;
    }

    public static BufferedImage setRadius(BufferedImage srcImage, int radius, int border, int padding) {
        int width = srcImage.getWidth();
        int height = srcImage.getHeight();
        int canvasWidth = width + padding * 2;
        int canvasHeight = height + padding * 2;

        BufferedImage image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gs = image.createGraphics();
        gs.setComposite(AlphaComposite.Src);
        gs.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gs.setColor(Color.WHITE);
        gs.fill(new RoundRectangle2D.Float(0, 0, canvasWidth, canvasHeight, radius, radius));
        gs.setComposite(AlphaComposite.SrcAtop);
        gs.drawImage(setClip(srcImage, radius), padding, padding, null);
        if (border != 0) {
            gs.setColor(Color.WHITE);
            gs.setStroke(new BasicStroke(border));
            gs.drawRoundRect(padding, padding, canvasWidth - 2 * padding, canvasHeight - 2 * padding, radius, radius);
        }
        gs.dispose();
        return image;
    }

    /**
     * 图片切圆角
     *
     * @param srcImage
     * @param radius
     * @return
     */
    public static BufferedImage setClip(BufferedImage srcImage, int radius) {
        int width = srcImage.getWidth();
        int height = srcImage.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gs = image.createGraphics();

        gs.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gs.setClip(new RoundRectangle2D.Double(0, 0, width, height, radius, radius));
        gs.drawImage(srcImage, 0, 0, null);
        gs.dispose();
        return image;
    }


    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? black : white);
            }
        }
        return image;
    }

}
