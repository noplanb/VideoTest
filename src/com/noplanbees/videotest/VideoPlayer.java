package com.noplanbees.videotest;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.VideoView;

public class VideoPlayer {
	String TAG = this.getClass().getSimpleName();
	Activity activity;
	Context context;	
	VideoView videoView;
	Button playButton;

	public VideoPlayer(Activity a) {
		activity = a;
		context = activity.getApplicationContext();
		videoView = (VideoView) activity.findViewById(R.id.video_view);
		playButton = (Button) activity.findViewById(R.id.btn_play);
		videoView.setVideoPath(Config.recordedFilePath());
	}

	public void start(){
		videoView.start();
	}

	public void stop(){
		if (videoView.isPlaying())
			videoView.stopPlayback();
	}
}

