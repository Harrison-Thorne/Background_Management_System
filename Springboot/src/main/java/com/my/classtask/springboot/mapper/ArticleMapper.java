package com.my.classtask.springboot.mapper;

import com.my.classtask.springboot.entity.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleMapper {
    List<Article> selectAll(Article article);
    @Select("SELECT * FROM `article` WHERE ID= #{id}")//用select或者xml中两种模式写查询
    Article selectById(Integer id);

    void insert(Article article);

    void updateById(Article article);

    @Delete("delete from `article` where id= #{id}")
    void deleteById(Integer id);


    @Select("select count(*) from article where time like '%${date}%'")
    Integer selectCountByDate(String date);
}
