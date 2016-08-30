package com.uto.djf.test.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


import com.uto.djf.test.R;
import com.uto.djf.test.objectparse.BuilderInterface;
import com.uto.djf.test.objectparse.ObjectParser;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by liuenbao on 2/19/16.
 */
public class MiniMapView extends View {

    private static final String TAG = MiniMapView.class.getSimpleName();

    public ArrayList<Point> allPoints = new ArrayList<>();

    private Context mContext;
    private ObjectParser mObjectParser;
    private Camera mCamera;
    private Matrix mMatrix;
    private int mCenterX;
    private int mCenterY;

    /**
     * 保存创建背景图片的ID
     */
    private int mBackGroudDrawableId;
    /**
     * 利用图片ID加载图片
     */
    private Drawable mBackGroudDrawable;


    private Bitmap mBackgroundBitmap;

    private Canvas mBackgroundCanvas;
    private int width;
    private int height;

    public MiniMapView(Context context) {
        super(context);
        init(context);
    }

    public MiniMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.MiniMapView);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int att = typedArray.getIndex(i);
            switch (att) {
                case R.styleable.MiniMapView_view_width:
                    width = typedArray.getDimensionPixelSize(att, -1);
                    break;
                case R.styleable.MiniMapView_view_height:
                    height = typedArray.getDimensionPixelSize(att, -1);
                    break;
            }

        }
        init(context);

//        mBackGroudDrawableId = attrs.getAttributeResourceValue(null, "background", 0);
//        mBackGroudDrawable = context.getResources().getDrawable(mBackGroudDrawableId);
    }

    private void init(Context context) {
        mContext = context;

//        mBackgroundBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);//透明背景
        mBackgroundBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);//黑色背景
        mBackgroundCanvas = new Canvas(mBackgroundBitmap);

        mCenterX = 800 >> 1;
        mCenterY = 900;

        mCamera = new Camera();
        mMatrix = new Matrix();
    }

    public void loadObjFile(String fileName) {

        mObjectParser = new ObjectParser(mContext, new BuilderInterface() {

            public void setObjFilename(String filename) {

            }

            public void addVertexGeometric(float x, float y, float z) {
                Point point = new Point((int) x, (int) -y);
                allPoints.add(point);
            }

            public void addVertexTexture(float u, float v) {

            }

            public void addVertexNormal(float x, float y, float z) {

            }

            public void addPoints(int values[]) {

            }

            public void addLine(int values[]) {

            }

            // The param for addFace is an array of vertex indices.   This array should have a length that is a multiple of 3.
            //
            // For each triplet of values;
            //
            // The first value is an absolute or relative index to a geometric vertex. (VertexGeometric)
            // The second value is an absolute or relative index to a vertex texture coordinate. (VertexTexture)
            // The third vertex is an absolute or relative index to a vertex normal.  (VertexNormal)
            //
            // The indices for the texture and normal MAY be empty in which case they will be set equal to the special
            // value defined in BuilderInterface, EMPTY_VERTEX_VALUE.
            //
            // Absolute indices are positive values that specify a vertex/texture/normal by it's absolute position within the OBJ file.
            //
            // Relative indices are negative values that specify a vertex/texture/normal by it's position relative to the line the index
            // is on, i.e. a line specifying a face (triangle) may specify an geometry vertex as -5 which means the 5 most recently seen
            // geometry vertex.
            public void addFace(int vertexIndices[]) {

            }

            public void addObjectName(String name) {

            }

            public void addMapLib(String[] names) {

            }

            public void setCurrentGroupNames(String[] names) {

            }

            public void setCurrentSmoothingGroup(int groupNumber) {

            }

            public void setCurrentUseMap(String name) {

            }

            public void setCurrentUseMaterial(String name) {

            }

            public void newMtl(String name) {

            }

            public void setXYZ(int type, float x, float y, float z) {

            }

            public void setRGB(int type, float r, float g, float b) {

            }

            public void setIllum(int illumModel) {

            }

            public void setD(boolean halo, float factor) {

            }

            public void setNs(float exponent) {

            }

            public void setSharpness(float value) {

            }

            public void setNi(float opticalDensity) {

            }

            public void setMapDecalDispBump(int type, String filename) {

            }

            public void setRefl(int type, String filename) {

            }

            public void doneParsingMaterial() {

            }

            public void doneParsingObj(String filename) {
                Log.d(TAG, "PaintView allPoints is : " + allPoints.size());
            }
        });

        try {
            mObjectParser.parse(fileName);
        } catch (Exception e) {
            Log.d(TAG, "PaintView allPoints is : " + e.getLocalizedMessage());
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //画到背景Bitmap去
        Paint linePen = new Paint();
        linePen.setColor(Color.RED);
        linePen.setStrokeWidth(2f);

        // mBackgroundCanvas.drawColor(getResources().getColor(R.color.bg_translate));

        if (MiniMapView.this.allPoints.size() > 1) {
            Iterator<Point> iter = MiniMapView.this.allPoints.iterator();
            Point first = null;
            Point last = null;
            while (iter.hasNext()) {
                if (first == null) {
                    first = (Point) iter.next(); //开始
                } else {
                    if (last != null) {
                        first = last;
                    }
                    last = (Point) iter.next();//结束
                    mBackgroundCanvas.drawLine(first.x, first.y, last.x, last.y, linePen);
                }
            }
        }

        mCamera.save();

//        mCamera.rotateX(30);
//        mCamera.rotateY(30);

        mCamera.getMatrix(mMatrix);
        mCamera.restore();

        mMatrix.preTranslate(-mCenterX, -mCenterY);
        mMatrix.postTranslate(mCenterX, mCenterY);

        canvas.save();

        canvas.concat(mMatrix);

        //画到界面上面去
        canvas.drawBitmap(mBackgroundBitmap, 0, 0, null);

        canvas.restore();
    }

}