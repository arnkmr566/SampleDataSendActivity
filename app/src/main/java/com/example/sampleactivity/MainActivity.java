package com.example.sampleactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;


import com.example.sampleactivity.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

// You can change the text = For use of the TextWatcher



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

 // You can change the text = For use of the TextWatcher
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!binding.edtSetSpeed.getText().toString().equals("") && !binding.edtTime.getText().toString().equals("")) {
                    float num1 = Float.parseFloat(binding.edtSetSpeed.getText().toString());
                    float num2 = Float.parseFloat(binding.edtTime.getText().toString());
                    //   int sum = num1 + num2;
                    binding.edtTotal.setText(String.valueOf(num1 / num2));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };


        binding.edtSetSpeed.addTextChangedListener(textWatcher);
        binding.edtTime.addTextChangedListener(textWatcher);


        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String setSpeed = binding.edtSetSpeed.getText().toString();
                String setTime = binding.edtTime.getText().toString();
                String setTotal = binding.edtTotal.getText().toString();

                //  set the Error Message on Edit Text
                if (setSpeed.length() == 0) {
                    binding.edtSetSpeed.setError("Enter Speed");
                } else if (setTime.length() == 0) {
                    binding.edtTime.setError("Enter Time");
                } else {

                    // Send data on Second Activity
                    Intent intent = new Intent(getApplicationContext(), MonitorActivity.class);
                    // now by putExtra method put the value in key, value pair key is
                    // message_key by this key we will receive the value, and put the string
                    intent.putExtra("Speed", setSpeed);
                    intent.putExtra("Time", setTime);
                    intent.putExtra("Total", setTotal);

                    // start the Intent
                    startActivity(intent);

                    Toast.makeText(MainActivity.this, "Record Send", Toast.LENGTH_SHORT).show();
                }


            }
        });

    // Receive data
        Intent intent = getIntent();

        String setSpeed = intent.getStringExtra("Speed");
        String setTime = intent.getStringExtra("Time");
        String setTotal = intent.getStringExtra("Total");

        //    int data = intent.getIntExtra("value",0);

        binding.edtSetSpeed.setText(setSpeed);
        binding.edtTime.setText(setTime);
        binding.edtTotal.setText(setTotal);


        //  binding.edtTotal.setText(Integer.toString(data));



/*
        binding.btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Checking Input First Is Blank Or Not
                if (binding.edtSetSpeed.getText().toString().equals("")) {
                    // Showing Toast (Message)
                    Toast.makeText(MainActivity.this, "Please Enter Number", Toast.LENGTH_SHORT).show();
                } else if (binding.edtTime.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Please Enter Number", Toast.LENGTH_SHORT).show();
                }

                // Both Inputs Are Not Blank , Starting Calculation
                else {
                    float a, b, c;
                    a = Float.parseFloat(binding.edtSetSpeed.getText().toString());
                    b = Float.parseFloat(binding.edtTime.getText().toString());
                    c = a / b; // Using Third Variable To Store Output Value
                    binding.edtTotal.setText(" " + c);

                }
            }
        });

          <Button
        android:id="@+id/btndiv"
        android:layout_width="120dp"
        android:layout_height="wrap_content"

        android:layout_marginTop="16dp"
        android:text="Divide"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/linearLayout6" />

 */

        //edtSetSpeed.setText(number);
        binding.edtSetSpeed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {

                int minvalue=0,maxvalue=100;
                if( binding.edtSetSpeed.getText().toString().equals("")){}else{
                    int d= Integer.parseInt( binding.edtSetSpeed.getText().toString());
                    if(d > (minvalue-1) && d < (maxvalue+1)){  if (binding.edtTime.getText().equals("")){}else{designsize_calculation();} } else {
                        binding.edtSetSpeed.setError("Please enter between " +minvalue+" to "+maxvalue);} }

            }
        });

        //edtTime.setText(number);
        binding.edtTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {

                int minvalue=0,maxvalue=100;
                if( binding.edtTime.getText().toString().equals("")){}else{
                    int d= Integer.parseInt( binding.edtTime.getText().toString());
                    if(d > (minvalue-1) && d < (maxvalue+1)){  if (binding.edtSetSpeed.getText().equals("")){}else{designsize_calculation();} } else {
                        binding.edtTime.setError("Please enter between " +minvalue+" to "+maxvalue);} }

            }
        });


        binding.edtSetSpeed.setOnEditorActionListener(editorListener);
        binding.edtTime.setOnEditorActionListener(editorListener);

        // Assigning filters
    //    binding.edtSetSpeed.setFilters( new InputFilter[]{ new MinMaxFilter( "1" , "110" )});

    }

    //  How to Change the Input Method Action = button on the keyboard
    private TextView.OnEditorActionListener editorListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

            switch (actionId) {
                case EditorInfo.IME_ACTION_NEXT:
                    Toast.makeText(MainActivity.this, "Next", Toast.LENGTH_SHORT).show();
                    break;
                case EditorInfo.IME_ACTION_DONE:
                    Toast.makeText(MainActivity.this, "Send", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }

    };
    /*

    // Custom class to define min and max for the edit text
    public class MinMaxFilter implements InputFilter {
        private int mIntMin , mIntMax ;
        public MinMaxFilter ( int minValue , int maxValue) {
            this . mIntMin = minValue ;
            this . mIntMax = maxValue ;
        }
        public MinMaxFilter (String minValue , String maxValue) {
            this . mIntMin = Integer. parseInt (minValue) ;
            this . mIntMax = Integer. parseInt (maxValue) ;
        }
        @Override
        public CharSequence filter (CharSequence source , int start , int end , Spanned dest , int dstart , int dend) {
            try {
                int input = Integer. parseInt (dest.toString() + source.toString()) ;
                if (isInRange( mIntMin , mIntMax , input))
                    return null;
            } catch (NumberFormatException e) {
                e.printStackTrace() ;
            }
            return "" ;
        }
        private boolean isInRange ( int a , int b , int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a ;
        }

    }

     */

    private void designsize_calculation() {

        int edtTime = 0, edtSetSpeed = 0;
        if (binding.edtTime.getText().toString().equals("")) {
        } else {
            edtTime = Integer.parseInt(binding.edtTime.getText().toString());
        }
        if (binding.edtSetSpeed.getText().toString().equals("")) {
        } else {
            edtSetSpeed = Integer.parseInt(binding.edtSetSpeed.getText().toString());
        }

    }

}