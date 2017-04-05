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
                lines.add(new Line("龙东专线 (04:15~16:55)", "1553399810", "10638", "1", false, false));
                break;
            case 下班:
                lines.add(new Line("浦东25路 (07:35～19:10)", "1921777664", "90011", "1", false, true));
                lines.add(new Line("龙东专线 (06:00~19:00)", "1569914891", "10638", "0", false, false));
                break;
            case 上班:
                lines.add(new Line("989路 (04:40~21:00)", "2023227395", "10760", "0", false, false));
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
