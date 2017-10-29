package PitStop.Controller;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import PitStop.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WeeklyTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WeeklyTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeeklyTab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    protected TextView date;
    protected TextView datem;
    protected TextView datet;
    protected TextView datew;
    protected TextView dateth;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public WeeklyTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeeklyTab.
     */
    // TODO: Rename and change types and number of parameters
    public static WeeklyTab newInstance(String param1, String param2) {
        WeeklyTab fragment = new WeeklyTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Date date1 = (new GregorianCalendar(2017 , Calendar.OCTOBER, 30)).getTime();
        Date date2 = (new GregorianCalendar(2017 , Calendar.OCTOBER, 31)).getTime();
        Date date3 = (new GregorianCalendar(2017 , Calendar.NOVEMBER, 1)).getTime();
        Date date4 = (new GregorianCalendar(2017 , Calendar.NOVEMBER, 2)).getTime();
        Date date5 = (new GregorianCalendar(2017 , Calendar.NOVEMBER, 3)).getTime();
        Format formatter = new SimpleDateFormat("EE, MMM, d");
        String mon = formatter.format(date1);
        String tue = formatter.format(date2);
        String wed = formatter.format(date3);
        String thu = formatter.format(date4);
        String fri = formatter.format(date5);
        LayoutInflater layoutInflate = getActivity().getLayoutInflater();
        View view = layoutInflate.inflate(R.layout.fragment_weekly_tab,container,false);
        date = (TextView) view.findViewById(R.id.textView2);
        datem = (TextView) view.findViewById(R.id.textView3);
        datet = (TextView) view.findViewById(R.id.textView4);
        datew = (TextView) view.findViewById(R.id.textView5);
        dateth = (TextView) view.findViewById(R.id.textView6);
        date.setText(mon);
        datem.setText(tue);
        datet.setText(wed);
        datew.setText(thu);
        dateth.setText(fri);
        // Inflate the layout for this fragment
        return view;
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
