package shou.traceability.presenter;

import android.content.Intent;

import com.google.gson.Gson;

import shou.traceability.View.ShowDataActivity;
import shou.traceability.View.UserCenterActivity;
import shou.traceability.module.FacTraceData;
import shou.traceability.tool.ConstantUtil;
import shou.traceability.tool.ErrorUtil;
import shou.traceability.tool.ResponseInfo;

public class QueryPresenter extends BasePresenter {

    private UserCenterActivity activity;
    public QueryPresenter(String url,UserCenterActivity activity) {
        super(url);
        this.activity= activity;
    }

    @Override
    public void paresJson(ResponseInfo responseInfo) {
        Intent intent=new Intent();
        intent.setClass(activity, ShowDataActivity.class);
        intent.putExtra("data",responseInfo.getData());
        intent.putExtra("which", ConstantUtil.INDEX_FAC);
        activity.startActivity(intent);
    }

    @Override
    public void showError(ErrorUtil error) {

    }
}
