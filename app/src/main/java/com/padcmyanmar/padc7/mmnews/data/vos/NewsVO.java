package com.padcmyanmar.padc7.mmnews.data.vos;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity (tableName = "news", indices = {@Index(value = {"news_id"}, unique = true)})
public class NewsVO {

    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "new_id_pk")
    private int newIdPK;

    @ColumnInfo(name = "news_id")
    @SerializedName("news_id")
    private String newsId;

    @ColumnInfo(name = "brief")
    @SerializedName("brief")
    private String brief;

    @ColumnInfo (name = "details" )
    @SerializedName("details")
    private String details;

    private List<String> images;

    @ColumnInfo(name = "postedDate")
    private String postedDate;

    @Embedded(prefix = "pub_")
    @SerializedName("publication")
    private PublicationVO publication; // no need to set Entity

    @Ignore
    @SerializedName("favorites")
    private List<FavoriteVO> favorites;

    @Ignore
    @SerializedName("comments")
    private List<CommentVO> comments;

    @SerializedName("sent-tos")
    private List<SendToVO> sendTos;

    public String getNewsId() {
        return newsId;
    }

    public String getBrief() {
        return brief;
    }

    public String getDetails() {
        return details;
    }

    public List<String> getImages() {
        if (images == null)
            //throw new NullPointerException("Images in NewsVO shouldn't be null.");
            return new ArrayList<>();

        return images;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public PublicationVO getPublication() {
        return publication;
    }

    public List<FavoriteVO> getFavorites() {

        if (comments == null) {
            return new ArrayList<>();
        }
        return favorites;
    }

    public List<CommentVO> getComments() {
        if (comments == null) {
            return new ArrayList<>();
        }
        return comments;
    }

    public List<SendToVO> getSendTos() {
        return sendTos;
    }

    public String getHeroImage() {
        if (getImages().isEmpty()) {
            //throw new IndexOutOfBoundsException("Images in this news - " + newsId + " - is null");
            return null;
        }
        return getImages().get(0);
    }

    public int getNewIdPK() {
        return newIdPK;
    }

    public void setNewIdPK(int newIdPK) {
        this.newIdPK = newIdPK;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public void setPublication(PublicationVO publication) {
        this.publication = publication;
    }

    public void setFavorites(List<FavoriteVO> favorites) {
        this.favorites = favorites;
    }

    public void setComments(List<CommentVO> comments) {
        this.comments = comments;
    }

    public void setSendTos(List<SendToVO> sendTos) {
        this.sendTos = sendTos;
    }
}
