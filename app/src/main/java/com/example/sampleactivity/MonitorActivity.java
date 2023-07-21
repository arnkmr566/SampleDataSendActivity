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

import com.example.sampleactivity.databinding.ActivityMonitorBinding;


public class MonitorActivity extends AppCompatActivity {

    ActivityMonitorBinding binding;
    String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMonitorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Receive data
        Intent intent = getIntent();

        String setSpeed = intent.getStringExtra("Speed");
        String setTime = intent.getStringExtra("Time");
        String setTotal = intent.getStringExtra("Total");

        // display the string into textView
        binding.edtSpeedMonitor.setText(setSpeed);
        binding.edtTimeMonitor.setText(setTime);
        binding.edtTotal.setText(setTotal);

        //   int num1=Integer.parseInt(e.getText().toString());
        //   int num2=Integer.parseInt(e2.getText().toString());
        //   int sum=num1+num2;
        //   Intent in = new Intent(FirstActivity.this, SecondActivity.class);
        //   in.putExtra("value",sum);
        //   startActivity(in);

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String setSpeed = binding.edtSpeedMonitor.getText().toString();
                String setTime = binding.edtTimeMonitor.getText().toString();
                String setTotal = binding.edtTotal.getText().toString();

                //   int num1=Integer.parseInt(binding.edtSpeedMonitor.getText().toString());
                //   int num2=Integer.parseInt(binding.edtTimeMonitor.getText().toString());
                //  int sum=num1/num2;

                //  set the Error Message on Edit Text
                if (setSpeed.length() == 0) {
                    binding.edtSpeedMonitor.setError("Enter Speed");
                } else if (setTime.length() == 0) {
                    binding.edtTimeMonitor.setError("Enter Time");
                } else {

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    // now by putExtra method put the value in key, value pair key is
                    // message_key by this key we will receive the value, and put the string
                    intent.putExtra("Speed", setSpeed);
                    intent.putExtra("Time", setTime);
                    intent.putExtra("Total", setTotal);

                    //   intent.putExtra("value",sum);

                    // start the Intent
                    startActivity(intent);
                    Toast.makeText(MonitorActivity.this, "Record Send", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // You can change the text = For use of the TextWatcher
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!binding.edtSpeedMonitor.getText().toString().equals("") && !binding.edtTimeMonitor.getText().toString().equals("")) {
                    float num1 = Float.parseFloat(binding.edtSpeedMonitor.getText().toString());
                    float num2 = Float.parseFloat(binding.edtTimeMonitor.getText().toString());
                    //   int sum = num1 + num2;
                    binding.edtTotal.setText(String.valueOf(num1 / num2));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        binding.edtSpeedMonitor.addTextChangedListener(textWatcher);
        binding.edtTimeMonitor.addTextChangedListener(textWatcher);

        //edtSpeedMonitor.setText(number);
        binding.edtSpeedMonitor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                int minvalue = 0, maxvalue = 100;
                if (binding.edtSpeedMonitor.getText().toString().equals("")) {
                } else {
                    int d = Integer.parseInt(binding.edtSpeedMonitor.getText().toString());
                    if (d > (minvalue - 1) && d < (maxvalue + 1)) {
                        if (binding.edtTimeMonitor.getText().equals("")) {
                        } else {
                            designsize_calculation();
                        }
                    } else {
                        binding.edtSpeedMonitor.setError("Please enter between " + minvalue + " to " + maxvalue);
                    }
                }

            }
        });

        //edtTimeMonitor.setText(number);
        binding.edtTimeMonitor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                int minvalue = 0, maxvalue = 100;
                if (binding.edtTimeMonitor.getText().toString().equals("")) {
                } else {
                    int d = Integer.parseInt(binding.edtTimeMonitor.getText().toString());
                    if (d > (minvalue - 1) && d < (maxvalue + 1)) {
                        if (binding.edtSpeedMonitor.getText().equals("")) {
                        } else {
                            designsize_calculation();
                        }
                    } else {
                        binding.edtTimeMonitor.setError("Please enter between " + minvalue + " to " + maxvalue);
                    }
                }

            }
        });


        binding.edtSpeedMonitor.setOnEditorActionListener(editorListener);
        binding.edtTimeMonitor.setOnEditorActionListener(editorListener);

    }

    //  How to Change the Input Method Action = button on the keyboard
    private TextView.OnEditorActionListener editorListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

            switch (actionId) {
                case EditorInfo.IME_ACTION_NEXT:
                    Toast.makeText(MonitorActivity.this, "Next", Toast.LENGTH_SHORT).show();
                    break;
                case EditorInfo.IME_ACTION_DONE:
                    Toast.makeText(MonitorActivity.this, "Send", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }

    };

    private void designsize_calculation() {

        int edtTime = 0, edtSetSpeed = 0;
        if (binding.edtTimeMonitor.getText().toString().equals("")) {
        } else {
            edtTime = Integer.parseInt(binding.edtTimeMonitor.getText().toString());
        }
        if (binding.edtSpeedMonitor.getText().toString().equals("")) {
        } else {
            edtSetSpeed = Integer.parseInt(binding.edtSpeedMonitor.getText().toString());
        }


    }

}