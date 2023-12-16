package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonMain;
    private EditText fieldUser;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fieldUser = findViewById(R.id.user_field);
        buttonMain = findViewById(R.id.main_button);

        buttonMain.setOnClickListener(view -> {
            if (fieldUser.getText().toString().trim().equals("")) {
                Toast.makeText(MainActivity.this, R.string.not_valid_input, Toast.LENGTH_LONG).show();
            } else {
                String userInputCity = fieldUser.getText().toString();
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("userInputCity", userInputCity);
                startActivity(intent);
            }
        });
    }
}

