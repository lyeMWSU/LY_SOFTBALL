package csc445.lye.edu.missouriwestern.ly_softball;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.ViewGroupCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class SoftballFragment extends Fragment{
    private Softball mSoftball;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mHereCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mSoftball = new Softball();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_softball, container, false);
        mTitleField = (EditText)v.findViewById(R.id.softball_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(
                    CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mSoftball.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = (Button)v.findViewById(R.id.softball_date);
        mDateButton.setText(mSoftball.getDate().toString());
        mDateButton.setEnabled(false);

        mHereCheckBox = (CheckBox)v.findViewById(R.id.softball_here);
        mHereCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {// Set the crime's solved property
                mSoftball.setHere(isChecked);
            }
        });
        return v;
    }
}
