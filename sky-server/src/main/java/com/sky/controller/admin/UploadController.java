package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传控制器
 */
@Slf4j
@RestController
public class UploadController {

    /**
     * 注入阿里云OSS操作器
     */
    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * 文件上传接口
     * 处理文件上传请求，使用阿里云OSS服务进行文件存储
     *
     * @param file 用户上传的文件
     * @return 返回文件上传的结果，包括文件的访问URL
     * @throws Exception 文件上传过程中可能抛出的异常
     */
    @PostMapping("/admin/common/upload")
    public Result upload(MultipartFile file) throws Exception {
        // 记录文件上传日志
        log.info("文件上传：{}", file.getOriginalFilename());
        // 检查文件是否为空
        if (!file.isEmpty()) {
            // 获取文件原始名称
            String originalFilename = file.getOriginalFilename();
            // 获取文件扩展名
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 调用阿里云OSS操作器上传文件，并获取文件的访问URL
            String url = aliOssUtil.upload(file.getBytes(), AliOssUtil.generateUniqueName(extName));
            // 返回文件上传成功的结果，包含文件的访问URL
            return Result.success(url);
        }
        // 如果文件为空，返回上传失败的结果
        return Result.error("上传失败");
    }
}