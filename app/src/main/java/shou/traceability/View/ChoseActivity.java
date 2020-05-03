package shou.traceability.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import shou.traceability.R;
import shou.traceability.presenter.BasePresenter;

public class ChoseActivity extends BaseActivity implements View.OnClickListener{
    private Button btn_chose_toblock;
    private Button btn_chose_tonone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose);
        initComponent();
    }

    @Override
    protected void initComponent() {
        btn_chose_toblock=(Button)findViewById(R.id.btn_chose_toblock);
        btn_chose_toblock.setOnClickListener(this);
        btn_chose_tonone=(Button)findViewById(R.id.btn_chose_tonone);
        btn_chose_tonone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_chose_toblock:
                Intent intent1=new Intent();
                intent1.setClass(this,UserCenterActivity.class);
                intent1.putExtra("kinds",1);
                startActivity(intent1);
                this.finish();
                break;
            case R.id.btn_chose_tonone:
                Intent intent2=new Intent();
                intent2.setClass(this,UserCenterActivity.class);
                intent2.putExtra("kinds",2);
                startActivity(intent2);
                this.finish();
                break;
        }
    }
}