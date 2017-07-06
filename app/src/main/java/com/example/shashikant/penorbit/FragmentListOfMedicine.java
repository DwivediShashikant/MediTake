package com.example.shashikant.penorbit;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.shashikant.penorbit.data.MedicineContract;
import com.example.shashikant.penorbit.data.MedicineContract.MedicineEntry;

import java.util.zip.Inflater;

/**
 * Created by Shashikant on 7/2/2017.
 */

public class FragmentListOfMedicine extends Fragment {

    public static final String LOG_TAG =  FragmentListOfMedicine.class.getSimpleName();
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater Inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
         v = Inflater.inflate(R.layout.list_of_medicine,container,false);
        displayDatabseInfo();
        return v;
    }
    @Override
    public void onStart(){
        super.onStart();
        displayDatabseInfo();
    }

    public void displayDatabseInfo(){
        String []projection = {
                MedicineEntry._ID,
                MedicineEntry.MEDICINE_NAME,
                MedicineEntry.MEDICINE_FREQUENCY_TYPE,
                MedicineEntry.MEDICINE_QUANTITY_AT_A_TIME,
                MedicineEntry.MEDICINE_DOSE_PER_DAY,
                MedicineEntry.MEDICINE_REMINDERS,
                MedicineEntry.MEDICINE_NO_OF_PURCHASED
        };

        Cursor cursor = getActivity().getContentResolver().query(MedicineEntry.CONTENT_URI,projection,null,null,null);

        ListView medicineListView = (ListView)v.findViewById(R.id.list);
        MedicineCursorAdapter adapter =  new MedicineCursorAdapter(getActivity(),cursor);
        medicineListView.setAdapter(adapter);
        Log.v(LOG_TAG,"@@@@@@@@@@@@@@@@@@@@@@"+medicineListView.getItemAtPosition(0).toString());
        //cursor.close();
    }
}
