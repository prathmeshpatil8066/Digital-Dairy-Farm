package com.example.dfarm;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import java.util.Calendar;

public class totalcollectionFragment extends Fragment {

    private TextView tvSelectDate;
    private RadioGroup milkTypeGroup, shiftGroup;
    private AppCompatButton submitButton;

    public totalcollectionFragment() {
        // Required empty public constructor
    }

    public static totalcollectionFragment newInstance(String param1, String param2) {
        totalcollectionFragment fragment = new totalcollectionFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_totalcollection, container, false);

        // Bind views
        tvSelectDate = view.findViewById(R.id.tvSelectDate);
        milkTypeGroup = view.findViewById(R.id.radioGroupMilkType); // We'll assign an ID in XML
        shiftGroup = view.findViewById(R.id.radioGroupShift);       // We'll assign an ID in XML
        submitButton = view.findViewById(R.id.btnSubmit);           // We'll assign an ID in XML

        // Handle date picker
        tvSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Handle submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSubmit();
            }
        });

        return view;
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        String selectedDate = String.format("%02d/%02d/%04d",
                                selectedDay, selectedMonth + 1, selectedYear);
                        tvSelectDate.setText(selectedDate);
                    }
                }, year, month, day);

        datePickerDialog.show();
    }

    private void handleSubmit() {
        int milkTypeId = milkTypeGroup.getCheckedRadioButtonId();
        int shiftId = shiftGroup.getCheckedRadioButtonId();

        if (milkTypeId == -1 || shiftId == -1 || tvSelectDate.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "Please select all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedMilkType = milkTypeGroup.findViewById(milkTypeId);
        RadioButton selectedShift = shiftGroup.findViewById(shiftId);
        String selectedDate = tvSelectDate.getText().toString();

        String message = "Milk Type: " + selectedMilkType.getText().toString() +
                "\nShift: " + selectedShift.getText().toString() +
                "\nDate: " + selectedDate;

        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
