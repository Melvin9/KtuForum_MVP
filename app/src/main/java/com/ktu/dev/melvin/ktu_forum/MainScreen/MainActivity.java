package com.ktu.dev.melvin.ktu_forum.MainScreen;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.ktu.dev.melvin.ktu_forum.MainScreen.Ask_Questions.View.AskPrivateFragment;
import com.ktu.dev.melvin.ktu_forum.MainScreen.Ask_Questions.View.AskPublicFragment;
import com.ktu.dev.melvin.ktu_forum.MainScreen.Bookmark.View.BookmarkFragment;
import com.ktu.dev.melvin.ktu_forum.MainScreen.Profile.View.ProfileFragment;
import com.ktu.dev.melvin.ktu_forum.MainScreen.View_Answer.View.ViewAnswerFragment;
import com.ktu.dev.melvin.ktu_forum.R;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("KTU");
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
                        break;
                    case 1:
                        fragment=new BookmarkFragment();
                        break;
                    case 2:
                        fragment=new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragment).commit();
            }

            @Override
            public void onMenuItemReselect(int i, int i1, boolean b) {

            }
        });


    }

}

