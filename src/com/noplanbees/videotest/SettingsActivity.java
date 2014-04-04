package com.noplanbees.videotest;

import android.app.ListActivity;
import android.content.Intent;
import android.media.CamcorderProfile;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class SettingsActivity extends ListActivity {
	private final String TAG = getClass().getSimpleName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String[][] listItems = new CamcorderHelper().listItems();

		setListAdapter(new SettingsArrayAdapter(this, listItems));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		int[] cp = new CamcorderHelper().cameraAndProfileForPosition(position);
		int camera = cp[0];
		int profile = cp[1];
		Log.i(TAG, String.format("onListItemClick: got camera=%d  cp=%d", camera, profile));
		if (CamcorderProfile.hasProfile(profile)){
			Intent i = new Intent(this, RecordActivity.class);
			i.putExtra("camera", camera);
			i.putExtra("profile", profile);
			startActivity(i);
		} else {
			Log.i(TAG, "Not a valid profile.");
		}
	}
}

