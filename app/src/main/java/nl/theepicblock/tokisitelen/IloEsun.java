package nl.theepicblock.tokisitelen;

import android.content.Context;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

public class IloEsun {
    public static <T> void ponaEEsun(Context kon, AdapterView<Adapter> esun, T[] wilePoki, T lon, IjoMoku<T> wileSin) {
        ArrayAdapter<T> ilo = new ArrayAdapter<>(kon, R.layout.ijo_ike, wilePoki);
        ilo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        esun.setAdapter(ilo);
        esun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wileSin.moku(wilePoki[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        for (int nanpa = 0; nanpa < wilePoki.length; nanpa++) {
            if (wilePoki[nanpa] == lon) {
                esun.setSelection(nanpa);
                break;
            }
        }
    }
}
