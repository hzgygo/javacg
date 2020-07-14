package com.hzgy.generator.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {

	public static String getFileEncode(String path){
		String charset = "asci";
		byte[] first3Bytes = new byte[3];
		BufferedInputStream bis = null;
		try{
			boolean checked = false;
			bis = new BufferedInputStream(new FileInputStream(path));
			bis.mark(0);
			int read = bis.read(first3Bytes,0,3);
			if(read == -1)
				return charset;
			if(first3Bytes[0] == (byte)0xFF && first3Bytes[1] == (byte)0xFE){
				charset = "Unicode";// UTF-16LE
				checked = true;
			}
			else if(first3Bytes[0] == (byte)0xFE && first3Bytes[1] == (byte)0xFF){
				charset = "Unicode";// UTF-16BE
				checked = true;
			}
			else if(first3Bytes[0] == (byte)0xEF && first3Bytes[1] == (byte)0xBB && first3Bytes[2] == (byte)0xBF){
				charset = "UTF8";
				checked = true;
			}
			bis.reset();
			if(!checked){
				while((read = bis.read()) != -1){
					if(read >= 0xF0)
						break;
					if(0x80 <= read && read <= 0xBF) // 单独出现BF以下的，也算是GBK
						break;
					if(0xC0 <= read && read <= 0xDF){
						read = bis.read();
						if(0x80 <= read && read <= 0xBF){
							// 双字节 (0xC0 - 0xDF) (0x80 - 0xBF),也可能在GB编码内
							continue;
						}
						else{
							break;
						}
					}
					else if(0xE0 <= read && read <= 0xEF){ // 也有可能出错，但是几率较小
						read = bis.read();
						if(0x80 <= read && read <= 0xBF){
							read = bis.read();
							if(0x80 <= read && read <= 0xBF){
								charset = "UTF-8";
								break;
							}
							else{
								break;
							}
						}
						else{
							break;
						}
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(bis != null){
				try{
					bis.close();
				}
				catch(IOException ex){
				}
			}
		}
		return charset;
	}

	// private static String getEncode(int flag1, int flag2, int flag3) {
	// String encode = "";
	// // txt文件的开头会多出几个字节，分别是FF、FE（Unicode）,
	// // FE、FF（Unicode big endian）,EF、BB、BF（UTF-8）
	// if (flag1 == 255 && flag2 == 254) {
	// encode = "Unicode";
	// } else if (flag1 == 254 && flag2 == 255) {
	// encode = "UTF-16";
	// } else if (flag1 == 239 && flag2 == 187 && flag3 == 191) {
	// encode = "UTF8";
	// } else {
	// encode = "asci";// ASCII码
	// }
	// return encode;
	// }
	/**
	 * 通过路径获取文件的内容，这个方法因为用到了字符串作为载体，为了正确读取文件（不乱码），只能读取文本文件，安全方法！
	 */
	public static String readFile(String path){
		String data = null;
		// 判断文件是否存在
		File file = new File(path);
		if(!file.exists()){
			return data;
		}
		// 获取文件编码格式
		String code = FileUtil.getFileEncode(path);
		BufferedReader reader = null;
		try{
			// 根据编码格式解析文件
			if("asci".equals(code)){
				// 这里采用GBK编码，而不用环境编码格式，因为环境默认编码不等于操作系统编码
				// code = System.getProperty("file.encoding");
				code = "GBK";
			}
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),code));
			// 读取文件内容
			StringBuffer sb = new StringBuffer();
			String lineContent = null;
			while((lineContent = reader.readLine()) != null){
				sb.append(lineContent).append("\n\r");
			}
			data = new String(sb);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(reader != null){
					reader.close();
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		return data;
	}

	/**
	 * 按照指定的路径和编码格式保存文件内容，这个方法因为用到了字符串作为载体，为了正确写入文件（不乱码），只能写入文本内容，安全方法
	 * 
	 * @param data
	 *            将要写入到文件中的字节数据
	 * @param path
	 *            文件路径,包含文件名
	 * @return boolean 当写入完毕时返回true;
	 */
	public static boolean writeFile(byte data[],String path,String code){
		boolean flag = true;
		OutputStreamWriter osw = null;
		try{
			File file = new File(path);
			if(!file.exists()){
				file = new File(file.getParent());
				if(!file.exists()){
					file.mkdirs();
				}
			}
			if("asci".equals(code)){
				code = "GBK";
			}
			osw = new OutputStreamWriter(new FileOutputStream(path),code);
			osw.write(new String(data,code));
			osw.flush();
		}
		catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		finally{
			try{
				if(osw != null){
					osw.close();
				}
			}
			catch(IOException e){
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}

	/**
	 * 从指定路径读取文件到字节数组中,对于一些非文本格式的内容可以选用这个方法 457364578634785634534
	 * 
	 * @param path
	 *            文件路径,包含文件名
	 * @return byte[] 文件字节数组
	 * 
	 */
	public static byte[] getFile(String path) throws IOException{
		FileInputStream stream = new FileInputStream(path);
		int size = stream.available();
		byte data[] = new byte[size];
		stream.read(data);
		stream.close();
		stream = null;
		return data;
	}

	/**
	 * 把字节内容写入到对应的文件，对于一些非文本的文件可以采用这个方法。
	 * 
	 * @param data
	 *            将要写入到文件中的字节数据
	 * @param path
	 *            文件路径,包含文件名
	 * @return boolean isOK 当写入完毕时返回true;
	 * @throws Exception
	 */
	public static boolean toFile(byte data[],String path) throws Exception{
		FileOutputStream out = new FileOutputStream(path);
		out.write(data);
		out.flush();
		out.close();
		out = null;
		return true;
	}

	/**
	 * 文件压缩
	 * 
	 * @param sourceFilePath
	 * @param zipFilePath
	 * @param fileName
	 * @return
	 */
	public static boolean fileToZip(String sourceFilePath,String zipFilePath,String fileName){
		boolean flag = false;
		File sourceFile = new File(sourceFilePath);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		if(sourceFile.exists() == false){
			System.out.println("待压缩的文件目录：" + sourceFilePath + "不存在.");
		}
		else{
			try{
				File zipFile = new File(zipFilePath + File.separator + fileName);
				if(zipFile.exists()){
					System.out.println(zipFilePath + "目录下存在名字为:" + fileName + "打包文件.");
				}
				else{
					File[] sourceFiles = sourceFile.listFiles();
					if(null == sourceFiles || sourceFiles.length < 1){
						System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
					}
					else{
						fos = new FileOutputStream(zipFile);
						zos = new ZipOutputStream(new BufferedOutputStream(fos));
						byte[] bufs = new byte[1024 * 10];
						for(int i = 0;i < sourceFiles.length;i++){
							try{
								// 创建ZIP实体，并添加进压缩包
								ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
								zos.putNextEntry(zipEntry);
								// 读取待压缩的文件并写进压缩包里
								fis = new FileInputStream(sourceFiles[i]);
								bis = new BufferedInputStream(fis,1024 * 10);
								int read = 0;
								while((read = bis.read(bufs,0,1024 * 10)) != -1){
									zos.write(bufs,0,read);
								}
							}
							catch(FileNotFoundException e){
								e.printStackTrace();
							}
						}
						flag = true;
					}
				}
			}
			catch(FileNotFoundException e){
				e.printStackTrace();
			}
			catch(IOException e){
				e.printStackTrace();
			}
			finally{
				// 关闭流
				try{
					if(bis != null){
						bis.close();
					}
					if(zos != null){
						zos.close();
					}
					if(fos != null){
						fos.close();
					}
					if(fis != null){
						fis.close();
					}
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	/**
	 * 根绝 url地址，下载远端资源文件
	 * @param remote
	 *            域名
	 * @param url
	 *            相对地址
	 * @param localFileRoot
	 *            本地文件跟目录
	 * @return
	 * @throws Exception 
	 */
	public static String downloadRemoteFile(String remoteUrl,String localFileRoot,String fileName) throws Exception{
		String returnFile = null;
		InputStream is = null;
		OutputStream os = null;
		try{
			// 如果目录不存在，则创建目录
			File f = new File(localFileRoot);
			if(!f.exists()){
				f.mkdirs();
			}
			// 文件绝对路径
			String fileRealPath = localFileRoot + File.separator + fileName;
			// 构造URL
			URL url = new URL(remoteUrl);
			// 打开连接
			URLConnection con = url.openConnection();
			// 设置请求超时为5s
			con.setConnectTimeout(5 * 1000);
			// 输入流
			is = con.getInputStream();
			os = new FileOutputStream(fileRealPath);
			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			while((len = is.read(bs)) != -1){
				os.write(bs,0,len);
			}
			returnFile = fileRealPath;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			try{
				// 完毕，关闭所有链接
				os.close();
				is.close();
			}
			catch(IOException e){
				throw e;
			}
		}
		return returnFile;
	}
}
