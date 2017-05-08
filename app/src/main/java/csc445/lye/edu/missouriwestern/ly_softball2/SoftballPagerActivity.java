package csc445.lye.edu.missouriwestern.ly_softball2;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;


public class SoftballPagerActivity extends AppCompatActivity {
    private static final String EXTRA_Softball_ID =
            "csc445.lye.edu.missouriwestern.criminalIntent.Softball_id";


    private ViewPager mViewPager;
    private List<Softball> mSoftballs;

    public static Intent newIntent(Context packageContext, UUID SoftballId) {
        Intent intent = new Intent(packageContext, SoftballPagerActivity.class);
        intent.putExtra(EXTRA_Softball_ID, SoftballId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_softball_pager);

        UUID SoftballId = (UUID) getIntent().getSerializableExtra(EXTRA_Softball_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_softball_pager_view_pager);
        mSoftballs = SoftballSingleton.get(this).getSoftball();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Softball Softball = mSoftballs.get(position);
                return SoftballFragment.newInstance(Softball.getId());
            }

            @Override
            public int getCount() {
                return mSoftballs.size();
            }
        });

        for (int i = 0; i < mSoftballs.size(); i++) {
            if (mSoftballs.get(i).getId().equals(SoftballId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }
}
