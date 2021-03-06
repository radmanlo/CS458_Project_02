package com.example.cs458_project_02;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    //containers
    EditText birthDate;
    TextInputLayout name, city;
    Spinner gender, vaccinate, vaccine, sideEffect, posTest;
    Button submit;
    TextView display;

    //listener for getting date form the componenet
    DatePickerDialog.OnDateSetListener datelistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Date calculation
        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);


        // array for genders choice in spinner
        String[] genders = {"Your Gender", "Female", "Male", "Not To Share"};

        // array for are you vaccinated choice in spinner
        String[] vac = {"Are You Vaccinated", "Yes", "No"};

        // array for Vaccinate Type choice in spinner
        String[] vaccineTypes = {"Your Vaccine Type",
                                "Oxford???AstraZeneca",
                                "Pfizer???BioNTech",
                                "Janssen",
                                "Moderna",
                                "Sinopharm BIBP",
                                "Sputnik V",
                                "CoronaVac",
                                "others",};

        // array for side effect choice in spinner
        String[] sideEffects = {"Any side effect after vaccination", "Yes", "No"};

        // array for positive cases choice in spinner
        String[] posCases = {"Any positive case after 3rd dose of vaccination", "Yes", "No"};

        //adapter for each spinner
        ArrayAdapter<String> genderAdapter;
        ArrayAdapter<String> vacAdapter;
        ArrayAdapter<String> vaccineAdapter;
        ArrayAdapter<String> sideEffectAdapter;
        ArrayAdapter<String> posCaseAdapter;

        //style of adapter
        genderAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, genders);
        vacAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, vac);
        vaccineAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, vaccineTypes);
        sideEffectAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, sideEffects);
        posCaseAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, posCases);

        //dropdown style
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vacAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vaccineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sideEffectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        posCaseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //specifying the containers
        name = findViewById(R.id.NameInput);
        gender = findViewById(R.id.gender);
        vaccinate = findViewById(R.id.vac);
        birthDate = findViewById(R.id.Date);
        city = findViewById(R.id.CityInput);
        sideEffect = findViewById(R.id.SideEffect);
        vaccine = findViewById(R.id.vaccinateType);
        posTest = findViewById(R.id.positiveTest);
        submit = findViewById(R.id.button);

        //specifying the spinners Items
        gender.setAdapter(genderAdapter);
        vaccinate.setAdapter(vacAdapter);
        sideEffect.setAdapter(sideEffectAdapter);
        vaccine.setAdapter(vaccineAdapter);
        posTest.setAdapter(posCaseAdapter);

        //birthday style
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

        //
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String surname = name.getEditText().getText().toString();
                String gen = gender.getSelectedItem().toString();
                String vacci = vaccinate.getSelectedItem().toString();
                String birth = birthDate.getText().toString();
                String cityC = city.getEditText().getText().toString();
                String vac = vaccine.getSelectedItem().toString();
                String sideEff = sideEffect.getSelectedItem().toString();
                String pos = posTest.getSelectedItem().toString();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date bDate = new Date();;
                try {
                    bDate = format.parse(birth);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Date curDate = new Date();
                int days = 0;
                if(bDate != null){
                    Long age = curDate.getTime() - bDate.getTime();
                    long seconds = age / 1000;
                    long minutes = seconds / 60;
                    long hours = minutes / 60;
                    days = ((int) (long) hours / 24);
                }
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
                }
                else if(!surname.matches("^([A-Za-z]+)(\\s[A-Za-z]+)*\\s?$")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Name-Surname is Wrong Format").
                            setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else if (gen.equals("Your Gender")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Please Specify Your Gender").
                            setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else if (birth.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Please Specify Your Birth Date").
                            setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else if(bDate.after(curDate) || curDate.equals(bDate)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Invalid Birth Date").
                            setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else if(days < 6571){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Too Young to Be Vaccinated or Invalid Birth Date").
                            setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else if(vacci.equals("Are You Vaccinated")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Please Specify Your Are Vaccinated or not").
                            setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else if(vacci.equals("No")){
                    if (gen.equals("Female")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Thank You Mis." + surname + " For Participation But You Are Not Vaccinated").
                                setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        name.getEditText().getText().clear();
                                        birthDate.getText().clear();
                                        city.getEditText().getText().clear();
                                        gender.setAdapter(genderAdapter);
                                        vaccinate.setAdapter(vacAdapter);
                                        sideEffect.setAdapter(sideEffectAdapter);
                                        vaccine.setAdapter(vaccineAdapter);
                                        posTest.setAdapter(posCaseAdapter);
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Thank You Mr." + surname + " For Participation But You Are Not Vaccinated").
                                setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        name.getEditText().getText().clear();
                                        birthDate.getText().clear();
                                        city.getEditText().getText().clear();
                                        vaccinate.setAdapter(vacAdapter);
                                        gender.setAdapter(genderAdapter);
                                        sideEffect.setAdapter(sideEffectAdapter);
                                        vaccine.setAdapter(vaccineAdapter);
                                        posTest.setAdapter(posCaseAdapter);
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                }
                else if (cityC.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Please Specify Your City").
                            setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else if(!cityC.matches("^([A-Za-z]+)(\\s[A-Za-z]+)*\\s?$")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Name-Surname is Wrong Format").
                            setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else if (vac.equals("Your Vaccine Type")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Please Specify Your Vaccine Type").
                            setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else if (sideEff.equals("Any side effect after vaccination")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Please Specify Your Side Effects").
                            setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else if (pos.equals("Any positive case after 3rd dose of vaccination")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Please Specify Positive Cases").
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
                        builder.setMessage("Thank You Mis." + surname + " For Your Participation").
                                setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        name.getEditText().getText().clear();
                                        birthDate.getText().clear();
                                        city.getEditText().getText().clear();
                                        gender.setAdapter(genderAdapter);
                                        vaccinate.setAdapter(vacAdapter);
                                        sideEffect.setAdapter(sideEffectAdapter);
                                        vaccine.setAdapter(vaccineAdapter);
                                        posTest.setAdapter(posCaseAdapter);
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                    else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Thank You Mr." + surname + " For Your Participation").
                                setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        name.getEditText().getText().clear();
                                        birthDate.getText().clear();
                                        city.getEditText().getText().clear();
                                        vaccinate.setAdapter(vacAdapter);
                                        gender.setAdapter(genderAdapter);
                                        sideEffect.setAdapter(sideEffectAdapter);
                                        vaccine.setAdapter(vaccineAdapter);
                                        posTest.setAdapter(posCaseAdapter);
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