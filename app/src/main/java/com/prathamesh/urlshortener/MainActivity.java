package com.prathamesh.urlshortener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button submit;
    CheckBox small, capital, numbers, specialChars;
    EditText inputUrl;


    private ArrayList<Boolean> checkboxes;

    private Random random;

    private final String StringSmall = "abcdefghijklmnopqrstuvwxyz";
    private final String StringCapital = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String StringNumbers = "0123456789";
    private final String StringSpecialChars = "!@#$%^&*()-+/[]{}";



    private String shortUrl = "";
    String allowedCharString = "";

    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.BTNSubmit);

        small = findViewById(R.id.CBSmallatoz);
        capital = findViewById(R.id.CBCapitalatoz);
        numbers = findViewById(R.id.CBNumbers);
        specialChars = findViewById(R.id.CBSpecialChars);

        inputUrl = findViewById(R.id.ETInputUrl);

        random = new Random();

        counter = 0;

        small.setChecked(true);
        capital.setChecked(true);

        checkboxes = new ArrayList<>();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputUrl.getText().toString().equals("")){
                    inputUrl.setError("Please Enter a url ");
                    return;
                }

                if(small.isChecked()){
                    counter++;
                    allowedCharString += StringSmall;
                }
                if(capital.isChecked()){
                    counter++;
                    allowedCharString += StringCapital;
                }
                if(numbers.isChecked()){
                    counter++;
                    allowedCharString += StringNumbers;
                }
                if(specialChars.isChecked()){
                    counter++;
                    allowedCharString += StringSpecialChars;
                }

                if(counter >= 2){
                    shortUrl = getString(allowedCharString);
                    counter = 0;
                    allowedCharString = "";
                }
                else {

                    Toast.makeText(MainActivity.this, "Please select minimum 2 params..", Toast.LENGTH_SHORT).show();
                    counter = 0;
                    allowedCharString = "";
                    return;
                }
                Toast.makeText(MainActivity.this, shortUrl, Toast.LENGTH_SHORT).show();




            }
        });

    }


    private String getString(String allowedCharString){


         StringBuilder shortUrl = new StringBuilder();


        for (int i=0;i<9;i++){
            int size = allowedCharString.length();
            int ran = random.nextInt(size);
            shortUrl.append(allowedCharString.charAt(ran));
        }
        Log.d("ShortUrl", shortUrl.toString());

        return shortUrl.toString();

    }


}