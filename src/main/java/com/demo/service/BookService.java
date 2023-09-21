package com.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.pojo.Book;

import java.util.List;

public interface BookService {

    //新增图书
    boolean addBook(Book book);

    //修改图书
    boolean updateBook(Book book);

    //删除图书
    boolean deleteBookById(Integer id);

    //按图书ID查询
    Book findBookById(Integer id);

    //按图书名称、类型、描述条件分页查询
    IPage<Book> findBooksByOpeions(Integer currentPage,Integer pageSize,Book book);

}
