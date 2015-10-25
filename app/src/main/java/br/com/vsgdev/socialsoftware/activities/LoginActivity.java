package br.com.vsgdev.socialsoftware.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;

import br.com.vsgdev.socialsoftware.R;


public class LoginActivity extends Activity implements FacebookCallback<LoginResult>, View.OnClickListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private LoginButton facebook;
    private com.google.android.gms.common.SignInButton google;
    private String TAG = LoginActivity.class.getName();
    private CallbackManager callbackManager;
    private Button signUp;
    public static CallbackManager callbackmanager;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 0;
    private boolean mIsResolving = false;
    private boolean mShouldResolve = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_login);

        final AccessToken token =
                AccessToken.getCurrentAccessToken();
        if (token != null) {
            //todo ir para tela principal
            openMain();
//            startActivity(new Intent(this, GraphApiSampleActivity.class));
//            finish();
        }
        callbackManager = CallbackManager.Factory.create();

        facebook = (LoginButton) findViewById(R.id.btn_facebook_login);
        facebook.setReadPermissions("public_profile", "email");
        facebook.registerCallback(callbackManager, this);
        signUp = (Button) findViewById(R.id.btn_signup_login);
        signUp.setOnClickListener(this);

        // Build GoogleApiClient with access to basic profile
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .addScope(new Scope(Scopes.EMAIL))
                .build();

        google = (com.google.android.gms.common.SignInButton) findViewById(R.id.btn_google_login);
        google.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);

        if (requestCode == RC_SIGN_IN) {
            // If the error resolution was not successful we should not resolve further.
            if (resultCode != RESULT_OK) {
                mShouldResolve = false;
            }

            mIsResolving = false;
            mGoogleApiClient.connect();
        }

    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        //caso conecte-se com sucesso, coleta o token gerado.
        final AccessToken token = loginResult.getAccessToken();
        openMain();
        Toast.makeText(this, token.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCancel() {
        Log.d(TAG, "login request canceled");
    }

    @Override
    public void onError(FacebookException e) {
        final String errorMsg = e.getMessage();
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
        System.out.println(errorMsg);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public void onClick(View v) {
        if (signUp.isPressed()) {
            Intent newUser = new Intent(this, NewUser.class);
            startActivity(newUser);
        }
        if (v.getId() == R.id.btn_google_login) {
            if (mShouldResolve == false) {
                if (mGoogleApiClient.isConnected()) {
                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                    mGoogleApiClient.disconnect();
                }
            } else {
                mShouldResolve = true;
                mGoogleApiClient.connect();
                System.out.println("Logou");
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(Bundle bundle) {
        //TODO xxx continue
        Log.d(TAG, "onConnected:" + bundle);
        mShouldResolve = false;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e("LoginActivity", connectionResult.toString());
        if (!mIsResolving && mShouldResolve) {
            if (connectionResult.hasResolution()) {
                try {
                    connectionResult.startResolutionForResult(this, RC_SIGN_IN);
                    mIsResolving = true;
                } catch (IntentSender.SendIntentException e) {
                    Log.e(TAG, "Could not resolve ConnectionResult.", e);
                    mIsResolving = false;
                    mGoogleApiClient.connect();
                }
            } else {
                // Could not resolve the connection result, show the user an
                // error dialog.
                Log.e("LoginActivity", connectionResult.toString());
            }
        } else {
            // Show the signed-out UI
            Log.e("LoginActivity", connectionResult.toString());
        }
    }

    private void openMain() {
        Intent main = new Intent(this, Main.class);
        startActivity(main);
        finish();
    }
}