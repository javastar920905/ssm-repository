package com.ouzhx.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 文件工具类
 * @author Administrator
 *
 */
public class FileTool {

	/**
	 * 将文件转成byte数组
	 * @return
	 * @throws IOException 
	 */
	public static byte[] fileToByte(File file) throws IOException  {  
        if(file == null){
        	return null;
        }
        FileInputStream fis = new FileInputStream(file);  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        byte[] b = new byte[1024];  
        int n;  
        while ((n = fis.read(b)) != -1)  {  
            bos.write(b, 0, n);  
        }  
        bos.flush();
        fis.close();  
        bos.close();  
        return bos.toByteArray();   
    }  
	
	public static void writeBytes(String filename, byte[] source) throws IOException {
		if (source == null) {
			return;
		}
		writeBytes(new File(filename), source, 0, source.length);
	}

	public static void writeBytes(File file, byte[] source) throws IOException {
		if (source == null) {
			return;
		}
		writeBytes(file, source, 0, source.length);
	}

	public static void writeBytes(String filename, byte[] source, int offset, int len) throws IOException {
		writeBytes(new File(filename), source, offset, len);
	}

	public static void writeBytes(File file, byte[] source, int offset, int len) throws IOException {
		if (len < 0) {
			throw new IOException("File size is negative!");
		}
		if (offset + len > source.length) {
			len = source.length - offset;
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(source, offset, len);
			fos.flush();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}
	
	/**
	 * 获取图片的宽高
	 * @param imgFile
	 * @return int[]{width, height}(异常时，返回{0, 0})
	 */
	public static int[] getImgWidthHeight(File imgFile){
		BufferedImage bufferedImage = null;
		int[] widthHeight = new int[2];;
		try {
			bufferedImage = ImageIO.read(imgFile);
			int width = bufferedImage.getWidth();   
			int height = bufferedImage.getHeight();  
			widthHeight[0] = width;
			widthHeight[1] = height;
		} catch (IOException e) { 
			e.printStackTrace();
			widthHeight[0] = 0;
			widthHeight[1] = 0;
		} 
		return widthHeight;
	}
	
	/**
	 * 删除文件
	 * @param file
	 * @return
	 */
	public static boolean deleteFile(File file){
		boolean flag = false;
		if(null != file && file.exists() && file.isFile()){
			file.delete();
			flag = true;
		}
		return flag;
	}
}
