package cc.txin.common.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 *  文件上传工具类
 * @author 印修河
 * @date 2017年12月9日 下午9:42:02
 */
public class FileUploadUtil {

	/**
	 *  上传
	 * @author 印修河
	 * @date 2017年10月9日 下午9:41:56
	 * @param multipartFile 上传的文件
	 * @param rootPath 上传根路径
	 * @param uploadPath 上传路径
	 * @return
	 * @throws Exception
	 */
	public static String upload(MultipartFile multipartFile, String rootPath, String uploadPath) throws Exception{
		Assert.notNull(multipartFile, "请上送文件");
		Assert.notNull(uploadPath,"请上送上传文件路径");
		// 原始名称
		String originalFilename = multipartFile.getOriginalFilename();
		// 上传文件
		if (originalFilename.length() > 0) {
			uploadPath = uploadPath + "/" + DateFormatUtils.format(new Date(), "yyyyMMdd") + "/";
			// 判断文件夹是否存在，若不存在则创建文件夹
			if (!new File(rootPath + uploadPath).exists()) {
				new File(rootPath + uploadPath).mkdirs();
			}
			// 生成新文件名称
			String newFileName = UUID.randomUUID().toString().replace("-", "") + originalFilename.substring(originalFilename.lastIndexOf("."));
			File newFile = new File(rootPath + uploadPath + newFileName);
			// 将内存中的数据写入磁盘
			multipartFile.transferTo(newFile);
			// 将新文件名称返回
			return uploadPath + newFileName;
		}
		return null;
	}
}
