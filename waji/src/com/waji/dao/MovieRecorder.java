package com.waji.dao;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.waji.http.SendImage;
import com.waji.local.SharedPrefer;
import com.waji.utils.StreamUtils;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.media.MediaRecorder.AudioEncoder;
import android.media.MediaRecorder.AudioSource;
import android.os.Environment;
import android.view.SurfaceView;

public class MovieRecorder {
	private MediaRecorder mediarecorder;
	private String lastFileName;

	public void startRecording(SurfaceView surfaceView, Context context, int who)
			throws Exception {
		mediarecorder = new MediaRecorder();
		mediarecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
		mediarecorder.setAudioSource(AudioSource.MIC);
		mediarecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mediarecorder.setAudioEncoder(AudioEncoder.AMR_NB);
		mediarecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
		mediarecorder.setVideoSize(176, 144);
		mediarecorder.setVideoFrameRate(20);
		mediarecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());
		lastFileName = newFileName(context, who);
		mediarecorder.setOutputFile(lastFileName);

		try {
			mediarecorder.prepare();
			mediarecorder.start();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String newFileName(Context context, int who) {
		try {
			int number = SharedPrefer.ReadNumber(context);
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy—MM—dd");
			SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm:ss ");
			Date curDate = new Date(System.currentTimeMillis());
			String str = formatter.format(curDate);
			String str1;
			if (who == 2) {
                 str1 = formatter1.format(curDate);
			} else {
               str1 = number+"";
			}
            File mFile = new File(Environment.getExternalStorageDirectory()
					.getAbsoluteFile() + "/QRcode/" + str);
             if (!mFile.exists()) {
				mFile.mkdir();
			}

			String f = mFile + "/" + str1 + ".3gp";
			SendImage.serviceVideoPath=f;
			return f;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public void stopRecording() {
		if (mediarecorder != null) {
			mediarecorder.stop();
			mediarecorder.release();
			mediarecorder = null;
		}
	}

	public void release() {
		if (mediarecorder != null) {
			mediarecorder.stop();
			mediarecorder.release();
			mediarecorder = null;
		}
	}
}
