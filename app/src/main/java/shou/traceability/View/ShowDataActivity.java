package shou.traceability.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import shou.traceability.R;
import shou.traceability.View.adapter.ShowDataAdapter;
import shou.traceability.module.FacTraceData;
import shou.traceability.module.TraceData;
import shou.traceability.tool.ConstantUtil;

public class ShowDataActivity extends BaseActivity {
    private List<TraceData> list=new ArrayList<>();
    private int which;
    private String json;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        Intent intent=getIntent();
        which=intent.getIntExtra("which", ConstantUtil.HTTP_ERROR);
        json=intent.getStringExtra("data");
        switchMethod();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.rv_showdata);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ShowDataAdapter adapter=new ShowDataAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    private void switchMethod(){
        switch (which){
            case ConstantUtil.INDEX_FAC:
                //加工溯源显示
                initFacList();
            break;

        }
    }

    private void initFacList(){
        Gson gson=new Gson();
        FacTraceData data=gson.fromJson(json,FacTraceData.class);
        TraceData item1=new TraceData("加工批号",data.getFactNumber());
        list.add(item1);
        TraceData item2 = new TraceData("加工方式",data.getFactoryMethod());
        list.add(item2);
        TraceData item3 = new TraceData("制单人",data.getSigniture());
        list.add(item3);
        TraceData item4 = new TraceData("制单日期",data.getListDate());
        list.add(item4);
        TraceData item5= new TraceData("产品名称",data.getProducitonName());
        list.add(item5);
        TraceData item6= new TraceData("执行标准",data.getExcutesStander());
        list.add(item6);
        TraceData item7= new TraceData("加工企业名称",data.getFacotryname());
        list.add(item7);
        TraceData item8=new TraceData("加工企业编号",data.getFactoryNumber());
        list.add(item8);
        TraceData item9=new TraceData("生产许可证编号",data.getLicenceNumber());
        list.add(item9);
        TraceData item10=new TraceData("生产许可证编号",data.getFactoryClass());
        list.add(item10);
        TraceData item11=new TraceData("鱼源基地编号",data.getOriginNumber());
        list.add(item11);
        TraceData item12 = new TraceData("规格",data.getSize());
        list.add(item12);
    }


}
