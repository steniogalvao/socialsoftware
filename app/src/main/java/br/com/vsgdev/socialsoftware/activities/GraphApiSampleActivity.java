package br.com.vsgdev.socialsoftware.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.vsgdev.socialsoftware.R;

public class GraphApiSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_sample);

        //faz uma requisicao assincrona para buscar as infos do user conectado
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(final GraphResponse response) {
                        /* handle the result */
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                final TextView tvUserName = (TextView) findViewById(R.id.tv_user_name);
                                final JSONObject userJson = response.getJSONObject();
                                final String userName;
                                try {
                                    userName = userJson.get("name").toString();
                                    tvUserName.setText(userName);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
        ).executeAsync();

//        //friends list
//        new GraphRequest(
//                AccessToken.getCurrentAccessToken(),
//                "/me/friends",
//                null,
//                HttpMethod.GET,
//                new GraphRequest.Callback() {
//                    public void onCompleted(final GraphResponse response) {
//                        /* handle the result */
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                final JSONArray friendsArray = response.getJSONArray();
//                            }
//                        });
//                    }
//                }
//        ).executeAsync();
    }
}
