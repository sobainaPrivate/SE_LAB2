package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    int current_pos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        cityList = findViewById(R.id.city_list);
        String[] cities = {"Kyoto", "Bergen", "Curitiba", "Busan", "Dakar", "Adelaide", "Innsbruck", "Lagos", "Chiang Mai", "Antwerp", "Medellín", "Utrecht", "Fukuoka", "Ljubljana", "Porto", "Verona", "Tbilisi", "Cartagena", "Cologne", "Baku"};
        dataList = new ArrayList<> ();
        dataList.addAll(Arrays.asList(cities));
        cityAdapter= new ArrayAdapter<>(this,R.layout.content, R.id.content_view,dataList);
// created a button and edit text using drag and drop in .xml file
        Button addbutton = findViewById(R.id.my_button);
        EditText city_input = findViewById(R.id.city_edittext);
        cityList.setOnItemClickListener((parent,view,position,id)->
        {
            current_pos = position;
            String selectedCityName = dataList.get(position);

        });
        addbutton.setOnClickListener(v -> {
                    String city_is = city_input.getText().toString();
                    if (!city_is.isEmpty()) {
                        dataList.add(city_is);
                        cityAdapter.notifyDataSetChanged();
                        city_input.setText("");
                    }
                }

        );
        Button del_button = findViewById(R.id.my_del_button);
        del_button.setOnClickListener(v -> {
//            String current_pos = String.valueOf(cityList.hasOnClickListeners());
            if (current_pos != -1) {
                dataList.remove(current_pos);
                cityAdapter.notifyDataSetChanged();
                current_pos = -1;
            }

        });
        cityList.setAdapter(cityAdapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


}