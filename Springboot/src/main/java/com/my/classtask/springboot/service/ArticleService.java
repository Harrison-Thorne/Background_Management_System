package com.my.classtask.springboot.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.classtask.springboot.entity.Account;
import com.my.classtask.springboot.entity.Article;
import com.my.classtask.springboot.exception.CustomException;
import com.my.classtask.springboot.mapper.ArticleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    public void add(Article article) {
        article.setTime(DateUtil.now());//存储当前时间
        articleMapper.insert(article);
    }
    public void deleteById(Integer id) {
        articleMapper.deleteById(id);
    }
    public void deleteBatch(List<Integer> ids) {
        for(Integer id:ids){
            this.deleteById((id));
        }
    }
    public void update(Article article) {
        articleMapper.updateById(article);
    }
    public List<Article> selectAll(Article article) {
        return articleMapper.selectAll(article);
    }
    public Article selectById(Integer id) {
        return articleMapper.selectById(id);
    }

    public PageInfo<Article> selectPage(Article article, Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Article> list=articleMapper.selectAll(article);
        return PageInfo.of(list);
    }

    public Integer selectCountByDate(String date) {
        return articleMapper.selectCountByDate(date);
    }
}
