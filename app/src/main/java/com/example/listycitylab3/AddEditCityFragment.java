package com.example.listycitylab3;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class AddEditCityFragment extends DialogFragment {

    public interface OnCityEdited {
        void onCitySaved(int position, String name, String province);
    }

    //Argument keys for bundling state into the Fragment
    private static final String ARG_CITY = "arg_city";
    private static final String ARG_POS  = "arg_pos";

    public static AddEditCityFragment newInstance(City city, int position) {
        Bundle args = new Bundle();

        //Put the City if we’re in "edit" mode
        if (city != null) args.putSerializable(ARG_CITY, city);

        // Always store the position (index in the list). For adds, pass -1
        args.putInt(ARG_POS, position);

        //Create the Fragment and attach the arguments so Android can restore them later
        AddEditCityFragment fragment = new AddEditCityFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.dialog_add_edit_city, null, false);

        //Grabs references to input fields
        final EditText inputCity = view.findViewById(R.id.editCityName);
        final EditText inputProv = view.findViewById(R.id.editProvince);

        // Unpack arguments (existing city and position)
        Bundle args = getArguments();
        City existing = null;
        if (args != null) existing = (City) args.getSerializable(ARG_CITY);


        // If editing, prefill text fields with existing values
        if (existing != null) {
            inputCity.setText(existing.getCity());
            inputProv.setText(existing.getProvince());
        }
        //Save the position we’re editing (or -1 for "add")
        final int posFinal = (args != null) ? args.getInt(ARG_POS, -1) : -1;

        //Build and return the AlertDialog
        return new AlertDialog.Builder(getContext())
                .setTitle("Add/edit city")
                .setView(view)
                .setNegativeButton("Cancel", null)
                //validate/collect inputs and send them back to host via callback
                .setPositiveButton("OK", (dialog, which) -> {
                    String name = inputCity.getText().toString().trim();
                    String prov = inputProv.getText().toString().trim();

//                    //Deliver result to host Activity if implements callback
                    if (getActivity() instanceof OnCityEdited) {
                        ((OnCityEdited) getActivity()).onCitySaved(posFinal, name, prov);
                    }
                })
                .create();
    }
}
