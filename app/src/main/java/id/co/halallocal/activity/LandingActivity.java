package id.co.halallocal.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import id.co.halallocal.R;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }

    @Override
    protected void onResume() {
        super.onResume();
        View tLogo = findViewById(R.id.imageView_logo);
        tLogo.setAlpha(0f);
        tLogo.animate().setDuration(5000).alpha(1f).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // TODO: 9/11/2016 User Signed In Logic
                Intent tIntent = new Intent(getApplicationContext(), SignInActivity.class);
                startActivity(tIntent);
                finish();
            }
        }).start();
    }
}
