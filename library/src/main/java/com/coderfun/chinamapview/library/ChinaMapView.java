package com.coderfun.chinamapview.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * a view to show a china map
 * It will crushed if scale too large and I can't find the reason.
 */
public class ChinaMapView extends View {
    public static final String TAG = "ChinaMapView";
    /**
     * max scale ratio of china map
     */
    public static final float MAX_SCALE_RATIO = 15;
    /**
     * min scale ratio of china map
     */
    public static final float MIN_SCALE_RATIO = 0.15f;
    Paint paint;
    Paint paintMarked;

    Matrix matrix = new Matrix();
    RectF rectMap = new RectF();
    RectF rectMapDist = new RectF();
    ArrayList<Region> regions = new ArrayList<>();
    ArrayList<Path> paths = new ArrayList<>();
    ArrayList<Path> distPaths = new ArrayList<>();
    private Path markedPath;
    Region region = new Region();
    Rect screen;

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

        screen = new Rect(0, 0, getResources().getDisplayMetrics().widthPixels, getResources().getDisplayMetrics().heightPixels);

        region.set(screen);

        for (int i = 0; i < ChinaMap.svgProvinces.length; i++) {
            paths.add(SVGParser.parsePath(ChinaMap.svgProvinces[i]));
            regions.add(new Region());
        }
        RectF rectF = new RectF();
        for (int i = 0; i < regions.size(); i++) {
            regions.get(i).setPath(paths.get(i), region);
            rectF.set(regions.get(i).getBounds());
            rectMap.union(rectF);
            distPaths.add(new Path());
        }
        rectMapDist.set(rectMap);
        Log.e(TAG, "init: "+rectMap );
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);

    }

    float fitScale;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        float xRatio = getMeasuredWidth() / rectMap.width();
        float yRatio = getMeasuredHeight() / rectMap.height();
        scaleRatio = xRatio > yRatio ? yRatio : xRatio;
        fitScale = scaleRatio;
        Matrix matrix = new Matrix();
        matrix.setTranslate((getMeasuredWidth()-rectMap.width())/2,(getMeasuredHeight()- rectMap.height())/2);
        matrix.mapRect(rectMap);
        rectMapDist.set(rectMap);
        Log.e(TAG, "onSizeChanged: "+rectMapDist );
        for (int i = 0; i < distPaths.size(); i++) {
            paths.get(i).transform(matrix);
        }
        resizeMap(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < paths.size(); i++) {

            if (Rect.intersects(screen, regions.get(i).getBounds())) {
                canvas.drawPath(distPaths.get(i), paint);
                canvas.clipRect(screen);
            }
        }
        if (markedPath != null) {
            canvas.drawPath(markedPath, paintMarked);
        }
    }

    private void resizeMap(boolean shouldRegion) {
        matrix.reset();
        if (scaleRatio > MAX_SCALE_RATIO) {
            scaleRatio = MAX_SCALE_RATIO;
        } else if (scaleRatio < MIN_SCALE_RATIO) {
            scaleRatio = MIN_SCALE_RATIO;
        }
        float maxTranslateX = getMeasuredWidth()/fitScale*scaleRatio;
        float maxTranslateY = getMeasuredHeight()/fitScale*scaleRatio;
        if (translateX > maxTranslateX) {
            translateX = maxTranslateX;
        } else if (translateX < -maxTranslateX) {
            translateX = -maxTranslateX;
        }
        if (translateY > maxTranslateY) {
            translateY =maxTranslateY;
        } else if (translateY < -maxTranslateY) {
            translateY = -maxTranslateY;
        }
        matrix.setScale(scaleRatio, scaleRatio, rectMapDist.centerX(), rectMapDist.centerY());
        matrix.postTranslate(translateX, translateY);
        for (int i = 0; i < regions.size(); i++) {
            paths.get(i).transform(matrix, distPaths.get(i));
        }
        if (shouldRegion) {
            for (int i = 0; i < regions.size(); i++) {
                regions.get(i).setPath(distPaths.get(i), region);
            }
        }
    }

    int x1;
    int x2;
    int y1;
    int y2;
    double distance;
    float tempX;
    float tempY;
    float tempScale;

    int scaleTolerance = 60;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getPointerCount() == 2) {
            markedPath = null;
            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_POINTER_DOWN:
                    x1 = (int) event.getX(0);
                    x2 = (int) event.getX(1);
                    y1 = (int) event.getY(0);
                    y2 = (int) event.getX(1);
                    distance = Math.sqrt(Math.pow((event.getX(1) - event.getX(0)), 2) + Math.pow((event.getY(1) - event.getY(0)), 2));
                    tempX = translateX;
                    tempY = translateY;
                    tempScale = scaleRatio;
                    break;
                case MotionEvent.ACTION_MOVE:
                    translateX = tempX + event.getX(0) - x1;
                    translateY = tempY + event.getY(0) - y1;
                    double distance2 = Math.sqrt(Math.pow((event.getX(1) - event.getX(0)), 2) + Math.pow((event.getY(1) - event.getY(0)), 2));
                    if (Math.abs(distance2 - distance) > scaleTolerance) {
                        scaleRatio = tempScale * (float) ((distance2 - scaleTolerance) / distance);
                    }
                    resizeMap(true);
                    invalidate();
                    break;
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_CANCEL:
                    resizeMap(true);
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
