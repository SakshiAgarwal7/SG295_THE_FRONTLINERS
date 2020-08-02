
package com.example.vissionsikkimnew;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadNewSchemes extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button btnSubmit,btnUploadPDF;
    TextView textPDF;
    EditText editSchemeName,editDepartmentName,editDivisionName,editFinancialYear,editSchemeDescription,editIncomeLevel,editWTOContact;
    RadioGroup radio_group;
    RadioButton radioButton;
    Spinner editSpinnerAge,editSpinnerCategory;
    ProgressDialog progressDialog;
    private String SpinnerResultAge,SpinnerResultCategory;
    private String radioBtnResult;
    AddScheme addScheme;
    int id=0;

    //the firebase objects for storage and database
    StorageReference mStorageReference;
    DatabaseReference mDatabaseReference;
    FirebaseDatabase mfirebaseDatabase;
    //this is the pic pdf code used in file chooser
    final static int PICK_PDF_CODE = 2342;
    private DatabaseReference DrMember;
    Intent pdfData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_new_schemes);


        //getting firebase objects
        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mStorageReference = FirebaseStorage.getInstance().getReference();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        editIncomeLevel = findViewById(R.id.editIncomeLevel);
        editSchemeDescription = findViewById(R.id.editSchemeDescription);
        editFinancialYear = findViewById(R.id.editFinancialYear);
        editDivisionName = findViewById(R.id.editDivisionName);
        editDepartmentName = findViewById(R.id.editDepartmentName);
        editSchemeName = findViewById(R.id.editSchemeName);
        radio_group = findViewById(R.id.radio_group);
        btnSubmit = findViewById(R.id.btnSubmit);
        textPDF = findViewById(R.id.textPDF);
        btnUploadPDF = findViewById(R.id.btnUploadPDF);
        editWTOContact = findViewById(R.id.editWTOContact);



        // check which option is selected form spinner
        editSpinnerCategory = findViewById(R.id.editSpinnerCategory);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.editSpinnerCategory,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editSpinnerCategory.setAdapter(adapter);
        editSpinnerCategory.setOnItemSelectedListener(this);

        editSpinnerAge=(Spinner)findViewById(R.id.editSpinnerAge);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.editspinnerAge,
                android.R.layout.simple_spinner_item);
        editSpinnerAge.setAdapter(adapter2);
        editSpinnerAge.setOnItemSelectedListener(this);






        btnUploadPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //uploading pdf files
                getPDF();

            }
        });

        DrMember = mDatabaseReference.child("Schemes"); //      variable going to help to store Schemes in child --------
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // check which option is select form radio button
                int radioID = radio_group.getCheckedRadioButtonId();
                radioButton = findViewById(radioID);
                radioBtnResult= radioButton.getText().toString();

                Toast.makeText(UploadNewSchemes.this, ""+SpinnerResultAge, Toast.LENGTH_SHORT).show();
                Toast.makeText(UploadNewSchemes.this, ""+SpinnerResultCategory, Toast.LENGTH_SHORT).show();
                Toast.makeText(UploadNewSchemes.this, ""+radioBtnResult, Toast.LENGTH_LONG).show();

                String SchemeName = editSchemeName.getText().toString().trim();
                String DepartmentName = editDepartmentName.getText().toString().trim();
                String DivisionName = editDivisionName.getText().toString().trim();
                String FinancialYear = editFinancialYear.getText().toString().trim();
                String SchemeDescription = editSchemeDescription.getText().toString().trim();
                String IncomeLevel = editIncomeLevel.getText().toString().trim();
                String Age = SpinnerResultAge;
                String Category = SpinnerResultCategory;
                String WTOContact = editWTOContact.getText().toString().trim();
                String Gender = radioBtnResult;
                String PDFurl = "";



//                if(TextUtils.isEmpty(SchemeName)){
//                    editSchemeName.setError("SchemeName is required");
//                    return;
//                }
//                if(TextUtils.isEmpty(DepartmentName)){
//                    editDepartmentName.setError("SchemeName is required");
//                    return;
//                }
//                if(TextUtils.isEmpty(DivisionName)){
//                    editDivisionName.setError("SchemeName is required");
//                    return;
//                }
//                if(TextUtils.isEmpty(SchemeDescription)){
//                    editSchemeDescription.setError("SchemeName is required");
//                    return;
//                }
//                if(TextUtils.isEmpty(IncomeLevel)){
//                    editIncomeLevel.setError("SchemeName is required");
//                    return;
//                }


                id++;
                addScheme = new AddScheme(SchemeName,DepartmentName,DivisionName,FinancialYear,SchemeDescription,IncomeLevel,Age,Category,WTOContact,Gender,PDFurl);
                if(pdfData!=null && pdfData.getData()!=null) {
                    uploadFile(pdfData.getData());
                }
                else {
                    DrMember.child(mDatabaseReference.push().getKey()).setValue(addScheme).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(UploadNewSchemes.this, "Details uploaded", Toast.LENGTH_SHORT).show();
                                //startActivity(new Intent(getApplicationContext(), SuccessScreen.class));
                                startActivity(new Intent(getApplicationContext(), SuccessScreen.class));
                            } else {
                                Toast.makeText(UploadNewSchemes.this, "Fail to upload", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }




        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // TODO Auto-generated method stub
        Spinner spin = (Spinner)adapterView;
        Spinner spin2 = (Spinner)adapterView;

        if(spin.getId() == R.id.editSpinnerCategory)
        {
            SpinnerResultCategory = adapterView.getSelectedItem().toString();

        }
        else if(spin2.getId() == R.id.editSpinnerAge)
        {
            SpinnerResultAge = adapterView.getSelectedItem().toString();

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //this function will get the pdf from the storage
    private void getPDF() {
        //for greater than lolipop versions we need the permissions asked on runtime
        //so if the permission is not available user will go to the screen to allow storage permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            startActivity(intent);
            return;
        }

        //creating an intent for file chooser
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PDF_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //when the user choses the file
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //if a file is selected
            if (data.getData() != null) {
                //uploading the file
                textPDF.setText(data.getData().getPath());
                pdfData = data;

            }else{
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
    }


    //this method is uploading the file
    //the code is same as the previous tutorial
    //so we are not explaining it
    private void uploadFile(final Uri data) {
        progressDialog =new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("Uploading the file");
        progressDialog.setProgress(0);
        progressDialog.show();

        StorageReference sRef = mStorageReference.child(Constants.STORAGE_PATH_UPLOADS + System.currentTimeMillis() + ".pdf");
        sRef.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        //textViewStatus.setText("File Uploaded Successfully");
                        progressDialog.hide();
                        Toast.makeText(UploadNewSchemes.this, "File Uploaded ....", Toast.LENGTH_LONG).show();
                        String url = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();  // return the url of uploaded file
                        addScheme.setPdfURL(url);
                        DrMember.child(mDatabaseReference.push().getKey()).setValue(addScheme).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(UploadNewSchemes.this, "Details uploaded", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), ListOfRunningSchemes.class));
                                } else {
                                    Toast.makeText(UploadNewSchemes.this, "Fail to upload", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        //UploadPDF upload = new UploadPDF(editTextFilename.getText().toString(), taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
                        //UploadPDF upload = new UploadPDF(textPDF.getText().toString().trim(), taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
                        //DrMember.child(mDatabaseReference.push().getKey()).setValue(upload);
//                        DrMember.setValue(url).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()){
//                                    Toast.makeText(UploadNewSchemes.this, "File is uploaded finaly....", Toast.LENGTH_LONG).show();
//                                    startActivity(new Intent(getApplicationContext(),SuccessScreen.class));
//                                }else{
//                                    Toast.makeText(UploadNewSchemes.this, "File not is uploaded", Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                        System.out.println("Upload is " + progress + "% done");
                        int currentprogress = (int) progress;
                        progressDialog.setProgress(currentprogress);
                    }
                }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                System.out.println("Upload is paused");
            }
        });

    }
    public class Constants {
        public static final String STORAGE_PATH_UPLOADS = "Pdf/";
        public static final String DATABASE_PATH_UPLOADS = "Schemes";
    }

}