package com.coderfun.chinamapview.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 */
public class ChinaMapView extends View{
    private Path pNeiMengGu = SVGParser.parsePath(ChinaMap.NeiMengGu);
    public ChinaMapView(Context context) {
        super(context);
    }

    public ChinaMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChinaMapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(0xffffddae);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        canvas.drawPath(pNeiMengGu,paint);
    }
}
