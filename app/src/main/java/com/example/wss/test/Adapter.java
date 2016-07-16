package com.example.wss.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wss on 5/12/16.
 */
public class Adapter extends RecyclerView.Adapter {
    private Context context;
    private List<Integer> imageList;

    public Adapter(Context context, List<Integer> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_list_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).upDate(imageList.get(position), position);
    }

    public void updata() {
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private Button imageView;
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (Button) itemView.findViewById(R.id.button);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }

        public void upDate(int rds, int position) {
            textView.setText(String.valueOf(position));
        }
    }
}
