package com.ouzhx.common;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * Created by liuwb on 2017/3/31. 分割合成图片
 */
public class ImageUtil {

  /**
   * 分割合成智联招聘验证码图片
   * 
   * @param fis 页面请求获取的图片流
   * @return
   * @author liuwb
   * @date 2017/4/6
   */
  public static byte[] splitAndMergeZhilianImage(InputStream fis) throws Exception {
    BufferedImage image = ImageIO.read(fis);

    // 分割成2*20(40)个小图
    int rows = 2;
    int cols = 20;
    int chunks = rows * cols;

    // 计算每个小图的宽度和高度
    int chunkWidth = image.getWidth() / cols;
    int chunkHeight = image.getHeight() / rows;
    // 图片类型
    int type = image.getType();

    // 开始对原始图片进行分割
    int count = 0;
    BufferedImage imgs[] = new BufferedImage[chunks];
    for (int x = 0; x < rows; x++) {
      for (int y = 0; y < cols; y++) {
        // 设置小图的大小和类型
        imgs[count] = new BufferedImage(chunkWidth, chunkHeight, type);
        // 写入图像内容
        Graphics2D gr = imgs[count++].createGraphics();
        gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x,
            chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
        gr.dispose();
      }
    }
    // 新图片的拼接顺序(从智联招聘js文件获取得到)
    Integer[] imageSort = new Integer[] {10, 17, 14, 8, 1, 9, 4, 2, 3, 12, 19, 15, 11, 13, 6, 0, 5,
        7, 16, 18, 35, 26, 37, 34, 22, 30, 29, 33, 23, 27, 24, 31, 39, 32, 38, 21, 20, 36, 28, 25};
    // 设置拼接后图的大小和类型
    BufferedImage finalImg = new BufferedImage(image.getWidth(), image.getHeight(), type);

    // 写入图像内容
    int num = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        finalImg.createGraphics().drawImage(imgs[imageSort[num]], chunkWidth * j, chunkHeight * i,
            null);
        num++;
      }
    }
    // 对最后的图片写入字节数组并返回
    ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
    ImageIO.write(finalImg, "jpeg", byteOutputStream);
    return byteOutputStream.toByteArray();
  }

  /**
   * 前程无忧验证码图片分割合成
   * 
   * @param fis 页面请求获取的图片流
   * @return
   * @author liuwb
   * @date 2017/4/6
   */
  public static byte[] splitAndMergeWuyouImage(InputStream fis) throws Exception {
    BufferedImage image = ImageIO.read(fis);
    int rows = 3; // 总行数
    int cols = 15; // 总列数
    int chunkWidth = image.getWidth() / cols;// 每个小图的宽度

    int headImageHeight = 40; // 头部小图高度40
    int bottomImageHeight = 58;// 底部小图高度58

    // 将原始图片分割成45个小图
    BufferedImage[] smallImage = new BufferedImage[45];
    // 头部小图的排序(根据ps软件切割图片获取)
    Integer[] headSort = new Integer[] {7, 8, 2, 9, 6, 4, 10, 1, 5, 11, 12, 3, 0, 13, 14};

    // 写入头部小图
    int index;
    for (int y = 0; y < cols; y++) {
      // 设置小图的大小和类型
      index = headSort[y];
      smallImage[30 + index] = new BufferedImage(chunkWidth, headImageHeight, image.getType());

      // 写入图像内容
      Graphics2D gr = smallImage[30 + index].createGraphics();
      gr.drawImage(image, 0, 0, chunkWidth, headImageHeight, chunkWidth * y, 0,
          chunkWidth * y + chunkWidth, headImageHeight, null);
      gr.dispose();
    }

    // 底部小图的排序(根据ps软件切割图片获取)
    Integer[] bottomSort = new Integer[] {23, 5, 3, 0, 16, 20, 14, 4, 11, 7, 18, 24, 19, 1, 10, 12,
        27, 25, 2, 6, 29, 13, 17, 15, 8, 26, 21, 9, 22, 28};

    // 写入底部小图
    int count = 0;
    for (int x = 0; x < 2; x++) {
      for (int y = 0; y < cols; y++) {
        // 设置小图的大小和类型
        index = bottomSort[count++];
        smallImage[index] = new BufferedImage(chunkWidth, bottomImageHeight, image.getType());

        // 写入图像内容
        Graphics2D gr = smallImage[index].createGraphics();
        gr.drawImage(image, 0, 0, chunkWidth, bottomImageHeight, chunkWidth * y,
            headImageHeight + bottomImageHeight * x, chunkWidth * y + chunkWidth,
            bottomImageHeight * x + headImageHeight + bottomImageHeight, null);
        gr.dispose();
      }
    }

    // 设置拼接后图的大小和类型
    BufferedImage finalImg =
        new BufferedImage(image.getWidth(), image.getHeight(), image.getType());

    // 写入图像内容
    int num = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        finalImg.createGraphics().drawImage(smallImage[num], chunkWidth * j, bottomImageHeight * i,
            null);
        num++;
      }
    }
    // 对最后的图片写入字节数组并返回
    ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
    ImageIO.write(finalImg, "jpeg", byteOutputStream);
    ImageIO.write(finalImg, "jpeg", new File("D:/img/split/test.jpg"));
    return byteOutputStream.toByteArray();
  }
}
