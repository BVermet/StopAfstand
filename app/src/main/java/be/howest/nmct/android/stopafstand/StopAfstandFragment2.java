package be.howest.nmct.android.stopafstand;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.*;

import be.howest.nmct.android.cars.StopAfstandInfo;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StopAfstandFragment2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StopAfstandFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StopAfstandFragment2 extends Fragment implements OnSeekBarChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SeekBar skbSnelheid;
    private SeekBar skbReactietijd;
    private TextView textKM,textS;
    private TextView uwStopAfstandView;
    private RadioGroup uwRadiogroup;
    private Button buttonStopafstand;
    StopAfstandInfo sai = new StopAfstandInfo();

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StopAfstandFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static StopAfstandFragment2 newInstance(String param1, String param2) {
        StopAfstandFragment2 fragment = new StopAfstandFragment2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public StopAfstandFragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stop_afstand2,container,false);
        skbSnelheid = (SeekBar)v.findViewById(R.id.skbSnelheid);
        skbReactietijd = (SeekBar) v.findViewById(R.id.skbReactietijd);
        skbSnelheid.setOnSeekBarChangeListener(this);
        skbReactietijd.setOnSeekBarChangeListener(this);

        this.textKM = (TextView) v.findViewById(R.id.chkSnelheid);
        this.textS = (TextView) v.findViewById(R.id.chkReactietijd);

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
        int snelheid = this.skbSnelheid.getProgress();
        float reactietijd = this.skbReactietijd.getProgress();

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
        Toast.makeText(getActivity(), stopAfstandString + " meter", Toast.LENGTH_LONG).show();
        /*Context context = getApplicationContext();
        CharSequence text = stopAfstandString + " meter";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/
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

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
        //Log.v("", "" + bar);
           switch (seekBar.getId()){

               case R.id.skbSnelheid:
                   textKM.setText(progress + " KM/u");
                   break;


               case R.id.skbReactietijd:
                   textS.setText(progress + " seconden");
                   break;

           }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar){
                seekBar.setSecondaryProgress(seekBar.getProgress());
    }


}
