package cn.pfc.pfc523.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author fyh 2019.5.7 12:53
 * 此适配器可用于多个ViewPager 互相不会产生冲突
 *
 * */
public class MViewPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> list;

    public MViewPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
