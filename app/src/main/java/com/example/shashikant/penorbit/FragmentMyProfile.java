package com.example.shashikant.penorbit;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.shashikant.penorbit.data.MedicineContract;
import com.example.shashikant.penorbit.data.MedicineContract.MedicineEntry;
import com.example.shashikant.penorbit.data.MedicineDbHelper;

/**
 * Created by Shashikant on 7/2/2017.
 */

public class FragmentMyProfile extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater Inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = Inflater.inflate(R.layout.my_profile,container,false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        Button button = (Button)v.findViewById(R.id.add_medicine);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getActivity(), MedicineEditorActivity.class));
            }
        });
        return v;
    }
    public void insertMedicine(){

        ContentValues values = new ContentValues();
        values.put(MedicineEntry.MEDICINE_NAME,"combifalm");
        values.put(MedicineEntry.MEDICINE_FREQUENCY_TYPE,0);
        values.put(MedicineEntry.MEDICINE_QUANTITY_AT_A_TIME,21);
        values.put(MedicineEntry.MEDICINE_DOSE_PER_DAY,2);
        values.put(MedicineEntry.MEDICINE_REMINDERS,"11:00AM,6:00PM");
        values.put(MedicineEntry.MEDICINE_NO_OF_PURCHASED,30);

        Uri uri = getActivity().getContentResolver().insert( MedicineEntry.CONTENT_URI, values);
        if(uri!=null){
            Toast.makeText(getActivity(),R.string.MEDICINE_INSERTION_SUCCESSFULL,Toast.LENGTH_SHORT).show();
        }
    }
}
