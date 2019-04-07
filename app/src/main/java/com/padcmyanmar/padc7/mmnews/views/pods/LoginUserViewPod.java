package com.padcmyanmar.padc7.mmnews.views.pods;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.padcmyanmar.padc7.mmnews.R;
import com.padcmyanmar.padc7.mmnews.data.vos.LoginUserVO;
import com.padcmyanmar.padc7.mmnews.delegates.ViewPodSetDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginUserViewPod extends RelativeLayout implements ViewPodSetDelegate<LoginUserViewPod.LoginViewPodActionDelegate> {

    @BindView(R.id.iv_login_user_bg)
    ImageView ivLoginUserBg;

    @BindView(R.id.iv_login_user)
    ImageView ivLoginUser;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_phone_no)
    TextView tvPhoneNo;

    @BindView(R.id.btn_logout)
    Button btnLogOut;

    private LoginViewPodActionDelegate mDelegate;

    public LoginUserViewPod(Context context) {
        super(context);
    }

    public LoginUserViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoginUserViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);

        btnLogOut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDelegate.onTapLogout();
            }
        });
    }

    public void setData(LoginUserVO loginUser) {
        tvName.setText(loginUser.getName());
        tvPhoneNo.setText(loginUser.getPhoneNo());


    }

    @Override
    public void setDelegate(LoginViewPodActionDelegate loginViewPodActionDelegate) {
        mDelegate = loginViewPodActionDelegate;
    }


    public interface LoginViewPodActionDelegate{

        void onTapLogout();

    }
}
