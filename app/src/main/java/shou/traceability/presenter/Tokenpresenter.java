package shou.traceability.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import shou.traceability.View.LoginActivity;
import shou.traceability.View.MainActivity;
import shou.traceability.View.UserCenterActivity;
import shou.traceability.module.Token;
import shou.traceability.tool.ConstantUtil;
import shou.traceability.tool.ErrorUtil;
import shou.traceability.tool.ResponseInfo;

public class Tokenpresenter extends BasePresenter {
    private static final String TAG = "Tokenpresenter";
    private MainActivity activity;

    public Tokenpresenter(String url,MainActivity activity) {
        super(url);
        this.activity=activity;
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
            case ConstantUtil.TOKEN_NEGATIVE:
                Log.d(TAG, "showError: "+error.getMessage());
                Intent intent=new Intent();
                intent.setClass(activity, LoginActivity.class);
                activity.startActivity(intent);
                activity.finish();
            break;
            case ConstantUtil.HTTP_ERROR:
                Log.d(TAG, "showError: 网络请求错误");
            break;
        }
    }
}
