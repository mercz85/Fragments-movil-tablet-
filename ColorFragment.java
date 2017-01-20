package es.iesnervion.mrequena.a18ejfragcolores;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by mrequena on 14/12/16.
 */

public class ColorFragment extends Fragment {
    final static String ARG_BOTON = "Button";
    LinearLayout layout;
    int btnId;
    String color = "WHITE";

    public interface cambioColorFragment{
/*
Se debe implementar si la aplicación usa fragments en su xml
*/
        public void cambioColor(String color, View v);

    }

    public static ColorFragment newInstance(String color) {
        ColorFragment fragment = new ColorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_BOTON, color);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);

        View v = null;


        if(savedInstanceState !=null){
            btnId = savedInstanceState.getInt(ARG_BOTON);
        }

        if (getArguments() != null) {
            color = getArguments().getString(ARG_BOTON);
        }

        v = inflater.inflate(R.layout.color_view, container, false);

        if (color.compareTo("RED")==0){
            v.setBackgroundColor(Color.RED);
        }else if(color.compareTo("BLUE") == 0){
            v.setBackgroundColor(Color.BLUE);
        }

        return v;
    }

    /* CAMBIAR COLOR
       Cuando el fragment ColorFragment ya esta creado, no puedo insertar uno nuevo, por lo que
       debo crear un metodo que cambie el color de mi fragment en el Main activity (tb podría crearlo aqui)
    */

}

