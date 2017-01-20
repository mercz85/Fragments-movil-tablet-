package es.iesnervion.mrequena.a18ejfragcolores;

/*

Layouts
activity_main   Framelayout @+id/fragment_container phone?
activity_main(sw600dp)  Linearlayout
                            Fragment @+id/botones_fragment (headlines_fragment)
                            Fragment @+id/color_fragment (article_fragment)
color_view  Linearlayout    @+id/colorScreen
                background: white


Clases
BotonesFragment extends Fragment (ListFragment)(HeadlinesFragment)
ColorFragment extends Fragment (ArticleFragment)
MainActivity

*/

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends FragmentActivity implements BotonesFragment.OnButtonSelectedListener, ColorFragment.cambioColorFragment { // implements View.OnClickListener
/*
IMPORTANTE: Comunicacion entre Activities, Fragments, etc

No es correcto implementar metodos directamente, ejemplo, le digo a mi BotonesFragment que llame a
 la actividad y ejecute un metodo onClick escrito por mi

Correcto: debo crear una interfaz en BotonesFragment, por ejemplo OnButtonSelectedListener con el
 metodo con el que quiero que se comuniquen,
ej, en vez del onClick, uso el metodo de la interfaz onButtonSelected
Ademas debo crear en onAttach un codigo de verificacion que avise a la clase(programador) que quiere
 usar mi fragment de que tiene que implementar mi interfaz
Para crear el metodo de verificacion debo usar un atributo de mi Fragment llamado mCallback o mListener
x ej: OnButtonSelectedListener mCallback;

Luego tengo que decire a la clase que llama a mi fragment
implements BotonesFragment.OnButtonSelectedListener e imlementar los metodos de mi interfaz

*/
    Button btnPulsado;
    final static String ARG_BOTON = "Button";
    ViewGroup actualV; //Vista del primer componente del xml (Child 0)
    String color = "WHITE";
    View clrFrgView;
    /*
    actualV = indViewById(R.id.fragment_containerPhone) //SI MOVIL
    actualV = indViewById(R.id.linearLayoutFragments) //SI TABLET
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clrFrgView = findViewById(R.id.color_fragment);
        actualV = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);

        if(savedInstanceState!=null){
            color = savedInstanceState.getString("color");
            if(actualV == findViewById(R.id.linearLayoutFragments)){//TABLET
                cambioColor(color, clrFrgView);
            }
        }

        //Identificamos los fragments que tenemos en activity_main.xml sw600dp
        //http://stackoverflow.com/questions/18296868/how-to-add-a-fragment-to-a-programmatically-generated-layout
      //  LinearLayout fragContainer = (LinearLayout) findViewById(R.id.linearLayoutFragments);





        if (actualV == findViewById(R.id.fragment_containerPhone)){
            if(savedInstanceState!=null){
               return;
            }

            BotonesFragment btnFrg= new BotonesFragment();
            btnFrg.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_containerPhone, btnFrg).commit();
            //getSupportFragmentManager().beginTransaction().add(fragContainer.getId(),btnFrg).commit();

//            btnPulsado = (Button) findViewById( btnFrg.getArguments().getInt(ARG_BOTON));
         //   btnPulsado.setOnClickListener(this);
        }else if(actualV == findViewById(R.id.linearLayoutFragments)){//TABLET
            if(savedInstanceState!=null){
                return;
            }

/*          BotonesFragment btnFrg= new BotonesFragment();
            btnFrg.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().replace(R.id.botones_fragment, btnFrg).commit();*/
//Nooooooooooooooooooooooooooooo
           // ColorFragment clrFrg= new ColorFragment();
           // clrFrg.setArguments(getIntent().getExtras());
           // getSupportFragmentManager().beginTransaction().add(R.id.color_fragment, clrFrg).commit();
            /*
                No hay que poner nada xq los fragments de la tablet ya estan creados en el xml,
                y si creo un nuevo fragment intenta meterlos dentro del que ya tengo, y no se puede
                Lo que tengo que hacer es crear un método en el ColorFragment que me haga el cambio
                 de color del fragment color_fragment o usar Frames en el xml para meter dentro el fragment
            */

        }

    }

/*    @Override
    public void onClick(View v) {
        if(btnPulsado.getId()== R.id.btnRojo){

        }else if(btnPulsado.getId()== R.id.btnRojo){

        }
    }*/

    //NO SE USA!!!!
    public void onClickRojo(View v) {
        //como podria diferenciar el boton seleccionado aqui??
/*        if(btnPulsado.getId()== R.id.btnRojo){

        }else if(btnPulsado.getId()== R.id.btnRojo){

        }*/

/*        Intent color = new Intent(this, ColorFragment.class);
        color.putExtra(ColorFragment.ARG_BOTON,"rojo");
        startActivity(color);*/
        ColorFragment clrFrg= new ColorFragment();
        clrFrg.setArguments(getIntent().getExtras());
        clrFrg.layout.setBackgroundColor(Color.RED);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_containerPhone, clrFrg).commit();

    }
//NO SE USA!!!
    public void onClickAzul(View v) {
        Intent color = new Intent(this, ColorFragment.class);
        color.putExtra(ColorFragment.ARG_BOTON,"azul");
        startActivity(color);
    }

//NO SE USA!!!
    public void onClick(View v) {
        if (v.getId() == R.id.btnRojo) {
            ColorFragment clrFrg= new ColorFragment();
            clrFrg.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().add(R.id.fragment_containerPhone, clrFrg).commit();
            //clrFrg.layout.setBackgroundColor(Color.RED);

            FrameLayout fl = (FrameLayout) findViewById(R.id.fragment_containerPhone);
            fl.setBackgroundColor(Color.RED);
            int hijos = fl.getChildCount();
            fl.getChildAt(0).setBackgroundColor(Color.RED);

/*            View btnView = (View) findViewById(R.id.botonesScreen);
            btnView.setBackgroundColor(Color.parseColor("#000000"));*/

        }
        else if (v.getId() == R.id.btnAzul) {

        }
    }
//METODO DE LA INTERFAZ BotonesFragment.OnButtonSelectedListener
    @Override
    public void onButtonSelected(View v) {


        if (v.getId() == R.id.btnRojo) {
            color = "RED";
         //   ColorFragment clrFrg= new ColorFragment();
         //   clrFrg.setArguments(getIntent().getExtras());

            //Mejor usar newInstance, para poder pasar mas facilmente los parametros, ya que lso constructores de fragment son vacios
            ColorFragment clrFrg = ColorFragment.newInstance("RED");

            // con ADD se pueden poner fragments encima unos de otros
            //getSupportFragmentManager().beginTransaction().add(R.id.fragment_containerPhone, clrFrg).commit();
            // en este caso debo usar REPLACE, para que no se superpongan
            if (actualV == findViewById(R.id.fragment_containerPhone)){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerPhone, clrFrg).addToBackStack(null).commit();
            }else if(actualV == findViewById(R.id.linearLayoutFragments)){//TABLET
                //No se puede insertar un fragment en un fragment de xml que ya tengo!!
               // getSupportFragmentManager().beginTransaction().replace(R.id.color_fragment, clrFrg).commit();//TABLET

                //buscar fragment de la derecha y cambiarle color

                cambioColor(color, clrFrgView);
            }


            //clrFrg.layout.setBackgroundColor(Color.RED);

            /* ESTO ES PARA CAMBIAR EL FONDO DEL FrameLayout(contenedor en movil) y del fragment,
            pero el del fragment es mejor cambiarlo enviando el color por parametros con newInstance
            y modificarlo en al crear el fragment

            FrameLayout fl = (FrameLayout) findViewById(R.id.fragment_containerPhone);
            fl.setBackgroundColor(Color.RED);
            fl.getChildAt(0).setBackgroundColor(Color.RED); //cambia el color del fragment, que es el hijo 0 del FrameLayout
            */


            //PARA PODER VOLVER ATRAS
            /*
            public void loadFragment(Fragment fragmentB, String tag) {
                FragmentManager fm = getSupportFragmentManager();
                View fragmentContainer = findViewById(R.id.fragment_container);

                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container,fragmentB);
                ft.addToBackStack(null);
                ft.commit();
            }
            */


        }
        else if (v.getId() == R.id.btnAzul) {
            color = "BLUE";

            ColorFragment clrFrg = ColorFragment.newInstance("BLUE");

            if (actualV == findViewById(R.id.fragment_containerPhone)){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_containerPhone, clrFrg).addToBackStack(null).commit();
            }else if(actualV == findViewById(R.id.linearLayoutFragments)){//TABLET
               // getSupportFragmentManager().beginTransaction().replace(R.id.color_fragment, clrFrg).commit();//TABLET
                cambioColor(color, clrFrgView);
            }
        }
    }
//Metodo de la Interface ColorFragment.cambioColorFragment
    public void cambioColor(String color, View v){

        if(color.compareTo("RED")==0){
            v.setBackgroundColor(Color.RED);
        }else if(color.compareTo("BLUE")==0){
            v.setBackgroundColor(Color.BLUE);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("color", color);
    }
}



