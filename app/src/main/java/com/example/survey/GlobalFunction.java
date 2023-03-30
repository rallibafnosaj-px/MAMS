package com.example.survey;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GlobalFunction {
    Activity activity;
    AlertDialog.Builder chooseItemDialog;
    Uri outputFileUri;
    String currentPhotoPath;
    String currentPhotoPath2;
    String currentPhotoPath3;
    String currentPhotoPathSN1, currentPhotoPathSN2, currentPhotoPathSN3, currentPhotoPathSN4,
    currentPhotoPathSN5, currentPhotoPathSN6, currentPhotoPathSN7, currentPhotoPathSN8,
            currentPhotoPathSN9, currentPhotoPathSN10, currentPhotoPathSQR, currentPhotoPathEXTRA, currentPhotoPathPisoTest;
    String date;

    public static final int IMG_REQUEST_CAMERA = 3;
    public final int IMG_REQUEST_PICKER = 2;
    public static final int IMG_REQUEST_CAMERA2 = 33;
    public final int IMG_REQUEST_PICKER2 = 22;
    public static final int IMG_REQUEST_CAMERA3 = 333;
    public final int IMG_REQUEST_PICKER3 = 222;

    public static final int IMG_REQUEST_CAMERASN1 = 101;
    public final int IMG_REQUEST_PICKERSN1 = 201;
    public static final int IMG_REQUEST_CAMERASN2 = 102;
    public final int IMG_REQUEST_PICKERSN2 = 202;
    public static final int IMG_REQUEST_CAMERASN3 = 103;
    public final int IMG_REQUEST_PICKERSN3 = 203;
    public static final int IMG_REQUEST_CAMERASN4 = 104;
    public final int IMG_REQUEST_PICKERSN4 = 204;
    public static final int IMG_REQUEST_CAMERASN5 = 105;
    public final int IMG_REQUEST_PICKERSN5 = 205;
    public static final int IMG_REQUEST_CAMERASN6 = 106;
    public final int IMG_REQUEST_PICKERSN6 = 206;
    public static final int IMG_REQUEST_CAMERASN7 = 107;
    public final int IMG_REQUEST_PICKERSN7 = 207;
    public static final int IMG_REQUEST_CAMERASN8 = 108;
    public final int IMG_REQUEST_PICKERSN8 = 208;
    public static final int IMG_REQUEST_CAMERASN9 = 109;
    public final int IMG_REQUEST_PICKERSN9 = 209;
    public static final int IMG_REQUEST_CAMERASN10 = 110;
    public final int IMG_REQUEST_PICKERSN10 = 210;

    public static final int IMG_REQUEST_CAMERASQR1 = 1001;
    public final int IMG_REQUEST_PICKERSQR1 = 2001;
    public static final int IMG_REQUEST_CAMERAEXTRA = 1002;
    public final int IMG_REQUEST_PICKEREXTRA = 2002;

    public static final int IMG_REQUEST_CAMERAPISOTEST = 1003;
    public final int IMG_REQUEST_PICKERPISOTEST = 2003;


    public GlobalFunction(Activity activity) {
        this.activity = activity;
    }

    public String ProcessingBitmap(Uri path) {

        String image = "";

        try {

            Bitmap bitmap = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), path);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream);
            byte[] imgBytes = byteArrayOutputStream.toByteArray();
            image = Base64.encodeToString(imgBytes, Base64.DEFAULT);

            return image;

        } catch (Exception e) {

        }

        return image;
    }

    public void SelectPhotoSQR1() {


        final String[] items = {"Camera", "Gallery"};


        chooseItemDialog = new AlertDialog.Builder(activity);
        chooseItemDialog.setTitle("Select image resource.");
        chooseItemDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File photoFile = null;
                    try {

                        photoFile = createImageFileSQR();

                    } catch (IOException ex) {

                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        outputFileUri = FileProvider.getUriForFile(activity.getApplicationContext(),

                                "com.example.android.fileproviderSurvey",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        activity.startActivityForResult(takePictureIntent, IMG_REQUEST_CAMERASQR1);
                    }

                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activity.startActivityForResult(intent, IMG_REQUEST_PICKERSQR1);
                }


            }
        });
        chooseItemDialog.show();
    }

    private File createImageFileSQR() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPathSQR = image.getAbsolutePath();
        return image;
    }

    public void SelectPhotoPisoTest() {


        final String[] items = {"Camera", "Gallery"};


        chooseItemDialog = new AlertDialog.Builder(activity);
        chooseItemDialog.setTitle("Select image resource.");
        chooseItemDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File photoFile = null;
                    try {

                        photoFile = createImageFilePisoTest();

                    } catch (IOException ex) {

                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        outputFileUri = FileProvider.getUriForFile(activity.getApplicationContext(),

                                "com.example.android.fileproviderSurvey",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        activity.startActivityForResult(takePictureIntent, IMG_REQUEST_CAMERAPISOTEST);
                    }

                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activity.startActivityForResult(intent, IMG_REQUEST_PICKERPISOTEST);
                }


            }
        });
        chooseItemDialog.show();
    }

    private File createImageFilePisoTest() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPathPisoTest = image.getAbsolutePath();
        return image;
    }

    public void SelectPhotoEXTRA() {


        final String[] items = {"Camera", "Gallery"};


        chooseItemDialog = new AlertDialog.Builder(activity);
        chooseItemDialog.setTitle("Select image resource.");
        chooseItemDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File photoFile = null;
                    try {

                        photoFile = createImageFileEXTRA();

                    } catch (IOException ex) {

                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        outputFileUri = FileProvider.getUriForFile(activity.getApplicationContext(),

                                "com.example.android.fileproviderSurvey",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        activity.startActivityForResult(takePictureIntent, IMG_REQUEST_CAMERAEXTRA);
                    }

                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activity.startActivityForResult(intent, IMG_REQUEST_PICKEREXTRA);
                }


            }
        });
        chooseItemDialog.show();
    }

    private File createImageFileEXTRA() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPathEXTRA = image.getAbsolutePath();
        return image;
    }

    public void SelectPhoto() {


        final String[] items = {"Camera", "Gallery"};


        chooseItemDialog = new AlertDialog.Builder(activity);
        chooseItemDialog.setTitle("Select image resource.");
        chooseItemDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File photoFile = null;
                    try {

                        photoFile = createImageFile();

                    } catch (IOException ex) {

                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        outputFileUri = FileProvider.getUriForFile(activity.getApplicationContext(),

                                "com.example.android.fileproviderSurvey",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        activity.startActivityForResult(takePictureIntent, IMG_REQUEST_CAMERA);
                    }

                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activity.startActivityForResult(intent, IMG_REQUEST_PICKER);
                }


            }
        });
        chooseItemDialog.show();
    }

    public void SelectPhoto2() {


        final String[] items = {"Camera", "Gallery"};


        chooseItemDialog = new AlertDialog.Builder(activity);
        chooseItemDialog.setTitle("Select image resource.");
        chooseItemDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File photoFile = null;
                    try {

                        photoFile = createImageFile2();

                    } catch (IOException ex) {

                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        outputFileUri = FileProvider.getUriForFile(activity.getApplicationContext(),

                                "com.example.android.fileproviderSurvey",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        activity.startActivityForResult(takePictureIntent, IMG_REQUEST_CAMERA2);
                    }

                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activity.startActivityForResult(intent, IMG_REQUEST_PICKER2);
                }


            }
        });
        chooseItemDialog.show();
    }

    public void SelectPhoto3() {


        final String[] items = {"Camera", "Gallery"};


        chooseItemDialog = new AlertDialog.Builder(activity);
        chooseItemDialog.setTitle("Select image resource.");
        chooseItemDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File photoFile = null;
                    try {

                        photoFile = createImageFile3();

                    } catch (IOException ex) {

                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        outputFileUri = FileProvider.getUriForFile(activity.getApplicationContext(),

                                "com.example.android.fileproviderSurvey",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        activity.startActivityForResult(takePictureIntent, IMG_REQUEST_CAMERA3);
                    }

                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activity.startActivityForResult(intent, IMG_REQUEST_PICKER3);
                }


            }
        });
        chooseItemDialog.show();
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private File createImageFile2() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath2 = image.getAbsolutePath();
        return image;
    }

    private File createImageFile3() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath3 = image.getAbsolutePath();
        return image;
    }

    public void SelectPhotoSN1() {


        final String[] items = {"Camera", "Gallery"};


        chooseItemDialog = new AlertDialog.Builder(activity);
        chooseItemDialog.setTitle("Select image resource.");
        chooseItemDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File photoFile = null;
                    try {

                        photoFile = createImageFileSN1();

                    } catch (IOException ex) {

                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        outputFileUri = FileProvider.getUriForFile(activity.getApplicationContext(),

                                "com.example.android.fileproviderSurvey",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        activity.startActivityForResult(takePictureIntent, IMG_REQUEST_CAMERASN1);
                    }

                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activity.startActivityForResult(intent, IMG_REQUEST_PICKERSN1);
                }


            }
        });
        chooseItemDialog.show();
    }

    private File createImageFileSN1() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPathSN1 = image.getAbsolutePath();
        return image;
    }

    public void SelectPhotoSN2() {


        final String[] items = {"Camera", "Gallery"};


        chooseItemDialog = new AlertDialog.Builder(activity);
        chooseItemDialog.setTitle("Select image resource.");
        chooseItemDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File photoFile = null;
                    try {

                        photoFile = createImageFileSN2();

                    } catch (IOException ex) {

                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        outputFileUri = FileProvider.getUriForFile(activity.getApplicationContext(),

                                "com.example.android.fileproviderSurvey",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        activity.startActivityForResult(takePictureIntent, IMG_REQUEST_CAMERASN2);
                    }

                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activity.startActivityForResult(intent, IMG_REQUEST_PICKERSN2);
                }


            }
        });
        chooseItemDialog.show();
    }

    private File createImageFileSN2() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPathSN2 = image.getAbsolutePath();
        return image;
    }

    public void SelectPhotoSN3() {


        final String[] items = {"Camera", "Gallery"};


        chooseItemDialog = new AlertDialog.Builder(activity);
        chooseItemDialog.setTitle("Select image resource.");
        chooseItemDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File photoFile = null;
                    try {

                        photoFile = createImageFileSN3();

                    } catch (IOException ex) {

                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        outputFileUri = FileProvider.getUriForFile(activity.getApplicationContext(),

                                "com.example.android.fileproviderSurvey",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        activity.startActivityForResult(takePictureIntent, IMG_REQUEST_CAMERASN3);
                    }

                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activity.startActivityForResult(intent, IMG_REQUEST_PICKERSN3);
                }


            }
        });
        chooseItemDialog.show();
    }

    private File createImageFileSN3() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPathSN3 = image.getAbsolutePath();
        return image;
    }

    public void SelectPhotoSN4() {


        final String[] items = {"Camera", "Gallery"};


        chooseItemDialog = new AlertDialog.Builder(activity);
        chooseItemDialog.setTitle("Select image resource.");
        chooseItemDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File photoFile = null;
                    try {

                        photoFile = createImageFileSN4();

                    } catch (IOException ex) {

                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        outputFileUri = FileProvider.getUriForFile(activity.getApplicationContext(),

                                "com.example.android.fileproviderSurvey",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        activity.startActivityForResult(takePictureIntent, IMG_REQUEST_CAMERASN4);
                    }

                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activity.startActivityForResult(intent, IMG_REQUEST_PICKERSN4);
                }


            }
        });
        chooseItemDialog.show();
    }

    private File createImageFileSN4() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPathSN4 = image.getAbsolutePath();
        return image;
    }

    public void SelectPhotoSN5() {


        final String[] items = {"Camera", "Gallery"};


        chooseItemDialog = new AlertDialog.Builder(activity);
        chooseItemDialog.setTitle("Select image resource.");
        chooseItemDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File photoFile = null;
                    try {

                        photoFile = createImageFileSN5();

                    } catch (IOException ex) {

                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        outputFileUri = FileProvider.getUriForFile(activity.getApplicationContext(),

                                "com.example.android.fileproviderSurvey",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        activity.startActivityForResult(takePictureIntent, IMG_REQUEST_CAMERASN5);
                    }

                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activity.startActivityForResult(intent, IMG_REQUEST_PICKERSN5);
                }


            }
        });
        chooseItemDialog.show();
    }

    private File createImageFileSN5() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPathSN5 = image.getAbsolutePath();
        return image;
    }

    public void SelectPhotoSN6() {


        final String[] items = {"Camera", "Gallery"};


        chooseItemDialog = new AlertDialog.Builder(activity);
        chooseItemDialog.setTitle("Select image resource.");
        chooseItemDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File photoFile = null;
                    try {

                        photoFile = createImageFileSN6();

                    } catch (IOException ex) {

                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        outputFileUri = FileProvider.getUriForFile(activity.getApplicationContext(),

                                "com.example.android.fileproviderSurvey",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        activity.startActivityForResult(takePictureIntent, IMG_REQUEST_CAMERASN6);
                    }

                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activity.startActivityForResult(intent, IMG_REQUEST_PICKERSN6);
                }


            }
        });
        chooseItemDialog.show();
    }

    private File createImageFileSN6() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPathSN6 = image.getAbsolutePath();
        return image;
    }

    public void SelectPhotoSN7() {


        final String[] items = {"Camera", "Gallery"};


        chooseItemDialog = new AlertDialog.Builder(activity);
        chooseItemDialog.setTitle("Select image resource.");
        chooseItemDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File photoFile = null;
                    try {

                        photoFile = createImageFileSN7();

                    } catch (IOException ex) {

                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        outputFileUri = FileProvider.getUriForFile(activity.getApplicationContext(),

                                "com.example.android.fileproviderSurvey",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        activity.startActivityForResult(takePictureIntent, IMG_REQUEST_CAMERASN7);
                    }

                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activity.startActivityForResult(intent, IMG_REQUEST_PICKERSN7);
                }


            }
        });
        chooseItemDialog.show();
    }

    private File createImageFileSN7() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPathSN7 = image.getAbsolutePath();
        return image;
    }

    public void SelectPhotoSN8() {


        final String[] items = {"Camera", "Gallery"};


        chooseItemDialog = new AlertDialog.Builder(activity);
        chooseItemDialog.setTitle("Select image resource.");
        chooseItemDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File photoFile = null;
                    try {

                        photoFile = createImageFileSN8();

                    } catch (IOException ex) {

                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        outputFileUri = FileProvider.getUriForFile(activity.getApplicationContext(),

                                "com.example.android.fileproviderSurvey",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        activity.startActivityForResult(takePictureIntent, IMG_REQUEST_CAMERASN8);
                    }

                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activity.startActivityForResult(intent, IMG_REQUEST_PICKERSN8);
                }


            }
        });
        chooseItemDialog.show();
    }

    private File createImageFileSN8() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPathSN8 = image.getAbsolutePath();
        return image;
    }

    public void SelectPhotoSN9() {


        final String[] items = {"Camera", "Gallery"};


        chooseItemDialog = new AlertDialog.Builder(activity);
        chooseItemDialog.setTitle("Select image resource.");
        chooseItemDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File photoFile = null;
                    try {

                        photoFile = createImageFileSN9();

                    } catch (IOException ex) {

                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        outputFileUri = FileProvider.getUriForFile(activity.getApplicationContext(),

                                "com.example.android.fileproviderSurvey",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        activity.startActivityForResult(takePictureIntent, IMG_REQUEST_CAMERASN9);
                    }

                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activity.startActivityForResult(intent, IMG_REQUEST_PICKERSN9);
                }


            }
        });
        chooseItemDialog.show();
    }

    private File createImageFileSN9() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPathSN9 = image.getAbsolutePath();
        return image;
    }

    public void SelectPhotoSN10() {


        final String[] items = {"Camera", "Gallery"};


        chooseItemDialog = new AlertDialog.Builder(activity);
        chooseItemDialog.setTitle("Select image resource.");
        chooseItemDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (which == 0) {

                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File photoFile = null;
                    try {

                        photoFile = createImageFileSN10();

                    } catch (IOException ex) {

                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        outputFileUri = FileProvider.getUriForFile(activity.getApplicationContext(),

                                "com.example.android.fileproviderSurvey",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                        activity.startActivityForResult(takePictureIntent, IMG_REQUEST_CAMERASN10);
                    }

                } else {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activity.startActivityForResult(intent, IMG_REQUEST_PICKERSN10);
                }


            }
        });
        chooseItemDialog.show();
    }

    private File createImageFileSN10() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPathSN10 = image.getAbsolutePath();
        return image;
    }
}
