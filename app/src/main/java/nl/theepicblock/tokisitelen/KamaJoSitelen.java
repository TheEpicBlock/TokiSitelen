package nl.theepicblock.tokisitelen;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import nl.theepicblock.ilopali.kepekenale.Nimi;

public class KamaJoSitelen extends InputMethodService {
    @Override
    public View onCreateInputView() {
        View lukin = getLayoutInflater().inflate(R.layout.lukin_ilo, null);

        ViewGroup kamaNena = lukin.findViewById(R.id.kamaNenaMute);
        KulupuNimi kulupuNimi = KulupuNimi.kepekenLipuInsa(this.getBaseContext());

        for (Nimi nimi : kulupuNimi.nimi()) {
            Button nena = (Button)getLayoutInflater().inflate(R.layout.nena, kamaNena, false);
            nena.setText(nimi.sitelenEmosi());
            nena.setOnClickListener(v -> {
                getCurrentInputConnection().commitText(nimi.sitelenEmosi(), 1);
            });
            kamaNena.addView(nena);
        }


        lukin.findViewById(R.id.kamaAla).setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                v.performClick();
                getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                getCurrentInputConnection().sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
            }
            return false;
        });

        lukin.findViewById(R.id.kamaInsaNimi).setOnClickListener(v -> {
            getCurrentInputConnection().commitText("·", 1);
        });
        lukin.findViewById(R.id.kamaAnte).setOnClickListener(v -> {
            System.out.println("EEEE");
        });
        return lukin;
    }
}
