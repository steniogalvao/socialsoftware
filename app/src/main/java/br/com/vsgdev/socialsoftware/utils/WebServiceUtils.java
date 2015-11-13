package br.com.vsgdev.socialsoftware.utils;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

import br.com.vsgdev.socialsoftware.activities.NewUser2;
import br.com.vsgdev.socialsoftware.models.User;

public class WebServiceUtils {

    public static boolean production = true;
    public static final String PORT = "9001";
    public static RequestQueue queue;
    private static boolean requestingNextAuction;

    //registra o usuario em nosso server, armazenando, por exemplo o token GCM.
    //listar services
    public static final String WS_REGISTER_USER = "/registerUser";
    public static final String WS_CHECK_EMAIL = "/checkEmail";

    private static String getServer() {
        if (production) {
            return "http://107.170.121.53";
        } else {
            return "http://192.168.0.31";
        }
    }

    private static RequestQueue getRequestQueue(final Context context) {
        if (queue == null && context != null) {
            queue = Volley.newRequestQueue(context);
        }
        return queue;
    }

    public static void validFake(NewUser2.EmailHandler emailHandler) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("valido", true);
        Message msg = Message.obtain();
        msg.setData(bundle);
        emailHandler.handleMessage(msg);

    }

    public static void validateEmail(final String email, final Context context, final NewUser2.EmailHandler emailHandler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("email", email);
        JSONObject jsonObject = new JSONObject(params);
        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                getServer() + ":" + PORT + WS_CHECK_EMAIL,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {

                            Bundle bundle = new Bundle();
                            final Message msg = Message.obtain();
                            //usuario cadastrado com sucesso, ir para main
                            if (Integer.valueOf(response.getString("code")) == 4) {
                                Log.d("WebService", "email livre");
                                bundle.putBoolean("valido", true);
                            } else {
                                Log.d("WebService", "email ocupado");
                                bundle.putBoolean("valido", false);
                            }
                            msg.setData(bundle);
                            emailHandler.handleMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("WebService", e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.e(WebServiceUtils.class.getName(), "volleyError on register user. " + error.toString());
            }
        }
        );
        //politica de tentativas
        request.setRetryPolicy(new DefaultRetryPolicy(15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue(context).add(request);
    }

    public static void registerUser(final User user, final Context context) {
        final JSONObject jsonUser = User.userToJson(user);

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                getServer() + ":" + PORT + WS_REGISTER_USER,
                jsonUser,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("WebService", "user register successful");
                            //usuario cadastrado com sucesso, ir para main
                            if (Integer.valueOf(response.getString("code")) == 0) {
                                //chamar servico de logar
                                final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                                sharedPreferences.edit().putString("token", response.getString("accessToken"));
                            } else {
                                //code
                            }

// final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//                            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, true).apply();
//                            final User user = JSONConverter.responseToUser(response);
//                            MainActivity.currentUser = user;

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("WebService", e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.e(WebServiceUtils.class.getName(), "volleyError on register user. " + error.toString());
            }
        }
        );
        //politica de tentativas
        request.setRetryPolicy(new DefaultRetryPolicy(15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue(context).add(request);
    }

//    public static void login(final User user, final Context context) {
//        final JSONObject jsonUser = User.userToJson(user);
//
//        final JsonObjectRequest request = new JsonObjectRequest(
//                Request.Method.POST,
//                getServer() + ":" + PORT + WS_REGISTER_USER,
//                jsonUser,
//                new Response.Listener<JSONObject>() {
//                    public void onResponse(JSONObject response) {
//                        try {
//                            Log.d("WebService", "user register successful");
//                            //usuario cadastrado com sucesso, ir para main
//                            if (Integer.valueOf(response.getString("code")) == 0){
//                                //chamar servico de logar
//                                final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//                                sharedPreferences.edit().putString("token", response.getString("accessToken"));
//                            }else{
//
//                            }
//
//// final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
////                            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, true).apply();
////                            final User user = JSONConverter.responseToUser(response);
////                            MainActivity.currentUser = user;
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                            Log.e("WebService", e.getMessage());
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            public void onErrorResponse(VolleyError error) {
//                Log.e(WebServiceUtils.class.getName(), "volleyError on register user. " + error.toString());
//            }
//        }
//        );
//        request.setRetryPolicy(new DefaultRetryPolicy(15000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        getRequestQueue(context).add(request);
//    }
}

