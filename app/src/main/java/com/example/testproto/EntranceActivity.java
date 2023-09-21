package com.example.testproto;



import static com.example.testproto.utils.Constants.TEST_NUMBER_EXTRA;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EntranceActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entrace2);

		
		Button button1 = (Button) findViewById(R.id.normal);
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				launchMainProtosoundActivity(view.getContext(), 0);
			}
		});

	}


	/**
	 * @param test the test number that we wants to run (ideally each button has a unique test number to pass
	 *             as a parameter to the main activity so that it runs in different settings
	 *             If no test number is set, run normal MainActivity
	 *             For example, currently Hung set:
	 *             - 0 for default main activity
	 *             - 1 as the test for end to end prediction latency test
	 *             - 2 as the test for end to eng training training pipeline test
	 *             - TODO Khoa: 3 4 5 6.. can be other tests that we want to run
	 */
	public void launchMainProtosoundActivity(Context context, int test) {
		Intent intent = new Intent(context, MainActivity.class);
		intent.putExtra(TEST_NUMBER_EXTRA, test);
		context.startActivity(intent);
	}
}
