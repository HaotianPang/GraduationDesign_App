package shou.traceability.View;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import shou.traceability.R;
import shou.traceability.presenter.Tokenpresenter;
import shou.traceability.tool.ConstantUtil;

/**
 * 权限检查等
 */
public class MainActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks{
    private static final String TAG = "permission request";
    private static final int PERMISSION_GRANTED=1;
    private String token;
    private Tokenpresenter tokenpresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences=getSharedPreferences("token",Context.MODE_PRIVATE);
        token=preferences.getString("token","");
        checkAndAskForPermissions();
    }
    private void toLogin(){
        Intent intent=new Intent();
        intent.setClass(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @AfterPermissionGranted(PERMISSION_GRANTED)
    private void checkAndAskForPermissions(){
        String[] permissions={Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE};
        if(EasyPermissions.hasPermissions(this,permissions)){
            if(token.equals("")){
                toLogin();
            }else{
                tokenpresenter=new Tokenpresenter(ConstantUtil.USER_TOKEN,MainActivity.this);
                RequestBody body=new FormBody.Builder()
                        .add("token",token)
                        .build();
                tokenpresenter.doRequest(body);
            }
////
//            toLogin();
        }else{
            EasyPermissions.requestPermissions(this,
                    "需要赋予权限来保证程序正常运行",PERMISSION_GRANTED,permissions);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

    /**
     * 没有权限时弹出对话框进行提示
     */
    private void showDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("提示");
        builder.setMessage("请给予权限以保证程序正常运行");
        builder.setCancelable(true);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                MainActivity.this.finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                MainActivity.this.finish();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsGranted: 权限赋予成功");
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Log.d(TAG, "onPermissionsDenied: 权限赋予失败");
        showDialog();
    }
}
