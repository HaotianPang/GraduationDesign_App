package shou.traceability.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;

import shou.traceability.View.LoginActivity;
import shou.traceability.View.UserCenterActivity;
import shou.traceability.module.Token;
import shou.traceability.tool.ConstantUtil;
import shou.traceability.tool.ErrorUtil;
import shou.traceability.tool.ResponseInfo;

public class SignUpPresenter extends BasePresenter {
    private static final String TAG = "SignUpPresenter";
    private LoginActivity activity;

    public SignUpPresenter(String url,LoginActivity activity) {
        super(url);
        this.activity= activity;
    }


    @Override
    public void paresJson(ResponseInfo response) {
        Gson gson=new Gson();
        Token token = gson.fromJson(response.getData(), Token.class);
        SharedPreferences preferences = activity.getSharedPreferences("token", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", token.getToken());
        if (editor.commit()) {
            Intent intent = new Intent();
            intent.setClass(activity, UserCenterActivity.class);
            activity.startActivity(intent);
            activity.finish();
        } else {
            Toast.makeText(activity, "Token写入失败，请重新登录", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void showError(ErrorUtil error) {
        switch (error.getErrorCode()){
            case ConstantUtil.SIGNUP_ALEXIST:
                Toast.makeText(activity, "账号已存在", Toast.LENGTH_SHORT).show();
            break;
            case ConstantUtil.HTTP_ERROR:
                Toast.makeText(activity, "网络请求错误", Toast.LENGTH_SHORT).show();
            break;
        }

    }
}
