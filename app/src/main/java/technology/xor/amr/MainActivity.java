package technology.xor.amr;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;

import com.crashlytics.android.Crashlytics;
import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import io.fabric.sdk.android.Fabric;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import technology.xor.amr.MapViews.MapView;
import technology.xor.amr.trek.Trek;
import technology.xor.amr.trek.TrekAdapter;

/**
 * MainActivity - Main controller for recyclerview
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1000;

    private List<Site> sites;
    private List<Trek> candidates;
    private RecyclerView rv;
    private SharedPreferences sharedPreferences;
    private String tab;
    private ViewGroup viewGroup;
    private FrameLayout progressBarHolder;
    private AlphaAnimation inAnimation;
    private AlphaAnimation outAnimation;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        viewGroup = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);

        // Set up the UI
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);
        rv = (RecyclerView) findViewById(R.id.my_recycler_view);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Verify permissions
        RequestPermissionLocation();

        // Load the correct tab and view on start / resume
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        tab = sharedPreferences.getString("state", "INTEGRITY");

        if (tab.equals("MAP")) {
            String teamName = sharedPreferences.getString("team_name", "INTEGRITY");
            SetView(teamName.toUpperCase());
            SetSelectedTab(teamName.toUpperCase(),navigationView);
        } else {
            SetView(tab);
            SetSelectedTab(tab,navigationView);
        }

        // Get data for the recyclerview
        AsyncTaskRunner runner = new AsyncTaskRunner();
        runner.execute("Running");
    }

    private void RequestPermissionLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                                MY_PERMISSIONS_REQUEST_LOCATION);

            } else {
                Log.d("Home", "Already granted access to location.");
            }
        }
    }

    private void SetSelectedTab(String ix, NavigationView nv) {

        switch (ix) {
            case "INTEGRITY":
                nv.setCheckedItem(R.id.nav_integrity);
                break;
            case "HONOR":
                nv.setCheckedItem(R.id.nav_honor);
                break;
            case "LOYALTY":
                nv.setCheckedItem(R.id.nav_loyalty);
                break;
            case "COURAGE":
                nv.setCheckedItem(R.id.nav_courage);
                break;
            case "URBAN TREK":
                nv.setCheckedItem(R.id.nav_trek);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    Uri packageURI = Uri.parse("package:" + MainActivity.class.getPackage().getName());
                    Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
                    startActivity(uninstallIntent);
                }
                return;
            default:
                Log.e("Login", "Failed to login.");
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_integrity) {
            SetView("INTEGRITY");
        } else if (id == R.id.nav_loyalty) {
            SetView("LOYALTY");
        } else if (id == R.id.nav_honor) {
            SetView("HONOR");
        } else if (id == R.id.nav_courage) {
            SetView("COURAGE");
        } else if (id == R.id.nav_map) {
            SetView("MAP");
        } else if (id == R.id.nav_trek){
            SetView("URBAN TREK");
        } else if (id == R.id.nav_settings) {
            Intent prefActivity = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(prefActivity);
        } else if (id == R.id.nav_reset) {
            AlertUser();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void AlertUser() {
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setMessage("This action will reset the app to its default configuration. Continue?");
                alertDialogBuilder.setPositiveButton("Do It!",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                if (candidates != null) {
                                    for (Trek trek : candidates) {
                                        editor.putInt(trek.getName(), 0);
                                        editor.apply();
                                    }
                                }
                                if (sites != null) {
                                    for (Site site : sites) {
                                        editor.putInt(site.getName(), 0);
                                        editor.apply();
                                    }
                                    tab = sharedPreferences.getString("state", "INTEGRITY");
                                    SetView(tab);
                                    SetSelectedTab(tab,navigationView);
                                }
                            }
                        });

        alertDialogBuilder.setNegativeButton("Cancel",null);

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void SetView(String state) {

        SharedPreferences.Editor prefEditor = sharedPreferences.edit();

        switch(state) {
            case "INTEGRITY":
                InitializeIntegrity();
                InitializeAdapter();
                prefEditor.putString("state", state);
                prefEditor.apply();
                getSupportActionBar().setTitle("Integrity");
                break;
            case "LOYALTY":
                InitializeLoyalty();
                InitializeAdapter();
                prefEditor.putString("state", state);
                prefEditor.apply();
                getSupportActionBar().setTitle("Loyalty");
                break;
            case "HONOR":
                InitializeHonor();
                InitializeAdapter();
                prefEditor.putString("state", state);
                prefEditor.apply();
                getSupportActionBar().setTitle("Honor");
                break;
            case "COURAGE":
                InitializeCourage();
                InitializeAdapter();
                prefEditor.putString("state", state);
                prefEditor.apply();
                getSupportActionBar().setTitle("Courage");
                break;
            case "MAP":
                prefEditor.putString("state", state);
                prefEditor.apply();
                Intent mapActivity = new Intent(MainActivity.this, MapView.class);
                startActivity(mapActivity);
                break;
            case "URBAN TREK":
                prefEditor.putString("state", state);
                prefEditor.apply();
                InitializeTrek();
                InitializeTrekAdapter();
                getSupportActionBar().setTitle("Urban Trek");
                break;
        }
    }

    private void InitializeTrek() {
        Set<String> selections = sharedPreferences.getStringSet("active_candidates", null);

        candidates = new ArrayList<>();

        if (selections != null) {
            String[] selected = selections.toArray(new String[] {});
            Arrays.sort(selected);

            for (String select : selected) {
                candidates.add(new Trek(select));
            }
        }
    }

    private void InitializeIntegrity(){
        sites = new ArrayList<>();
        sites.add(new Site("ALABASTER", "Arlington National Cemetery", R.drawable.arlington));
        sites.add(new Site("AMETHYST DETOUR", "World War II Memorial", R.drawable.ww));
        sites.add(new Site("AQUAMARINE", "Franklin D. Roosevelt Memorial", R.drawable.fdr));
        sites.add(new Site("EMERALD", "Vietnam Veterans Memorial", R.drawable.vietnam));
        sites.add(new Site("GARNET", "National Air and Space Museum", R.drawable.airandspace));
        sites.add(new Site("GRANITE", "U.S. Navy Memorial", R.drawable.navy));
        sites.add(new Site("JASPER", "Iwo Jima Memorial", R.drawable.iwo));
        sites.add(new Site("OBSIDIAN", "National Archives", R.drawable.archives));
        sites.add(new Site("OPAL", "Botanic Gardens", R.drawable.gardens));
        sites.add(new Site("QUARTZ", "First Division Monument", R.drawable.armor));
        sites.add(new Site("ZIRCON", "National Law Enforcement Officers Memorial", R.drawable.officers));
    }

    private void InitializeLoyalty(){
        sites = new ArrayList<>();
        sites.add(new Site("ALABASTER", "Arlington National Cemetery", R.drawable.arlington));
        sites.add(new Site("AMBER", "Lincoln Memorial", R.drawable.lincoln));
        sites.add(new Site("AMETHYST", "World War II Memorial", R.drawable.ww));
        sites.add(new Site("CITRINE DETOUR", "56 Signers", R.drawable.signers));
        sites.add(new Site("DIAMOND", "Ulysses S. Grant Memorial", R.drawable.ulysses));
        sites.add(new Site("EMERALD", "Vietnam Veteran’s Memorial", R.drawable.vietnam));
        sites.add(new Site("GARNET", "National Air and Space Museum", R.drawable.airandspace));
        sites.add(new Site("PERIDOT", "National Postal Museum", R.drawable.postal));
        sites.add(new Site("QUARTZ", "First Division Monument", R.drawable.armor));
        sites.add(new Site("SAPPHIRE", "Korean War Veterans Memorial", R.drawable.korean));
        sites.add(new Site("ZIRCON", "National Law Enforcement Officers Memorial", R.drawable.officers));
    }

    private void InitializeHonor() {
        sites = new ArrayList<>();
        sites.add(new Site("ALABASTER", "Arlington National Cemetery", R.drawable.arlington));
        sites.add(new Site("AMETHYST", "World War II Memorial", R.drawable.ww));
        sites.add(new Site("AQUAMARINE", "Franklin D. Roosevelt Memorial", R.drawable.fdr));
        sites.add(new Site("BERYL", "George Mason Memorial", R.drawable.mason));
        sites.add(new Site("CORAL", "Zero Milestone", R.drawable.zero));
        sites.add(new Site("DIAMOND", "Ulysses S. Grant Memorial", R.drawable.ulysses));
        sites.add(new Site("GRANITE", "U.S. Navy Memorial", R.drawable.navy));
        sites.add(new Site("JADE DETOUR", "National Museum of Natural History", R.drawable.national));
        sites.add(new Site("JASPER", "Iwo Jima Memorial", R.drawable.iwo));
        sites.add(new Site("ONYX", "U.S. Holocaust Memorial Museum", R.drawable.holocaust));
    }

    private void InitializeCourage() {
        sites = new ArrayList<>();
        sites.add(new Site("ALABASTER", "Arlington National Cemetery", R.drawable.arlington));
        sites.add(new Site("AMBER", "Lincoln Memorial", R.drawable.lincoln));
        sites.add(new Site("AMETHYST", "World War II Memorial", R.drawable.ww));
        sites.add(new Site("BERYL", "George Mason Memorial", R.drawable.mason));
        sites.add(new Site("BLOODSTONE", "Thomas Jefferson Memorial", R.drawable.jefferson));
        sites.add(new Site("CITRINE DETOUR", "56 Signers", R.drawable.signers));
        sites.add(new Site("CORAL", "Zero Milestone", R.drawable.zero));
        sites.add(new Site("DIAMOND", "Ulysses S. Grant Memorial", R.drawable.ulysses));
        sites.add(new Site("GRANITE", "U.S. Navy Memorial", R.drawable.navy));
        sites.add(new Site("SAPPHIRE", "Korean War Veterans Memorial", R.drawable.korean));
        sites.add(new Site("SUNSTONE", "Haupt Garden", R.drawable.haupt));
    }

    private void InitializeAdapter(){
        SiteAdapter adapter = new SiteAdapter(sites, MainActivity.this, viewGroup);
        rv.setAdapter(adapter);
    }

    private void InitializeTrekAdapter(){
        TrekAdapter adapter = new TrekAdapter(candidates, MainActivity.this, viewGroup);
        rv.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        System.out.println("Key: " +  key);
        if (key.equals("active_candidates")) {
            if (tab.equals("URBAN TREK")) {
                InitializeTrek();
                InitializeTrekAdapter();
                SetSelectedTab("URBAN TREK", navigationView);
            }
        }
    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;

        @Override
        protected String doInBackground(String... params) {

            // Initialize the Telegram Bot
            TelegramBot bot = TelegramBotAdapter.build(getString(R.string.bot_token));
            GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);
            final GetUpdatesResponse updatesResponse = bot.execute(getUpdates);

            bot.execute(getUpdates, new Callback<GetUpdates, GetUpdatesResponse>() {

                @Override
                public void onResponse(GetUpdates request, GetUpdatesResponse response) {

                    List<Update> updates = updatesResponse.updates();
                    for (Update update : updates) {
                        // Update the bot to report to the most recent entry from getUpdates()
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("chatId", update.message().chat().id().toString());
                        editor.apply();
                    }
                }

                @Override
                public void onFailure(GetUpdates request, IOException e) {

                }
            });

            return resp;
        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
         */
        @Override
        protected void onPostExecute(String result) {
            outAnimation = new AlphaAnimation(1f, 0f);
            outAnimation.setDuration(200);
            progressBarHolder.setAnimation(outAnimation);
            progressBarHolder.setVisibility(View.GONE);
        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onPreExecute()
         */
        @Override
        protected void onPreExecute() {
            inAnimation = new AlphaAnimation(0f, 1f);
            inAnimation.setDuration(200);
            progressBarHolder.setAnimation(inAnimation);
            progressBarHolder.setVisibility(View.VISIBLE);
        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onProgressUpdate(Progress[])
         */
        @Override
        protected void onProgressUpdate(String... text) {
            // finalResult.setText(text[0]);
            // Things to be done while execution of long running operation is in
            // progress. For example updating ProgessDialog
        }
    }
}