package com.elysiaptr.cemenghuiweb.web.controller;

import com.elysiaptr.cemenghuiweb.common.entity.R;
import com.elysiaptr.cemenghuiweb.web.dto.*;
import com.elysiaptr.cemenghuiweb.web.po.Company;
import com.elysiaptr.cemenghuiweb.web.po.News;
import com.elysiaptr.cemenghuiweb.web.repo.CompanyRepository;
import com.elysiaptr.cemenghuiweb.web.service.CompanyService;
import com.elysiaptr.cemenghuiweb.web.service.MeetingService;
import com.elysiaptr.cemenghuiweb.web.service.NewsService;
import com.elysiaptr.cemenghuiweb.web.service.impl.CompanyServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public R deleteItems(@RequestBody List1Dto listDto) {
        List<Item1Dto> items = listDto.getItems();
        if (items != null && !items.isEmpty()) {
            for (Item1Dto item : items) {
                News existingItem = newsService.getNewsById(item.getId());
                if (existingItem != null && existingItem.getTitle().equals(item.getTitle())){
                    // 调用服务层删除对应的 item
                    newsService.deleteNews(item.getId());
                } else {
                    return R.error().data("提示", "ID 为 " + item.getId() + " 的项目不存在或名称不匹配");
                }
            }
            return R.OK().data("提示", "批量删除成功");
        } else {
            return R.error().data("提示", "请选择需要删除的项目");
        }
    }
    //修改
    @PostMapping("/update")
    public R updateNews(@RequestBody NewsDto newsDto){
        newsService.updateNews(newsDto.getId(), newsDto);
        return R.OK().data("提示", "修改信息成功");
    }
    //查找
    @GetMapping("/search_by_list")
    public R searchByListUser(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String introduction,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {

        // 先进行筛选
        List<News> newsList = newsService.getAllNews();
        if (title != null) {
            newsList = newsService.searchNewsByTitle(title,newsList);
        }
        if (id != null) {
            newsList = newsService.searchNewsById(id,newsList);
        }
        if (author != null) {
            newsList = newsService.searchNewsByAuthor(author,newsList);
        }
        if (introduction != null) {
            newsList = newsService.searchNewsByIntroduction(introduction,newsList);
        }

        // 再进行分页
        Pageable pageable = PageRequest.of(page, size);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), newsList.size());
        List<News> pageList = newsList.subList(start, end);

        List<NewsDto> newsDtos = pageList.stream()
                .map(news -> {
                    NewsDto dto = new NewsDto();
                    dto.setId(news.getId());
                    dto.setTitle(news.getTitle());
                    dto.setAuthor(news.getAuthor());
                    dto.setIntroduction(news.getIntroduction());
                    return dto;
                })
                .collect(Collectors.toList());

        Page<NewsDto> newsDtoPage = new PageImpl<>(newsDtos, pageable, newsList.size());

        return R.OK().data("newsList", newsDtoPage);
    }
    @GetMapping("/search_page")
    public R searchPage(@RequestParam(required = false, defaultValue = "0") int page,
                        @RequestParam(required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<News> newsPage = newsService.getNewsByPage(pageable.getPageNumber(),pageable.getPageSize());

        List<NewsDto> newsDtos = newsPage.getContent().stream()
                .map(news -> {
                    NewsDto dto = new NewsDto();
                    dto.setId(news.getId());
                    dto.setTitle(news.getTitle());
                    dto.setAuthor(news.getAuthor());
                    dto.setIntroduction(news.getIntroduction());
                    return dto;
                })
                .collect(Collectors.toList());

        Page<NewsDto> newsDtoPage = new PageImpl<>(newsDtos, pageable, newsPage.getTotalElements());

        return R.OK().data("newsList", newsDtoPage);
    }

    


}
