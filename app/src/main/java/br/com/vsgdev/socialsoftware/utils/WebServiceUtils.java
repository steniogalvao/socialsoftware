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

import br.com.vsgdev.socialsoftware.activities.AdressFragment;
import br.com.vsgdev.socialsoftware.activities.LoginActivity;
import br.com.vsgdev.socialsoftware.activities.NewUser2;
import br.com.vsgdev.socialsoftware.models.Address;
import br.com.vsgdev.socialsoftware.models.Item;
import br.com.vsgdev.socialsoftware.models.User;

public class WebServiceUtils {

    public static boolean production = true;
    public static final String PORT = "9001";
    public static RequestQueue queue;
    private static boolean requestingNextAuction;

    /**
     * LIsta de Webservices cadastrados
     */
    public static final String WS_REGISTER_USER = "/registerUser";
    public static final String WS_UPDATE_USER = "/updateUser";
    public static final String WS_CHECK_EMAIL = "/checkEmail";
    public static final String WS_LOGIN = "/login";
    public static final String WS_REGISTER_ADDRESS = "/registerAddress";
    public static final String WS_REGISTER_ITEM = "/registerItem";
    public static final String WS_REGISTER_SALE = "/registerSale";
    public static final String WS_GET_INSTITUTIONS = "/getInstitutions";


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

    /**
     * Metodo que verifica se o email já está cadastrado no BD.
     *
     * @param email        email para ser validado
     * @param context      contexto
     * @param emailHandler handler para resposta
     */
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

    /**
     * Metodo que verifica as credenciais passadas no login.
     *
     * @param user         ususario a ser logado
     * @param context      contexto
     * @param loginHandler handler para retorno
     */
    public static void login(final User user, final Context context, final LoginActivity.LoginHandler loginHandler) {
        HashMap<String, String> params = new HashMap<>();
        params.put("email", user.getEmail());
        params.put("password", user.getPassword());
        JSONObject jsonObject = new JSONObject(params);
        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                getServer() + ":" + PORT + WS_LOGIN,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {
                            Bundle bundle = new Bundle();
                            final Message msg = Message.obtain();
                            if (response.getString("accessToken") != null && !response.getString("accessToken").isEmpty()) {
                                Log.d("WebService", "usuario logado");
                                bundle.putBoolean("loged", true);
                            } else {
                                Log.d("WebService", "credenciais erradas");
                                bundle.putBoolean("loged", false);
                            }
                            msg.setData(bundle);
                            loginHandler.handleMessage(msg);
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

    /**
     * Metodo que registra um novo usuário.
     *
     * @param user    usuario para ser salvo
     * @param context contexto
     */
    public static void registerUser(final User user, final Context context) {
        final JSONObject jsonObject = User.userToJson(user);

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                getServer() + ":" + PORT + WS_REGISTER_USER,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("WebService", "user register successful");
                            //usuario cadastrado com sucesso, ir para main
                            if (Integer.valueOf(response.getString("code")) == 0) {
                                final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                                sharedPreferences.edit().putString("token", response.getString("accessToken")).apply();
                                WebServiceUtils.login(user, context, new LoginActivity.LoginHandler(context));
                            }
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

    /**
     * Metodo que registra um novo endereço.
     *
     * @param address        endereço a ser salvo
     * @param context        contexto
     * @param addressHandler handle necessario para retorno
     */
    public static void registreAddress(final Address address, final Context context, final AdressFragment.AddressHandler addressHandler) {
        final JSONObject jsonObject = Address.AdressToJson(address);

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                getServer() + ":" + PORT + WS_REGISTER_ADDRESS,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("WebService", "address registered");
                            int id = response.getInt("id");
                            if (id != 0) {
                                Message msg = Message.obtain();
                                Bundle bundle = new Bundle();
                                bundle.putInt("id", id);
                                bundle.putSerializable("address", address);
                                msg.setData(bundle);
                                addressHandler.handleMessage(msg);
                            }
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

    /**
     * Metodo que registra um novo item.
     *
     * @param item    item a ser salvo
     * @param context contexto
     */
    public static void registreItem(final Item item, final Context context) {
        final JSONObject jsonObject = Item.itemToJson(item);

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                getServer() + ":" + PORT + WS_REGISTER_ITEM,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("WebService", "item registered");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("WebService", e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.e(WebServiceUtils.class.getName(), "volleyError on register item. " + error.toString());
            }
        }
        );
        //politica de tentativas
        request.setRetryPolicy(new DefaultRetryPolicy(15000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue(context).add(request);
    }

    /**
     * Metodo que atualiza um usuário.
     *
     * @param user    usuario para ser salvo
     * @param context contexto
     */
    public static void updateUser(final User user, final Context context) {
        final JSONObject jsonObject = User.userToJson(user);

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                getServer() + ":" + PORT + WS_UPDATE_USER,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("WebService", "user updated successful");
                            //usuario cadastrado com sucesso, ir para main
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

    /**
     * Metodo que carrega instituições.
     *
     * @param context contexto
     */
    public static void loadInstitutions(final Context context) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                getServer() + ":" + PORT + WS_GET_INSTITUTIONS,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("WebService", "user register successful");
                            //usuario cadastrado com sucesso, ir para main
//                            if (Integer.valueOf(response.getString("code")) == 0) {
//                                final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//                                sharedPreferences.edit().putString("token", response.getString("accessToken")).apply();
//                                WebServiceUtils.login(user, context, new LoginActivity.LoginHandler(context));
//                            }
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


    public static void loadItemsFromUser(final User user,final Context context) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                getServer() + ":" + PORT + WS_GET_INSTITUTIONS,
                new Response.Listener<JSONObject>() {
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("WebService", "user register successful");
                            //usuario cadastrado com sucesso, ir para main
//                            if (Integer.valueOf(response.getString("code")) == 0) {
//                                final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//                                sharedPreferences.edit().putString("token", response.getString("accessToken")).apply();
//                                WebServiceUtils.login(user, context, new LoginActivity.LoginHandler(context));
//                            }
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

}
