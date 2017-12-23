package com.example.navdrawercore;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.navdrawercore.Assets.CustomTypefaceSpan;
import com.example.navdrawercore.Assets.FontTypeface;
import com.example.navdrawercore.Fragments.News;
import com.example.navdrawercore.Fragments.Subjects;
import com.example.navdrawercore.Fragments.Teachers;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FontTypeface fontTypeface;
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        fontTypeface = new FontTypeface(this);
        typeface = fontTypeface.getTypefaceAndroid();

        setSupportActionBar(toolbar);
        setNavDrawer(toolbar);
    }

    private void setNavDrawer(Toolbar toolbar)
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        changeTypeface(navigationView);

        View header=navigationView.getHeaderView(0);
        TextView name = header.findViewById(R.id.profile_username);
        name.setTypeface(typeface);
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_news:
                fragment = new News();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case R.id.nav_subjects:
                fragment = new Subjects();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;

            case R.id.nav_teachers:
                fragment = new Teachers();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeTypeface(NavigationView navigationView)
    {


        MenuItem item;
        item = navigationView.getMenu().findItem(R.id.nav_news);
        item.setTitle("الاخبار و الاحداث");
        applyFontToItem(item, typeface);

        item = navigationView.getMenu().findItem(R.id.nav_subjects);
        item.setTitle("المواد الدراسية");
        applyFontToItem(item, typeface);

        item = navigationView.getMenu().findItem(R.id.nav_teachers);
        item.setTitle("شؤون المدرسين");
        applyFontToItem(item, typeface);

        item = navigationView.getMenu().findItem(R.id.nav_share);
        item.setTitle("المشاركة");
        applyFontToItem(item, typeface);

        item = navigationView.getMenu().findItem(R.id.nav_send);
        item.setTitle("ارسال تعليق");
        applyFontToItem(item, typeface);
    }

    private void applyFontToItem(MenuItem item, Typeface font)
    {
        SpannableString mNewTitle = new SpannableString(item.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font, 16), 0 , mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        item.setTitle(mNewTitle);
    }
}
