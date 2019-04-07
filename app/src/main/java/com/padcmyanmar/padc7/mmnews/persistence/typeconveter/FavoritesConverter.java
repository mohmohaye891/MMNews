package com.padcmyanmar.padc7.mmnews.persistence.typeconveter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.padcmyanmar.padc7.mmnews.data.vos.CommentVO;
import com.padcmyanmar.padc7.mmnews.data.vos.FavoriteVO;

import java.lang.reflect.Type;
import java.util.List;

public class FavoritesConverter {

    @TypeConverter
    public static String fromListToJson (List<FavoriteVO> favorites){
        return new Gson().toJson(favorites);

    }


    @TypeConverter
    public static List<FavoriteVO> fromJsonToList(String jsonfavorite){
        Type ListType = new TypeToken<List<FavoriteVO>>(){}.getType();
        return new Gson().fromJson(jsonfavorite, ListType);
    }
}
