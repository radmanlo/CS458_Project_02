package com.example.cs458_project_02;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText birthDate;
    TextInputLayout name, city;
    Spinner gender, vaccine, sideEffect, posTest;
    Button submit;
    TextView display;

    //listener for getting date form the componenet
    DatePickerDialog.OnDateSetListener datelistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        birthDate = findViewById(R.id.Date);
        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        datelistener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        datelistener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month += 1;
                String date = dayOfMonth+"/"+month+"/"+year;
                birthDate.setText(date);
            }
        };
        name = findViewById(R.id.NameInput);
        city = findViewById(R.id.CityInput);
        gender = findViewById(R.id.gender);
        sideEffect = findViewById(R.id.SideEffect);
        vaccine = findViewById(R.id.vaccinateType);
        posTest = findViewById(R.id.positiveTest);
        submit = findViewById(R.id.button);
        display = findViewById(R.id.textView);
        List<String> genders = new ArrayList<>();
        genders.add(0, "Your Gender");
        genders.add("Female");
        genders.add("Male");
        genders.add("Not to Be Shared");

        ArrayAdapter<String> genderAdapter;
        genderAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item);

        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //gender.setAdapter(genderAdapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String surname = name.getEditText().getText().toString();
                String gen = gender.getSelectedItem().toString();
                String birth = birthDate.getText().toString();
                String cityC = city.getEditText().getText().toString();
                String vac = vaccine.getSelectedItem().toString();
                String sideEff = sideEffect.getSelectedItem().toString();
                String pos = posTest.getSelectedItem().toString();
                if (surname.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Name-Surname is Empty").
                            setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else if (gen.equals("Gender")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Gender is Empty").
                            setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else if (birth.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Birth date is Empty").
                            setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else if (cityC.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("City is Empty").
                            setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else if (vac.equals("Vaccine type applied")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("You should specify your vaccine type").
                            setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else if (sideEff.equals("Any side effect after vaccination")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Side Effects is empty").
                            setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else if (pos.equals("Any PCR positive cases and Covid-19 symptoms after 3rd vaccination")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Positive cases is empty").
                            setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else {
                    if (gen.equals("Female")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Thank You Mis." + surname + " Participation").
                                setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        name.getEditText().getText().clear();
                                        birthDate.getText().clear();
                                        city.getEditText().getText().clear();
                                        //gender.setAdapter();


                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
            }
        });
    }
}