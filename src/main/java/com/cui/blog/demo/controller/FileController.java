package com.cui.blog.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: FileController
 * @DESCRIPT: TODO
 * @AUTHOR: cuizhichao
 * @DATA: 2019/6/2 5:35 PM
 **/
@Controller
public class FileController {

    @ResponseBody
    @PostMapping(value = "/upload")
    public String uploadFile(String image){

        String filename = image;
        System.out.println(filename);
        return "ok";
    }

}
