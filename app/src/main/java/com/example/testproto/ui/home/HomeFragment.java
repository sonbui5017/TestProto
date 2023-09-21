package com.example.testproto.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.testproto.MainActivity;
import com.example.testproto.R;
import com.example.testproto.ui.SharedViewModel;
import com.example.testproto.ui.home.service.ForegroundService;
import com.kuassivi.component.RipplePulseRelativeLayout;

public class HomeFragment extends Fragment {

	private static final String TAG = "HomeFragment";
	private HomeViewModel homeViewModel;
	private SharedViewModel sharedViewModel;
	private ImageButton listeningBtn;
	private RipplePulseRelativeLayout pulseLayout;
	private TextView soundTextView;

	/**
	 * Recording
	 */

	public static boolean IS_RECORDING = false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
		sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);


        View root = inflater.inflate(R.layout.fragment_home, container, false);
		ListView listView = root.findViewById(R.id.listView);
		sharedViewModel.getAdapter().observe(requireActivity(), (Observer<MainActivity.TimelineAdapter>) adapter -> {
			listView.setAdapter(adapter);
			listView.setEnabled(false);
		});
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        listeningBtn = (ImageButton) root.findViewById(R.id.mic);
		pulseLayout = root.findViewById(R.id.pulseLayout);
		soundTextView = root.findViewById(R.id.description);
		// setup button for the first time
//		if (IS_RECORDING) {
//			switchToStartRecord(listeningBtn, pulseLayout, soundTextView);
//		} else {
//			switchToStopRecord(listeningBtn, pulseLayout, soundTextView);
//		}
////		soundTextView.setText(R.string.tap_blue);
//		setOnClickListening(listeningBtn, pulseLayout, soundTextView);
//		Log.d(TAG, "ONCREATEVIEW EXECUTED");

		listeningBtn.setOnClickListener(view -> {
			Log.d("sobu","listeningBtn.setOnClickListener");
			if (IS_RECORDING) {
				stopRecording(requireActivity());
//				switchToStopRecord(listeningBtn, pulseLayout, soundTextView);
			} else  {
				startRecording(requireActivity());
//				switchToStartRecord(listeningBtn, pulseLayout, soundTextView);
			}
			IS_RECORDING = !IS_RECORDING;
		});
        return root;
    }


	@Override
	public void onPause() {
		super.onPause();
		switchToStopRecord(listeningBtn, pulseLayout, soundTextView);
		stopRecording(requireActivity());
		IS_RECORDING = false;
	}

	private void setOnClickListening(ImageButton listeningBtn, RipplePulseRelativeLayout pulseLayout, TextView soundTextView) {
//		TextView soundTextView = requireActivity().findViewById(R.id.description);
		Log.d("sobu","setOnClickListening");
    	listeningBtn.setOnClickListener(v -> {
			Log.d("sobu","listeningBtn.setOnClickListener");
			if (!IS_RECORDING) {
//				switchToStartRecord(listeningBtn, pulseLayout, soundTextView);
//				startRecording(requireActivity());
			} else {
//				switchToStopRecord(listeningBtn, pulseLayout, soundTextView);
//				stopRecording(requireActivity());
			}
			// Flip the flag so we can turn off/on next time
			IS_RECORDING = !IS_RECORDING;
		});
	}

	private void switchToStartRecord(ImageButton listeningBtn, RipplePulseRelativeLayout pulseLayout, TextView soundTextView) {
		listeningBtn.setBackground(getResources().getDrawable(R.drawable.rounded_background_red, null));
		listeningBtn.setImageResource(R.drawable.ic_baseline_pause_24);
//				listeningBtn.setForegroundGravity(Gravity.BOTTOM);
		soundTextView.setText("Listening...");
		Log.d("sobu","switchToStartRecord");
		pulseLayout.startPulse();
	}


	private void switchToStopRecord(ImageButton listeningBtn, RipplePulseRelativeLayout pulseLayout, TextView soundTextView) {
		listeningBtn.setBackground(getResources().getDrawable(R.drawable.rounded_background_blue, null));
		listeningBtn.setImageResource(R.drawable.ic_mic_24);
		soundTextView.setText(R.string.tap_blue);
		Log.d("sobu","switchToStopRecord");
		pulseLayout.stopPulse();
	}

	/**
	 * Run this function to start streaming audio and send prediction back to phone
	 */
	public void onRecordClick() {
    	if (!IS_RECORDING) {
//			startRecording(requireActivity());
		} else {
//    		stopRecording(requireActivity());
		}
    	// Flip the flag so we can turn off/on next time
    	IS_RECORDING = !IS_RECORDING;
	}

	private void startRecording(final Context main) {
		Log.d("sobu","startRecording");
		try {
			Intent serviceIntent = new Intent(main, ForegroundService.class);
			ContextCompat.startForegroundService(main, serviceIntent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopRecording(final Context main) {
		Log.d("sobu","stopRecording");
		try {
			Intent serviceIntent = new Intent(main, ForegroundService.class);
			main.stopService(serviceIntent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
