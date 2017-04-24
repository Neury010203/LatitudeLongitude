package com.test.nafl.test;

/**
 * Created by Neury on 2/24/2017.
 */
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



//Esta clase no se usa//
public class GetInfo extends Fragment {

    /*String pagPrecio = "http://www.loteriasdominicanas.com/";
    Elements listaPrecios, fechaPrecio;
    Element precioGas;
    Document doc;

    TextView precioTextView, fechaTextView, preciototalTextView, galonesTextView, calcularTextView,titulo,km,kmg,titulocalc,galonestext;
   // EditText kilometros, kilometrosPorGalon;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_main, container, false);

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(v.getContext());
        final SharedPreferences.Editor editor = preferences.edit();
        precioTextView = (TextView) v.findViewById(R.id.textView2);
      *//*  fechaTextView = (TextView) v.findViewById(R.id.Fecha);
        titulo = (TextView) v.findViewById(R.id.Titulo);
        km = (TextView) v.findViewById(R.id.km);
        kmg = (TextView) v.findViewById(R.id.kmg);
        galonestext = (TextView) v.findViewById(R.id.galonestext);
        titulocalc = (TextView) v.findViewById(R.id.TituloCalculadora);
        preciototalTextView = (TextView) v.findViewById(R.id.preciocal);
        galonesTextView = (TextView) v.findViewById(R.id.galones);
        kilometros = (EditText) v.findViewById(R.id.kilometros);
        kilometrosPorGalon = (EditText) v.findViewById(R.id.kilogalo);
        calcularTextView = (TextView) v.findViewById(R.id.calcular);*//*
      //  Typeface font = Typeface.createFromAsset(v.getContext().getAssets(), "OpenSans-Bold.ttf");


        *//*precioTextView.setTypeface(font);
        fechaTextView.setTypeface(font);
        preciototalTextView.setTypeface(font);
        galonesTextView.setTypeface(font);
        calcularTextView.setTypeface(font);
        titulo.setTypeface(font);
        galonestext.setTypeface(font);
        km.setTypeface(font);
        kmg.setTypeface(font);
        titulocalc.setTypeface(font);*//*

*//*
        kilometros.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        kilometrosPorGalon.getBackground().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);*//*

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    doc = Jsoup.connect(pagPrecio).get();
                    precioTextView.setText("Cargando...");
                    listaPrecios = doc.select(".precio");
                    precioGas = listaPrecios.select("div").get(6);
                    fechaPrecio = doc.getElementsByClass("combustibles-date");
                    editor.putString("precioGuardado", precioGas.child(0).text()); // value to store
                    editor.putString("fechaGuardada", fechaPrecio.text()); // value to store
                    editor.apply();


                } catch (Exception e) {


                }
                precioTextView.post(new Runnable() {
                    @Override
                    public void run() {
                        fechaTextView.setText(preferences.getString("fechaGuardada", "-- al -- de -------"));
                        precioTextView.setText(preferences.getString("precioGuardado", "RD$--.--"));


                    }
                });
            }
        }).start();


        final String numPrecio = preferences.getString("precioGuardado", "RD$--.--");

        calcularTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kilometros.getText().length() == 0 || kilometrosPorGalon.getText().length() == 0) {
                    Toast.makeText(v.getContext(), "Introduzca todos los valores.",
                            Toast.LENGTH_SHORT).show();
                } else if (kilometrosPorGalon.getText().equals("0")) {
                    Toast.makeText(v.getContext(), "Introduzca la cantidad de km/g.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    float km = Integer.parseInt(kilometros.getText().toString());
                    float kmGal = Integer.parseInt(kilometrosPorGalon.getText().toString());
                    float resultado = km / kmGal;
                    float resultadoPrecio = resultado * Float.parseFloat(numPrecio.substring(3));
                    galonesTextView.setText(String.format("%.1f", resultado));
                    preciototalTextView.setText("RD$" + String.format("%.1f", resultadoPrecio));
                }


            }
        });
        InputMethodManager inputMethodManager = (InputMethodManager)  getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);

        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, InputMethodManager.HIDE_IMPLICIT_ONLY);
        setupUI(v);

        return v;
    }
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);

    }

    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupUI(innerView);
            }
        }
    }


*/



}
