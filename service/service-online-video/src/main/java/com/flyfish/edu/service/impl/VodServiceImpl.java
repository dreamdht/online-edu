package com.flyfish.edu.service.impl;

import com.flyfish.edu.exception.MyException;
import com.flyfish.edu.service.VodService;
import com.flyfish.edu.utils.TencentService;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaRequest;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @autohr flyfish
 * @date: 2023/5/17 18:28
 * @description:
 */
@Service
@Slf4j
public class VodServiceImpl implements VodService {
    @Override
    public String upload() {
        VodUploadClient client = new VodUploadClient(
                TencentService.ACCESS_KEY_ID,
                TencentService.SECRET_KEY_SECURITY
        );
        // 上传请求对象
        VodUploadRequest request = new VodUploadRequest();
        // 上传文件
        request.setMediaFilePath("E:\\素材\\视频资源\\父母爱情\\海边散步.mp4");
        // 任务流
        request.setProcedure("LongVideoPreset");
        try {
            VodUploadResponse response = client.upload("ap-guangzhou", request);
            log.info("Upload FileId = {}", response.getFileId());
            String fileId = response.getFileId();
            return fileId;
        } catch (Exception e) {
            // 业务方进行异常处理
            log.error("Upload Err", e);
            throw new MyException(404,"视频上传失败");
        }
    }

    @Override
    public String remove(String tarId) {
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户 SecretId 和 SecretKey，此处还需注意密钥对的保密
            // 代码泄露可能会导致 SecretId 和 SecretKey 泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考，建议采用更安全的方式来使用密钥，请参见：https://cloud.tencent.com/document/product/1278/85305
            // 密钥可前往官网控制台 https://console.cloud.tencent.com/cam/capi 进行获取
            Credential cred = new Credential(
                    TencentService.ACCESS_KEY_ID,
                    TencentService.SECRET_KEY_SECURITY
            );
            // 实例化一个http选项，可选的，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("vod.tencentcloudapi.com");
            // 实例化一个client选项，可选的，没有特殊需求可以跳过
            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);
            // 实例化要请求产品的client对象,clientProfile是可选的
            VodClient client = new VodClient(cred, "", clientProfile);
            // 实例化一个请求对象,每个接口都会对应一个request对象
            DeleteMediaRequest req = new DeleteMediaRequest();
            req.setFileId(tarId);
            // 返回的resp是一个DeleteMediaResponse的实例，与请求对象对应
            DeleteMediaResponse resp = client.DeleteMedia(req);
            // 输出json格式的字符串回包
//            System.out.println(DeleteMediaResponse.toJsonString(resp));
            return resp.getRequestId();
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
            throw new MyException(404,"删除失败");
        }
    }
}
