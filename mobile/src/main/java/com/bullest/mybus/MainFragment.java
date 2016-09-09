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
    public List<Line> lines = new ArrayList<>();

    private MyLocation currentLocation;

    private OnFragmentInteractionListener mListener;

    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter lineAdapter;
    private RecyclerView lineRecycelerView;

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
        initCardNumber();

        View rootView = inflater.inflate(
                R.layout.fragment_main, container, false);

        lineRecycelerView = (RecyclerView) rootView.findViewById(R.id.bus_recycler_view);
        mLayoutManager = new LinearLayoutManager(getContext());
        lineAdapter = new LineRecyclerAdapter(lines);
        lineRecycelerView.setLayoutManager(mLayoutManager);
        lineRecycelerView.setAdapter(lineAdapter);

        return rootView;
    }

    private void initCardNumber() {
        lines.clear();
        switch (currentLocation) {
            case 家:
                lines.add(new Line("张江1路 (06:30~21:30)", "1939537921", "12085", "0", false, true));
                lines.add(new Line("浦东2路 (06:20~21:00)", "1921777664", "12213", "1", false, true));
                lines.add(new Line("上川专线 (05:00:22:30)", "20", "505600", "true", true, false));
                lines.add(new Line("浦东11路 (04:45~21:10)", "15", "751512", "true", true, false));
                break;
            case 张江地铁:
                lines.add(new Line("张江1路 (06:30~22:05)", "1921777664", "12085", "0", false, false));
                break;
            case 德国中心:
                lines.add(new Line("浦东11路 (06:00~22:35)", "13", "751512", "false", true, false));
                break;
            case 码头:
                lines.add(new Line("81路 (04:40~23:50)", "2357788672", "10065", "1", false, false));
                break;
            case 唐镇地铁:
                lines.add(new Line("浦东2路 (07:05:21:30)", "1973026819", "12213", "0", false, false));
                break;
            case 长泰:
                lines.add(new Line("张江1路 (06:30~22:05)", "1938751490", "12085", "1", false, true));
                lines.add(new Line("浦东11路 (06:00~22:35)", "17", "751512", "false", true, false));
                break;
            case 萨莉亚:
                lines.add(new Line("浦东2路 (07:05~21:30)", "2073952270", "12213", "0", false, true));
                break;
            case 浦东大道:
                lines.add(new Line("上川专线 (05:20~23:59)", "13", "505600", "false", true, false));
                lines.add(new Line("81路 (04:44~23:00)", "2173239297", "10065", "0", false, false));
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

    }

}
