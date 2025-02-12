package com.sky.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@Data
@AllArgsConstructor
@Slf4j
public class AliOssUtil {

    private String endpoint;
    private String bucketName;
    private String region;

    /**
     * 生成一个唯一的 Bucket 名称
     */
    public static String generateUniqueName(String prefix) {
        // 获取当前时间
        LocalDate timestamp = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
        UUID uuid = UUID.randomUUID();
        // 连接以形成一个唯一的 Bucket 名称
        return timestamp.format(formatter) + "-" + uuid.toString().replace("-", "") + prefix;
    }

    /**
     * 文件上传
     *
     * @param content
     * @param objectName
     * @return
     */
    public String upload(byte[] content, String objectName) throws com.aliyuncs.exceptions.ClientException {

        // 创建 OSSClient 实例
        EnvironmentVariableCredentialsProvider credentialsProvider =
                CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .region(region)
                .build();

        try {
            // 2. 上传文件
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
            System.out.println("2. 文件 " + objectName + " 上传成功。");
        } finally {
            ossClient.shutdown();
        }
        return endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + objectName;
    }
}
