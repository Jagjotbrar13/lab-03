package com.example.listycitylab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomList extends ArrayAdapter<City> {
    private final List<City> cities; //Backing list of City objects shown in the ListView
    private final int resource;

    public CustomList(Context context, int resource, List<City> cities) {
        super(context, resource, cities);
        this.cities = cities;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Reuse existing view if possible, otherwise inflate a new row from the layout resource
        View view = (convertView != null)
                ? convertView
                : LayoutInflater.from(getContext()).inflate(resource, parent, false);

        City c = cities.get(position);

        ((TextView) view.findViewById(R.id.city_name)).setText(c.getCity());
        ((TextView) view.findViewById(R.id.province_name)).setText(c.getProvince());
        return view;
    }
}
