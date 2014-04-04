package com.noplanbees.videotest;

import android.app.Activity;
import android.content.Intent;
import android.media.CamcorderProfile;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class PlayActivity extends Activity{
	private final String TAG = this.getClass().getSimpleName();
	
	private static PlayActivity instance;
	private VideoPlayer videoPlayer;
	private FrameSizeHelper fsh;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		setContentView(R.layout.play);
		
		instance = this;
		videoPlayer = new VideoPlayer(this);	
		
		fsh = new FrameSizeHelper(this);
		
		if (fsh.noCameraParamsInIntent()){
			finish();
			return;
		}
		addListeners();
		fsh.resize();
		videoPlayer.start();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.i(TAG, "onStart:");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i(TAG, "onStop:");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i(TAG, "onPause");
	}


	@Override
	protected void onResume() {
		super.onResume();
		Log.i(TAG, "onResume");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "onDestroy");
	}
	
	private void addListeners() {
		findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				videoPlayer.stop();
				Intent i = new Intent(instance, SettingsActivity.class);
				startActivity(i);
			}
		});	
	}

}
