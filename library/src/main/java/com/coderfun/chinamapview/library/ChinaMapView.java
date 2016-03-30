package com.coderfun.chinamapview.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * a view to show a china map
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

    float translateX;
    float translateY;
    float scaleRatio;

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
        if (needInvalidate) {
            matrix.reset();
            float xRatio = getMeasuredWidth() / (float) rectMap.width();
            float yRatio = getMeasuredHeight() / (float) rectMap.height();
            scaleRatio = xRatio > yRatio ? yRatio : xRatio;
            matrix.setScale(scaleRatio, scaleRatio);
            matrix.postTranslate(translateX, translateY);
            //matrix.setTranslate(translateX, translateY);
            for (int i = 0; i < regions.size(); i++) {
                paths.get(i).transform(matrix, distPaths.get(i));
                regions.get(i).setPath(distPaths.get(i), region);
            }
            needInvalidate = false;
        }

        for (int i = 0; i < paths.size(); i++) {
            canvas.drawPath(distPaths.get(i), paint);
        }
        if (markedPath != null) {
            canvas.drawPath(markedPath, paintMarked);
        }
    }

    int x1;
    int x2;
    int y1;
    int y2;

    float tempX;
    float tempY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Log.e(TAG, "onTouchEvent: " + event.getPointerCount());
        if (event.getPointerCount() == 2) {
            Log.e(TAG, "onTouchEvent: " + event.getActionMasked());
            markedPath = null;
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_POINTER_DOWN:
                    x1 = (int) event.getX(0);
                    x2 = (int) event.getX(1);
                    y1 = (int) event.getY(0);
                    y2 = (int) event.getX(1);
                    tempX = translateX;
                    tempY = translateY;
                    break;
                case MotionEvent.ACTION_MOVE:
                    translateX = tempX + event.getX(0) - x1;
                    translateY = tempY + event.getY(0) - y1;
                    needInvalidate = true;
                    invalidate();
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_CANCEL:
                    break;

            }
        } else {
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
        }

        return super.onTouchEvent(event);
    }
}
