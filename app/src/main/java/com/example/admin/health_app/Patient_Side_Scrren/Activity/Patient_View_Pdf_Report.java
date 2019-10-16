//package com.example.admin.health_app.Patient_Side_Scrren.Activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Environment;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.ImageView;
//
//import com.example.admin.health_app.R;
//import com.example.admin.health_app.VolleyApiHit.DownloadPdf_file;
//import com.github.barteksc.pdfviewer.PDFView;
//import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
//import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
//import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
//import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//public class Patient_View_Pdf_Report extends AppCompatActivity implements OnPageChangeListener, OnLoadCompleteListener,
//        OnPageErrorListener {
//
//    private String url;
//    private PDFView pdfView;
//    private ImageView cancelIV;
//    private Integer pageNumber = 0;
//    private File File_download;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pdf_viewer);
//        cancelIV = (ImageView) findViewById(R.id.cancelIV);
//        pdfView = (PDFView) findViewById(R.id.pdfView);
//        Bundle bundle=getIntent().getExtras();
//        if(bundle!=null){
//            File_download= new File(bundle.getString("file"));
//            pdfView.fromFile(File_download)
//                    .defaultPage(pageNumber)
//                    .onPageChange(this)
//                    .enableAnnotationRendering(true)
//                    .onLoad(this)
//                    .scrollHandle(new DefaultScrollHandle(this))
//                    .spacing(10) // in dp
//                    .onPageError(this)
//                    .password("admin123")
//
//                    .load();
//
//        }
//        cancelIV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Patient_View_Pdf_Report.super.finish();
//            }
//        });
//
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        onBackPressed();
//        return super.onOptionsItemSelected(item);
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//    }
//
//    @Override
//    public void loadComplete(int nbPages) {
//
//    }
//
//    @Override
//    public void onPageChanged(int page, int pageCount) {
//        pageNumber = page;
//    }
//
//    @Override
//    public void onPageError(int page, Throwable t) {
//
//    }
//}
//
//
