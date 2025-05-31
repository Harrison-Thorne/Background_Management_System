package com.my.classtask.springboot.controller;

import com.github.pagehelper.PageInfo;
import com.my.classtask.springboot.common.Result;
import com.my.classtask.springboot.entity.Article;
import com.my.classtask.springboot.service.ArticleService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //表明返回为json
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;
    //增加数据
    @PostMapping("/add")
    public Result add(@RequestBody Article article) {
        articleService.add(article);
        return Result.success();
    }
    //删除数据
    @DeleteMapping("/deleteById/{id}")
    public Result deleteById(@PathVariable Integer id) {
        articleService.deleteById(id);
        return Result.success();
    }
    //批量删除数据
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        articleService.deleteBatch(ids);
        return Result.success();
    }
    //更新数据
    @PutMapping("/update")
    public Result update(@RequestBody Article article) {
        articleService.update(article);
        return Result.success();
    }
    //查询所有数据
    @GetMapping("/selectAll")
    public Result selectAll(Article article){
       List<Article> list= articleService.selectAll(article);
       return Result.success(list);
    }
    //根据id查询传参ParaVariable
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id){
        Article Article= articleService.selectById(id);
        return Result.success(Article);
    }
    //分页查询
    @GetMapping("/selectPage")
    public Result selectPage(Article article,
                             @RequestParam(defaultValue="1") Integer pageNum,
                             @RequestParam(defaultValue="10") Integer pageSize){
        PageInfo<Article> pageInfo=articleService.selectPage(article,pageNum,pageSize);
        return Result.success(pageInfo);
    }

}


