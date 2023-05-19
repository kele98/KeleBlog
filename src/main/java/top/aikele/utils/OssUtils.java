package top.aikele.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyun.oss.model.VoidResult;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

/**
 * @projectName: KeleBlog
 * @package: top.aikele.utils
 * @className: OssUtils
 * @author: Kele
 * @description: TODO
 * @date: 2023/4/19 15:42
 * @version: 1.0
 */
public class OssUtils {
    static String endpoint = "oss-cn-beijing.aliyuncs.com";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    static  String accessKeyId = "LTAI5t8LMbYFAZU3unwFvnEf";
    static String accessKeySecret = "gpBdHnwpAcJJfLiVwPIgAIXsqLjjuB";
    // 填写Bucket名称，例如examplebucket。
    static String bucketName = "kele-blog";
    private static OSS createOssClient(){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        return ossClient;
    }
    //上传文件到oss
    public static String upload(String fileName,String path,byte data[]){
        OSS ossClient = null;
        try {
            ossClient = createOssClient();
            PutObjectResult objectResult = ossClient.putObject(bucketName, path+"/"+fileName, new ByteArrayInputStream(data));
            //不为空上传成功 返回文件地址
            if(!"".equals(objectResult.getETag()))
                return "https://"+bucketName+"."+endpoint+"/"+path+"/"+fileName;
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }
    //删除文件
    public static boolean delete(String file){
        OSS ossClient = null;
        try {
            ossClient = createOssClient();
            VoidResult voidResult = ossClient.deleteObject(bucketName, file);
            //不出异常就是删除成功
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return false;
    }
    //判断文件是否存在
    public static boolean exist(String file){
        OSS ossClient = null;
        try {
            ossClient = createOssClient();
            return ossClient.doesObjectExist(bucketName, file);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return false;
    }
}
