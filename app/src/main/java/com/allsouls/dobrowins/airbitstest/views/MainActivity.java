package com.allsouls.dobrowins.airbitstest.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.allsouls.dobrowins.airbitstest.R;
import com.allsouls.dobrowins.airbitstest.controllers.ItemClickSupport;
import com.allsouls.dobrowins.airbitstest.controllers.JsonHelper;
import com.allsouls.dobrowins.airbitstest.controllers.RecyclerViewAdapter;
import com.allsouls.dobrowins.airbitstest.controllers.TextChangeListener;
import com.allsouls.dobrowins.airbitstest.models.Product;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText productInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productInputEditText = (EditText)findViewById(R.id.product_input_editText);

        JsonHelper jsonHelper = new JsonHelper(this);
        ArrayList<Product> productsArrayList = jsonHelper.provideArrayListFromJson();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(productsArrayList);
        recyclerView.setAdapter(adapter);

        GridLayoutManager layoutManager =
                new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        layoutManager.setSpanSizeLookup(
                new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (position % 3 == 0 ? 2 : 1);
                    }
                }
        );

        TextChangeListener listener = new TextChangeListener();
        listener.init(productInputEditText, recyclerView, productsArrayList);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener((recyclerView1, position, v) -> {
            Product product = listener.getFilteredList().get(position);
            productInputEditText.setText(product.toString());
            recyclerView.setVisibility(View.INVISIBLE);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
