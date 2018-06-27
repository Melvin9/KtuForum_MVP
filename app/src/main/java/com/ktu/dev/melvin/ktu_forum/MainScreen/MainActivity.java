package com.ktu.dev.melvin.ktu_forum.MainScreen;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_main_items, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_search:
                // search action
                return true;
            case R.id.action_ask_private:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new AskPrivateFragment()).commit();
                //action private
                return true;
            case R.id.action_ask_public:
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new AskPublicFragment()).commit();
                //action public
                return true;
            case R.id.action_refresh:
                // refresh
                return true;
            case R.id.action_help:
                // help action
                return true;
            case R.id.action_check_updates:
                // check for updates action
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

