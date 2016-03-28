package com.coderfun.chinamapview.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 *
 */
public class ChinaMapView extends View {
    public static final String TAG = "ChinaMapView";
    Paint paint;
    Paint paintMarked;

    Matrix matrix = new Matrix();
    Rect rectMap = new Rect();
    ArrayList<Region> regions = new ArrayList<>();
    ArrayList<Path> paths = new ArrayList<>();
    ArrayList<Path> distPaths = new ArrayList<>();
    private Path markedPath;
    Region region = new Region();

    boolean needInvalidate = true;

    public ChinaMapView(Context context) {
        super(context);
        init();
    }

    public ChinaMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChinaMapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setClickable(true);

        paint = new Paint();
        paint.setColor(0xffffddae);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        paintMarked = new Paint();
        paintMarked.setColor(0xfffdcc9e);
        paintMarked.setStyle(Paint.Style.FILL);
        paintMarked.setAntiAlias(true);

        Rect screen = new Rect(0, 0, getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);

        region.set(screen);

        for (int i = 0; i < ChinaMap.svgProvinces.length; i++) {
            paths.add(SVGParser.parsePath(ChinaMap.svgProvinces[i]));
            regions.add(new Region());
        }

        for (int i = 0; i < regions.size(); i++) {
            regions.get(i).setPath(paths.get(i), region);
            rectMap.union(regions.get(i).getBounds());
            distPaths.add(new Path());
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (needInvalidate){
            float xRatio = getWidth()/(float)rectMap.width();
            float yRatio = getHeight()/(float)rectMap.height();
            if (xRatio>yRatio){
                matrix.setScale(yRatio, yRatio);
            }else{
                matrix.setScale(xRatio, xRatio);
            }
            for (int i=0;i<regions.size();i++){
                paths.get(i).transform(matrix,distPaths.get(i));
                regions.get(i).setPath(distPaths.get(i),region);
            }
            needInvalidate = false;
        }

        for (int i = 0; i < paths.size(); i++) {
            canvas.drawPath(distPaths.get(i),paint);
        }
        if (markedPath != null) {
            canvas.drawPath(markedPath, paintMarked);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int x = (int) event.getX();
                int y = (int) event.getY();
                markedPath = null;
                for (int i = 0; i < regions.size(); i++) {
                    if (regions.get(i).contains(x, y)) {
                        markedPath = distPaths.get(i);
                        break;
                    }
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                markedPath = null;
                invalidate();
                break;
        }
        return super.onTouchEvent(event);
    }
}
