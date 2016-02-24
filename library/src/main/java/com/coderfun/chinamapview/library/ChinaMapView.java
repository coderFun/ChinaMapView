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
    Paint paint;
    Paint paintMarked;

    Matrix matrix = new Matrix();

    ArrayList<Region> regions = new ArrayList<>();
    ArrayList<Path> paths = new ArrayList<>();

    private Path markedPath;

    private Path pNeiMengGu = SVGParser.parsePath(ChinaMap.NeiMengGu);
    private Path pGanSu = SVGParser.parsePath(ChinaMap.GanSu);
    private Path pNingXia = SVGParser.parsePath(ChinaMap.NingXia);
    private Path pXinJiang = SVGParser.parsePath(ChinaMap.XinJiang);
    private Path pQingHai = SVGParser.parsePath(ChinaMap.QingHai);
    private Path pSiChuan = SVGParser.parsePath(ChinaMap.SiChuan);
    private Path pXiZang = SVGParser.parsePath(ChinaMap.XiZang);
    private Path pYunNan = SVGParser.parsePath(ChinaMap.YunNan);
    private Path pGuiZhou = SVGParser.parsePath(ChinaMap.GuiZhou);
    private Path pGuangXi = SVGParser.parsePath(ChinaMap.GuangXi);
    private Path pChongQing = SVGParser.parsePath(ChinaMap.ChongQing);
    private Path pShan3Xi = SVGParser.parsePath(ChinaMap.Shan3Xi);
    private Path pShan1Xi = SVGParser.parsePath(ChinaMap.Shan1Xi);
    private Path pHuNan = SVGParser.parsePath(ChinaMap.HuNan);
    private Path pHuBei = SVGParser.parsePath(ChinaMap.HuBei);
    private Path pGuangDong = SVGParser.parsePath(ChinaMap.GuangDong);
    private Path pJiangXi = SVGParser.parsePath(ChinaMap.JiangXi);
    private Path pFuJian = SVGParser.parsePath(ChinaMap.FuJian);
    private Path pZheJiang = SVGParser.parsePath(ChinaMap.ZheJiang);
    private Path pAnHui = SVGParser.parsePath(ChinaMap.AnHui);
    private Path pTianJin = SVGParser.parsePath(ChinaMap.TianJin);
    private Path pBeiJing = SVGParser.parsePath(ChinaMap.BeiJing);
    private Path pLiaoNing = SVGParser.parsePath(ChinaMap.LiaoNing);
    private Path pJiLin = SVGParser.parsePath(ChinaMap.JiLin);
    private Path pHeiLongJiang = SVGParser.parsePath(ChinaMap.HeiLongJiang);
    private Path pShanDong = SVGParser.parsePath(ChinaMap.ShanDong);
    private Path pShangHai = SVGParser.parsePath(ChinaMap.ShangHai);
    private Path pJiangSu = SVGParser.parsePath(ChinaMap.JiangSu);
    private Path pHeBei = SVGParser.parsePath(ChinaMap.HeBei);
    private Path pHeNan = SVGParser.parsePath(ChinaMap.HeNan);
    private Path pTaiWan = SVGParser.parsePath(ChinaMap.TaiWan);
    private Path pHaiNan = SVGParser.parsePath(ChinaMap.HaiNan);

    private Region rNeiMengGu = new Region();
    private Region rGanSu = new Region();
    private Region rNingXia = new Region();
    private Region rXinJiang = new Region();
    private Region rQingHai = new Region();
    private Region rSiChuan = new Region();
    private Region rXiZang = new Region();
    private Region rYunNan = new Region();
    private Region rGuiZhou = new Region();
    private Region rGuangXi = new Region();
    private Region rChongQing = new Region();
    private Region rShan3Xi = new Region();
    private Region rShan1Xi = new Region();
    private Region rHuNan = new Region();
    private Region rHuBei = new Region();
    private Region rGuangDong = new Region();
    private Region rJiangXi = new Region();
    private Region rFuJian = new Region();
    private Region rZheJiang = new Region();
    private Region rAnHui = new Region();
    private Region rTianJin = new Region();
    private Region rBeiJing = new Region();
    private Region rLiaoNing = new Region();
    private Region rJiLin = new Region();
    private Region rHeiLongJiang = new Region();
    private Region rShanDong = new Region();
    private Region rShangHai = new Region();
    private Region rJiangSu = new Region();
    private Region rHeBei = new Region();
    private Region rHeNan = new Region();
    private Region rTaiWan = new Region();
    private Region rHaiNan = new Region();

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
        Region region = new Region();
        region.set(screen);

        regions.add(rNeiMengGu);
        regions.add(rGanSu);
        regions.add(rNingXia);
        regions.add(rXinJiang);
        regions.add(rQingHai);
        regions.add(rSiChuan);
        regions.add(rXiZang);
        regions.add(rYunNan);
        regions.add(rGuiZhou);
        regions.add(rGuangXi);
        regions.add(rChongQing);
        regions.add(rShan3Xi);
        regions.add(rShan1Xi);
        regions.add(rHuNan);
        regions.add(rHuBei);
        regions.add(rGuangDong);
        regions.add(rJiangXi);
        regions.add(rFuJian);
        regions.add(rZheJiang);
        regions.add(rAnHui);
        regions.add(rTianJin);
        regions.add(rBeiJing);
        regions.add(rLiaoNing);
        regions.add(rJiLin);
        regions.add(rHeiLongJiang);
        regions.add(rShanDong);
        regions.add(rShangHai);
        regions.add(rJiangSu);
        regions.add(rHeBei);
        regions.add(rHeNan);
        regions.add(rTaiWan);
        regions.add(rHaiNan);

        paths.add(pNeiMengGu);
        paths.add(pGanSu);
        paths.add(pNingXia);
        paths.add(pXinJiang);
        paths.add(pQingHai);
        paths.add(pSiChuan);
        paths.add(pXiZang);
        paths.add(pYunNan);
        paths.add(pGuiZhou);
        paths.add(pGuangXi);
        paths.add(pChongQing);
        paths.add(pShan3Xi);
        paths.add(pShan1Xi);
        paths.add(pHuNan);
        paths.add(pHuBei);
        paths.add(pGuangDong);
        paths.add(pJiangXi);
        paths.add(pFuJian);
        paths.add(pZheJiang);
        paths.add(pAnHui);
        paths.add(pTianJin);
        paths.add(pBeiJing);
        paths.add(pLiaoNing);
        paths.add(pJiLin);
        paths.add(pHeiLongJiang);
        paths.add(pShanDong);
        paths.add(pShangHai);
        paths.add(pJiangSu);
        paths.add(pHeBei);
        paths.add(pHeNan);
        paths.add(pTaiWan);
        paths.add(pHaiNan);

        matrix.setScale(3, 3);
        for (int i=0;i<regions.size();i++){
            paths.get(i).transform(matrix);
            regions.get(i).setPath(paths.get(i),region);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(pNeiMengGu, paint);
        canvas.drawPath(pGanSu, paint);
        canvas.drawPath(pNingXia, paint);
        canvas.drawPath(pXinJiang, paint);
        canvas.drawPath(pQingHai, paint);
        canvas.drawPath(pSiChuan, paint);
        canvas.drawPath(pXiZang, paint);
        canvas.drawPath(pYunNan, paint);
        canvas.drawPath(pGuiZhou, paint);
        canvas.drawPath(pGuangXi, paint);
        canvas.drawPath(pChongQing, paint);
        canvas.drawPath(pShan3Xi, paint);
        canvas.drawPath(pShan1Xi, paint);
        canvas.drawPath(pHuNan, paint);
        canvas.drawPath(pHuBei, paint);
        canvas.drawPath(pGuangDong, paint);
        canvas.drawPath(pJiangXi, paint);
        canvas.drawPath(pFuJian, paint);
        canvas.drawPath(pZheJiang, paint);
        canvas.drawPath(pAnHui, paint);
        canvas.drawPath(pTianJin, paint);
        canvas.drawPath(pBeiJing, paint);
        canvas.drawPath(pLiaoNing, paint);
        canvas.drawPath(pJiLin, paint);
        canvas.drawPath(pHeiLongJiang, paint);
        canvas.drawPath(pShanDong, paint);
        canvas.drawPath(pShangHai, paint);
        canvas.drawPath(pJiangSu, paint);
        canvas.drawPath(pHeBei, paint);
        canvas.drawPath(pHeNan, paint);
        canvas.drawPath(pTaiWan, paint);
        canvas.drawPath(pHaiNan, paint);

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
                        markedPath = paths.get(i);
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
