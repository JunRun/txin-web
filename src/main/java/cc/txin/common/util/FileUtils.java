package cc.txin.common.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * 描述： file工具类
 */
@Slf4j
@SuppressWarnings({ "rawtypes", "unchecked" })
public class FileUtils {
	
	/** 文件读写大小，值为{@value} */
	private static final int BUFFER_SIZE = 16 * 1024;

	/**
	 * 
	 * <b></b> 描述 ： 删除文件，boolean。
	 * 
	 * @param filePathAndName
	 * @return
	 */
	public static boolean deleteFile(String filePathAndName) {
		File myDelFile = new File(filePathAndName);
		if (!myDelFile.exists()) {
			return true;
		}
		return myDelFile.delete();
	}

	/**
	 * 
	 * <b></b> 描述 ： 删除文件后如果当前文件夹没有文件了那就再删除文件夹，boolean。
	 * 
	 * @param filePathAndName
	 * @return
	 */
	public static boolean deleteFileAndDirectory(String filePathAndName) {
		deleteFile(filePathAndName);
		filePathAndName = filePathAndName.substring(0, filePathAndName.lastIndexOf(File.separator));
		File dirFile = new File(filePathAndName);
		if (dirFile.exists() && dirFile.listFiles().length == 0) {
			return deleteDirectory(filePathAndName);
		}
		return true;
	}

	/**
	 * 
	 * <b></b> 描述 ： 删除目录（包括子目录），boolean。
	 * 
	 * @param sPath
	 * @return
	 */
	public static boolean deleteDirectory(String sPath) {
		// 如果spath 不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下所有文件（包括子目录）
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag){
					break;
				}
			} else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag){
					break;
				}
			}
		}
		if (!flag){
			return false;
		}
		// 删除当前目录
		if (dirFile.delete()) {
			return false;
		} else {
			return false;
		}
	}

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * 
	 * @param dirPath
	 *            将要删除的文件目录
	 * @return boolean 如果全部删除成功返回true，否则返回false.
	 */
	public static boolean deleteDir(String dirPath) {
		File dir = new File(dirPath);
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				// 递归删除目录中的子目录下
				boolean success = deleteDir(dir + File.separator + children[i]);
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}

	/**
	 * 
	 * <b></b> 描述 ： 获取文件大小，long。
	 * 
	 * @param path
	 * @return
	 */
	public static long getFileSize(String path) {
		return getFileSize(new File(path));
	}

	/**
	 * 描述 ： 获取文件大小，long。
	 * 
	 * @param f
	 * @return
	 */
	public static long getFileSize(File f) {
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(f);
				s = fis.available();
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return s;
	}

	/**
	 * 描述 ：获得随机文件名，保证在同一个文件下不同名 ，String。
	 * 
	 * @param fileName
	 * @param dir
	 * @return
	 */
	private static String getRandomName(String fileName, String dir) {
		// 将文件名拆分
		String[] split = fileName.split("\\.");
		String extendFile = "." + split[split.length - 1].toLowerCase();

		Random random = new Random();
		int add = random.nextInt(1000000);
		String ret = add + extendFile;
		while (isFileExist(ret, dir)) {
			add = random.nextInt(1000000);
			ret = fileName + add + extendFile;
		}
		return ret;
	}

	/**
	 * 描述 ： 判断文件是否存在，boolean。
	 * 
	 * @param fileName
	 * @param dir
	 * @return
	 */
	private static boolean isFileExist(String fileName, String dir) {
		File files = new File(dir + fileName);
		return (files.exists()) ? true : false;
	}

	/**
	 * 
	 * <b></b> 描述 ： 把源文件对象复制成到文件夹
	 * 
	 * @param srcDirName
	 *            待复制目录的目录名
	 * @param destDirName
	 *            目标目录名
	 * @param overlay
	 *            如果目标目录存在，是否覆盖
	 * @return
	 * @return 如果复制成功返回true，否则返回false
	 */
	public static boolean copyDirectory(String srcDirName, String destDirName, boolean overlay) {
		// 判断源目录是否存在
		File srcDir = new File(srcDirName);
		if (!srcDir.exists()) {
			log.debug("复制目录失败：源目录" + srcDirName + "不存在！");
			return false;
		}

		return false;
	}

	/**
	 * 
	 * <b></b> 描述 ： 把源文件对象复制成目标文件对象，void。
	 * 
	 * @param src
	 * @param dst
	 */
	public static void copy(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		if (null != in) {
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (null != out) {
			try {
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 文件转换为byte数组
	 * 
	 * @param path
	 * @return 文件不存在时返回null
	 * @throws Exception
	 */
	public static byte[] file2BetyArray(String path) throws Exception {
		return file2BetyArray(new File(path));
	}

	/**
	 * 文件转换为byte数组
	 * 
	 * @param file
	 * @return 文件不存在时返回null
	 * @throws Exception
	 */
	public static byte[] file2BetyArray(File file) throws Exception {
		FileInputStream fileInputStream = null;
		byte[] bFile = null;
		try {
			if (file.exists()) {
				bFile = new byte[(int) file.length()];
				fileInputStream = new FileInputStream(file);
				fileInputStream.read(bFile);
			}
		} catch (Exception e) {
			log.debug("file转换为byte数组出错！！！", e);
			e.printStackTrace();
		} finally {
			try {
				if (fileInputStream != null){
					fileInputStream.close();
				}
			} catch (IOException e) {
				log.debug("file转换为byte数组出错！！！", e);
				e.printStackTrace();
			}
		}
		return bFile;
	}

	/**
	 * 文件大小单位换算：把字节转换为{"B","KB","MB","GB","TB"}
	 * 
	 * @param leng
	 * @return
	 */
	public static String fileUnit(double leng) {
		String[] unit = { "B", "KB", "MB", "GB", "TB" };
		int count = 0;
		while (leng >= 1024) {
			count++;
			leng = leng / 1024;
		}
		DecimalFormat df = new DecimalFormat("#.##");
		if (count > unit.length){
			count = unit.length;
		}
		return df.format(leng) + unit[count];
	}

	/**
	 * 组装拼接文件名称
	 * 
	 * @param fjmc
	 * @param fjkzm
	 * @return
	 */
	public static String fileNameAssemble(String fjmc, String fjkzm) {
		return fjmc + "." + fjkzm;
	}

	/**
	 * 组装拼接文件名称,并转换为ISO-8859-1
	 * 
	 * @param fjmc
	 * @param fjkzm
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String fileNameAssembleISO(String fjmc, String fjkzm) throws UnsupportedEncodingException {
		String fileName = new String(fileNameAssemble(fjmc, fjkzm).getBytes("UTF-8"), "ISO-8859-1");
		return fileName;
	}

	/**
	 * 组装拼接文件名称,并转换为ISO-8859-1,并加入文件大小单位
	 * 
	 * @param fjmc
	 * @param fjkzm
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String fileNameAssembleISOAndUnit(String fjmc, String fjkzm, double length) throws Exception {
		String fileName = fileNameAssembleISO(fjmc, fjkzm) + "(" + fileUnit(length) + ")";
		return fileName;
	}

	/**
	 * 组装拼接文件名称,并加入文件大小单位
	 * 
	 * @param fjmc
	 * @param fjkzm
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String fileNameAssembleAndUnit(String fjmc, String fjkzm, double length) {
		String fileName = fileNameAssemble(fjmc, fjkzm) + "(" + fileUnit(length) + ")";
		return fileName;
	}

	public static final boolean isFileExists(String filePath) {
		File f = new File(filePath);
		if (f.exists()) {
			return true;
		}
		return false;
	}

	public static void mkDirectory(String path) {
		File file;
		try {
			file = new File(path);
			if (!file.exists()){
				file.mkdirs();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			file = null;
		}
	}

	public static void mkDirectory(File file) {
		try {
			if (!file.exists()){
				file.mkdirs();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		} finally {
			file = null;
		}
	}

	public static final List loadFileName(File file) {
		List fileList = new ArrayList();
		if (file.isFile()) {
			fileList.add(file);
		} else if (file.isDirectory()) {
			List subList = new ArrayList();
			File[] f = file.listFiles();
			if (f.length == 0)
				fileList.add(file);
			else {
				for (int i = 0; i < f.length; i++) {
					subList = loadFileName(f[i]);
					Iterator it = subList.iterator();
					while (it.hasNext()) {
						fileList.add(it.next());
					}
				}
			}
		}
		return fileList;
	}

	public static final String getEntryName(String base, File file) {
		File baseFile = new File(base);
		String filename = file.getPath();
		if (baseFile.getParentFile().getParentFile() == null){
			return filename.substring(baseFile.getParent().length());
		}
		return filename.substring(baseFile.getParent().length() + 1);
	}

	/**
	 * 压缩zip
	 * 
	 * @param zipFilePath
	 *            zip路径
	 * @param path
	 *            被压缩文件路径
	 * @throws IOException
	 */
	public static void zipCode(String zipFilePath, String[] path) throws IOException {
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File(zipFilePath)));
		byte[] buffer = new byte[8192];
		int length = 0;
		for (int i = 0; i < path.length; i++) {
			List fileList = new ArrayList();
			File f = new File(path[i]);
			fileList = loadFileName(f);

			if (f.isDirectory()) {
				String[] strings = f.list();
				if (strings.length == 0) {
					String st = File.separator;
					zos.putNextEntry(new ZipEntry(getEntryName(path[i], f) + st));
				} else {
					for (int j = 0; j < fileList.size(); j++) {
						File file = (File) fileList.get(j);
						zos.putNextEntry(new ZipEntry(getEntryName(path[i], file)));
						BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
						while (true) {
							length = in.read(buffer);
							if (length == -1){
								break;
							}
							zos.write(buffer, 0, length);
						}
						in.close();
					}
				}
			} else {
				for (int j = 0; j < fileList.size(); j++) {
					File file = (File) fileList.get(j);
					zos.putNextEntry(new ZipEntry(getEntryName(path[i], file)));
					BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
					while (true) {
						length = in.read(buffer);
						if (length == -1){
							break;
						}
						zos.write(buffer, 0, length);
					}
					in.close();
				}
			}
		}
		zos.close();
	}

	/**
	 * 压缩zip
	 * 
	 * @param decodeFilePath
	 *            zip路径
	 * @param path
	 *            被压缩文件路径
	 * @throws IOException
	 */
	public static void zipDecode(String decodeFilePath, String path) throws IOException {
		ZipFile zip = new ZipFile(decodeFilePath);
		Enumeration e = zip.entries();
		ZipEntry entry = null;
		byte[] buffer = new byte[8192];
		int length = -1;
		InputStream inputStream = null;
		BufferedOutputStream out = null;
		while (e.hasMoreElements()) {
			entry = (ZipEntry) e.nextElement();
			inputStream = zip.getInputStream(entry);
			String st = File.separator;
			File file = new File(path + st + entry.getName());
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			out = new BufferedOutputStream(new FileOutputStream(file));
			while (true) {
				length = inputStream.read(buffer);
				if (length == -1){
					break;
				}
				out.write(buffer, 0, length);
			}
			out.close();
			inputStream.close();
		}
		zip.close();
	}

	public static byte[] fileToByteArray(File src) {
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), 16384);
			out = new ByteArrayOutputStream(16384);
			byte[] buffer = new byte[16384];
			int len = 0;
			while ((len = in.read(buffer)) > 0){
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();

			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (out != null){
				try {
					out.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return out.toByteArray();
	}

	public static int writeFileFormByte(byte[] data, String filePath) {
		if (!isFileExists(filePath)) {
			createFile(filePath);
		}
		File file = new File(filePath);
		try {
			org.apache.commons.io.FileUtils.writeByteArrayToFile(file, data);
			return 1;
		} catch (IOException e) {
			log.error("Error in Writing File", e);
		}
		return 0;
	}

	public static final boolean createFile(String filePath) {
		boolean flag = true;
		File f = new File(filePath);

		if (f.exists()) {
			f.delete();
			try {
				f.createNewFile();
			} catch (IOException e) {
				flag = false;
			}
		} else {
			try {
				f.createNewFile();
			} catch (IOException e) {
				flag = false;
			}
		}

		return flag;
	}

	public static File createDir(String dirPath) {
		File path = new File(dirPath);
		if (!path.exists()) {
			path.mkdirs();
		}
		return path;
	}

	public static File createFile(String filePath, String fileName) throws IOException {
		File path = new File(filePath);
		if (!path.exists()) {
			path.mkdirs();
		}
		File fileWrite = new File(filePath + fileName);
		if (!fileWrite.exists()) {
			fileWrite.createNewFile();
		}
		return fileWrite;
	}

}
