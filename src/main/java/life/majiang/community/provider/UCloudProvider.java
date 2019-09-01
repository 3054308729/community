package life.majiang.community.provider;

import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import cn.ucloud.ufile.exception.UfileClientException;
import cn.ucloud.ufile.exception.UfileServerException;
import com.google.common.collect.Lists;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
import life.majiang.community.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * UcloudProvider文件上传
 * Created by hp on 2019/9/1 17:15
 */
@Service
public class UCloudProvider {
    private Logger logger = LoggerFactory.getLogger(UCloudProvider.class);

    @Value("${ucloud.ufile.public-key}")
    private String publicKey;

    @Value("${ucloud.ufile.private-key}")
    private String privateKey;

    @Value("${ucloud.ufile.bucket-name}")
    private String bucketName;

    @Value("${ucloud.ufile.region}")
    private String region;

    @Value("${ucloud.ufile.proxy}")
    private String proxy;

    @Value("${ucloud.ufile.expires}")
    private Integer expires;

    /**
     * ucloud上传
     *
     * @param fileStream 获取的到文件流
     * @param mimeType   判断文件类型
     * @param fileName   文件名
     * @return
     */
    public String upload(InputStream fileStream, String mimeType, String fileName) {
        //如果重名则使用UUID避免重复
        String generateFileName;
        String[] filePaths = fileName.split("\\.");
        if (filePaths.length > 1) {
            generateFileName = UUID.randomUUID().toString() + "." + filePaths[filePaths.length - 1];
        } else {
            return null;
        }
        try {
            // Bucket相关API的授权器
            ObjectAuthorization objectAuthorization = new UfileObjectLocalAuthorization(publicKey, privateKey);
            // 对象操作需要ObjectConfig来配置您的地区和域名后缀
            ObjectConfig config = new ObjectConfig(region, proxy);
            PutObjectResultBean response = UfileClient.object(objectAuthorization, config)
                    .putObject(fileStream, mimeType)
                    .nameAs(generateFileName)
                    .toBucket(bucketName)
                    /**
                     * 是否上传校验MD5, Default = true
                     */
                    //  .withVerifyMd5(false)
                    /**
                     * 指定progress callback的间隔, Default = 每秒回调
                     */
                    //  .withProgressConfig(ProgressConfig.callbackWithPercent(10))
                    /**
                     * 配置进度监听
                     */
                    .setOnProgressListener((bytesWritten, contentLength) -> {

                    })
                    .execute();
            if (response != null && response.getRetCode() == 0) {
                //上传成功后获取预览地址
                String url = UfileClient.object(objectAuthorization, config)
                        .getDownloadUrlFromPrivateBucket(generateFileName, bucketName, expires)
                        .createUrl();
                return url;
            } else {
                throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
            }
        } catch (UfileClientException e) {
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        } catch (UfileServerException e) {
            e.printStackTrace();
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_FAIL);
        }
    }

    /**
     * FTP图片上传
     * @param file
     * @param path
     * @return
     */
    public String upload(MultipartFile file,String path){
        //拿到上传文件的文件名
        String fileName = file.getOriginalFilename();
        //获取扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        //上传文件的名字----为了避免重复加上随机数
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        //输出日志
        logger.info("开始上传文件!上传文件的文件名为:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);

        //声明目录的file
        File fileDir = new File(path);
        //判断fileDir这个目录是否存在
        if(!fileDir.exists()){
            //赋予这个目录是可写的
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        //创建文件
        File targetFile = new File(path,uploadFileName);
        //开始上传文件
        try {
            file.transferTo(targetFile);
            //此时文件已经上传成功!

            //还要将targetFile上传到FTP服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));

            //上传完毕后删除的本地的targetFile
            targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件异常!"+e);
        }
        return targetFile.getName();
    }
}
