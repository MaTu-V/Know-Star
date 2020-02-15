package com.wd.zykt.controller;

import com.wd.zykt.pojo.Book;
import com.wd.zykt.service.BookService;
import com.wd.zykt.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@RestController
@RequestMapping("/book")
public class BookController extends BasicController {

    @Autowired
    BookService bookService;
    @Autowired
    ControllerJSONResult result;

    /**
     * 获取书籍信息
     *
     * @param userColId
     * @param classifyId
     * @param keyword
     * @param isSave
     * @param page
     * @param map
     * @return
     */
    @PostMapping("/getBook")
    public ControllerJSONResult getBook(@RequestParam("userColId") String userColId, @RequestParam("classifyId") Integer classifyId, @RequestParam("keyword") String keyword, @RequestParam("isSave") Integer isSave, @RequestParam(value = "page", defaultValue = "1") Integer page, Map<String, Object> map) {
        // 调用service查询所有类别
        //调用service查询所有当前类别的分类科目
        ServiceResult<PagedUtil> serviceResult = null;

        if (!StringUtils.isEmpty(userColId)) {
            //查询用户收藏书籍
            serviceResult = bookService.QueBookByUserId(userColId, page, Constant.PageHelper.PAGE_SIZE);

        } else if (classifyId == null) {
            // 根据热词查询
            serviceResult = bookService.QueBookByKeyword(keyword, isSave, page, Constant.PageHelper.PAGE_SIZE);
        } else {
            // 根据分类id查询
            serviceResult = bookService.QueBookByClassifyId(classifyId, page, Constant.PageHelper.PAGE_SIZE);
        }

        return serviceResult.isSuccess() ? result.ok(serviceResult.getData()) : result.error(serviceResult.getCode(), serviceResult.getMsg());
    }


    /**
     * 获取推荐书籍
     *
     * @param recommend
     * @param page
     * @return
     */
    @PostMapping("/getBookInfo")
    public ControllerJSONResult getBook(@RequestParam("recommend") boolean recommend, @RequestParam(value = "page", defaultValue = "1") Integer page) {
        ServiceResult<PagedUtil> serviceResult = null;
        if (!StringUtils.isEmpty(recommend) && recommend) {
            serviceResult = bookService.QueBookByNumber(page, Constant.PageHelper.PAGE_SIZE);
        }
        return serviceResult.isSuccess() ? result.ok(serviceResult.getData()) : result.error(serviceResult.getCode(), serviceResult.getMsg());
    }

    /**
     * 上传书籍
     *
     * @param book
     * @return
     */
    @PostMapping("/addBook")
    public ControllerJSONResult addBook(@RequestBody Book book) {
        ServiceResult<Boolean> serviceResult = bookService.insBook(book);
        return serviceResult.isSuccess() ? result.ok(serviceResult.getData()) : result.error(serviceResult.getCode(), serviceResult.getMsg());
    }

    /**
     * 上传书籍封面
     *
     * @param Img
     * @return
     */
    @PostMapping("/uploadImg")
    public ControllerJSONResult uploadImg(@RequestParam("Img") MultipartFile Img) {
        //获取当前日期
        Date now = new Date();
        // 设定日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //定义上传书籍的文件命名空间
        String fileSpace = "/zykt_books";
        // 保存到数据库的相对路径
        String uploadPathDB = "/" + dateFormat.format(now) + "/Imgs";

        // 上传书籍封面图片
        return uploadResources(Img, fileSpace, uploadPathDB);
    }

    @GetMapping("/addCollectionByBookId/{id}")
    public ControllerJSONResult addCollectionByBookId(@PathVariable String id){

        bookService.addCollectionByBookId(id);
        return result.ok();
    }
}
