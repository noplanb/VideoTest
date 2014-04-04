package com.noplanbees.videotest;

import java.util.ArrayList;
import java.util.HashMap;

import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.media.MediaRecorder.VideoEncoder;
import android.util.Log;

public class CamcorderHelper {
	
	private final String TAG = this.getClass().getSimpleName();
	private HashMap<Integer, String> profilesH = new HashMap<Integer, String>();
	private ArrayList<Integer> profilesA = new ArrayList<Integer>();
	private HashMap<Integer, String> vidoeCodecsH = new HashMap<Integer, String>();
	private HashMap<Integer, String> audioCodecsH = new HashMap<Integer, String>();
	private HashMap<Integer, String> fileFormatsH = new HashMap<Integer, String>();
	
	public int[][] optionsList = new int[16][2]; // Holds camera and profile.
	public String[][] listItems = new String[16][2]; // Holds list of title and subtitle for display.
	

	public CamcorderHelper(){
		Log.i(TAG, "constructor");
		loadProfiles();
//		printProfiles();
	}

	private void loadProfiles() {
		loadCamcorderProfiles();
		loadVideoCodecs();
		loadFileFormats();
		loadAudioCodecs();
		loadProfilesArray();
	}

	public String[][] listItems(){
		return listItems;
	}
	
	public int[] cameraAndProfileForPosition(int position){
		return optionsList[position];
	}
	
	private void loadAudioCodecs() {
		audioCodecsH.put(MediaRecorder.AudioEncoder.AAC, "AAC");
		audioCodecsH.put(MediaRecorder.AudioEncoder.AAC_ELD, "AAC_ELD");
		audioCodecsH.put(MediaRecorder.AudioEncoder.AMR_NB, "AMR_NB");
		audioCodecsH.put(MediaRecorder.AudioEncoder.AMR_WB, "AMR_WB");
		audioCodecsH.put(MediaRecorder.AudioEncoder.DEFAULT, "DEFAULT");
		audioCodecsH.put(MediaRecorder.AudioEncoder.HE_AAC, "HE_AAC");
	}

	private void loadCamcorderProfiles() {
		loadProfile(CamcorderProfile.QUALITY_1080P, "QUALITY_1080P");
		loadProfile(CamcorderProfile.QUALITY_480P, "QUALITY_480P");
		loadProfile(CamcorderProfile.QUALITY_720P, "QUALITY_720P");
		loadProfile(CamcorderProfile.QUALITY_CIF, "QUALITY_CIF");
		loadProfile(CamcorderProfile.QUALITY_HIGH, "QUALITY_HIGH");
		loadProfile(CamcorderProfile.QUALITY_LOW, "QUALITY_LOW");
		loadProfile(CamcorderProfile.QUALITY_QCIF, "QUALITY_QCIF");
		loadProfile(CamcorderProfile.QUALITY_QVGA, "QUALITY_QVGA");
	}
	
	private void loadVideoCodecs(){
		loadVEProfile(VideoEncoder.DEFAULT, "DEFAULT");
		loadVEProfile(VideoEncoder.H263, "H263");
		loadVEProfile(VideoEncoder.H264, "H264");
		loadVEProfile(VideoEncoder.MPEG_4_SP, "MPEG_4_SP");
	}
	
	private void loadFileFormats(){
		fileFormatsH.put(MediaRecorder.OutputFormat.AAC_ADTS, "AAC_ADTS");
		fileFormatsH.put(MediaRecorder.OutputFormat.AMR_NB, "AMR_NB");
		fileFormatsH.put(MediaRecorder.OutputFormat.AMR_WB, "AMR_WB");
		fileFormatsH.put(MediaRecorder.OutputFormat.MPEG_4, "MPEG_4");
		fileFormatsH.put(MediaRecorder.OutputFormat.THREE_GPP, "THREE_GPP");
	}
	
	
	private void loadProfile(Integer i, String s){
		profilesA.add(i);
		profilesH.put(i, s);
	}
	
	private void loadVEProfile(Integer i, String s){
		vidoeCodecsH.put(i, s);
	}
	
	private void printProfiles() {
		for (Integer i : profilesA){
			Boolean hasProfile = CamcorderProfile.hasProfile(1, i);
			Log.i(TAG, profilesH.get(i) +"-"+ CamcorderProfile.hasProfile(1, i));
			if (hasProfile){
				CamcorderProfile cp = CamcorderProfile.get(1, i);
				Log.i(TAG, "fileFormat = " + fileFormatsH.get(cp.fileFormat));
				Log.i(TAG, "quality = " + cp.quality);
				Log.i(TAG, "videoBitRate = " + cp.videoBitRate);
				Log.i(TAG, "videoCodec = " + vidoeCodecsH.get(cp.videoCodec) );
				Log.i(TAG, "videoFrameSize = " + cp.videoFrameWidth + "x" + cp.videoFrameHeight);
				Log.i(TAG, "videoFrameRate = " + cp.videoFrameRate);
				Log.i(TAG, "audioBitRate = " + cp.audioBitRate);
				Log.i(TAG, "audioChannels = " + cp.audioChannels);
				Log.i(TAG, "audioCodec = " + cp.audioCodec);
				Log.i(TAG, "audioSampeRate = " + cp.audioSampleRate);
			}
		}
	}
	
	public void loadProfilesArray(){
		int position = 0;
		
		for (int camera=0; camera<2; camera++){
			for (Integer profile : profilesA){
				Boolean hasProfile = CamcorderProfile.hasProfile(camera, profile);

				String cameraName;
				if (camera==0){
					cameraName = "BackCamera ";
				} else {
					cameraName = "FrontCamera ";
				}
				
				String subtitle = "Not Available";
				if (hasProfile){
					CamcorderProfile cp = CamcorderProfile.get(camera, profile);
					subtitle = 
							"fileFormat=" + fileFormatsH.get(cp.fileFormat) +
							"  quality=" + cp.quality + 
							"  videoBitRate=" + cp.videoBitRate +
							"  videoCodec=" + vidoeCodecsH.get(cp.videoCodec) +
							"  videoFrameSize=" + cp.videoFrameWidth + "x" + cp.videoFrameHeight +
							"  videoFrameRate=" + cp.videoFrameRate +
							"  audioBitRate=" + cp.audioBitRate +
							"  audioChannels=" + cp.audioChannels +
							"  audioCodec=" + cp.audioCodec +
							"  audioSampeRate=" + cp.audioSampleRate;
				}
				String profileName = profilesH.get(profile);
				String title = cameraName + profileName;
				
				optionsList[position][0] = camera;
				optionsList[position][1] = profile;
				
				listItems[position][0] = title;
				listItems[position][1] = subtitle;
				position++;
			}
		}
	}
}

