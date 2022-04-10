package com.example.omborxona_rest_api.controller.admincontroller;

import com.example.omborxona_rest_api.dto.ApiResponse;
import com.example.omborxona_rest_api.service.adminservice.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/file")
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;
    //upload
    @PostMapping("/upload")
    public ApiResponse upload(MultipartHttpServletRequest request) {
        return attachmentService.upload(request);
    }

    //download
    @GetMapping("/download/{id}")
    public HttpEntity<?> download(@PathVariable Integer id) {
        return attachmentService.download(id);
    }
}
