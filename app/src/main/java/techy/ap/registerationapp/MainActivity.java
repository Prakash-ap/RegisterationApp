package techy.ap.registerationapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import techy.ap.registerationapp.Database.DatabaseHandler;
import techy.ap.registerationapp.Modal.User;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText fname,lname,dob;
    private RadioGroup radioGroup;
    Spinner spinner;
    Button save,view;
    private String gender;
    private String selectedlocation;
    final Calendar calendar=Calendar.getInstance();
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fname=findViewById(R.id.edt_firstname);
        lname=findViewById(R.id.edt_lastname);
        spinner=findViewById(R.id.select_location);
        dob=findViewById(R.id.edt_dob);
        save=findViewById(R.id.btn_save);
        view=findViewById(R.id.btn_view);
        radioGroup=findViewById(R.id.radiogroup);
        spinner.setOnItemSelectedListener(this);
        db=new DatabaseHandler(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=(RadioButton)group.findViewById(checkedId);
                if(null!=radioButton && checkedId >-1){
                     gender= String.valueOf(radioButton.getText());

                }
            }
        });

        final List<String>location=new ArrayList<String>();
        location.add("Select Your Location");
        location.add("Coimbatore");
        location.add("Chennai");
        location.add("Bangalore");
        location.add("Mumbai");
        location.add("Hyderabad");
        location.add("Salem");
        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,location);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        final DatePickerDialog.OnDateSetListener dateformat = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();

            }
        };

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, dateformat,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rfname = fname.getText().toString();
                String rlname = lname.getText().toString();
                String rgender = gender;
                String rlocation = selectedlocation;
                String rdob = dob.getText().toString();

                User user = new User();
                user.setFname(rfname);
                user.setLname(rlname);
                user.setLocation(rlocation);
                user.setDob(rdob);
                user.setGender(rgender);

                if (rfname.equals("") || rlname.equals("") || rdob.equals("") || rlocation.equals("") || rgender.equals("")) {
                    Toast.makeText(MainActivity.this, "Enter All Credentials", Toast.LENGTH_SHORT).show();
                } else if(rlocation.equals("Select Your Location")) {
                    Toast.makeText(MainActivity.this, "Select your  Location", Toast.LENGTH_SHORT).show();
                }else{
                    db.insertdata(user);
                    Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,DisplayActivity.class);
                startActivity(intent);
            }
        });




    }
    private void updateLabel() {
        String format="dd/MM/yyy";
        SimpleDateFormat sdf=new SimpleDateFormat(format, Locale.getDefault());
        dob.setText(sdf.format(calendar.getTime()));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedlocation=parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}
