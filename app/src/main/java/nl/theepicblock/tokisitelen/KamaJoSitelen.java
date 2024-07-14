package nl.theepicblock.tokisitelen;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;
import android.widget.Button;
import nl.theepicblock.ilopali.kepekenale.Nimi;

public class KamaJoSitelen extends InputMethodService {
    @Override
    public View onCreateInputView() {
        ViewGroup lukin = (ViewGroup)getLayoutInflater().inflate(R.layout.lukin_ilo, null);
        KulupuNimi kulupuNimi = KulupuNimi.kepekenLipuInsa(this.getBaseContext());

        for (Nimi nimi : kulupuNimi.nimi()) {
            Button nena = (Button)getLayoutInflater().inflate(R.layout.nena, lukin, false);
            nena.setText(nimi.sitelenEmosi());
            nena.setOnClickListener(v -> {
                getCurrentInputConnection().commitText(nimi.sitelenEmosi(), 1);
            });
            lukin.addView(nena);
        }
        return lukin;
    }
}
