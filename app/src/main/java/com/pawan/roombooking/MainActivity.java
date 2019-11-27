package com.pawan.roombooking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    
    TextView chDate, choDate, tvPrice, tvResult;
    Spinner sRoom;
    Button btnCal;
    EditText etAdult, etChild, etRoom;
    int year2, year3;
    int month2, month3;
    int day2, day3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chDate = findViewById(R.id.chDate);
        choDate = findViewById(R.id.choDate);
        sRoom = findViewById(R.id.sRoom);
        etAdult = findViewById(R.id.etAdult);
        etChild = findViewById(R.id.etChild);
        etRoom = findViewById(R.id.etRoom);
        tvPrice = findViewById(R.id.tvPrice);
        btnCal = findViewById(R.id.btnCal);
        tvResult = findViewById(R.id.tvResult);

        
        chDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePickerDate1();
            }
        });

        choDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePickerDate2();
            }
        });
        String room[] = {"Select room type", "Deluxe -Rs. 2000", "Presidential -Rs. 5000", "Premium- Rs. 4000"};
        ArrayAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,room);
        sRoom.setAdapter(adapter);

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sRoom.getSelectedItem()=="Select room type")
                {

                }
                if (TextUtils.isEmpty(etAdult.getText())) {
                    etAdult.setError("Enter no of Adults");
                    return;
                }
                if (TextUtils.isEmpty(etChild.getText())) {
                    etChild.setError("Enter no of childs");
                    return;
                }
                if (TextUtils.isEmpty(etRoom.getText())) {
                    etRoom.setError("Enter no of rooms");
                    return;
                }


                if (sRoom.getSelectedItem() == "Deluxe -Rs. 2000") {
                    tvPrice.setText("2000");


                }
                if (sRoom.getSelectedItem() == "Presidential -Rs. 5000") {
                    tvPrice.setText("5000");


                }
                if (sRoom.getSelectedItem() == "Premium- Rs. 4000") {
                    tvPrice.setText("4000");

                }

                int adult, child, room;
                double total, price, vat, GrossTotal;

                adult = Integer.parseInt(etAdult.getText().toString());
                child = Integer.parseInt(etChild.getText().toString());
                room = Integer.parseInt(etRoom.getText().toString());

                Calendar cal1=Calendar.getInstance();
                Calendar cal2=Calendar.getInstance();
                cal1.set(year2,month2,day2);
                cal2.set(year3,month3,day3);
                long millis1=cal1.getTimeInMillis();
                long millis2= cal2.getTimeInMillis();
                long diff=millis2-millis1;
                long diffDays=(diff/86400000);

                price = Integer.parseInt(tvPrice.getText().toString());
                total = price * room * diffDays;
                vat = 0.13 * total;
                GrossTotal = total + vat;

                if (diff<0)
                {
                    chDate.setError("Checkin date is invalid");
                    choDate.setError("Checkout date is invalid");
                    return;
                }

                tvResult.setText("Total: Rs." + total + "\n" + "Vat Rs.:" + vat + "\n" + "Gross Total: Rs." + GrossTotal);


            }
        });
        
    }

    private void loadDatePickerDate2() {
        final Calendar c1=Calendar.getInstance();
        int year= c1.get(Calendar.YEAR);
        final int month = c1.get(Calendar.MONTH);
        int day= c1.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year1, int month, int dayOfMonth1) {
                String date = "Checking out Date: "+month + "/" + dayOfMonth1 + "/" + year1;
                month3=month;
                day3=dayOfMonth1;
                year3=year1;
                choDate.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();


    }

    private void loadDatePickerDate1() {
        final Calendar c=Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = "Checking Date: "+month + "/" + dayOfMonth + "/" + year;
                month2=month;
                day2=dayOfMonth;
                year2=year;
                chDate.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();



    }

}
