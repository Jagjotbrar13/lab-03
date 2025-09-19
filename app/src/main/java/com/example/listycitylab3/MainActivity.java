package com.example.listycitylab3;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddEditCityFragment.OnCityEdited {

    private ArrayList<City> cityData;
    private CustomList cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityData = new ArrayList<>();
        cityData.add(new City("Edmonton", "AB"));
        cityData.add(new City("Vancouver", "BC"));
        cityData.add(new City("Toronto", "ON"));
        cityData.add(new City("Hamilton", "ON"));
        cityData.add(new City("Denver", "CO"));
        cityData.add(new City("Los Angeles", "CA"));

        ListView listView = findViewById(R.id.city_list);
        cityAdapter = new CustomList(this, R.layout.list_item, cityData);
        listView.setAdapter(cityAdapter);

        // EDIT: tap a row → dialog prefilled with city + province
        listView.setOnItemClickListener((AdapterView<?> parent, android.view.View view, int position, long id) -> {
            AddEditCityFragment.newInstance(cityData.get(position), position)
                    .show(getSupportFragmentManager(), "editCity");
        });
    }
    @Override
    public void onCitySaved(int position, String name, String province) {
        if (position == -1) {
            // not used now, but supports adding later
            cityData.add(new City(name, province));
        } else {
            // update the selected item and refresh
            City c = cityData.get(position);
            c.setCity(name);
            c.setProvince(province);
        }
        cityAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Saved: " + name + " " + province, Toast.LENGTH_SHORT).show();
    }
}
