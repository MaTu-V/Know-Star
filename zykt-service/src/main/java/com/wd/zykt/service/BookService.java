package com.wd.zykt.service;

import com.wd.zykt.pojo.Book;
import com.wd.zykt.utils.PagedUtil;
import com.wd.zykt.utils.ServiceResult;

import java.util.List;

public interface BookService {


    /**
     * 返回该类别的书籍
     *
     * @param id
     * @param page
     * @param pageSize
     * @return
     */
    public ServiceResult<PagedUtil> QueBookByClassifyId(Integer id, Integer page, Integer pageSize);

    /**
     * 查询关键字
     *
     * @param keyword
     * @param page
     * @param pageSize
     * @return
     */
    public ServiceResult<PagedUtil> QueBookByKeyword(String keyword, Integer isSave, Integer page, Integer pageSize);

    /**
     * 查找用户收藏书籍
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    public ServiceResult<PagedUtil> QueBookByUserId(String userId, Integer page, Integer pageSize);

    /**
     * 查询书籍状态
     *
     * @param status
     * @param page
     * @param pageSize
     * @return
     */
    public ServiceResult<PagedUtil> QueBookByStatus(boolean status, Integer page, Integer pageSize);

    /**
     * 查询推荐书籍
     *
     * @param page
     * @param pageSize
     * @return
     */
    public ServiceResult<PagedUtil> QueBookByNumber(Integer page, Integer pageSize);

    /**
     * 上传书籍
     *
     * @param book
     * @return
     */
    public ServiceResult<Boolean> insBook(Book book);

    /**
     * 上传书籍封面
     *
     * @param book
     * @return
     */
    public ServiceResult<Boolean> updBook(Book book);

    /**
     * 点击访问量 + 1
     * @param id
     * @return
     */
    public ServiceResult<Boolean> addCollectionByBookId(String id);
}