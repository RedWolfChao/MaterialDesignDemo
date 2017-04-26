package redwolf.com.materialdesigndemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


/**
 * @作者 RedWolf
 * @时间 2017/4/25 17:12
 * @简介 MeiZiAdapter.java
 */

public class MeiZiAdapter extends RecyclerView.Adapter<MeiZiAdapter.MeiZiViewHolder> implements View.OnClickListener {
    private List<MeiZi> meiZiList;
    private Context mContext;

    private OnItemClickListener listener;

    public MeiZiAdapter(List<MeiZi> meiZiList, Context mContext) {
        this.meiZiList = meiZiList;
        this.mContext = mContext;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public MeiZiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_meizi_list, parent, false);
        view.setOnClickListener(this);
        return new MeiZiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MeiZiViewHolder holder, int position) {
        MeiZi meiZi = meiZiList.get(position);
        Glide.with(mContext).load(meiZi.getMeiZiId()).into(holder.imageView);
        holder.textView.setText(meiZi.getMeiZiName());
    }


    @Override
    public int getItemCount() {
        return meiZiList.size();
    }

    @Override
    public void onClick(View v) {
        if (listener == null)
            return;
        listener.OnItemClick(v);
    }

    static class MeiZiViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MeiZiViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(View v);
    }
}
