package com.elysiaptr.cemenghuiweb.web.controller;

import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.web.dto.NewsDto;
import com.elysiaptr.cemenghuiweb.web.dto.UserDto;
import com.elysiaptr.cemenghuiweb.web.po.News;
import com.elysiaptr.cemenghuiweb.web.service.CompanyService;
import com.elysiaptr.cemenghuiweb.web.service.MeetingService;
import com.elysiaptr.cemenghuiweb.web.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    private CompanyService companyService;

    @PostMapping("/add")
    public R addNews(@RequestBody NewsDto newsDto){
        if (newsDto == null){
            return R.error().message("资讯信息不能为空");
        }
        News news=new News();
        news.setTitle(newsDto.getTitle());
        news.setImage(newsDto.getImage());
        news.setContent(newsDto.getContent());
        news.setAuthor(newsDto.getAuthor());
        news.setIntroduction(newsDto.getIntroduction());
        long companyId =Long.parseLong(newsDto.getCompany_id());
        news.setCompany(companyService.getCompanyById((companyId)));
        newsService.saveNews(news);
        return R.OK().data("提示", "新增资讯成功");
    }
    //删除
    @PostMapping("/delete")
    public R deleteNews(@RequestBody NewsDto newsDto){
        newsService.deleteNews(newsDto.getId());
        return R.OK().data("提示", "删除资讯成功");
    }
    //修改
    @PostMapping("/update")
    public R updateNews(@RequestBody NewsDto newsDto){
        News news=new News();
        news.setTitle(newsDto.getTitle());
        news.setImage(newsDto.getImage());
        news.setContent(newsDto.getContent());
        newsService.updateNews(newsDto.getId(), news);
        return R.OK().data("提示", "修改信息成功");
    }
    //查找
    @GetMapping("/search_by_list")
    public R  searchByListUser(@RequestParam(required = false)String title,
                               @RequestParam(required = false)Long id,
                               @RequestParam(required = false)String author,
                               @RequestParam(required = false)String introduction){
        List<News> newsList=newsService.getAllNews();
        if(title !=null){
            newsList=newsService.searchNewsByTitle(title);
        }
        if(id !=null){
            newsList=newsService.searchNewsById(id);
        }
        if(author !=null){
            newsList=newsService.searchNewsByAuthor(author);
        }
        if (introduction!=null){
            newsList=newsService.searchNewsByIntroduction(introduction);
        }
        return R.OK().data("newsList", newsList);
    }
    @GetMapping("/search_all")
    public R  searchAllNews(){
        List<News> newsList=newsService.getAllNews();
        return R.OK().data("newsList", newsList);
    }

}
