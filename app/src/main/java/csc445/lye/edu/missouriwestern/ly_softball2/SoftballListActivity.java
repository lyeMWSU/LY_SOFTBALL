package csc445.lye.edu.missouriwestern.ly_softball2;

import android.support.v4.app.Fragment;

public class SoftballListActivity extends SingleFragmentActivity{


    @Override
    protected Fragment createFragment() {
        return new SoftballListFragment();
    }
}
