package com.hutuxiaogui.autoconfigure.aliyun.oss;

import java.io.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import com.aliyun.oss.model.OSSObject;
import com.hutuxiaogui.autoconfigure.aliyun.oss.client.AliyunOssClient;

/**
 * 单实例
 */
@SpringBootTest(classes = EnableAliyunOssAutoConfiguration.class)
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class AliyunOssSpringBootStarterSingleInstanceApplicationTests {
    
    @Autowired
    @Qualifier( value = "aliyunOssClient")
    private AliyunOssClient aliyunOssClient;


    @Test
    public void upload(){
        // 填写文件名。文件名包含路径，不包含Bucket名称。例如 testDir/test.txt。
        String filetName = "testDir/test.txt";

        String content = "Hello OSS";
        aliyunOssClient.oss().putObject(aliyunOssClient.getBucketName(), filetName, new ByteArrayInputStream(content.getBytes()));

        String url = "https://" + aliyunOssClient.getBucketName()
                + "." + aliyunOssClient.getAliyunOssProperties().getEndpoint() + "/" + filetName;
        System.out.println(url);
    }

    @Test
    public void download() throws IOException {
        // 填写文件名。文件名包含路径，不包含Bucket名称。例如 testDir/test.txt。
        String filetName = "testDir/test.txt";

        // 调用 aliyunOssClient.oss().getObject 返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = aliyunOssClient.oss().getObject(aliyunOssClient.getBucketName(), filetName);
        // 调用 ossObject.getObjectContent 获取文件输入流，可读取此输入流获取其内容。
        InputStream content = ossObject.getObjectContent();

        if (content != null) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                while (true) {
                    String line = reader.readLine();
                    if (line == null) break;
                    System.out.println("\n" + line);
                }
            } finally {
                // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
                content.close();
            }
        }
    }

}
