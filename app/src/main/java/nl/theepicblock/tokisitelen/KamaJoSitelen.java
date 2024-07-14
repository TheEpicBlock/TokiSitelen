package nl.theepicblock.tokisitelen;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

public class KamaJoSitelen extends InputMethodService {
    @Override
    public View onCreateInputView() {
        View lukin = getLayoutInflater().inflate(R.layout.lukin_ilo, null);
        if (lukin instanceof LukinPiIloToki) {
            ((LukinPiIloToki)lukin).panaIjoPiWileSitelen(this);
        }
        lukin.findViewById(R.id.button).setOnClickListener(v -> ilo(KeyEvent.KEYCODE_A));
        lukin.findViewById(R.id.button2).setOnClickListener(v -> ilo(KeyEvent.KEYCODE_B));
        lukin.findViewById(R.id.button3).setOnClickListener(v -> ilo(KeyEvent.KEYCODE_C));
        lukin.findViewById(R.id.button4).setOnClickListener(v -> ilo(KeyEvent.KEYCODE_D));
        return lukin;
    }

    public void ilo(int sitelen) {
        InputConnection wileToki = this.getCurrentInputConnection();
        wileToki.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, sitelen));
        wileToki.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, sitelen));
    }
}
