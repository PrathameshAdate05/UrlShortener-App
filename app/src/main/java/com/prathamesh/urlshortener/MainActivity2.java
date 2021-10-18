package com.prathamesh.urlshortener;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    private EditText etLongUrl, etShortUrl;
    private Button copy;
    private String longUrl, shortUrl;

    private ClipboardManager clipboardManager;
    private ClipData clipData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etLongUrl = findViewById(R.id.ETLongUrl);
        etShortUrl = findViewById(R.id.ETShortUrl);

        copy = findViewById(R.id.copy);

        Intent intent = getIntent();

        longUrl = intent.getStringExtra("LongUrl");
        shortUrl = intent.getStringExtra("ShortUrl");

        etLongUrl.setEnabled(false);
        etShortUrl.setEnabled(false);

        etLongUrl.setText(longUrl);
        etShortUrl.setText(shortUrl);

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                clipData = ClipData.newPlainText("Short Url",shortUrl);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(MainActivity2.this, "Url copied to clipboard..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}