package com.demo.controller;

import com.alicp.jetcache.Cache;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.controller.result.Code;
import com.demo.controller.result.JsonResult;
import com.demo.pojo.Book;
import com.demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;


    //新增图书
    @PostMapping
    public JsonResult<Boolean> addBook(@RequestBody Book book){

        boolean bool = bookService.addBook(book);

        return new JsonResult<>(bool? Code.SAVE_OK:Code.SAVE_ERR,bool);
    }

    //修改图书
    @PutMapping
    public JsonResult<Boolean> updateBook(@RequestBody Book book){

        boolean bool = bookService.updateBook(book);

        return new JsonResult<>(bool? Code.SAVE_OK:Code.SAVE_ERR,bool);
    }

    //删除图书
    @DeleteMapping("/{id}")
    public JsonResult<Boolean> deleteBookById(@PathVariable Integer id){

        boolean bool = bookService.deleteBookById(id);

        return new JsonResult<>(bool? Code.SAVE_OK:Code.SAVE_ERR,bool);
    }

    //按图书ID查询
    @GetMapping("/{id}")
    public JsonResult<Book> findBookById(@PathVariable Integer id){

        Book book = bookService.findBookById(id);

        int code = book!=null?Code.GET_OK:Code.GET_ERR;

        String msg = book!=null?"success":"fail";

        return new JsonResult<>(code,book,msg);
    }

    //按图书名称、类型、描述条件分页查询
    @GetMapping("/{currentPage}/{pageSize}")
    public JsonResult<IPage<Book>> findBooksByOpeions(@PathVariable Integer currentPage,
                                                      @PathVariable Integer pageSize,
                                                      Book book){
        IPage<Book> booksByOpeions = bookService.findBooksByOpeions(currentPage, pageSize, book);

        int code = booksByOpeions != null ? Code.GET_OK : Code.GET_ERR;

        String msg = booksByOpeions.getRecords() != null ? "success":"fail";

        return new JsonResult<>(code,booksByOpeions,msg);
    }

}
