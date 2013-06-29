package com.singhamit089.android.rbg;

import java.util.Timer;
import java.util.TimerTask;

import com.singhamit089.android.rbg.CustomCanvas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;

public class RbgActivity extends Activity {
	protected PowerManager.WakeLock mWakeLock;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = this.getIntent().getExtras();
		String targetColor = null;
		String key = null;
		targetColor=b.getString(key);
		Log.w("","targetColor IN RbgActivity ===== "+targetColor);
		final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		this.mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK,
				"My Tag");
		this.mWakeLock.acquire();
		final CustomCanvas cc = new CustomCanvas(this,targetColor);
		setContentView(cc);
		
		TimerTask task = new TimerTask() {

			@Override
			public void run() {

				Runnable runnable = new Runnable() {

					public void run() {

						cc.invalidate();

					}
				};

				runOnUiThread(runnable);

			}
		};
		Timer timer = new Timer();
		timer.schedule(task, 1000, 1000);
	}

	@Override
	public void onDestroy() {
		this.mWakeLock.release();
		super.onDestroy();
	}
}
