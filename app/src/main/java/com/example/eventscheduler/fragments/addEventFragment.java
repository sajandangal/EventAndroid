package com.example.eventscheduler.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eventscheduler.R;
import com.example.eventscheduler.activity.AddEventActivity;
import com.example.eventscheduler.api.EventAPI;
import com.example.eventscheduler.api.UsersAPI;
import com.example.eventscheduler.model.Event;
import com.example.eventscheduler.serverresponse.ImageResponse;
import com.example.eventscheduler.adapter.EventAdapter;
import com.example.eventscheduler.strictmode.StrictModeClass;
import com.example.eventscheduler.url.Url;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class addEventFragment extends Fragment {
    private CircleImageView imgProfile;
    Button btnAddNotes;
    EditText etNote;
    EditText etDesc;
    EditText etLocation;
    String imagePath;
    private String imageName = "";


    public addEventFragment() {
        // Required empty public constructor
    }



    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_event, container, false);
        imgProfile = view.findViewById(R.id.imgEvent);
        btnAddNotes = view.findViewById(R.id.btnAddNote);
        etNote = view.findViewById(R.id.etNote);
        etDesc=view.findViewById(R.id.etDesc);
        etLocation=view.findViewById(R.id.etLoc);
        btnAddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImageOnly();
                addNotes();

            }
        });
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });

        return view;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_add_event, container, false);
    }


    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(getContext(), "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imgProfile.setImageURI(uri);
        imagePath = getRealPathFromUri(uri);
    }
    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getActivity().getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                file.getName(), requestBody);

        UsersAPI usersAPI = Url.getInstance().create(UsersAPI.class);
        Call<ImageResponse> responseBodyCall = usersAPI.uploadImage(body);

        StrictModeClass.StrictMode();
        //Synchronous methid
        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(getContext(), "Image inserted" + imageName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(getContext(), "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void addNotes() {
        //Task notes = new Task(etNote.getText().toString());
        String name = etNote.getText().toString();
        String desc = etDesc.getText().toString();
        String location = etLocation.getText().toString();
        //String password = etSignUpPassword.getText().toString();
        Event event= new Event(name,desc,imageName,location);

        EventAPI noteAPI = Url.getInstance().create(EventAPI.class);

        Call<Void> voidCall = noteAPI.addEvent(Url.token,etNote.getText().toString(),etDesc.getText().toString(),imageName,etLocation.getText().toString());

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    //  Toast.makeText(AddEventActivity.this, "Code : " + response.code() + ", Message : " + response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getContext(), "Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Toast.makeText(AddEventActivity.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });



    }

}
