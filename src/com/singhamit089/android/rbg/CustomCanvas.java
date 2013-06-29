package com.singhamit089.android.rbg;

import java.util.Random;
import java.util.Timer;

import android.R.color;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class CustomCanvas extends View {

	float rectHeight;
	float rectWidth;
	int life = 5;
	int score = 0;
	int level = 0;
	boolean isTouchedDown;
	Random rand = new Random();
	int colors[][] = new int[3][3];
	int color[] = { Color.BLACK, Color.BLUE, Color.CYAN, Color.DKGRAY,
			Color.GRAY, Color.GREEN, Color.LTGRAY, Color.MAGENTA, Color.RED,
			Color.WHITE, Color.YELLOW };
	int randomColor;
	int x = -1, y = -1;
	String targetColor;
	int c = 0;

	public CustomCanvas(Context context, String targetColor) {
		super(context);
		this.targetColor = targetColor;
		isTouchedDown = false;
		Log.w("", "targetColor in CustomCanvas Cunstructor===" + targetColor);
		if (targetColor.equals("red"))
			c = (int) Color.RED;
		else if (targetColor.equals("green"))
			c = (int) Color.GREEN;
		else if (targetColor.equals("blue"))
			c = (int) Color.BLUE;
	}

	Paint paint = new Paint();
	Timer timer = new Timer();
	Rect bounds = new Rect();
	private String tag = "CustomCanvas";

	@Override
	protected void onDraw(final Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawColor(Color.BLACK);

		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setTextSize(50);
		paint.setColor(Color.YELLOW);
		paint.getTextBounds("Life:3       Score=20", 0, 21, bounds);
		canvas.drawText("Score:" + score + "    Life:" + life, (getWidth()
				- bounds.right + bounds.left) / 2, Math.abs(bounds.top), paint);

		final float topBar = Math.abs(bounds.top);
		rectHeight = rectHeight - topBar;
		rectHeight = getHeight() / (level + 3);
		rectWidth = getWidth() / (level + 3);

		for (int i = 0; i < (level + 3); i++) {
			for (int j = 0; j < (level + 3); j++) {
				// randomColor = Color.argb(255,
				// rand.nextInt(255),rand.nextInt(255), rand.nextInt(255));
				randomColor = color[rand.nextInt(11)];
				colors[i][j] = randomColor;
				paint.setColor(randomColor);

				if (x == i && y == j && isTouchedDown) {
					Log.d(tag, "isTouchedDown");

					if (j == 0) {
						canvas.drawRect(i * (rectWidth) + 10,
								(j * rectHeight + topBar) +10, (i + 1)
										* (rectWidth) -10, (j + 1)
										* (rectHeight) -10, paint);
					} else
						canvas.drawRect(i * (rectWidth) +10,
								(j * rectHeight) +10,
								(i + 1) * (rectWidth) -10, (j + 1)
										* (rectHeight) -10, paint);
				}

				else {
					if (j == 0) {
						canvas.drawRect(i * (rectWidth) + 5,
								(j * rectHeight + topBar) + 5, (i + 1)
										* (rectWidth) - 5, (j + 1)
										* (rectHeight) - 5, paint);
					} else
						canvas.drawRect(i * (rectWidth) + 5,
								(j * rectHeight) + 5,
								(i + 1) * (rectWidth) - 5, (j + 1)
										* (rectHeight) - 5, paint);
				}

			}
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			isTouchedDown = true;
			float eventX = event.getX();
			float eventY = event.getY();
			x = (int) (eventX / rectWidth);
			y = (int) (eventY / rectHeight);
			Log.w("", "eventX ===" + eventX + " and x ===" + x);
			Log.w("", "eventY ===" + eventY + "and y===" + y);
			Log.w("", "c===" + c + "and colors[][]===" + colors[x][y]);
			if (c == colors[x][y])
				score++;
			else
				life--;
		} else if(event.getAction()==MotionEvent.ACTION_UP)
			isTouchedDown = false;
		return true;
	}
}
