package csc445.lye.edu.missouriwestern.ly_softball2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.TextViewCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.UUID;

public class SoftballFragment extends Fragment {

    private static final String ARG_CRIME_ID = "softballId";
    private static final String DIALOG_DATE = "DialogDate";

    private static final int REQUEST_DATE = 0;

    private Softball mSoftball;
    private TextView mTitleField;
    private EditText mLastNameField;
    private EditText mFirstNameField;
    private Button mDateButton;
    private CheckBox mPitcherCheckBox;
    private CheckBox mCatcherCheckBox;
    private CheckBox mInfieldCheckBox;
    private CheckBox mOutfieldCheckBox;

    public static SoftballFragment newInstance(UUID softballId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, softballId);

        SoftballFragment fragment = new SoftballFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mSoftball = new Crime(); taken out from page 196
        //page 199 out
        // UUID crimeId = (UUID) getActivity().getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);
        UUID softballId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
        mSoftball = SoftballSingleton.get(getActivity()).getSoftballs(softballId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_softball, container, false);

        mTitleField = (TextView) v.findViewById(R.id.softball_title);
        mTitleField.setText(mSoftball.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSoftball.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mLastNameField = (EditText) v.findViewById(R.id.softball_last_name);
        mLastNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //this is left blank for now page 141
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSoftball.setLastName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //this is left blank as well page 141
            }
        });

        mFirstNameField = (EditText) v.findViewById(R.id.softball_first_name);
        mFirstNameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //this is left blank for now page 141
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mSoftball.setLastName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //this is left blank as well page 141
            }
        });

        mDateButton = (Button) v.findViewById(R.id.date_button);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mSoftball.getLastUpdate());
                dialog.setTargetFragment(SoftballFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        mPitcherCheckBox = (CheckBox)v.findViewById(R.id.softball_pitcher);
        mPitcherCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSoftball.setPitcher(isChecked);
            }
        });

        mCatcherCheckBox = (CheckBox)v.findViewById(R.id.softball_catcher);
        mCatcherCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSoftball.setCatcher(isChecked);
            }
        });

        mInfieldCheckBox = (CheckBox)v.findViewById(R.id.softball_infield);
        mInfieldCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSoftball.setInfield(isChecked);
            }
        });

        mOutfieldCheckBox = (CheckBox)v.findViewById(R.id.softball_outfield);
        mOutfieldCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSoftball.setOutfield(isChecked);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mSoftball.setLastUpdate();
            updateDate();
        }
    }

    private void updateDate() {
        mDateButton.setText(mSoftball.getLastUpdate().toString());
    }
}



