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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //意味着这个类中所有方法的返回值都会被直接作为 HTTP 响应体发送给客户端，并且 Spring 会尝试将返回值转换为 JSON 格式
@RequestMapping("/files")
public class FileControlloer {
    private static final String filePath=System.getProperty("user.dir")+"/files/"; //定义了所有上传文件在服务器上实际存储的根目录

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

    @GetMapping("/download/{fileName}") //HttpServletResponse response是Spring MVC 注入的 Servlet API 中的响应对象。你将直接通过它来向客户端写入文件内容
    public void download(@PathVariable String fileName, HttpServletResponse response)throws IOException{
        try {
            response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            response.setContentType("application/octet-stream");//通用的二进制流类型，告诉浏览器这是一个未知类型的二进制文件，应该按字节流处理（通常意味着下载）
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

    @PostMapping("/wang/upload")
    public Map<String,Object> wangEditorUpload(MultipartFile file){
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
        // wangEditor上传图片成功后，需要返回的参数
        Map<String, Object> resMap = new HashMap<>();

        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> urlMap = new HashMap<>();
        urlMap.put("url", url);  // 请确保此处 url 变量已定义
        list.add(urlMap);

        resMap.put("errno", 0);  // 0 表示成功
        resMap.put("data", list);

        return resMap;
    }
}
