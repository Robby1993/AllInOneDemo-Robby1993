package com.app.municy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.app.municy.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

       /* mBinding.searchView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mOfficeListAdapter.getFilter().filter(newText);
               *//* if (TextUtils.isEmpty(newText)) {
                    mOfficeListAdapter.filter("");
                } else {
                    mOfficeListAdapter.filter(newText);
                }*//*
                return false;
            }
        });*/
    }
}