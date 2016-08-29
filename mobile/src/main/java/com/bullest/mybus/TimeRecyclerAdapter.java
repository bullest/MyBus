package com.bullest.mybus;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bullest.mybus.model.Car;

import java.util.List;

/**
 * Created by yunfezhang on 8/11/16.
 */
public class TimeRecyclerAdapter extends RecyclerView.Adapter<TimeRecyclerAdapter.ViewHolder> {
    private List<Car> mCarList;

    public TimeRecyclerAdapter(List<Car> mCarList){
        this.mCarList = mCarList;
    }

    @Override
    public TimeRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bus_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(TimeRecyclerAdapter.ViewHolder holder, int position) {
        holder.timeTextView.setText(mCarList.get(position).getTime());
        if (mCarList.get(position).getStopdis()!= "null") {
            holder.disTextView.setText(mCarList.get(position).getStopdis() + "站");
        }
    }

    @Override
    public int getItemCount() {
        return mCarList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView timeTextView, disTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            timeTextView = (TextView) itemView.findViewById(R.id.timeTextView);
            disTextView = (TextView) itemView.findViewById(R.id.disTextView);
        }
    }
}
