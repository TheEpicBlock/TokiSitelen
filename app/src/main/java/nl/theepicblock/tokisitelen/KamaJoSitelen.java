package nl.theepicblock.tokisitelen;

import android.content.Context;
import android.content.SharedPreferences;
import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ViewAnimator;
import nl.theepicblock.ilopali.kepekenale.Nimi;

public class KamaJoSitelen extends InputMethodService {
    @Override
    public View onCreateInputView() {
        ViewAnimator lukin = (ViewAnimator)getLayoutInflater().inflate(R.layout.lukin_ilo, null);

        ViewGroup pokiNena = lukin.findViewById(R.id.kamaNenaMute);
        KulupuNimi kulupuNimi = KulupuNimi.kepekenLipuInsa(this);
        SharedPreferences wilePiJanKepeken =  getBaseContext()
                .getSharedPreferences("wilePiJanKepeken", Context.MODE_PRIVATE);

        oNena(kulupuNimi, pokiNena, wilePiJanKepeken);

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
        lukin.findViewById(R.id.nenaAnte).setOnClickListener(v -> {
            lukin.setDisplayedChild(1);
        });
        lukin.findViewById(R.id.nenaPiniAnte).setOnClickListener(v -> {
            lukin.setDisplayedChild(0);
        });

        IloEsun.ponaEEsun(this, lukin.findViewById(R.id.esunPiNasinToki), NasinToki.values(), NasinToki.kepeken(wilePiJanKepeken), nasinToki -> {
            wilePiJanKepeken.edit()
                    .putString("nasin_toki", nasinToki.nimiKon())
                    .apply();
            oNena(kulupuNimi, pokiNena, wilePiJanKepeken);
        });
        IloEsun.ponaEEsun(this, lukin.findViewById(R.id.esunPiSitelenNena), SitelenNena.values(), SitelenNena.kepeken(wilePiJanKepeken), sitelenNena -> {
            wilePiJanKepeken.edit()
                    .putString("sitelen_nena", sitelenNena.nimiKon())
                    .apply();
            oNena(kulupuNimi, pokiNena, wilePiJanKepeken);
        });
        return lukin;
    }

    private void oNena(KulupuNimi kulupuNimi, ViewGroup pokiNena, SharedPreferences wilePiJanKepeken) {
        SitelenNena sitelenNena = SitelenNena.kepeken(wilePiJanKepeken);
        NasinToki nasinToki = NasinToki.kepeken(wilePiJanKepeken);

        System.out.println("jan li ante e wile");
        System.out.println("sitelen nena: "+sitelenNena);
        System.out.println("nasin toki: "+nasinToki);

        pokiNena.removeAllViews();
        for (Nimi nimi : kulupuNimi.nimi()) {
            View nena = sitelenNena.oNemaENimi(nimi,
                    this,
                    (lukinNanpa) -> getLayoutInflater().inflate(lukinNanpa, pokiNena, false));

            String nasinNimi = nasinToki.oPonaENimi(nimi);
            nena.setOnClickListener(v -> {
                getCurrentInputConnection().commitText(nasinNimi, 1);
            });
            pokiNena.addView(nena);
        }
    }
}
