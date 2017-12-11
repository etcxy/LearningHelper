package com.lh.learninghelper.Actitivy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lh.learninghelper.R;
import com.lh.learninghelper.slidinguppanel.view.CountDownView;

/**
 * Created by etcxy@live.cn on 12/10/2017.
 */

public class CountDownActivity extends Activity {
    CountDownView cdv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdownlayout);


        cdv = (CountDownView) findViewById(R.id.countDownView);

        cdv.setAddCountDownListener(new CountDownView.OnCountDownFinishListener() {
            @Override
            public void countDownFinished() {
//                Toast.makeText(CountDownActivity.this, "倒计时结束", Toast.LENGTH_SHORT).show();
                CountDownActivity.this.finish();
                startActivity(new Intent(CountDownActivity.this,ReadActivity.class));
            }
        });

        cdv.startCountDown();
//        cdv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cdv.startCountDown();
//            }
//        });
    }

}
