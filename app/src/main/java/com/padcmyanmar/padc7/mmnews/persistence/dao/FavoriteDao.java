package com.padcmyanmar.padc7.mmnews.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.padcmyanmar.padc7.mmnews.data.vos.CommentVO;
import com.padcmyanmar.padc7.mmnews.data.vos.FavoriteVO;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertFavorite(List<FavoriteVO> comments);

    @Delete
    int deleteFavorite(List<FavoriteVO> comments);

    @Delete
    int deleteFavorite(FavoriteVO comment);

    @Query("DELETE FROM favorites WHERE news_id = :newsId")
    int deleteCommentByNewsId(String newsId);

    @Query("SELECT * FROM favorites WHERE news_id = :newsId")
    List<FavoriteVO> getFavoritesByNewsId(String newsId);
}
