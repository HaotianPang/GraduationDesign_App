package shou.traceability.View;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import shou.traceability.R;
import shou.traceability.module.AccountData;
import shou.traceability.presenter.SignInPresenter;
import shou.traceability.presenter.SignUpPresenter;
import shou.traceability.tool.ConstantUtil;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    //控件
    private EditText edt_login_username;
    private EditText edt_login_pass;
    private SignUpPresenter presenter;
    private SignInPresenter signInPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponent();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login_in:
                String username=edt_login_username.getText().toString();
                String password=edt_login_pass.getText().toString();
                if(username.equals("")||password.equals("")){
                    Toast.makeText(this, "请输入账号或密码", Toast.LENGTH_SHORT).show();
                }else{
                    AccountData accountData=new AccountData(username,password);
                    Gson gson=new Gson();
                    String userInfo=gson.toJson(accountData);
                    RequestBody body=new FormBody.Builder()
                            .add("UserInfo",userInfo)
                            .build();
                    signInPresenter =new SignInPresenter(ConstantUtil.USER_LOG_IN,LoginActivity.this);
                    signInPresenter.doRequest(body);
                }
                break;
            case R.id.btn_login_nin:
                Log.d("BasePresenter", "onClick: +++++++++");
                String username2=edt_login_username.getText().toString();
                String password2=edt_login_pass.getText().toString();
                AccountData accountData=new AccountData(username2,password2);
                //presenter
                Gson gson=new Gson();
                String data=gson.toJson(accountData);
                SignUpPresenter presenter=new SignUpPresenter(ConstantUtil.USER_SIGN_UP,LoginActivity.this);
                RequestBody body= new FormBody.Builder()
                        .add("UserData",data)
                        .build();
                presenter.doRequest(body);
                break;
        }
    }

    @Override
    protected void initComponent(){
        edt_login_username=(EditText)findViewById(R.id.edt_login_username);
        edt_login_pass=(EditText)findViewById(R.id.edt_login_pass);
        Button btn_login_in = (Button) findViewById(R.id.btn_login_in);
        btn_login_in.setOnClickListener(this);
        Button btn_login_nin = (Button) findViewById(R.id.btn_login_nin);
        btn_login_nin.setOnClickListener(this);
    }
}
