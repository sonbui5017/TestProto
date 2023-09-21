package com.example.testproto.ui.home.service;



import static com.example.testproto.utils.Constants.CHANNEL_ID;
import static com.example.testproto.utils.Constants.VOICE_FILE_NAME;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;


import com.example.testproto.MainActivity;
import com.example.testproto.R;
import com.example.testproto.utils.StreamingSoundRecorder;

import java.util.HashSet;
import java.util.Set;

public class ForegroundService extends Service {
	private static final String TAG = "ForegroundService";

	private StreamingSoundRecorder mSoundRecorder;
	private CountDownTimer mCountDownTimer;
	private static Set<String> connectedHostIds = new HashSet<>();
	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate: ");
		super.onCreate();
		mSoundRecorder = new StreamingSoundRecorder(this, VOICE_FILE_NAME);
	}

	@Override
	public int onStartCommand(final Intent intent, int flags, int startId) {
		String input = intent.getStringExtra("connectedHostIds");
//		final Set<String> connectedHostIds = new HashSet<>(
//				Arrays.asList(
//						input.split(",")
//				)
//		);
//		createNotificationChannel();
//		Intent notificationIntent = new Intent(this, MainActivity.class);
//		PendingIntent pendingIntent = PendingIntent.getActivity(this,
//				0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
//		Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
//				.setContentTitle("Listening...")
//				.setSmallIcon(com.hololo.tutorial.library.R.drawable.circle_white)
//				.setContentIntent(pendingIntent)
//				.build();
//		startForeground(2, notification);
		mSoundRecorder.startRecording();
		Log.d(TAG, "Start Recording...");
		return START_NOT_STICKY;
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "Stopping foreground services");
		if (mSoundRecorder != null) {
			Log.i(TAG, "Stop recorder");
			mSoundRecorder.stopRecording();
		}
		if (mCountDownTimer != null) {
			mCountDownTimer.cancel();
		}
	}
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	private void createNotificationChannel() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			NotificationChannel serviceChannel = new NotificationChannel(
					CHANNEL_ID,
					"Foreground Service Channel",
					NotificationManager.IMPORTANCE_DEFAULT
			);
			NotificationManager manager = getSystemService(NotificationManager.class);
			manager.createNotificationChannel(serviceChannel);
		}
	}
}
