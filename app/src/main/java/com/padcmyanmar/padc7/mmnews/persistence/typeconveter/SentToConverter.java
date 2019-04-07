package com.padcmyanmar.padc7.mmnews.persistence.typeconveter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.padcmyanmar.padc7.mmnews.data.vos.SendToVO;

import java.lang.reflect.Type;
import java.util.List;

public class SentToConverter {

    @TypeConverter
    public static String fromListToJson (List<SendToVO> sentTo){
        return new Gson().toJson(sentTo);
    }


    @TypeConverter
    public static List<SendToVO> fromJsonToList(String jsonSentTo){
        Type ListType = new TypeToken<List<SendToVO>>(){}.getType();
        return new Gson().fromJson(jsonSentTo, ListType);
    }
}
