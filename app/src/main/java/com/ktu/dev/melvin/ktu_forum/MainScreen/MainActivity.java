package com.ktu.dev.melvin.ktu_forum.MainScreen;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.ktu.dev.melvin.ktu_forum.Login.View.SessionManager;
import com.ktu.dev.melvin.ktu_forum.MainScreen.Bookmark.View.BookmarkFragment;
import com.ktu.dev.melvin.ktu_forum.MainScreen.Profile.View.ProfileFragment;
import com.ktu.dev.melvin.ktu_forum.MainScreen.View_Answer.View.ViewAnswerFragment;
import com.ktu.dev.melvin.ktu_forum.R;

import java.util.HashMap;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
public class MainActivity extends AppCompatActivity {
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new SessionManager(getApplicationContext());
        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        ViewAnswerFragment.user_id= user.get(SessionManager.KEY_NAME);

        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("KTU");
        MobileAds.initialize(this, "ca-app-pub-5841415504299472~5721239352");
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        BottomNavigation bottomNavigation=findViewById(R.id.BottomNavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new ViewAnswerFragment()).commit();

        bottomNavigation.setOnMenuItemClickListener(new BottomNavigation.OnMenuItemSelectionListener() {
            @Override
            public void onMenuItemSelect(int i, int i1, boolean b) {
                android.support.v4.app.Fragment fragment=null;
                switch (i1){
                    case 0:
                        fragment=new ViewAnswerFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();
                        break;
                    case 1:
                        fragment=new BookmarkFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();
                        break;
                    case 2:
                        fragment=new ProfileFragment();
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();
                        break;
                    case 3:
                        session.logoutUser();
                        break;
                }

            }

            @Override
            public void onMenuItemReselect(int i, int i1, boolean b) {

            }
        });

    }

}

