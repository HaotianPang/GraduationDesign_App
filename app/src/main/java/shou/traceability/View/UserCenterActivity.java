package shou.traceability.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import shou.traceability.R;
import shou.traceability.presenter.QueryPresenter;
import shou.traceability.tool.ConstantUtil;

public class UserCenterActivity extends BaseActivity implements View.OnClickListener{

    private static final int QR_SCAN_FACTORY=1;
    private int selectionIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        initComponent();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_uc_factory:
                selectionIndex= ConstantUtil.INDEX_FAC;
                scanQRCode();

                break;
            case R.id.btn_uc_pbtrace:
                //二维码测试
                selectionIndex= ConstantUtil.INDEX_PUB;
                scanQRCode();
                break;
            case R.id.btn_uc_rm:
//                selectionIndex= ConstantUtil.INDEX_ORI;
//                scanQRCode();
                //生成二维码
                Intent intent=new Intent();
                intent.setClass(UserCenterActivity.this,QRCodeActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void initComponent(){
        Button btn_uc_pbtrace=(Button)findViewById(R.id.btn_uc_pbtrace);
        btn_uc_pbtrace.setOnClickListener(this);
        Button btn_uc_factory=(Button)findViewById(R.id.btn_uc_factory);
        btn_uc_factory.setOnClickListener(this);
        Button btn_uc_rm=(Button)findViewById(R.id.btn_uc_rm);
        btn_uc_rm.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Gson gson=new Gson();
        // 扫描二维码/条码回传
        if (requestCode == QR_SCAN_FACTORY && resultCode == RESULT_OK) {
            if (data != null) {
                switch (selectionIndex){
                    case ConstantUtil.INDEX_FAC:
                        String content = data.getStringExtra(Constant.CODED_CONTENT);
                        QueryPresenter presenter=new QueryPresenter(ConstantUtil.QUERY_FAC,UserCenterActivity.this);
                        RequestBody body=new FormBody.Builder()
                                .add("id",content)
                                .build();
                        presenter.doRequest(body);
                        selectionIndex=0;
                    break;

                }
//                String content = data.getStringExtra(ConstantUtil.CODED_CONTENT);
//                Toast.makeText(this, "扫描的结果是："+content, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void scanQRCode(){
        Intent intent1=new Intent(this,CaptureActivity.class);
        ZxingConfig config = new ZxingConfig();
        config.setShowbottomLayout(true);//底部布局（包括闪光灯和相册）
        config.setPlayBeep(true);//是否播放提示音
        config.setShake(false);//是否震动
        config.setShowAlbum(true);//是否显示相册
        config.setShowFlashLight(true);//是否显示闪光灯
        config.setFullScreenScan(false);//是否全屏扫描  默认为true  设为false则只会在扫描框中扫描
        intent1.putExtra(Constant.INTENT_ZXING_CONFIG,config);
        startActivityForResult(intent1, QR_SCAN_FACTORY);
    }



}
