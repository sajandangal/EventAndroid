package com.example.eventscheduler.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eventscheduler.api.EventAPI;
import com.example.eventscheduler.R;
import com.example.eventscheduler.api.EventAPI;
import com.example.eventscheduler.url.Url;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEventActivity extends AppCompatActivity {

    Button btnAddNotes;
    EditText etNote;
    EditText etDesc;
    EditText etLocation;
    private String imageName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        btnAddNotes = findViewById(R.id.btnAddNote);
        etNote = findViewById(R.id.etNote);
        etDesc=findViewById(R.id.etDesc);
        btnAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotes();
            }
        });
    }

    private void addNotes() {
        //Task notes = new Task(etNote.getText().toString());

        EventAPI noteAPI = Url.getInstance().create(EventAPI.class);

        Call<Void> voidCall = noteAPI.addEvent(Url.token,etNote.getText().toString(),etDesc.getText().toString(),imageName,etLocation.getText().toString());

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AddEventActivity.this, "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(AddEventActivity.this, "Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddEventActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }
}
