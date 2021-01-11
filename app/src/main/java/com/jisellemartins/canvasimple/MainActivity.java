package com.jisellemartins.canvasimple;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Paint mPaint2 = new Paint();
    private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBitmap;
    private ImageView mImageView;
    private Rect mRect = new Rect();
    private Rect mBounds = new Rect();
    private static final int OFFSET = 120;
    private int mOffset = OFFSET;
    private static final int MULTIPLIER = 100;
    private int mColorBackground;
    private int mColorRectangle;
    private int mColorAccent;
    private int mColorRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mColorBackground = ResourcesCompat.getColor(getResources(),
                R.color.colorBackground, null);
        mColorRectangle = ResourcesCompat.getColor(getResources(),
                R.color.colorRectangle, null);
        mColorAccent = ResourcesCompat.getColor(getResources(),
                R.color.colorCircle, null);
        mColorRed = ResourcesCompat.getColor(getResources(),
                R.color.colorCircle2, null);

        mPaint.setColor(mColorBackground);
        mPaint2.setColor(mColorRed);

        mPaintText.setColor(
                ResourcesCompat.getColor(getResources(),
                        R.color.black, null)
        );
        mPaintText.setTextSize(30);

        mImageView = findViewById(R.id.myImageView);

    }

    public void drawSomething(View view) {

        int vWidth = view.getWidth();
        int vHeight = view.getHeight();
        int halfWidth = vWidth / 2;
        int halfHeight = vHeight / 2;

        int thirdWidth = vWidth / 3;
        int thirdHeight = vHeight / 3;

        if (mOffset == OFFSET) {

            /* CRIANDO O QUADRADO DA IMAGEM */
            mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);

            mImageView.setImageBitmap(mBitmap);

            mCanvas = new Canvas(mBitmap);

            mCanvas.drawColor(mColorBackground);

            mCanvas.drawText(getString(R.string.keep_tapping), 100, 100, mPaintText);


            /* CRIANDO O PRIMEIRO CÍRCULO */

            mPaint2.setColor(mColorRed);
            mCanvas.drawCircle(thirdWidth, thirdHeight, thirdWidth / 3, mPaint2);

            mOffset += OFFSET;
        } else {

            /* CRIANDO O QUADRADO NO TRAMANHO MENOR */

            if (mOffset < halfWidth && mOffset < halfHeight) {
                mPaint.setColor(mColorRectangle - MULTIPLIER * mOffset);
                mRect.set(mOffset + 50, mOffset, vWidth - mOffset, vHeight - mOffset);
                mCanvas.drawRect(mRect, mPaint);
                mOffset += OFFSET;
            } else {

                /* CRIANDO O SEGUNDO CÍRCULO */

                mPaint.setColor(mColorAccent);
                mCanvas.drawCircle(halfWidth + 100, halfHeight + 100, halfWidth / 6, mPaint);
                /*String text = getString(R.string.done);
                mPaintText.getTextBounds(text, 0, text.length(), mBounds);
                int x = halfWidth - mBounds.centerX();
                int y = halfHeight - mBounds.centerY();
                mCanvas.drawText(text, x, y, mPaintText);*/
            }
        }
        view.invalidate();
    }
}