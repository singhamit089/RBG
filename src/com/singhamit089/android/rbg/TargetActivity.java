package com.singhamit089.android.rbg;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class TargetActivity extends Activity {
	String targetColor = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Random rand = new Random();
		int target = rand.nextInt(3);
		setContentView(R.layout.activity_target);

		switch (target) {
		case 0:
			targetColor = "red";
			 getWindow().getDecorView().setBackgroundColor(Color.RED);
			break;
		case 1:
			targetColor = "blue";
			getWindow().getDecorView().setBackgroundColor(Color.BLUE);
			break;
		case 2:
			targetColor = "green";
			getWindow().getDecorView().setBackgroundColor(Color.GREEN);
			break;
		}
		Log.w("", "target ====" + target);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void playGame(View view) {
		Intent intent = new Intent(TargetActivity.this, RbgActivity.class);
		Bundle b = new Bundle();
		String key = null;
		b.putString(key, targetColor);
		Log.w("", "targetColor in playGame() function in TargetActivity==="+targetColor);
		intent.putExtras(b);
		startActivity(intent);
	}
}
