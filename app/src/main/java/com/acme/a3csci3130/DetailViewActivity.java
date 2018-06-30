package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.Task;

public class DetailViewActivity extends Activity {

    private EditText nameField, businessField, primarybusinessField, addressField, provinceField;
    Contact receivedPersonInfo;
    private MyApplicationData appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");
        appState = ((MyApplicationData) getApplicationContext());

        nameField = (EditText) findViewById(R.id.Name);
        businessField = (EditText) findViewById(R.id.businessnumber);
        primarybusinessField = (EditText) findViewById(R.id.primarybusiness);
        addressField = (EditText) findViewById(R.id.address);
        provinceField = (EditText) findViewById(R.id.province);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            businessField.setText(receivedPersonInfo.businessnumber);
            primarybusinessField.setText(receivedPersonInfo.primarybusiness);
            addressField.setText(receivedPersonInfo.address);
            provinceField.setText(receivedPersonInfo.province);
        }
    }


    /**
     *
     * @param v It updates data from the firebase from the required field
     *          with the help of the uid which is unique for every contact
     */

    public void updateContact(View v){
        //TODO: Update contact funcionality

        receivedPersonInfo.name = nameField.getText().toString();
        receivedPersonInfo.businessnumber = businessField.getText().toString();
        receivedPersonInfo.primarybusiness = primarybusinessField.getText().toString();
        receivedPersonInfo.address = addressField.getText().toString();
        receivedPersonInfo.province = provinceField.getText().toString();
        appState.firebaseReference.child(receivedPersonInfo.uid).setValue(receivedPersonInfo);
        finish();

    }

    /**
     *
     * @param v It deletes the complete contact with the help of uid
     */

    public void eraseContact(View v)
    {
        //TODO: Erase contact functionality

        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();
        finish();
    }
}
