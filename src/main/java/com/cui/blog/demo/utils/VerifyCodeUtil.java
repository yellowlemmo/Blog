package com.cui.blog.demo.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

/**
 * 验证码生成工具
 */
public class VerifyCodeUtil {

    private int width = 100; //生成验证码图片宽度
    private int height = 50; //生成验证码图片高度
    private String[] fontNames = {"宋体","楷体","隶书","微软雅黑"};
    private Color bgColor = new Color(255, 212, 140);//验证码图片背景颜色
    private Random random = new Random();
    private String codes = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String text;//记录随机字符串


    /**
     * 获取随机颜色
     * @return
     */
    private Color randomColor(){
        int red = random.nextInt(150);
        int green = random.nextInt(150);
        int blue = random.nextInt(150);

        return new Color(red,green,blue);
    }

    /**
     * 获取随机字体
     * @return
     */
    private Font randomFont(){
        String name = fontNames[random.nextInt(fontNames.length)];
        int style = random.nextInt(4);
        int size = random.nextInt(5)+24;
        return new Font(name,style,size);
    }

    /**
     * 获取一个随机字符串
     * @return
     */
    private char randomChar(){
        return codes.charAt(random.nextInt(codes.length()));
    }

    /**
     * 创建一个空白的BufferedImag对象
     * @return
     */
    private BufferedImage createImag(){
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics g2 = image.getGraphics();
        g2.setColor(bgColor);
        g2.fillRect(0,0,width,height);
        return image;
    }

    public BufferedImage getImage(){
        BufferedImage image = createImag();
        Graphics g2 = image.getGraphics();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++){
            String s = randomChar()+"";
            sb.append(s);
            g2.setColor(randomColor());
            g2.setFont(randomFont());
            float x = i * width * 1.0f / 4;
            g2.drawString(s, (int) x,height - 15);
        }
        this.text = sb.toString();
        drawLine(image);
        return image;
    }

    /**
     * 绘制干扰线
     * @param image
     */
    private void drawLine(BufferedImage image){
        Graphics2D g2 = (Graphics2D) image.getGraphics();
        int num = 5;
        for(int i = 0; i< num; i++){
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);
            g2.setColor(randomColor());
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawLine(x1,y1,x2,y2);
        }
    }

    public String getText(){
        return text;
    }

    public static void output(BufferedImage image, OutputStream out) throws Exception{
        ImageIO.write(image,"JPEG",out);
    }
}
