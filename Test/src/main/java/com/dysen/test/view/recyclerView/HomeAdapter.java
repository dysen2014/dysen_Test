package com.dysen.test.view.recyclerView;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dysen.lib.util.MyRandom;
import com.dysen.lib.util.Utils;
import com.dysen.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dy on 2017/1/13.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    Context context;
    ArrayList<Utils.SampleModel> sampleData;

    public HomeAdapter(Context context, ArrayList<Utils.SampleModel> sampleData) {
        this.context = context;
        this.sampleData = sampleData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_recycler_view_item,
                parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        holder.tv.setText(""+sampleData.get(position).getSampleText());
//        holder.tv.setHeight(MyRandom.random2Int(30, 200));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder.tv.setElevation(15f);
        }
    }

    @Override
    public int getItemCount()
    {
        return sampleData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView tv;

        public MyViewHolder(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }
}
