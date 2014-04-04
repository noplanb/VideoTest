package com.noplanbees.videotest;

import android.app.Activity;
import android.graphics.Point;
import android.media.CamcorderProfile;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class FrameSizeHelper {
	private final String TAG = this.getClass().getSimpleName();

	Activity activity;
	public FrameSizeHelper(Activity a){
		activity = a;
	}
	
	public boolean noCameraParamsInIntent() {
		return camera() == null || profile() == null;
	}
	
	private Bundle cameraAndProfile(){
		Log.i(TAG, "cameraAndProfile: " + activity.getIntent().getExtras().toString());
		return activity.getIntent().getExtras();
	}
	public Integer profile() {
		return (Integer) cameraAndProfile().get("profile");
	}

	public Integer camera() {
		return (Integer) cameraAndProfile().get("camera");
	}
	
	private int[] windowSize(){
		Display display = activity.getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int r[] = new int[2];
		r[0] = size.x;
		r[1] = size.y;
		return r;
	}
	
	private int windowWidth(){
		return windowSize()[0];
	}
		
	private int[] calcFrameSize(){
		int r[] = new int[2];
		
		CamcorderProfile cp = CamcorderProfile.get(camera(), profile());
		float aspect = (float)cp.videoFrameWidth / (float)cp.videoFrameHeight;

		int maxWidth = (int) ((float) windowWidth() * 0.85F);
		Log.i(TAG, String.format("calcFrameSize: windowW=%d, maxWidth=%d, cpH=%d, aspect=%f", windowWidth(), maxWidth, cp.videoFrameHeight, aspect));
		if (cp.videoFrameHeight > maxWidth){
			Log.i(TAG, "calcFrameSize: setting r0 to max width");
			r[0] = maxWidth;
		} else {
			Log.i(TAG, "calcFrameSize: setting r0 to videoFrameHeight");
			r[0] = cp.videoFrameHeight;
		}
		r[1] = (int) ((float) r[0] * aspect);
		return r;
	}
	
	public void resize(){
		FrameLayout cpf = (FrameLayout) activity.findViewById(R.id.camera_preview_frame);
		LayoutParams lp = cpf.getLayoutParams();
		CamcorderProfile cp = CamcorderProfile.get(camera(), profile());
		int[] frameSize = calcFrameSize();
		lp.width = frameSize[0];
		lp.height = frameSize[1];
		cpf.setLayoutParams(lp);
		cpf.invalidate();
	}
}
