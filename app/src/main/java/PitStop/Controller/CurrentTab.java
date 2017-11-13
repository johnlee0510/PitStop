package PitStop.Controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import PitStop.Model.GetJsonImage;
import PitStop.Model.ImageListAdapter;
import PitStop.R;

import static PitStop.Model.URLs.URL_TRUCK_IMAGE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CurrentTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CurrentTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentTab extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView listView;
    public GetJsonImage getJsonObj;
    ImageListAdapter imageListAdapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CurrentTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrentTab.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentTab newInstance(String param1, String param2) {
        CurrentTab fragment = new CurrentTab();
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
        LayoutInflater layoutInflate = getActivity().getLayoutInflater();
        View view = layoutInflate.inflate(R.layout.fragment_current_tab,container,false);
        listView = (ListView) view.findViewById(R.id.lst);
        getURLs();
        //shows the list of food trucks
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                // User chose the "Settings" item, show the app settings UI...
                Intent intent =  new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_order:
                // User chose the "Logout" action, mark the current item
                this.startActivity(new Intent(getActivity(), OrderActivity.class));
                return true;

            case R.id.action_logout:
                // User chose the "Logout" action, mark the current item
                Toast.makeText(getContext(), "Sign off, good bye", Toast.LENGTH_SHORT).show();
                this.startActivity(new Intent(getActivity(), WelcomeScreen.class));
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
    //Get FoodTYpe
    private void getImages() {
        class GetImages extends AsyncTask<Void, Void, Void> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "Loading Menu", "Please wait...", false, false);
            }
            @Override
            protected void onPostExecute(Void v) {
                super.onPostExecute(v);
                loading.dismiss();
                imageListAdapter = new ImageListAdapter(getActivity(), getJsonObj.Truck_Name ,getJsonObj.bitmaps );
                listView.setAdapter(imageListAdapter);
            }
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    getJsonObj.getAllImages();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        GetImages getImages = new GetImages();
        getImages.execute();
    }
    private void getURLs() {
        class GetURLs extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(), "Loading...", "Please Wait...", true, true);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                getJsonObj = new GetJsonImage(s);
                getImages();
            }
            @Override
            protected String doInBackground(String... strings) {
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetURLs gu = new GetURLs();
        gu.execute(URL_TRUCK_IMAGE);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
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
