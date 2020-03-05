package shou.traceability.View.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import shou.traceability.R;
import shou.traceability.module.TraceData;

public class ShowDataAdapter extends RecyclerView.Adapter<ShowDataAdapter.ViewHolder> {
    private List<TraceData> mTraceData;

    public ShowDataAdapter(List<TraceData> mTraceData){
        this.mTraceData=mTraceData;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建recyclerView的view和并创建其viewholder
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_show_tracedata,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //给recycler当中的控件添加数据
        TraceData traceData=mTraceData.get(position);
        holder.tv_rv_tag.setText(traceData.getTag());
        holder.tv_rv_data.setText(traceData.getData());

    }

    @Override
    public int getItemCount() {
        return mTraceData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_rv_tag;
        TextView tv_rv_data;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_rv_data=(TextView)itemView.findViewById(R.id.tv_rv_data);
            tv_rv_tag=(TextView)itemView.findViewById(R.id.tv_rv_tag);
        }
    }
}
