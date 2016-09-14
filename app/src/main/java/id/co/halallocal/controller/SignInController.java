package id.co.halallocal.controller;

import android.view.View;
import android.view.View.OnClickListener;

import id.co.halallocal.R;
import id.co.halallocal.activity.SignInActivity;

/**
 * Created by Milzam on 9/8/2016.
 */
public class SignInController implements OnClickListener {
    private SignInActivity mActivity;

    public SignInController(SignInActivity pActivity) {
        mActivity = pActivity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.email_sign_in_button:
                mActivity.attemptLogin();
                break;
            case R.id.textView_forgetPassword:
                break;
            case R.id.textView_accountAction:
                mActivity.toggleSignInRegister();
                break;
        }
    }
}
