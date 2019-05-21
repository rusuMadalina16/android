package com.example.afinal;

import android.app.DatePickerDialog;
import android.media.Rating;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AdaugareExcursie extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private Excursie excursie = new Excursie();
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btnDisplay;

    private ArrayList<Excursie> lista = new ArrayList<>();
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugare_excursie);
        addListenerOnButton();
        final Button button = findViewById(R.id.dataEnd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 1;
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });


        Button button1 = findViewById(R.id.dataStart);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 2;
                DialogFragment dp = new DatePickerFragment();
                dp.show(getSupportFragmentManager(), "data picker 1");

            }
        });

        EditText editText = findViewById(R.id.destinationn);
        String value = editText.getText().toString();
        excursie.setDestin(value);

        EditText editText2 = findViewById(R.id.viw);
        String value2 = editText2.getText().toString();
        excursie.setTripName(value2);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONDAY, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String currentDate = DateFormat.getDateInstance().format(c.getTime());

        if (i == 1) {
            Button button = findViewById(R.id.dataEnd);
            button.setHint(currentDate);
            excursie.setDataEnd(currentDate);
        } else {
            if (i == 2) {
                Button button2 = findViewById(R.id.dataStart);
                button2.setHint(currentDate);
                excursie.setDataStart(currentDate);
            }
        }
    }

    private void addListenerOnButton() {


        radioGroup = findViewById(R.id.radiog);
        btnDisplay = findViewById(R.id.save);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);
                excursie.setType((String) radioButton.getText());

                SeekBar seek = findViewById(R.id.seek);
                int seekValue = seek.getProgress();
                excursie.setPret(seekValue);

                RatingBar mBar = findViewById(R.id.ratingg);
                excursie.setRating(mBar.getRating());

                lista.add(excursie);
            }
        });

    }

    public ArrayList<Excursie> returnLista(){
        return lista;
    }

}

