package cc.txin.common.controller;

import cc.txin.common.model.AjaxResult;
import cc.txin.common.util.FileUploadUtil;
import cc.txin.common.util.MyException;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.UUID;

/**
 *  文件上传控制器
 * @author 印修河
 * @date 2017年10月17日 下午3:16:04
 */
@RestController
public class FileUploadController extends BaseController {

	/** 上传文件的根路径 */
	@Value("${upload.root.path}")
	private String uploadRootPath;

	@PostMapping("fileUpload")
	public AjaxResult uplaod(@RequestParam MultipartFile file, String uploadPath) throws Exception {
		// 保存上传的文件
		String headImg = FileUploadUtil.upload(file, uploadRootPath, uploadPath);
		return new AjaxResult(HttpStatus.OK,"上传文件成功", headImg);
	}

	/**
	 * 通过bese64上传图片
	 * @param imgData
	 * @param uploadPath
	 * @return
	 * @throws Exception
	 */
	@PostMapping("uploadImgByBase64")
	public AjaxResult uploadImgByBase64(@RequestParam String imgData, String uploadPath) throws Exception {
		String dataPrix = "";
		String data = "";
		if (imgData == null || "".equals(imgData)) {
			throw new MyException("上传失败，上传图片数据为空");
		} else {
			String[] d = imgData.split("base64,");
			if (d != null && d.length == 2) {
				dataPrix = d[0];
				data = d[1];
			} else {
				throw new MyException("上传失败，数据不合法");
			}
		}
		String suffix = "";
		if ("data:image/jpeg;".equalsIgnoreCase(dataPrix)) {
			suffix = ".jpg";
		}else if ("data:image/gif;".equalsIgnoreCase(dataPrix)) {
			suffix = ".gif";
		} else if ("data:image/png;".equalsIgnoreCase(dataPrix)) {
			suffix = ".png";
		} else {
			throw new Exception("上传图片格式不合法");
		}
		uploadPath = "/" + uploadPath + "/" + DateFormatUtils.format(new Date(), "yyyyMMdd") + "/";
		// 存储图片的物理路径
		String fileName = UUID.randomUUID().toString().replace("-", "") + suffix;
		File file = new File(uploadRootPath + uploadPath + fileName);
		if (!new File(uploadRootPath + uploadPath).exists()) {
			new File(uploadRootPath + uploadPath).mkdirs();
		}
		// 进行解密
		byte[] result = Base64.decodeBase64(data);
		FileCopyUtils.copy(result, file);

		return new AjaxResult(HttpStatus.OK,"上传文件成功", uploadPath + fileName);
	}

	public String getUploadRootPath() {
		return uploadRootPath;
	}
}
