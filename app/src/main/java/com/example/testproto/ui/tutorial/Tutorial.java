package com.example.testproto.ui.tutorial;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.testproto.R;
import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;

public class Tutorial extends TutorialActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addFragment(new Step.Builder()
                .setBackgroundColor(Color.parseColor("#000000"))
                .setDrawable(R.drawable.tutorial_1)
                .build());
        addFragment(new Step.Builder()
                .setBackgroundColor(Color.parseColor("#000000"))
                .setDrawable(R.drawable.tutorial_2)
                .build());
        addFragment(new Step.Builder()
                .setBackgroundColor(Color.parseColor("#000000"))
                .setDrawable(R.drawable.tutorial_3)
                .build());
        addFragment(new Step.Builder()
                .setBackgroundColor(Color.parseColor("#000000"))
                .setDrawable(R.drawable.tutorial_4)
                .build());
        addFragment(new Step.Builder()
                .setBackgroundColor(Color.parseColor("#000000"))
                .setDrawable(R.drawable.tutorial_5)
                .build());
        addFragment(new Step.Builder()
                .setBackgroundColor(Color.parseColor("#000000"))
                .setDrawable(R.drawable.tutorial_6)
                .build());
        addFragment(new Step.Builder()
                .setBackgroundColor(Color.parseColor("#000000"))
                .setDrawable(R.drawable.tutorial_7)
                .build());
        addFragment(new Step.Builder()
                .setBackgroundColor(Color.parseColor("#000000"))
                .setDrawable(R.drawable.tutorial_8)
                .build());
        addFragment(new Step.Builder()
                .setBackgroundColor(Color.parseColor("#000000"))
                .setDrawable(R.drawable.tutorial_9)
                .build());
        addFragment(new Step.Builder()
                .setBackgroundColor(Color.parseColor("#000000"))
                .setDrawable(R.drawable.tutorial_10)
                .build());
        addFragment(new Step.Builder()
                .setBackgroundColor(Color.parseColor("#000000"))
                .setDrawable(R.drawable.tutorial_11)
                .build());
        addFragment(new Step.Builder()
                .setBackgroundColor(Color.parseColor("#000000"))
                .setDrawable(R.drawable.tutorial_12)
                .build());
        addFragment(new Step.Builder()
                .setBackgroundColor(Color.parseColor("#000000"))
                .setDrawable(R.drawable.tutorial_13)
                .build());
        addFragment(new Step.Builder()
                .setBackgroundColor(Color.parseColor("#000000"))
                .setDrawable(R.drawable.tutorial_14)
                .build());

    }

    @Override
    public void currentFragmentPosition(int position) {

    }
}
