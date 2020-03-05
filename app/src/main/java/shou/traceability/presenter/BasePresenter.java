package shou.traceability.presenter;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import shou.traceability.tool.ConstantUtil;
import shou.traceability.tool.ErrorUtil;
import shou.traceability.tool.ResponseInfo;

public abstract class BasePresenter {
    private static final String TAG = "BasePresenter";
    protected OkHttpClient client;
    protected String url;

    public BasePresenter(String url){
        this.url=url;
        client= new OkHttpClient();
    }

    public void  doRequest(RequestBody body){
        Request request=new Request.Builder()
                .url(ConstantUtil.BASE_URL+url)
                .post(body)
                .build();
        Call call =client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: 网络请求异常："+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response){
                if(response.code()==200){
                    //http请求是否成功
                    Gson gson=new Gson();
                    try {
                        String tempdata=response.body().string();
                        ResponseInfo responseInfo=gson.fromJson(tempdata,ResponseInfo.class);
                        switch (responseInfo.getCode()){
                            //服务端返回的信息进行分类处理
                            case 0:
                                paresJson(responseInfo);
                            break;
                            default:
                                ErrorUtil error=new ErrorUtil();
                                error.setErrorCode(responseInfo.getCode());
                                error.setMessage(responseInfo.getMessage());
                                showError(error);
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    ErrorUtil error=new ErrorUtil();
                    error.setMessage("Http请求错误");
                    error.setErrorCode(ConstantUtil.HTTP_ERROR);
                    showError(error);

                }
            }
        });

    }
    public abstract void paresJson(ResponseInfo responseInfo);
//    public abstract void paresJson(Response response);
    public abstract void showError(ErrorUtil error);
}