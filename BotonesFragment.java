package es.iesnervion.mrequena.a18ejfragcolores;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by mrequena on 14/12/16.
 */

public class BotonesFragment extends Fragment implements View.OnClickListener {

    Button btnRojo;
    Button btnAzul;
    OnButtonSelectedListener mCallback;

    public interface OnButtonSelectedListener{

        public void onButtonSelected(View btn);

    }

    @Override
    public void onClick(View v) {
        ((MainActivity) getActivity()).onButtonSelected(v);

/*        if (v.getId() == R.id.btnRojo) {
           // ((MainActivity) getActivity()).onClickRojo(v); //llamo a onclick de mainactivity}
            ((MainActivity) getActivity()).onButtonSelected(v); //llamo a onButtonSelected de mainactivity}
        } //getActivity devuelve la actividad host del fragment
        else if (v.getId() == R.id.btnAzul) {
            ((MainActivity) getActivity()).onButtonSelected(v);
        }*/

    }
        @Nullable
        @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            // return super.onCreateView(inflater, container, savedInstanceState);

           // View v= inflater.inflate(R.layout.activity_main, container,false);
            View v= inflater.inflate(R.layout.botones_view, container,false);
           //  v= inflater.inflate(R.layout.activity_main, container,false);
                  v.findViewById(R.id.btnRojo).setOnClickListener(this);
                  v.findViewById(R.id.btnAzul).setOnClickListener(this);

            return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();

        if(getFragmentManager().findFragmentById(R.id.color_fragment)!=null){
            //estamos en TABLET

        }
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);

        if (context instanceof BotonesFragment.OnButtonSelectedListener) {
            mCallback = (BotonesFragment.OnButtonSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnButtonSelectedListener");
        }
    }

}
