package com.my.classtask.springboot.controller;

import cn.hutool.core.io.FileUtil;
import com.my.classtask.springboot.exception.CustomException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import com.my.classtask.springboot.common.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/files")
public class FileControlloer {
    //获取到当前这个项目的根路径
    private static final String filePath=System.getProperty("user.dir")+"/files/";

    @PostMapping("/upload")
    public Result upload(MultipartFile file){//文件流接收前端发送来的文件
        String originalFilename=file.getOriginalFilename();//xxx.png
        if(!FileUtil.isDirectory(filePath)){//目录不存在先创建目录
            FileUtil.mkdir(filePath);
        }
        //提供文件存储的完整的路径 给文件名加一个唯一的标识
        String fileName= System.currentTimeMillis()+"_"+originalFilename ;
        String realPath=filePath+fileName;
        try{
            FileUtil.writeBytes(file.getBytes(),realPath);
        }catch (IOException e){
            e.printStackTrace();
            throw new CustomException("500","File Upload Failure");
        }
        String url="http://localhost:9090/files/download/" + fileName;
        //返回一个网络连接
        //http://localhost:9090/files/download/xxx.jpg
        return Result.success(url);
    }

    @GetMapping("/download/{fileName}")
    public void download(@PathVariable String fileName, HttpServletResponse response)throws IOException{
        try {
            response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            response.setContentType("application/octet-stream");
            OutputStream os= response.getOutputStream();
            //获取到文件的字节数组
            String realPath=filePath+fileName;
            byte[] bytes=FileUtil.readBytes(realPath);
            os.write(bytes);
            os.flush();
            os.close();
        }catch(IOException e){
           e.printStackTrace();
            throw new CustomException("500","File Download Failure");
        }

    }
}
