package com.example.shashikant.penorbit;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shashikant.penorbit.data.MedicineContract;
import com.example.shashikant.penorbit.data.MedicineContract.MedicineEntry;
import com.example.shashikant.penorbit.data.MedicineDbHelper;

import static android.text.style.TtsSpan.GENDER_FEMALE;

public class MedicineEditorActivity extends AppCompatActivity {

    private EditText mMedicineName;
    private EditText mQuantity;
    private EditText mDosePerDay;
    private EditText mReminders;
    private EditText mPurchased;
    private Spinner mFrequnecyTypeSpinner;
    public MedicineDbHelper mDbHelper;

    private int mfrequnecyType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_editor);

        mMedicineName = (EditText)findViewById(R.id.edit_med_name);
        mQuantity = (EditText)findViewById(R.id.edit_quantity);
        mDosePerDay = (EditText)findViewById(R.id.edit_dose_day);
        mReminders = (EditText)findViewById(R.id.edit_reminders);
        mPurchased = (EditText)findViewById(R.id.edit_purchased);
        mFrequnecyTypeSpinner = (Spinner)findViewById(R.id.spinner_dose_type);


        setUpSpinner();
    }
    public void setUpSpinner(){
        ArrayAdapter frequencySpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_frequnecy_type_option, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        frequencySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mFrequnecyTypeSpinner.setAdapter(frequencySpinnerAdapter);

        mFrequnecyTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.frequnecy_daily))) {
                        mfrequnecyType = MedicineContract.MedicineEntry.FREQUENCY_DAILY; // Daily
                    } else if (selection.equals(getString(R.string.frequnecy_weekly))) {
                        mfrequnecyType = MedicineContract.MedicineEntry.FREQUENCY_WEEKLY; // Weekly

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }
    public void insertMedicine(){
        ContentValues values = new ContentValues();
        values.put(MedicineEntry.MEDICINE_NAME,mMedicineName.getText().toString().trim());
        values.put(MedicineEntry.MEDICINE_FREQUENCY_TYPE,mfrequnecyType);
        values.put(MedicineEntry.MEDICINE_QUANTITY_AT_A_TIME,mQuantity.getText().toString().trim());
        values.put(MedicineEntry.MEDICINE_DOSE_PER_DAY,mDosePerDay.getText().toString().trim());
        values.put(MedicineEntry.MEDICINE_REMINDERS,mReminders.getText().toString().trim());
        values.put(MedicineEntry.MEDICINE_NO_OF_PURCHASED,mPurchased.getText().toString().trim());

        Uri uri = getContentResolver().insert(MedicineEntry.CONTENT_URI,values);
        if(uri!=null){
            Toast.makeText(this,"Medicine Inserted",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this,"Error with saving medicine",Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_save:
                insertMedicine();
                finish();
                return true;
            case R.id.action_delete:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
        }
        return true;
    }
}
