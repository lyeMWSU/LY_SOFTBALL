package csc445.lye.edu.missouriwestern.ly_softball2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class SoftballListFragment extends Fragment {

    private RecyclerView mSoftballRecyclerView;
    private SoftballAdapter mAdapter;
    private boolean mSubtitleVisible;

    private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

    //added page 245
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_softball_list, container, false);

        mSoftballRecyclerView = (RecyclerView) view.findViewById(R.id.softball_recycler_view);
        mSoftballRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (savedInstanceState != null) {
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }
        updateUI();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    //added page 254
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    //added at page 224
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_softball_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if (mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        } else {
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    //added at page 248
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_softball:
                Softball softball = new Softball();
                SoftballSingleton.get(getActivity()).addSoftball(softball);
                Intent intent = SoftballPagerActivity.newIntent(getActivity(), softball.getId());
                startActivity(intent);
                return true;
            case R.id.menu_item_show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle() {
        SoftballSingleton softballSingleton = SoftballSingleton.get(getActivity());
        int softballCount = softballSingleton.getSoftball().size();
        String subtitle = getString(R.string.subtitle_format, softballCount);

        if (!mSubtitleVisible) {
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI() {
        SoftballSingleton softballSingleton = SoftballSingleton.get(getActivity());
        List<Softball> softballs = softballSingleton.getSoftball();

        if (mAdapter == null) {
            mAdapter = new SoftballAdapter(softballs);
            mSoftballRecyclerView.setAdapter(mAdapter);
        } else
            mAdapter.notifyDataSetChanged();

        updateSubtitle();
    }

    private class SoftballHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mActive;

        private Softball mSoftball;

        public SoftballHolder (View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_softball_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_softball_date_text_view);
            mActive = (CheckBox) itemView.findViewById(R.id.list_item_softball_check_box);
        }

        public void bindSoftball(Softball softball) {
            mSoftball = softball;
            mTitleTextView.setText(mSoftball.getLastName());
            mDateTextView.setText(mSoftball.getLastUpdate().toString());
            mActive.setChecked(mSoftball.isCatcher());
        }

        @Override
        public void onClick(View v) {

            Intent intent = SoftballPagerActivity.newIntent(getActivity(), mSoftball.getId());
            startActivity(intent);
        }
    }

    private class SoftballAdapter extends RecyclerView.Adapter<SoftballHolder> {

        private List<Softball> mSoftball;

        public SoftballAdapter(List<Softball> Softballs) {
            mSoftball = Softballs;
        }

        @Override
        public SoftballHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_softball, parent, false);
            return new SoftballHolder(view);
        }

        @Override
        public void onBindViewHolder(SoftballHolder holder, int position) {
            Softball Softball = mSoftball.get(position);
            holder.bindSoftball(Softball);
        }

        @Override
        public int getItemCount() {
            return mSoftball.size();
        }
    }
}

