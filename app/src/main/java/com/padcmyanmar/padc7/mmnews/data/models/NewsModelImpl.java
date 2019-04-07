package com.padcmyanmar.padc7.mmnews.data.models;

import android.content.Context;
import android.support.annotation.Nullable;

import com.padcmyanmar.padc7.mmnews.data.vos.CommentVO;
import com.padcmyanmar.padc7.mmnews.data.vos.FavoriteVO;
import com.padcmyanmar.padc7.mmnews.data.vos.NewsVO;
import com.padcmyanmar.padc7.mmnews.data.vos.SendToVO;
import com.padcmyanmar.padc7.mmnews.delegates.GetNewsDelegate;
import com.padcmyanmar.padc7.mmnews.network.NewsDataAgent;
import com.padcmyanmar.padc7.mmnews.network.RetrofitDA;
import com.padcmyanmar.padc7.mmnews.persistence.NewsDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsModelImpl extends BaseModel implements NewsModel {

    private static NewsModelImpl instance;

  //  private Map<String, NewsVO> mNews;  // cos of Room



    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

//    private NewsModelImpl() {
//       // mNews = new HashMap<>();
//        //mDataAgent = HttpUrlConnectionDA.getInstance();
//        //mDataAgent = OkHttpDA.getInstance();
//        mDataAgent = RetrofitDA.getInstance();
//    }
//



    private NewsModelImpl(Context context) {
      super(context);
    }

    public static void initNewsModel(Context context) {
        instance = new NewsModelImpl(context);
    }


    public static NewsModelImpl getInstance() {
        if (instance == null) {
            throw new RuntimeException("NewsModelImpl should have been initialized before using it");
        }
        return instance;

    }


//    public static NewsModelImpl getInstance() {
//        if (instance == null) {
//            instance = new NewsModelImpl();
//        }
//        return instance;
//    }

    @Override
    public void addCommentNews(NewsVO news, CommentVO comment) {

    }

    @Override
    public void favoriteNews(NewsVO news, FavoriteVO favorite) {

    }

    @Override
    public void sendNewsTo(NewsVO news, SendToVO sendTo) {

    }

    @Override
    public @Nullable
    List<NewsVO> getNews(final NewsDelegate newsDelegate, boolean isForce) {
       // List<NewsVO> newsList = mNewsDB.newsDao().getAllNews();
        //newsList == null || newsList.isEmpty()
        if ( mNewsDB.isNewsEmpty() || isForce) {  // cos of encapsulation
            mDataAgent.loadNews(1,
                    DUMMY_ACCESS_TOKEN,
                    new GetNewsDelegate() {
                        @Override
                        public void onSuccess(List<NewsVO> newsList) {

//                            for (NewsVO news : newsList) {
//                                mNews.put(news.getNewsId(), news);
//                            }

                            int newsIds = mNewsDB.newsDao().saveNews(newsList, mNewsDB.commentDao(), mNewsDB.favoriteDao());
                            List<NewsVO> newsListDB = mNewsDB.newsDao().getAllNews();
                            newsDelegate.onNewsFetchedFromNetwork(newsListDB);
                        }

                        @Override
                        public void onFail(String msg) {
                            newsDelegate.onErrorNewsFetch(msg);
                        }
                    });
        } else {
            List<NewsVO> newsList = mNewsDB.newsDao().getAllNews();
            return newsList;
        }

        return null;
    }
}
