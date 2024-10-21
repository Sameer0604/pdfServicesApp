package com.example.servicesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Environment;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText pdf1, pdf2, pdf3, pdf4, pdf5;
    private Button downloadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize EditTexts with correct IDs
        pdf1 = findViewById(R.id.edittext_pdf1);
        pdf2 = findViewById(R.id.edittext_pdf2);
        pdf3 = findViewById(R.id.edittext_pdf3);
        pdf4 = findViewById(R.id.edittext_pdf4);
        pdf5 = findViewById(R.id.edittext_pdf5);

        // Initialize Button with correct ID
        downloadButton = findViewById(R.id.button_download);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownload(pdf1.getText().toString());
                startDownload(pdf2.getText().toString());
                startDownload(pdf3.getText().toString());
                startDownload(pdf4.getText().toString());
                startDownload(pdf5.getText().toString());
            }
        });
    }

    private void startDownload(String url) {
        if (!url.isEmpty()) {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
            request.setTitle("Downloading PDF");
            request.setDescription("Downloading file...");
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, Uri.parse(url).getLastPathSegment());

            DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            manager.enqueue(request);

            Toast.makeText(this, "Download started", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter a valid URL", Toast.LENGTH_SHORT).show();
        }
    }
}