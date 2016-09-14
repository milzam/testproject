package id.co.halallocal.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import id.co.halallocal.controller.SignInController;
import id.co.halallocal.R;

/**
 * A login screen that offers login via email/password.
 */
public class SignInActivity extends AppCompatActivity {
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    private SignInController mController;

    // UI references.
    private EditText mUsernameView;
    private EditText mPasswordView;
    private EditText mEmailView;
    private View mProgressView;
    private View mLoginFormView;
    private TextView mAccountPrompt;
    private TextView mAccountAction;
    private TextView mSignInButton;

    private boolean isSignIn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mUsernameView = (EditText) findViewById(R.id.username);
        mEmailView = (EditText) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
        mPasswordView.setTypeface(Typeface.DEFAULT);

        mAccountPrompt = (TextView) findViewById(R.id.textView_accountPrompt);
        mAccountAction = (TextView) findViewById(R.id.textView_accountAction);

        mSignInButton = (TextView) findViewById(R.id.email_sign_in_button);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        mController = new SignInController(this);

        findViewById(R.id.textView_forgetPassword).setOnClickListener(mController);
        mSignInButton.setOnClickListener(mController);
        mAccountAction.setOnClickListener(mController);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {
        if (mAuthTask == null) {
            // Reset errors.
            mUsernameView.setError(null);
            mPasswordView.setError(null);

            // Store values at the time of the login attempt.
            String username = mUsernameView.getText().toString();
            String password = mPasswordView.getText().toString();
            String email = mEmailView.getText().toString();


            // Check for a valid email address.
            if (TextUtils.isEmpty(username)) {
                mUsernameView.setError(getString(R.string.error_field_required));
                mUsernameView.requestFocus();
                return;
            } else if (TextUtils.isEmpty(password)) {
                mPasswordView.setError(getString(R.string.error_field_required));
                mPasswordView.requestFocus();
                return;
            } else if (!isSignIn) {
                if (TextUtils.isEmpty(email)) {
                    mEmailView.setError(getString(R.string.error_field_required));
                    mEmailView.requestFocus();
                    return;
                } else if (!isEmailValid(email)) {
                    mEmailView.setError(getString(R.string.error_invalid_email));
                    mEmailView.requestFocus();
                    return;
                } else if (!isPasswordValid(password)) {
                    mPasswordView.setError(getString(R.string.error_invalid_password));
                    mPasswordView.requestFocus();
                    return;
                }
            }

            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(username, password, email);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String pEmail) {
        String tEmailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern tPattern = Pattern.compile(tEmailPattern);
        Matcher tMatcher = tPattern.matcher(pEmail);
        return tMatcher.matches();
    }

    private boolean isPasswordValid(String pPassword) {
        //TODO: Password Logic
        return pPassword.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    private void showProgress(boolean show) {
        // The ViewPropertyAnimator APIs are not available, so simply show
        // and hide the relevant UI components.
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mAccountAction.setVisibility(show ? View.GONE : View.VISIBLE);
        mAccountPrompt.setVisibility(show ? View.GONE : View.VISIBLE);
        findViewById(R.id.textView_forgetPassword).setVisibility(show ? View.GONE : View.VISIBLE);
    }

    public void toggleSignInRegister() {
        isSignIn = !isSignIn;
        mAccountAction.setText(isSignIn ? R.string.sign_up : R.string.sign_in);
        mAccountPrompt.setText(isSignIn ? R.string.no_account_prompt : R.string.have_account_prompt);
        findViewById(R.id.email_container).setVisibility(isSignIn ? View.GONE : View.VISIBLE);
        mSignInButton.setText(isSignIn ? R.string.sign_in : R.string.sign_up);
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, String> {

        private final String mEmail;
        private final String mPassword;
        private final String mUsername;

        UserLoginTask(String email, String password, String username) {
            mEmail = email;
            mPassword = password;
            mUsername = username;
        }

        @Override
        protected String doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.
            String result = "true";
            try {
                // Simulate network access.
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                return e.getMessage();
            }

            if (isSignIn) {
            } else {
            }

            // TODO: register the new account here.
            return result;
        }

        @Override
        protected void onPostExecute(String success) {
            mAuthTask = null;
            showProgress(false);

            if (success.equals("true")) {
                Intent tIntent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(tIntent);
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

