package be.howest.nmct.android.stopafstand;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import be.howest.nmct.android.cars.*;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StopAfstandFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StopAfstandFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StopAfstandFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private EditText uwSnelheidView;
    private EditText uwReactietijdView;
    private TextView uwStopAfstandView;
    private RadioGroup uwRadiogroup;
    private Button buttonStopafstand;
    StopAfstandInfo sai = new StopAfstandInfo();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StopAfstandFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StopAfstandFragment newInstance(String param1, String param2) {
        StopAfstandFragment fragment = new StopAfstandFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public StopAfstandFragment() {
        // Required empty public constructor
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
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_stop_afstand2, container, false);
        View v = inflater.inflate(R.layout.fragment_stop_afstand,container,false);
        this.uwSnelheidView = (EditText) v.findViewById(R.id.skbSnelheid);
        this.uwReactietijdView = (EditText) v.findViewById(R.id.skbReactietijd);
        this.uwRadiogroup = (RadioGroup) v.findViewById(R.id.rdgWegdek);
        this.uwStopAfstandView = (TextView) v.findViewById(R.id.chkMeter);
        this.buttonStopafstand = (Button) v.findViewById(R.id.btnBereken);
        this.buttonStopafstand.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                toonStopAfstand(v);
            }
        });
        return v;
    }

    public void toonStopAfstand(View v){
        int snelheid = Integer.parseInt(this.uwSnelheidView.getText().toString());
        float reactietijd = Float.parseFloat(this.uwReactietijdView.getText().toString());

        sai.setSnelheid(snelheid);
        sai.setReactietijd(reactietijd);

        String wt = "nat";
        int id = uwRadiogroup.getCheckedRadioButtonId();
        if (id == R.id.radioNat) wt="droog";
        sai.setTypeWeg(wt);

        double stopAfstand = Math.round(sai.getStopAfstand());
        String stopAfstandString = Double.toString(stopAfstand);
        uwStopAfstandView.setText(stopAfstandString + " meter");

        //Testen Toast
        Toast.makeText(getActivity(),stopAfstandString + " meter", Toast.LENGTH_LONG).show();
        /*Context context = getApplicationContext();
        CharSequence text = stopAfstandString + " meter";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
/*
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
*/
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
        public void onFragmentInteraction(Uri uri);
    }

}
