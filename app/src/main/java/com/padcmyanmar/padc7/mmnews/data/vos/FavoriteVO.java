package com.padcmyanmar.padc7.mmnews.data.vos;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "favorites",
        foreignKeys = @ForeignKey(entity = NewsVO.class,
                parentColumns = "news_id",
        childColumns = "news_id"), indices = {@Index(value = {"news_id"}, unique = true)})
public class FavoriteVO {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("favorite-id_pk")
    private long favoriteIdPk;

    @SerializedName("favorite-id")
    @ColumnInfo (name = "favorite_id")
    private String favoriteId;

    @SerializedName("favorite-date")
    @ColumnInfo (name = "favorite_date")
    private String favoriteDate;

    @Embedded
    @SerializedName("acted-user")
    private ActedUserVO actedUser;

    @ColumnInfo(name = "news_id")
    private String newsId;

    public String getNewsId() {
        return newsId;
    }

    public long getFavoriteIdPk() {
        return favoriteIdPk;
    }

    public String getFavoriteId() {
        return favoriteId;
    }

    public String getFavoriteDate() {
        return favoriteDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }

    public void setFavoriteId(String favoriteId) {
        this.favoriteId = favoriteId;
    }

    public void setFavoriteDate(String favoriteDate) {
        this.favoriteDate = favoriteDate;
    }

    public void setActedUser(ActedUserVO actedUser) {
        this.actedUser = actedUser;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public void setFavoriteIdPk(long favoriteIdPk) {
        this.favoriteIdPk = favoriteIdPk;
    }
}
