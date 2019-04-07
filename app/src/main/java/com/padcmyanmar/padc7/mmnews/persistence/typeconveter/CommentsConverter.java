package com.padcmyanmar.padc7.mmnews.persistence.typeconveter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.padcmyanmar.padc7.mmnews.data.vos.CommentVO;

import java.lang.reflect.Type;
import java.util.List;

public class CommentsConverter {

    @TypeConverter
    public static String fromListToJson (List<CommentVO> comments){
        return new Gson().toJson(comments);

    }


    @TypeConverter
    public static List<CommentVO> fromJsonToList(String jsonComments){
        Type ListType = new TypeToken<List<CommentVO>>(){}.getType();
        return new Gson().fromJson(jsonComments, ListType);
    }
}
