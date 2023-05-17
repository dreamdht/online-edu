package com.flyfish.edu.service.impl;

import com.flyfish.edu.R.R;
import com.flyfish.edu.service.CosService;
import com.flyfish.edu.utils.DateUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.Upload;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @autohr flyfish
 * @date: 2023/5/15 17:43
 * @description:
 */
@Service
public class CosServiceImpl implements CosService {

    @Override
    public String upload(MultipartFile file) {
        COSCredentials cred = new BasicCOSCredentials
                (
                        "AKID1weEesX1PccdZTURCnEbmjW5Tfdz96qM",
                        "Z0ITceVDsOmz2vfEdu00oX6WlsphOyQj"
                );
        Region region = new Region("ap-shanghai");
        ClientConfig clientConfig = new ClientConfig(region);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        COSClient cosClient = new COSClient(cred, clientConfig);
        try {
            InputStream inputStream = file.getInputStream();
            String bucketName = "online-edu-1317112389";
            String key = UUID.randomUUID().toString().replace("-","")+
                    file.getOriginalFilename();
            String deteUrl = new DateTime().toString("yyyy/MM/dd");
            key = deteUrl+"/"+key;
            ObjectMetadata objectMetadata = new ObjectMetadata();
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(bucketName, key, inputStream, objectMetadata);
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            String url =
                    "https://online-edu-1317112389.cos.ap-shanghai.myqcloud.com/"+key;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
