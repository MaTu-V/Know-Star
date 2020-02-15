package com.wd.zykt.mapper;

import com.wd.zykt.pojo.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    /**
     * 根据类别查询书籍
     * @param id
     * @return
     */
    public List<Book> selBookByClassifyId(Integer id);

    /**
     * 根据关键字查询
     * @param keyword
     * @return
     */
    public List<Book> selBookByKeyword(String keyword);

    /**
     * 查找用户收藏的书籍
     * @param userId
     * @return
     */
    public List<Book> selBookByUserId(String userId);

    /**
     * 查询所有书籍状态
     * @param status
     * @return
     */
    public List<Book> selBookByStatus(int status);

    /**
     * 查询推荐书籍
     * @return
     */
    public List<Book> selBookByNumber();

    /**
     * 上传书籍
     * @param book
     * @return
     */
    public int insBook(Book book);

    /**
     * 上传书籍封面
     * @param book
     * @return
     */
    public int updBook(Book book);

    /**
     * 点击访问量 + 1
     * @param id
     * @return
     */
    public int addCollectionByBookId(String id);
}
