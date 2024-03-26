package com.yujin.spzx.manager.controller;

import com.yujin.spzx.manager.service.FileUploadService;
import com.yujin.spzx.model.vo.common.Result;
import com.yujin.spzx.model.vo.common.ResultCodeEnum;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/admin/system")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;
    @PostMapping("/fileUpload")
    public Result fileUploadService(@RequestParam(value = "file") MultipartFile multipartFile) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String fileUrl = fileUploadService.fileUpload(multipartFile);
        return Result.build(fileUrl , ResultCodeEnum.SUCCESS) ;
    }

}
