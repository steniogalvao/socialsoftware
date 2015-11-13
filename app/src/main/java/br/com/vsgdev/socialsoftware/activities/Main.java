package br.com.vsgdev.socialsoftware.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

import br.com.vsgdev.socialsoftware.R;
import br.com.vsgdev.socialsoftware.utils.UserLogedSingleton;

public class Main extends ActionBarActivity implements TextView.OnClickListener {

    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private static String TAG = Main.class.getSimpleName();
    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();
    private String profileId;
    private String name, surname, email;
    private URL img_value = null;
    private ImageView profilePicture;
    private TextView profileName, viewProfile;
    private Fragment servicesFragment = new ServicesFragment();
    private Fragment institutionFragment = new InstitutionsFragment();
    private Fragment viewProfileFragment = new ViewProfileFragment();
    private RelativeLayout profileArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle(null);
        profileArea = (RelativeLayout) findViewById(R.id.profileBox);
        profileArea.setOnClickListener(this);
        profilePicture = (ImageView) findViewById(R.id.iv_avatar_main);
        profileName = (TextView) findViewById(R.id.tv_user_name_main);
        viewProfile = (TextView) findViewById(R.id.tv_desc_main);
        viewProfile.setOnClickListener(this);
        mNavItems.add(new NavItem(getString(R.string.services), getString(R.string.see_and_search_services_registered), R.drawable.social));
        mNavItems.add(new NavItem(getString(R.string.instituitions), getString(R.string.know_partner_institutions), R.drawable.ic_institutions));
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);// Populate th
        // e Navigtion Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromMenu(position);
            }
        });
        populateProfile();
        useServicesFragment();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle
        // If it returns true, then it has handled
        // the nav drawer indicator touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.menu_main_logout:
                final AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if (accessToken != null) {
                    LoginManager.getInstance().logOut();
                    Intent login = new Intent(this, LoginActivity.class);
                    startActivity(login);
                    finish();
                } else {

                    Intent login = new Intent(this, LoginActivity.class);
                    startActivity(login);
                    finish();
                }
                break;

        }

        // Handle your other action bar items...
        return super.onOptionsItemSelected(item);
    }

    private void selectItemFromMenu(int position) {
        FragmentManager fragmentManager = getFragmentManager();
        switch (position) {
            case 0:
                //seleciona o fragmento a ser inflado
                useServicesFragment();
                break;
            case 1:
                useInstitutionFragment();
                break;

        }
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerPane);

    }

    class NavItem {
        String mTitle;
        String mSubtitle;
        int mIcon;

        public NavItem(String title, String subtitle, int icon) {
            mTitle = title;
            mSubtitle = subtitle;
            mIcon = icon;
        }
    }

    class DrawerListAdapter extends BaseAdapter {

        Context mContext;
        ArrayList<NavItem> mNavItems;

        public DrawerListAdapter(Context context, ArrayList<NavItem> navItems) {
            mContext = context;
            mNavItems = navItems;
        }

        @Override
        public int getCount() {
            return mNavItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mNavItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.drawer_item, null);
            } else {
                view = convertView;
            }

            TextView titleView = (TextView) view.findViewById(R.id.title);
            TextView subtitleView = (TextView) view.findViewById(R.id.subTitle);
            ImageView iconView = (ImageView) view.findViewById(R.id.icon);

            titleView.setText(mNavItems.get(position).mTitle);
            subtitleView.setText(mNavItems.get(position).mSubtitle);
            iconView.setImageResource(mNavItems.get(position).mIcon);

            return view;
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void populateProfile() {
        final AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if (accessToken != null) {
            GraphRequest request = GraphRequest.newMeRequest(
                    accessToken,
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(
                                JSONObject object,
                                GraphResponse response) {
                            JSONObject jResponse = response.getJSONObject();
                            try {
                                profileId = jResponse.get("id").toString();
                                UserLogedSingleton.getInstance().setName(jResponse.get("first_name").toString());
                                UserLogedSingleton.getInstance().setSurrname(jResponse.get("last_name").toString());
                                UserLogedSingleton.getInstance().setEmail(jResponse.get("email").toString());
                                // a linha abaixo da exception pq n pode usar a thread principal
                                HTTPExample getPicture = new HTTPExample();
                                getPicture.execute(new String[]{"https://graph.facebook.com/" + profileId + "/picture?type=large"});
                                refreshProfileInformations();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,first_name,last_name,link,email");
            request.setParameters(parameters);
            request.executeAsync();
//            new GraphRequest(
//                    AccessToken.getCurrentAccessToken(),
//                    "/me",
//                    null,
//                    HttpMethod.GET,
//                    new GraphRequest.Callback() {
//                        public void onCompleted(final GraphResponse response) {
//                        /* handle the result */
//
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    JSONObject jResponse = response.getJSONObject();
//                                    try {
//                                        profileId = jResponse.get("id").toString();
//                                        jResponse.get("name").toString();
//                                        UserLogedSingleton.getInstance().setName(jResponse.get("first_name").toString());
//                                        UserLogedSingleton.getInstance().setSurrname(jResponse.get("last_name").toString());
//                                        UserLogedSingleton.getInstance().setEmail(jResponse.get("email").toString());
//                                        // a linha abaixo da exception pq n pode usar a thread principal
//                                        HTTPExample getPicture = new HTTPExample();
//                                        getPicture.execute(new String[]{"https://graph.facebook.com/" + profileId + "/picture?type=large"});
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            });
//                        }
//                    }
//            ).executeAsync();
        } else {
            name = UserLogedSingleton.getInstance().getName();
            surname = UserLogedSingleton.getInstance().getSurrname();
            email = UserLogedSingleton.getInstance().getEmail();
        }
    }

    private class HTTPExample extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap picture = null;
            //urls is an array not a string, so iterate through urls.
            //Get Picture Here - BUT DONT UPDATE UI, Return a reference of the object
            try {
                img_value = new URL("https://graph.facebook.com/" + profileId + "/picture?type=large");
                picture = BitmapFactory.decodeStream(img_value.openConnection().getInputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return picture;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            //Update UI
            profilePicture.setImageBitmap(result);
            Log.i("Result", result.toString());
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            System.out.println("enabled");
            mDrawerLayout.closeDrawers();
            return;

        }
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
            return;
        } else
            super.onBackPressed();
    }

    private void useServicesFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainContent, servicesFragment, "SERVICE_FRAGMENT").addToBackStack("SERVICE_FRAGMENT")
                .commit();
    }

    private void useInstitutionFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainContent, institutionFragment, "INSTITUTION_FRAGMENT").addToBackStack("INSTITUTION_FRAGMENT")
                .commit();
    }

    private void useViewProfileFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.mainContent, viewProfileFragment, "VIEW_PROFILE_FRAGMENT").addToBackStack("VIEW_PROFILE_FRAGMENT")
                .commit();
    }

    @Override
    public void onClick(View view) {
        if (viewProfile.isPressed() || profileName.isPressed() || profileArea.isPressed()) {
            useViewProfileFragment();
            mDrawerLayout.closeDrawer(mDrawerPane);
        }
    }
    private void refreshProfileInformations(){
        profileName.setText(UserLogedSingleton.getInstance().getName() + " " + UserLogedSingleton.getInstance().getSurrname());
    }
}

