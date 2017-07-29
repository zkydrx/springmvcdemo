package com.gaussic.controller;

import com.gaussic.model.BlogEntity;
import com.gaussic.model.UserEntity;
import com.gaussic.repository.BlogRepository;
import com.gaussic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by ZKY on 2017-07-27 20:24.
 */
@Controller
public class BlogController
{
    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    //查看所有的博文
    @RequestMapping(value = "/admin/blogs",method = RequestMethod.GET)
    public String showBlogs(ModelMap modelMap)
    {
        List<BlogEntity> blogList= blogRepository.findAll();
        modelMap.addAttribute("blogList",blogList);
        return "admin/blogs";
    }

    // 添加博文
    @RequestMapping(value = "/admin/blogs/add",method = RequestMethod.GET)
    public String addBlog(ModelMap modelMap)
    {
        //向jsp注入用户列表
        List<UserEntity> userList = userRepository.findAll();

        modelMap.addAttribute("userList",userList);
        return "admin/addBlog";
    }

    // 添加博文，POST请求，重定向为查看博客页面
    @RequestMapping(value = "/admin/blogs/addP",method = RequestMethod.POST)
    public String addBlogPost(@ModelAttribute("blog") BlogEntity blogEntity)
    {
        //打印博客标题
        System.out.println(blogEntity.getTitle());
        //打印博客作者
        System.out.println(blogEntity.getUserByUserId().getNickName());
        //存库
        blogRepository.saveAndFlush(blogEntity);
        //重定向地址
        return "redirect:/admin/blogs";
    }

    //查看博文详情，默认使用GET方法，method 可以缺省。
    @RequestMapping(value = "/admin/blogs/show/{id}")
    public String showBlog(@PathVariable("id") int id,ModelMap modelMap)
    {
        BlogEntity blog = blogRepository.findOne(id);
        modelMap.addAttribute("blog",blog);
        return "admin/blogDetail";

    }

   //修改博文内容，页面
    @RequestMapping("/admin/blogs/update/{id}")
    public String updateBlog(@PathVariable("id") int id,ModelMap modelMap)
    {
        BlogEntity blog = blogRepository.findOne(id);
        List<UserEntity> userList = userRepository.findAll();
        modelMap.addAttribute("blog",blog);
        modelMap.addAttribute("userList",userList);
        return "admin/updateBlog";
    }

    // 修改博客内容，POST请求
    @RequestMapping(value = "/admin/blogs/updateP",method = RequestMethod.POST)
    public String updateBlogP(@ModelAttribute("blogP") BlogEntity blogEntity)
    {
        //更新博客信息
        blogRepository.updateBlog(blogEntity.getTitle(),blogEntity.getUserByUserId().getId(),blogEntity.getContent(),blogEntity.getPubDate(),blogEntity.getId());
        blogRepository.flush();
        return "redirect:/admin/blogs";
    }

    //删除博客文章
    @RequestMapping(value = "/admin/blogs/delete/{id}" )
    public String deleteBlog(@PathVariable("id") int id )
    {
        blogRepository.delete(id);
        blogRepository.flush();
        return "redirect:/admin/blogs";
    }

    //条件查找
    @RequestMapping(value = "/admin/blogs/showUsersBlogs")
    public String showUsersBlogByUserId(@Param("byContent") String byContent, ModelMap modelMap)
    {
        List<BlogEntity> blogList = blogRepository.selectBlog(byContent);
        modelMap.addAttribute("blogList",blogList);
        return "admin/blogs";
    }

}
