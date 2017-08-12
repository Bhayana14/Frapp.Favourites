package demo.frap.favourites.utilities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import demo.frap.favourites.fragments.Favourites;
import demo.frap.favourites.fragments.List;

/**
 * Created by Ramandeep on 8/12/17.
 */

public class Pager extends FragmentStatePagerAdapter {
    //integer to count number of tabs
    int tabCount;
    private String[] tabTitles = new String[] {"List", "Favourites"};

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }
    public CharSequence getPageTitle(int position) {

        return tabTitles[position];
    }


        //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                List list = new List();
                return list;
            case 1:
                Favourites favourites = new Favourites();
                return favourites;

            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}
