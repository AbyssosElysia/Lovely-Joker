package com.elysiaptr.cemenghuiweb.web.controller;

import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.web.dto.NewsDto;
import com.elysiaptr.cemenghuiweb.web.dto.UserDto;
import com.elysiaptr.cemenghuiweb.web.po.Company;
import com.elysiaptr.cemenghuiweb.web.po.News;
import com.elysiaptr.cemenghuiweb.web.repo.CompanyRepository;
import com.elysiaptr.cemenghuiweb.web.service.CompanyService;
import com.elysiaptr.cemenghuiweb.web.service.MeetingService;
import com.elysiaptr.cemenghuiweb.web.service.NewsService;
import com.elysiaptr.cemenghuiweb.web.service.impl.CompanyServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/open_api/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyServiceImpl companyServiceImpl;

    @PostMapping("/add")
    public R addNews(@RequestBody NewsDto newsDto){
        if (newsDto == null){
            return R.error().message("资讯信息不能为空");
        }
        News news=new News();
        try {

            BeanUtils.copyProperties(newsDto,news);
            news.setCompany(companyServiceImpl.getCompanyById(newsDto.getCompany_id()));
        }catch (Exception e) {
            e.printStackTrace();
        }

        // 通过 companyService 获取对应的 Company
        //Long CompanyId=newsDto.getCompany_id();

        Company company=companyService.getCompanyById(newsDto.getCompany_id());
        if (company == null) {
            return R.error().message("指定的公司不存在");
        }

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
        newsService.updateNews(newsDto.getId(), newsDto);
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
        List<NewsDto> newsDtos = new ArrayList<>();
        for(News news:newsList){
            NewsDto dto=new NewsDto();
           // dto.setId(news.getId());
            dto.setTitle(news.getTitle());
            dto.setAuthor(news.getAuthor());
            dto.setIntroduction(news.getIntroduction());
           // dto.setCompany_name(news.getCompany().getName());
            newsDtos.add(dto);
        }
        return R.OK().data("newsList", newsDtos);
    }

}
