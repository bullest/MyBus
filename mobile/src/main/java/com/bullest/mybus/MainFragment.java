package com.bullest.mybus;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bullest.mybus.model.Car;
import com.bullest.mybus.model.Line;
import com.bullest.mybus.model.MyLocation;
import com.bullest.mybus.model.RealtimeBus;
import com.bullest.mybus.network.RestClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String POSITION = "position";
    private int busNumber;
    public List<Car> cars = new ArrayList<>();
    public List<Line> lines = new ArrayList<>();
    private TextView textView;

    private MyLocation currentLocation;

    private OnFragmentInteractionListener mListener;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private RecyclerView.Adapter busAdapter;
    private RecyclerView busRecycelerView;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param location your position
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(MyLocation location) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putSerializable(POSITION, location);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            currentLocation = (MyLocation) getArguments().getSerializable(POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        monitorBus();

        // The last two arguments ensure LayoutParams are inflated
        // properly.
        View rootView = inflater.inflate(
                R.layout.fragment_main, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new MyRecyclerAdapter(cars);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        busRecycelerView = (RecyclerView) rootView.findViewById(R.id.bus_recycler_view);
        mLayoutManager = new LinearLayoutManager(getContext());
        busAdapter = new BusRecyclerAdapter();

        initCardNumber();

        return rootView;
    }

    private void initCardNumber() {
        switch (currentLocation) {
            case 家:
                lines.add(new Line("张江1路", "12085", "1921777664", "0", false, true));
                lines.add(new Line("浦东2路", "12213", "1939537921", "1", false, true));
                lines.add(new Line("上川专线", "505600", "20", "true", true, false));
                lines.add(new Line("浦东11路"));
                break;
            case 张江地铁:
                busNumber = 2;
                break;
            case 德国中心:
                busNumber = 1;
                break;
            case 码头:
                break;
            case 唐镇地铁:
                break;
            case 长泰:
                break;
            case SALIYA:
                break;
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onResume() {
        super.onResume();
        monitorBus();
    }

    public void monitorBus() {
        ArrayList<Line> lines;
        switch (currentLocation) {
            case 家:
                break;
            case 张江地铁:
                busNumber = 2;
                showZhangjiangMetroBus();
                break;
            case 德国中心:
                busNumber = 1;
                showGCBus();
                break;
            case 码头:
                break;
            case 唐镇地铁:
                break;
            case 长泰:
                break;
            case SALIYA:
                break;
        }

//        final Call<RealtimeBus> buses = RestClient.getClient().realBus("12085", "1921777664", "0", new SimpleDateFormat("yyyy-MM-dd HH:MM").format(Calendar.getInstance().getTime()));
//        final Call<RealtimeBus> buses = RestClient.getClient().dispatchBus("12213", "1939537921", "1");
//        buses.enqueue(new Callback<RealtimeBus>() {
//            @Override
//            public void onResponse(Call<RealtimeBus> call, Response<RealtimeBus> response) {
//                if (response.isSuccessful()){
//                    cars.clear();
//                    cars.addAll(response.body().cars.mCarList);
//                    mAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<RealtimeBus> call, Throwable t) {
//                Log.d("aa", t.toString());
//            }
//        });
    }

    private void showZhangjiangMetroBus() {
        final Call<RealtimeBus> zhangjiang1 = RestClient.getClient().realBus("12085", "1921777664", "0");
        zhangjiang1.enqueue(new Callback<RealtimeBus>() {
            @Override
            public void onResponse(Call<RealtimeBus> call, Response<RealtimeBus> response) {
                if (response.isSuccessful()) {
                    cars.clear();
                    cars.addAll(response.body().cars.mCarList);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<RealtimeBus> call, Throwable t) {

            }
        });
    }

    private void showGCBus() {
        final Call<RealtimeBus> buses11 = RestClient.getClientV2().realBusV2("751512", "13", "false");
        buses11.enqueue(new Callback<RealtimeBus>() {
            @Override
            public void onResponse(Call<RealtimeBus> call, Response<RealtimeBus> response) {
                if (response.isSuccessful()) {
                    Log.d("API", response.toString());
                    cars.clear();
                    cars.addAll(response.body().cars.mCarList);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<RealtimeBus> call, Throwable t) {
                Log.d("API", "API error");
            }
        });
    }
}
