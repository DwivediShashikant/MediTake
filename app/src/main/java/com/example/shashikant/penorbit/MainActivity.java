package com.example.shashikant.penorbit;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnMenuTabClickListener;

import static android.support.v7.widget.AppCompatDrawableManager.get;

public class MainActivity extends AppCompatActivity {

    BottomBar mbottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbottomBar = BottomBar.attach(this,savedInstanceState);
        mbottomBar.setItemsFromMenu(R.menu.menu_main, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if(menuItemId == R.id.my_medicine){
                    FragmentListOfMedicine f = new FragmentListOfMedicine();
                    getFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }
                else if(menuItemId == R.id.medicine_today){
                    FragmentMedicineToday f = new FragmentMedicineToday();
                    getFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }
                else if(menuItemId == R.id.my_profile){
                    FragmentMyProfile f = new FragmentMyProfile();
                    getFragmentManager().beginTransaction().replace(R.id.frame,f).commit();
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

            }
        });
        mbottomBar.mapColorForTab(0,"#009688");
        mbottomBar.mapColorForTab(1,"#0097a7");
        mbottomBar.mapColorForTab(2,"#ffab00");

        BottomBarBadge unread;
        unread = mbottomBar.makeBadgeForTabAt(1,"#f50057",7);
        unread.show();
    }
}
