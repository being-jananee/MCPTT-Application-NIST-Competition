package org.mcopenplatform.muoapi.mcopsdk.Messaging;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import org.mcopenplatform.muoapi.mcopsdk.Domain.UserData;

import org.mcopenplatform.muoapi.R;

import org.mcopenplatform.muoapi.mcopsdk.Messaging.ChatFragment.ChatFragment;
import org.mcopenplatform.muoapi.mcopsdk.Messaging.GroupFragment.GroupFragment;
import org.mcopenplatform.muoapi.mcopsdk.Messaging.UserFragment.UserFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MessageSelect extends AppCompatActivity {


    public UserData currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_select);
        currentUser = getIntent().getParcelableExtra("currentUser");
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager pager = findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new UserFragment(), "Users");
        adapter.addFragment(new ChatFragment(), "Chats");
        adapter.addFragment(new GroupFragment(), "Groups");
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        adapter.notifyDataSetChanged();
    }



    class ViewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        public void addFragment(Fragment f, String title) {
            Bundle b = new Bundle();
            b.putParcelable("currentUser", currentUser);
            f.setArguments(b);
            fragments.add(f);
            titles.add(title);
        }
    }

}
