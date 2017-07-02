package com.example.shashikant.penorbit;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

/**
 * Created by Shashikant on 7/2/2017.
 */

public class FragmentListOfMedicine extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater Inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = Inflater.inflate(R.layout.list_of_medicine,container,false);
        return v;
    }
}
