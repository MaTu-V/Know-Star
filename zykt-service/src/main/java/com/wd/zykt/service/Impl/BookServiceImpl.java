package com.wd.zykt.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wd.zykt.mapper.BookMapper;
import com.wd.zykt.mapper.HotwordMapper;
import com.wd.zykt.pojo.Book;
import com.wd.zykt.service.BookService;
import com.wd.zykt.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private HotwordMapper hotwordMapper;

    /**
     * 查询类别下的书籍
     *
     * @param id
     * @param page
     * @param pageSize
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ServiceResult<PagedUtil> QueBookByClassifyId(Integer id, Integer page, Integer pageSize) {
        // 设置查询的分页条件(多表连接设计外键查询可能会使得pageInfo失效)
        Page<Object> pageData = PageHelper.startPage(page, pageSize);
        List<Book> books = bookMapper.selBookByClassifyId(id);
        return ServiceResultHelper.genResultWithDataBaseSuccess(PagedUtilHelper.genResultPagedData(books, pageData));
    }

    /**
     * 查询关键字
     *
     * @param keyword
     * @param isSave   为1保存
     * @param page
     * @param pageSize
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ServiceResult<PagedUtil> QueBookByKeyword(String keyword, Integer isSave, Integer page, Integer pageSize) {
        if (StringUtils.isEmpty(keyword) || StringUtils.isEmpty(isSave) || StringUtils.isEmpty(page) || StringUtils.isEmpty(pageSize)) {
            return ServiceResultHelper.genResultWithParamFailed();
        }

        if (isSave == 1) {
            //保存关键字
            hotwordMapper.insHotWord(keyword);
        }

        // 设置查询的分页条件(多表连接设计外键查询可能会使得pageInfo失效)
        Page<Object> pageData = PageHelper.startPage(page, pageSize);
        // 查询
        List<Book> books = bookMapper.selBookByKeyword(keyword);
        //收集数据
        return ServiceResultHelper.genResultWithDataBaseSuccess(PagedUtilHelper.genResultPagedData(books, pageData));
    }

    /**
     * 查找用户收藏书籍
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ServiceResult<PagedUtil> QueBookByUserId(String userId, Integer page, Integer pageSize) {
        // 设置查询的分页条件(多表连接设计外键查询可能会使得pageInfo失效)
        Page<Object> pageData = PageHelper.startPage(page, pageSize);
        // 查询
        List<Book> books = bookMapper.selBookByUserId(userId);
        //收集数据
        return ServiceResultHelper.genResultWithDataBaseSuccess(PagedUtilHelper.genResultPagedData(books, pageData));
    }

    /**
     * 查询所有书籍状态
     *
     * @param newsest
     * @param page
     * @param pageSize
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public ServiceResult<PagedUtil> QueBookByStatus(boolean newsest, Integer page, Integer pageSize) {
        Page<Object> pageData = PageHelper.startPage(page, pageSize);
        int status = !newsest ? 0 : 1;
        List<Book> books = bookMapper.selBookByStatus(status);
        //收集数据
        return ServiceResultHelper.genResultWithDataBaseSuccess(PagedUtilHelper.genResultPagedData(books, pageData));
    }

    /**
     * 查询推荐书籍
     *
     * @param page
     * @param pageSize
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    public ServiceResult<PagedUtil> QueBookByNumber(Integer page, Integer pageSize) {
        // 设置查询的分页条件
        Page<Object> pageData = PageHelper.startPage(page, pageSize);
        List<Book> books = bookMapper.selBookByNumber();
        //收集数据
        return ServiceResultHelper.genResultWithDataBaseSuccess(PagedUtilHelper.genResultPagedData(books, pageData));
    }

    /**
     * 上传书籍
     *
     * @param book
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public ServiceResult<Boolean> insBook(Book book) {
        if (StringUtils.isEmpty(book)) {
            return ServiceResultHelper.genResultWithParamFailed();
        }
        //设置书籍唯一id
        book.setId(UUID.randomUUID().toString());
        if (bookMapper.insBook(book) > 0) {
            return ServiceResultHelper.genResultWithDataBaseSuccess();
        }
        return ServiceResultHelper.genResultWithDataBaseFailed();
    }

    /**
     * 上传书籍封面
     *
     * @param book
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public ServiceResult<Boolean> updBook(Book book) {
        if (StringUtils.isEmpty(book)) {
            return ServiceResultHelper.genResultWithParamFailed();
        }
        if (bookMapper.updBook(book) > 0) {
            return ServiceResultHelper.genResultWithDataBaseSuccess();
        }
        return ServiceResultHelper.genResultWithDataBaseFailed();
    }

    /**
     * 点击访问量 + 1
     *
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ServiceResult<Boolean> addCollectionByBookId(String id) {
        if (bookMapper.addCollectionByBookId(id) > 0) {
            return ServiceResultHelper.genResultWithDataBaseSuccess();
        }
        return ServiceResultHelper.genResultWithDataBaseFailed();
    }
}
