package com.ktu.dev.melvin.ktu_forum.MainScreen;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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
Button privateBt,publicBt;
LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        privateBt=findViewById(R.id.privateBtn);
        publicBt=findViewById(R.id.publicBtn);
        linearLayout=findViewById(R.id.linearLayout);
        BottomNavigation bottomNavigation=findViewById(R.id.BottomNavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new ViewAnswerFragment()).commit();
        bottomNavigation.setOnMenuItemClickListener(new BottomNavigation.OnMenuItemSelectionListener() {
            @Override
            public void onMenuItemSelect(int i, int i1, boolean b) {
                android.support.v4.app.Fragment fragment=null;
                switch (i1){
                    case 0:
                        fragment=new ViewAnswerFragment();
                        linearLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.orange));
                        break;
                    case 1:
                        fragment=new BookmarkFragment();
                        linearLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.red));
                        break;
                    case 2:
                        fragment=new ProfileFragment();
                        linearLayout.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.blue));
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
