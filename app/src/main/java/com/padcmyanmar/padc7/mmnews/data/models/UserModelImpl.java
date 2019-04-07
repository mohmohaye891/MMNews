package com.padcmyanmar.padc7.mmnews.data.models;

import android.content.Context;
import android.util.Log;

import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;
import com.padcmyanmar.padc7.mmnews.delegates.LoginDelegate;
import com.padcmyanmar.padc7.mmnews.network.NewsDataAgent;
import com.padcmyanmar.padc7.mmnews.network.RetrofitDA;
import com.padcmyanmar.padc7.mmnews.persistence.NewsDatabase;
import com.padcmyanmar.padc7.mmnews.views.pods.LoginUserViewPod;

public class UserModelImpl extends BaseModel implements UserModel {

    private static UserModel instance;



    private LoginUserVO mLoginUser;



    private UserModelImpl(Context context) {
//        mDataAgent = RetrofitDA.getInstance();
//        mNewsDB = NewsDatabase.getDatabase(context);

        super(context);
    }

    public static void initUserModel(Context context) {
        instance = new UserModelImpl(context);
    }


    public static UserModel getInstance() {
        if (instance == null) {
            throw new RuntimeException("USerModel should have been initialized before using it");
        }
        return instance;

    }

    @Override
    public void login(String emailOrPassword, String password, final LoginDelegate loginDelegate) {
        mDataAgent.login(emailOrPassword, password, new LoginDelegate() {
            @Override
            public void onSuccess(LoginUserVO loginUser) {
                mLoginUser = loginUser;
                long userID =  mNewsDB.loginUserDao().insertLoginUser(loginUser);
                Log.d("", "userID" + userID);
                loginDelegate.onSuccess(loginUser);
            }

            @Override
            public void onFail(String msg) {
                loginDelegate.onFail(msg);
            }
        });
    }

    @Override
    public void onUserLogout() {
        mNewsDB.loginUserDao().deleteLoginUser();
    }


    @Override
    public LoginUserVO getLoginUser() {

        LoginUserVO loginUser =  mNewsDB.loginUserDao().getLoginUser();
        return loginUser;
    }

    @Override
    public boolean isUserdLogin() {
        return mNewsDB.loginUserDao().getLoginUser() != null;
    }
}
