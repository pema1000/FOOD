package com.example.food;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class Additem extends AppCompatActivity {

    EditText pname,pamount,pdoe,prodidnumber;

    Button okbtn;
    FirebaseAuth fAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    String userID;

   // public static final int CAMERA_CODE=1;
    ImageView itemphoto;
    Button takephoto;
    Uri ImageUri;
    private StorageReference storageref;
    String productImageURL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        pname=findViewById(R.id.additemname);
        pamount=findViewById(R.id.additemnumber);
        pdoe=findViewById(R.id.additemdoe);
        prodidnumber=findViewById(R.id.additempid);
        okbtn=findViewById(R.id.additemconform);
        fAuth=FirebaseAuth.getInstance();
        userID=fAuth.getCurrentUser().getUid();
        itemphoto=findViewById(R.id.additemimage);
        takephoto=findViewById(R.id.additemtakephoto);

        storageref= FirebaseStorage.getInstance().getReference(userID);
        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
              //  if(intent.resolveActivity(getPackageManager())!=null){
              //      startActivity(intent);
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                launchSomeActivity.launch(intent);
                }

        });
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ProductName = pname.getText().toString();
                String ProductAmount = pamount.getText().toString();
                String ProductDoe = pdoe.getText().toString();
                String ProductIdnumber = prodidnumber.getText().toString();
                String ProductImage=productImageURL;

                if (ProductName.isEmpty()) {
                    pname.setError("Please Enter Product Name");
                    return;
                }
                if (ProductAmount.isEmpty()) {
                    pamount.setError("Please Enter correct number");
                    return;
                }
                if (ProductDoe.isEmpty()) {
                    pdoe.setError("Please Enter Password");
                    return;
                }
                if (ProductIdnumber.isEmpty()) {
                    prodidnumber.setError("Please Enter Product ID");
                    return;
                }


                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference(userID);
                   uploadPhoto();
                if(ProductImage.isEmpty()){
                    Toast.makeText(Additem.this, "Please add image", Toast.LENGTH_LONG).show();
                } else {
                    productinfo addinfo = new productinfo(ProductName, ProductAmount, ProductDoe, ProductIdnumber, ProductImage);
                    //rootNode = FirebaseDatabase.getInstance();
                    //reference = rootNode.getReference(userID);
                    reference.child(ProductName).setValue(addinfo).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Additem.this, "Item has been Added", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Additem.this, "Item could not be added", Toast.LENGTH_SHORT).show();
                        }
                    });


                    startActivity(new Intent(getApplicationContext(), Inventory.class));
                    finish();
                }
            }
        });

    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      if(requestCode==CAMERA_CODE && resultCode==RESULT_OK && data!=null&&data.getData()!=null){
       // if(requestCode==RESULT_OK && data!=null){
           // Bundle bundle=data.getExtras();
            //Bitmap photodata=(Bitmap) bundle.get("data");
            //itemphoto.setImageBitmap(photodata);
          imageUri=data.getData();
            itemphoto.setImageURI(imageUri);
        }
    }*/


    ActivityResultLauncher<Intent> launchSomeActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Bundle bundle=data.getExtras();
                        ImageUri = data.getData();
                        Bitmap photodata=(Bitmap) bundle.get("data");
                        itemphoto.setImageBitmap(photodata);
                    }
                }
            });
    private String getFileExtension(Uri uri){
        ContentResolver cR=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadPhoto(){
    if(ImageUri!=null){
     //   StorageReference fileReference=storageref.child(System.currentTimeMillis()+ "."+getFileExtension(ImageUri));
        StorageReference fileReference=storageref.child(pname+ "."+getFileExtension(ImageUri));
        fileReference.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Additem.this,"Image Uploaded",Toast.LENGTH_SHORT).show();
                productImageURL=fileReference.getDownloadUrl().toString();
                Imageupload upload=new Imageupload(pname.getText().toString(),productImageURL);
                String imageid=reference.push().getKey();
                reference.child(imageid).setValue(upload);

            }
        });
    }else{
        Toast.makeText(this,"No Image Uploaded",Toast.LENGTH_SHORT).show();
        uploadPhoto();
    }
    }



}