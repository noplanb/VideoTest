package com.noplanbees.videotest;

import android.app.Activity;
import android.content.Intent;
import android.media.CamcorderProfile;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;


public class RecordActivity extends Activity {
	private final String TAG = this.getClass().getSimpleName();

	public static RecordActivity instance;
	public VideoRecorder videoRecorder;
	private FrameSizeHelper fsh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "onCreate");
		setContentView(R.layout.record);
		fsh = new FrameSizeHelper(this);
		
		if (fsh.noCameraParamsInIntent()){
			finish();
			return;
		}
		fsh.resize();

		instance = this;
		videoRecorder = new VideoRecorder(this, fsh.camera(), fsh.profile());			
		addListeners();
		new CamcorderHelper();
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.i(TAG, "onStart:");
		videoRecorder.restore();
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i(TAG, "onStop:");
		videoRecorder.dispose();
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
		findViewById(R.id.btn_record).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				videoRecorder.startRecording();
			}
		});	
		findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				videoRecorder.stopRecording();
			}
		});	
		findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent ci = getIntent();
				Intent i = new Intent(RecordActivity.instance, PlayActivity.class);
				i.putExtras(ci);
				startActivity(i);
			}
		});	

		findViewById(R.id.btn_settings).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(RecordActivity.instance, SettingsActivity.class);
				startActivity(i);
			}
		});	
	}
}
