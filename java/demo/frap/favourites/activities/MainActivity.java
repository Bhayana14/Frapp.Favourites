package demo.frap.favourites.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import demo.frap.favourites.R;
import demo.frap.favourites.utilities.Pager;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    //This is our tablayout
    private com.kekstudio.dachshundtablayout.DachshundTabLayout tabLayout;

    //This is our viewPager
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (com.kekstudio.dachshundtablayout.DachshundTabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.pager);


        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager


        //Creating our pager adapter
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }



}
