package com.padcmyanmar.padc7.mmnews.data.vos;

import android.arch.persistence.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class SendToVO {

    @SerializedName("send-to-id")
    private String sentToId;

    @SerializedName("sent-date")
    private String sentToDate;

    @SerializedName("acted-user")
    private ActedUserVO actedUser;

    @SerializedName("received-user")
    private ActedUserVO receivedUser;

    public void setSentToId(String sentToId) {
        this.sentToId = sentToId;
    }

    public void setSentToDate(String sentToDate) {
        this.sentToDate = sentToDate;
    }

    public void setActedUser(ActedUserVO actedUser) {
        this.actedUser = actedUser;
    }

    public void setReceivedUser(ActedUserVO receivedUser) {
        this.receivedUser = receivedUser;
    }

    public String getSentToId() {
        return sentToId;
    }

    public String getSentToDate() {
        return sentToDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }

    public ActedUserVO getReceivedUser() {
        return receivedUser;
    }
}
