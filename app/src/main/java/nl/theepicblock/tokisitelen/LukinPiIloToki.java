package nl.theepicblock.tokisitelen;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class LukinPiIloToki extends ViewGroup {
    KamaJoSitelen wileSitelen;

    public LukinPiIloToki(Context context, AttributeSet attrs) {
        super(context, attrs, 301410);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public void panaIjoPiWileSitelen(KamaJoSitelen wileSitelen) {
        this.wileSitelen = wileSitelen;
    }
}
