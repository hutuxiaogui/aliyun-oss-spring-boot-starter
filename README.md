# 阿里云OOS对象存储自动装配
本工具集成阿里云OSS对象存储，简化项目中使用阿里云OSS对象存储的创建过程，让对象存储开箱即用。

[Github](https://github.com/hutuxiaogui/aliyun-oss-spring-boot-starter)

[Gitee](https://gitee.com/hutuxiaogui/aliyun-oss-spring-boot-starter)

## 开始使用
jar包已推送至maven中央仓库，可通过如下方式导入 aliyun-oss-spring-boot-starter 依赖，请使用 1.0.1 及以上版本

```xml
<dependency>
  <groupId>com.hutuxiaogui</groupId>
  <artifactId>aliyun-oss-spring-boot-starter</artifactId>
  <version>{latest.version}</version>
</dependency>
```
如需要配置指定版本的 aliyun-sdk-oss 先排除依赖包上的 aliyun-sdk-oss 然后引用 指定版本的 aliyun-sdk-oss
```xml
<dependency>
<groupId>com.hutuxiaogui</groupId>
<artifactId>aliyun-oss-spring-boot-starter</artifactId>
<version>{latest.version}</version>
<exclusions>
    <exclusion>
        <groupId>com.aliyun.oss</groupId>
        <artifactId>aliyun-sdk-oss</artifactId>
    </exclusion>
</exclusions>
</dependency>
<dependency>
    <groupId>com.aliyun.oss</groupId>
    <artifactId>aliyun-sdk-oss</artifactId>
    <version>${aliyun-sdk-oss.version}</version>
</dependency>
```

在 properties 或者 yml 上配置
以 application.properties 为例

默认就是 true 当前配置可以不用设置，如不需要启动就设置为 false
```properties
#开启自动装配
aliyun.oss.enable=true
```

单实例
```properties
#开启自动装配
aliyun.oss.enable=true
#单实例
aliyun.oss.access-key-id=XXX
aliyun.oss.access-key-secret=XXX
aliyun.oss.endpoint=XXX
aliyun.oss.bucket-name=XXX
```
多实例 实例名称可自定义
```properties
#开启自动装配
aliyun.oss.enable=true
# 多实例，实例名称为aliyunOssClient1
aliyun.oss.names.aliyunOssClient1.access-key-id=XXX
aliyun.oss.names.aliyunOssClient1.access-key-secret=XXX
aliyun.oss.names.aliyunOssClient1.endpoint=XXX
aliyun.oss.names.aliyunOssClient1.bucket-name=XXX
# 多实例，实例名称为aliyunOssClient2
aliyun.oss.names.aliyunOssClient2.access-key-id=XXX
aliyun.oss.names.aliyunOssClient2.access-key-secret=XXX
aliyun.oss.names.aliyunOssClient2.endpoint=XXX
aliyun.oss.names.aliyunOssClient2.bucket-name=XXX
```
或者 共用部分配置的方式
```properties
#开启自动装配
aliyun.oss.enable=true
#aliyun.oss.access-key-id=XXX
#aliyun.oss.access-key-secret=XXX
#aliyun.oss.endpoint=XXX
# 多实例，实例名称为aliyunOssClient1
aliyun.oss.names.aliyunOssClient1.bucket-name=XXX
# 多实例，实例名称为aliyunOssClient2
aliyun.oss.names.aliyunOssClient2.bucket-name=XXX
```
说明：配置单实例就不能配置多实例只允许一种方式生效



## 高级配置

可以选配置（已配置好配置文件会自动提示，带中文） 

单实例
```properties
aliyun.oss.aliyun-oss-client-config. *=
...
```

多实例
```properties
aliyun.oss.names.aliyunOssClient1.aliyun-oss-client-config. *=
...
```
说明：配置是跟实例走的，有需要的实例可以配置上

单实例的使用的Spring Bean中注入`aliyunOssClient`对象即可。
```java
    @Autowired
    private AliyunOssClient aliyunOssClient;
```
或
```java
    @Autowired
    @Qualifier( value = "aliyunOssClient")
    private AliyunOssClient aliyunOssClient;
```

多实例的使用的Spring Bean中注入自定义名称的对象即可。
```java
    @Autowired
    @Qualifier( value = "aliyunOssClient1")
    private AliyunOssClient aliyunOssClient1;
    
    @Autowired
    @Qualifier( value = "aliyunOssClient2")
    private AliyunOssClient aliyunOssClient2;
```
## 使用说明
```java
    // 原阿里云提供的对象OSS 操作对应的API参考阿里云提供的文档
    OSS oss = aliyunOssClient.oss();
    // 使用时需要对配置的 bucketName 做拼路径操作，单独写了方法调用
    String bucketName = aliyunOssClient.getBucketName();
    // 可以获取到生成实例时添加的配置信息
    AliyunOssProperties aliyunOssProperties  =  aliyunOssClient.getAliyunOssProperties();
```

## 上传文件
```java
public void upload(){
    // 填写文件名。文件名包含路径，不包含Bucket名称。例如 testDir/test.txt。
    String filetName = "testDir/test.txt";

    String content = "Hello OSS";
    aliyunOssClient.oss().putObject(aliyunOssClient.getBucketName(), filetName, new ByteArrayInputStream(content.getBytes()));

    String url = "https://" + aliyunOssClient.getBucketName()
            + "." + aliyunOssClient.getAliyunOssProperties().getEndpoint() + "/" + filetName;
    System.out.println(url);
}
```
## 下载文件
```java
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
```





