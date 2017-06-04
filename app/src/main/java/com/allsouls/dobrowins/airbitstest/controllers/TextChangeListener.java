package com.allsouls.dobrowins.airbitstest.controllers;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.allsouls.dobrowins.airbitstest.models.Product;

import java.util.ArrayList;

/**
 * Created on 6/3/2017.
 *
 * When user starts the search RecyclerView becomes visible and starts to display items
 * matching the query.
 * It happens every time onTextChanged is initiated.
 * If related editText is empty recyclerView becomes invisible again.
 */

public class TextChangeListener {

    public TextChangeListener(){
    }

    private ArrayList<Product> filteredList;
    public ArrayList<Product> getFilteredList() {
        return filteredList;
    }

    public void init(EditText view, RecyclerView recyclerView, ArrayList<Product> arrayList) {
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String query = s.toString().toLowerCase();

                filteredList = new ArrayList<>();
                for (int i = 0; i < arrayList.size(); i++) {
                    final String text = arrayList.get(i).toString().toLowerCase();
                    if (text.contains(query)) {
                        filteredList.add(arrayList.get(i));
                    }
                }

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(filteredList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (view.getText().toString().matches("")) {
                    recyclerView.setVisibility(View.INVISIBLE);
                }
                else {
                    recyclerView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}
