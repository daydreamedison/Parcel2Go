package com.wondermelonpapajoanne.joanne.parcel2go.activity.customer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.wondermelonpapajoanne.joanne.parcel2go.activity.LoginActivity;
import com.wondermelonpapajoanne.joanne.parcel2go.fragment.PlaceOrderFragment;
import com.wondermelonpapajoanne.joanne.parcel2go.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, PlaceOrderFragment.OnFragmentInteractionListener {

    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";
    private int currentSelectedPosition;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerToggle.setDrawerIndicatorEnabled(false);
        drawerLayout.addDrawerListener(drawerToggle);
        Menu menu = navigationView.getMenu();
        onNavigationItemSelected(menu.getItem(0));
        drawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        Fragment fragment = null;

        switch(id)
        {
            case R.id.nav_place_order:
                fragment = new PlaceOrderFragment();
                break;
            case R.id.nav_booking_status:
                break;
            case R.id.nav_track_delivery_status:
                break;
            case R.id.nav_histories_order:
                break;
            case R.id.nav_my_profile:
                break;
            case R.id.nav_about_us:
                break;
            case R.id.nav_logout:
                logout();
                return true;
            default:
                fragment = new PlaceOrderFragment();
        }

        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_content, fragment).commit();
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Fragment fragment = null;
        switch(item.getItemId()){
            case R.id.nav_place_order:
                fragment = new PlaceOrderFragment();
                break;
            case R.id.nav_booking_status:
                break;
            case R.id.nav_logout:
                logout();
                break;
        }

        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_content, fragment).commit();
        }

        return super.onOptionsItemSelected(item);
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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, currentSelectedPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION, 0);
        Menu menu = navigationView.getMenu();
        menu.getItem(currentSelectedPosition).setChecked(true);
    }

    private void setUpNavDrawerListener(NavigationView navigationView){
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener(){
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem){
                        return true;
                    }
                }
        );
    }

    @Override
    public void onPlaceOrderFragmentInteraction(Uri uri) {
    }

    private void logout(){
        auth = FirebaseAuth.getInstance();
        auth.signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }
}
