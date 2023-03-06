package com.example.diaryapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import io.realm.Realm;


public class AddDairyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dairy);

        EditText titleInput = findViewById(R.id.titleinput);
        EditText descriptionInput = findViewById(R.id.descriptioninput);
        MaterialButton saveBtn = findViewById(R.id.savebtn);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        saveBtn.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String description = descriptionInput.getText().toString();
            long createdTime = System.currentTimeMillis();

            realm.beginTransaction();
            Notes notes = realm.createObject(Notes.class);
            notes.setTitle(title);
            notes.setDescription(description);
            notes.setCreatedTime(createdTime);
            realm.commitTransaction();
            Toast.makeText(getApplicationContext(),"Entry Saved", Toast.LENGTH_SHORT).show();
            finish();

        });


    }
}