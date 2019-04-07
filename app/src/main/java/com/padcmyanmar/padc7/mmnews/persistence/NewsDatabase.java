package com.padcmyanmar.padc7.mmnews.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.padcmyanmar.padc7.mmnews.data.vos.CommentVO;
import com.padcmyanmar.padc7.mmnews.data.vos.FavoriteVO;
import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;
import com.padcmyanmar.padc7.mmnews.persistence.dao.CommentDao;
import com.padcmyanmar.padc7.mmnews.persistence.dao.FavoriteDao;
import com.padcmyanmar.padc7.mmnews.persistence.dao.NewsDao;
import com.padcmyanmar.padc7.mmnews.persistence.typeconveter.CommentsConverter;
import com.padcmyanmar.padc7.mmnews.persistence.typeconveter.FavoritesConverter;
import com.padcmyanmar.padc7.mmnews.persistence.typeconveter.NewsImagesConverter;
import com.padcmyanmar.padc7.mmnews.persistence.typeconveter.SentToConverter;

import java.util.List;


@Database(entities = {LoginUserVO.class,
        NewsVO.class,
        CommentVO.class,
        FavoriteVO.class},
        version = 3) // don't forget to update version if new table create
@TypeConverters({NewsImagesConverter.class,
        SentToConverter.class}
        )

public abstract class NewsDatabase extends RoomDatabase {

    private static NewsDatabase INSTANCE;

    public abstract LoginUserDao loginUserDao();
    public abstract NewsDao newsDao();

    public abstract CommentDao commentDao();

    public abstract FavoriteDao favoriteDao();


    public static NewsDatabase getDatabase(Context context){

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context,
                    NewsDatabase.class, "NewsDatabase.DB")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();


        }

        return INSTANCE;

    }


    public boolean isNewsEmpty(){

        NewsVO news = newsDao().getLatestNews();
        //return newsList == null || newsList.isEmpty();
        return news == null;

    }

}
