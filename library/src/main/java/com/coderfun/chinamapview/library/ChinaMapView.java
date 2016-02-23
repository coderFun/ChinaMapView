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
        canvas.drawPath(pGanSu,paint);
        canvas.drawPath(pNingXia,paint);
        canvas.drawPath(pXinJiang,paint);
        canvas.drawPath(pQingHai,paint);
        canvas.drawPath(pSiChuan,paint);
        canvas.drawPath(pXiZang,paint);
        canvas.drawPath(pYunNan,paint);
        canvas.drawPath(pGuiZhou,paint);
        canvas.drawPath(pGuangXi,paint);
        canvas.drawPath(pChongQing,paint);
        canvas.drawPath(pShan3Xi,paint);
        canvas.drawPath(pShan1Xi,paint);
        canvas.drawPath(pHuNan,paint);
        canvas.drawPath(pHuBei,paint);
        canvas.drawPath(pGuangDong,paint);
        canvas.drawPath(pJiangXi,paint);
        canvas.drawPath(pFuJian,paint);
        canvas.drawPath(pZheJiang,paint);
        canvas.drawPath(pAnHui,paint);
        canvas.drawPath(pTianJin,paint);
        canvas.drawPath(pBeiJing,paint);
        canvas.drawPath(pLiaoNing,paint);
        canvas.drawPath(pJiLin,paint);
        canvas.drawPath(pHeiLongJiang,paint);
        canvas.drawPath(pShanDong,paint);
        canvas.drawPath(pShangHai,paint);
        canvas.drawPath(pJiangSu,paint);
        canvas.drawPath(pHeBei,paint);
        canvas.drawPath(pHeNan,paint);
        canvas.drawPath(pTaiWan,paint);
        canvas.drawPath(pHaiNan,paint);
    }
}
