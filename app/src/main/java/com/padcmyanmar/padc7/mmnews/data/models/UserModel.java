package com.padcmyanmar.padc7.mmnews.data.models;

import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;
import com.padcmyanmar.padc7.mmnews.delegates.LoginDelegate;

public interface UserModel {

    void login(String emailOrPassword, String password, LoginDelegate loginDelegate);


    void onUserLogout();

    LoginUserVO getLoginUser();



    boolean isUserdLogin();
}
