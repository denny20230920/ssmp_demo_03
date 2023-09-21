package com.demo.service.impl;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.controller.result.Code;
import com.demo.exception.BusinessException;
import com.demo.mapper.BookMapper;
import com.demo.pojo.Book;
import com.demo.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public boolean addBook(Book book) {

        if (book == null){
            log.error("参数错误，数据:"+book);
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误！");
        }

        if (book.getName()==null||book.getName()==""){
            log.error("图书名称不能为空，数据:"+book);
            throw new BusinessException(Code.BUSINESS_BOOK_MNAME_NULL,"图书名称不能为空!");
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        book.setCreateTime(simpleDateFormat.format(new Date()));

        int insert =  bookMapper.insert(book);

        return insert>0;
    }

    @Override
    //修改数据后，同时修改缓存中的数据
    @CacheUpdate(area = "books",name = "book_",key = "#book.id",value = "#book")
    public boolean updateBook(Book book) {
        if (book == null){
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误！");
        }

        Book book1 = findBookById(book.getId());
        if (book1 == null){
            throw new BusinessException(Code.BUSINESS_BOOK_NOEXISTS_ERR,"图书不存在,请重试！");
        }

        int i = bookMapper.updateById(book);

        return i>0;
    }

    @Override
    //删除数据库数据后把缓存数据设置成无效
    @CacheInvalidate(area="books",name="book_",key="#id")
    public boolean deleteBookById(Integer id) {

        if (id < 0){
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误！");
        }

        Book book1 = findBookById(id);
        if (book1 == null){
            throw new BusinessException(Code.BUSINESS_BOOK_NOEXISTS_ERR,"图书不存在,请重试！");
        }

        int i = bookMapper.deleteById(id);

        return i>0;
    }

    @Override
    //使用远程缓存，存储方法的缓存数据
    @Cached(area = "books",name = "book_",key = "#id",expire = 3600)
    //每隔10秒钟更新一次缓存数据
    @CacheRefresh(refresh = 10)
    public Book findBookById(Integer id) {
        if (id < 0){
            throw new BusinessException(Code.BUSINESS_COMMON_PARAM_ERR,"参数错误！");
        }

        Book book = bookMapper.selectById(id);

        return book;
    }

    @Override
    public IPage<Book> findBooksByOpeions(Integer currentPage, Integer pageSize, Book book) {
        IPage<Book> page = new Page<>(currentPage,pageSize);

        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();

        if (book != null) {
            lqw.like(null != book.getName(), Book::getName, book.getName());
            lqw.like(null != book.getType(), Book::getType, book.getType());
            lqw.like(null != book.getDescription(), Book::getDescription, book.getDescription());
        }
        //按创建时间的倒序排列
        lqw.orderByDesc(Book::getCreateTime);

        IPage<Book> bookIPage = bookMapper.selectPage(page, lqw);

        //请求的当前页码大于查询结果的总页数，需要重新发起请求，且回到首页
        if (currentPage > bookIPage.getPages()){
            currentPage = (int)bookIPage.getPages();
            bookIPage = findBooksByOpeions(currentPage,pageSize,book);
        }

        return bookIPage;
    }
}
