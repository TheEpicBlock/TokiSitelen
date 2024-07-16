package nl.theepicblock.tokisitelen;

import android.content.Context;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

public class IloEsun {
    public static void ponaEEsun(Context kon, AdapterView<Adapter> esun, Object[] wilePoki) {
        ArrayAdapter<Object> ilo = new ArrayAdapter<>(kon, R.layout.ijo_ike, wilePoki);
        ilo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        esun.setAdapter(ilo);
    }
}
