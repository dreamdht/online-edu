package com.flyfish.edu.controller;

import com.flyfish.edu.R.R;
import com.flyfish.edu.service.CosService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @autohr flyfish
 * @date: 2023/5/15 17:20
 * @description:
 */
@CrossOrigin
@RestController
@RequestMapping("/file")
public class CosFileUploadController {
    @Autowired
    private CosService cosService;

    @PostMapping(value="/upload")
    public R addUser(MultipartFile file) {
        String url = cosService.upload(file);
        System.out.println("========>CON"+url);
        return R.ok().data(url).message("上传成功");
    }

}
