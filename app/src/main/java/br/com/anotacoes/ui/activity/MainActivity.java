package br.com.anotacoes.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import br.com.anotacoes.R;
import br.com.anotacoes.asynctask.GetAllAnnotationTask;
import br.com.anotacoes.asynctask.RemoveSheetTask;
import br.com.anotacoes.database.SheetDatabase;
import br.com.anotacoes.database.dao.SheetDAO;
import br.com.anotacoes.model.Sheet;
import br.com.anotacoes.ui.adapter.AnnotationAdapter;
import br.com.anotacoes.ui.listener.AnnotationListener;

import static br.com.anotacoes.ui.activity.Constants.SHEET_KEY;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AnnotationListener listener;
    private AnnotationAdapter adapter;
    private SheetDAO dao;
    private List<Sheet> sheetList = new ArrayList<Sheet>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SheetDatabase database = SheetDatabase.getInstance(this);
        dao = database.getSheetDatabase();
        recyclerView = findViewById(R.id.id_recyclerView);
        getListener();
        getForm();


    }

    private void getForm() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FormActivity.class));


            }
        });
    }

    private void openEditFormMode(Sheet sheet){
        Intent intent = new Intent(MainActivity.this, FormActivity.class);
        intent.putExtra(SHEET_KEY,sheet);
        startActivity(intent);
    }


    private void getListener() {
        listener = new AnnotationListener() {
            @Override
            public void onEditClick(Sheet sheet) {

                openEditFormMode(sheet);
            }

            @Override
            public void onDeletelick(Sheet sheet) {
                new RemoveSheetTask(dao, adapter, sheet).execute();
            }
        };
    }


    private void getList() {
        adapter = new AnnotationAdapter(sheetList, listener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new GetAllAnnotationTask(dao, adapter).execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getList();
    }
}
