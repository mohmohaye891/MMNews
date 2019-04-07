package com.padcmyanmar.padc7.mmnews.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.padc7.mmnews.data.vos.CommentVO;
import com.padcmyanmar.padc7.mmnews.data.vos.FavoriteVO;
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;

import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class NewsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract long[] insertNews(List<NewsVO> newsList);


    @Query("SELECT * FROM news WHERE news_id = :newsId")
    public abstract NewsVO getNewsById(String newsId);


    @Query("SELECT * FROM news ORDER BY new_id_pk DESC")
    public abstract List<NewsVO> getAllNews();


    @Query("SELECT * FROM news ORDER BY new_id_pk DESC LIMIT 1") // return upper one
    public abstract NewsVO getLatestNews();

    /**
     * Save news after preparing the foreign key constraint between NewsVO & CommentVO.
     * @param newsList
     * @return size of the news that are being saved
     */

    public int saveNews(List<NewsVO> newsList, CommentDao commentDao, FavoriteDao favoriteDao) {
        //Data preparatio for foreign key constraint

        List<CommentVO> allComments = new ArrayList<>();
        List<FavoriteVO> allFavorites = new ArrayList<>();

        for (NewsVO news : newsList) {
            for (CommentVO comment : news.getComments()) {
                comment.setNewsId(news.getNewsId());
            }

            for (FavoriteVO favorite : news.getFavorites()) {
                favorite.setNewsId(news.getNewsId());
            }
            allComments.addAll(news.getComments());
            allFavorites.addAll(news.getFavorites());

        }
        long[] ids = insertNews(newsList);

        long[] commentId = commentDao.insertComment(allComments);
        long[] favoriteId = favoriteDao.insertFavorite(allFavorites);

        return ids.length;
    }


    /**
     *
     * @param newsId
     * @param commentDao
     * @return
     */
    public NewsVO loadNewsById(String newsId, CommentDao commentDao) {
        NewsVO news = getNewsById(newsId);
        List<CommentVO> commentList = commentDao.getCommentsByNewsId(newsId);
        news.setComments(commentList);

        return news;
    }
}
