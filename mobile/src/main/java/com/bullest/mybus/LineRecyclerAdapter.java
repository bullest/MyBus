package com.bullest.mybus;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bullest.mybus.model.Car;
import com.bullest.mybus.model.Line;
import com.bullest.mybus.model.RealtimeBus;
import com.bullest.mybus.network.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

/**
 * Created by yunfezhang on 8/23/16.
 */
public class LineRecyclerAdapter extends RecyclerView.Adapter<LineRecyclerAdapter.ViewHolder> {
    private List<Line> mLines;

    public LineRecyclerAdapter(List<Line> lines) {
        mLines = lines;
    }

    @Override
    public LineRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.line_item, parent, false);
        LineRecyclerAdapter.ViewHolder vh = new LineRecyclerAdapter.ViewHolder(v);
        return vh;
    }

    @Override
        public void onBindViewHolder(LineRecyclerAdapter.ViewHolder holder, int position) {
        Line line = mLines.get(position);
        holder.lineName.setText(line.lineName);

        final Call<RealtimeBus> bus = getBusFromLine(line);


        final List<Car> cars = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(holder.timeRecyclerView.getContext());
        final TimeRecyclerAdapter timeAdapter = new TimeRecyclerAdapter(cars);

        holder.timeRecyclerView.setLayoutManager(mLayoutManager);
        holder.timeRecyclerView.setAdapter(timeAdapter);

        bus.enqueue(new Callback<RealtimeBus>() {
            @Override
            public void onResponse(Call<RealtimeBus> call, Response<RealtimeBus> response) {
                if (response.isSuccessful()) {
                    Log.d("API", response.toString());
                    cars.clear();
                    cars.addAll(response.body().cars.mCarList);
                    timeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<RealtimeBus> call, Throwable t) {
                Log.d("API", "API error");
            }
        });

    }

    private Call<RealtimeBus> getBusFromLine(Line line) {

        Call<RealtimeBus> bus;

        if (line.dispatch) {
            if (line.v2) {
                bus = RestClient.getClientV2().dispatchBus(line.lineId, line.terminalId, line.direction);
            } else {
                bus = RestClient.getClient().dispatchBus(line.lineId, line.terminalId, line.direction);
            }
        } else {
            if (line.v2) {
                bus = RestClient.getClientV2().realBusV2(line.lineId, line.terminalId, line.direction);
            } else {
                bus = RestClient.getClient().realBus(line.lineId, line.terminalId, line.direction);
            }
        }
        return bus;
    }

    @Override
    public int getItemCount() {
        Log.d("aa", "线路数量: " + mLines.size());
        return mLines.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView lineName;
        public RecyclerView timeRecyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            lineName = (TextView) itemView.findViewById(R.id.line_name);
            timeRecyclerView = (RecyclerView) itemView.findViewById(R.id.time_recyclerview);
        }
    }
}
