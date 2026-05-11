package com.jiumai.base.common.core.controller;

import javax.servlet.http.HttpServletRequest;

import com.jiumai.base.common.core.utils.OSSUtils;
import com.jiumai.base.common.core.utils.StringUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jiumai.base.common.core.dto.FileInfoDTO;
import com.jiumai.base.common.core.dto.ResultDTO;
import com.jiumai.base.common.core.enums.ResultCodeEnum;
import com.jiumai.base.common.core.utils.CommonFuntions;
import com.jiumai.base.common.core.utils.FileUtils;

import io.swagger.annotations.Api;

@Api(tags = { "文件" })
@Controller
@RequestMapping("d-admin/file")
public class CommFileUploadController {

	/**
	 * 上传文件
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	@ResponseBody
	@PostMapping("uploadFile")
	@ApiOperation(value = "上传文件（本地）")
	public ResultDTO<String> uploadFile(MultipartFile file, HttpServletRequest request) {

		ResultDTO<String> result = new ResultDTO<>();
		if (CommonFuntions.isEmptyObject(file)) {
			return result.set(ResultCodeEnum.ERROR_INVALID_PARAMS, "文件不能为空");
		}
		String fileNameUrl = null;
		if (file != null) {
			FileInfoDTO fileInfo = FileUtils.uploadFile(file);
			fileNameUrl = fileInfo.getUrl();
		}
		if(StringUtils.isEmpty(fileNameUrl)){
			return result.set(ResultCodeEnum.FAIL, "文件上传失败！", fileNameUrl);
		} else {
			return result.set(ResultCodeEnum.SUCCESS, "文件上传成功！", fileNameUrl);
		}
	}
	
	/**
	 * 上传文件，指定路径，指定文件名
	 * 
	 * @param file
	 * @param request
	 * @return
	 */
	@ResponseBody
	@PostMapping("uploadFileRename")
	@ApiOperation(value = "上传文件，指定路径，指定文件名（本地）")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", dataType = "String", dataTypeClass = String.class, name = "directory", value = "指定目录", required = true),
			@ApiImplicitParam(paramType = "query", dataType = "String", dataTypeClass = String.class, name = "newFileName", value = "新文件名", required = true),
	})
	public ResultDTO<String> uploadFile( HttpServletRequest request,MultipartFile file,String directory,String newFileName) {

		ResultDTO<String> result = new ResultDTO<>();
		if (CommonFuntions.isEmptyObject(file)) {
			return result.set(ResultCodeEnum.ERROR_INVALID_PARAMS, "文件不能为空");
		}
		String fileNameUrl = null;
		if (file != null) {
			if(directory == null ||"null".equals(directory)) {
				return result.set(ResultCodeEnum.ERROR_INVALID_PARAMS, "保存失败！");
			}
			FileInfoDTO fileInfo = FileUtils.uploadFile(file,directory,newFileName);
			fileNameUrl = fileInfo.getUrl();
		}
		if(StringUtils.isEmpty(fileNameUrl)){
			return result.set(ResultCodeEnum.FAIL, "文件上传失败！", fileNameUrl);
		} else {
			return result.set(ResultCodeEnum.SUCCESS, "文件上传成功！", fileNameUrl);
		}
	}

	/**
	 * 上传文件
	 *
	 * @param file
	 * @param request
	 * @return
	 */
	@ResponseBody
	@PostMapping("uploadFileOSS")
	@ApiOperation(value = "上传文件（阿里OSS）")
	public ResultDTO<String> uploadFileOSS(MultipartFile file, HttpServletRequest request) {

		ResultDTO<String> result = new ResultDTO<>();
		if (CommonFuntions.isEmptyObject(file)) {
			return result.set(ResultCodeEnum.ERROR_INVALID_PARAMS, "文件不能为空");
		}
		String fileUrl = null;
		if (file != null) {
			fileUrl = OSSUtils.uploadFileInputSteam(file,null);
		}
		if(StringUtils.isEmpty(fileUrl)){
			return result.set(ResultCodeEnum.FAIL, "文件上传失败！", fileUrl);
		} else {
			return result.set(ResultCodeEnum.SUCCESS, "文件上传成功！", fileUrl);
		}
	}

}
