package com.example.snapchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SnapsDetail extends AppCompatActivity {
ImageView imageSnap;
TextView txtmessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snaps_detail);
        imageSnap = findViewById(R.id.imageSnap);
        txtmessage = findViewById(R.id.txtMessage);

        Intent intent = getIntent();
        String imageUrl = intent.getExtras().getString("imageUrl");
        String message = intent.getStringExtra("message");
        txtmessage.setText(message);
        Picasso.get().load(imageUrl).into(imageSnap);

    }
}