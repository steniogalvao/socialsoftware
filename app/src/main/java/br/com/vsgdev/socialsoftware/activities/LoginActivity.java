package br.com.vsgdev.socialsoftware.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookButtonBase;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import br.com.vsgdev.socialsoftware.R;


public class LoginActivity extends Activity implements FacebookCallback<LoginResult>, View.OnClickListener {
    private LoginButton facebook;
    private String TAG = LoginActivity.class.getName();
    private CallbackManager callbackManager;
    private Button signUp;
    public static CallbackManager callbackmanager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_login);

        final AccessToken token =
                AccessToken.getCurrentAccessToken();
        if (token != null) {
            //todo ir para tela principal
//            startActivity(new Intent(this, GraphApiSampleActivity.class));
//            finish();
        }
        callbackManager = CallbackManager.Factory.create();

        facebook = (LoginButton) findViewById(R.id.btn_facebook_login);
        facebook.setReadPermissions("public_profile", "email");
        facebook.registerCallback(callbackManager, this);
        signUp = (Button) findViewById(R.id.btn_signup_login);
        signUp.setOnClickListener(this);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        //caso conecte-se com sucesso, coleta o token gerado.
        final AccessToken token = loginResult.getAccessToken();
        //todo ir para tela principal
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
    }
}