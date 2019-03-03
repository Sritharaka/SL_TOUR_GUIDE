package lk.atig.sltourguide;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ToursAdapter adapter;

    List<Tour> tourList;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        db = new DatabaseHelper(this);

        tourList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addSampleTours();
        tourList.addAll(db.getAllTours());

        //creating recyclerview adapter
        adapter = new ToursAdapter(this, tourList, new TourItemClickListener() {
            @Override
            public void OnTourClicked(int id) {
                Intent intent = new Intent(MainActivity.this, ViewTour.class);
                intent.putExtra(Tour.COLUMN_ID, id);
                startActivity(intent);
            }
        });

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }

    private void addSampleTours() {
        //adding some items to our list
        if (db.getTourCount() <= 0) {
            db.insertTour(
                    new Tour(
                            1,
                            "Apple MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra)",
                            "13.3 inch, Silver, 1.35 kg",
                            4.3,
                            70000,
                            R.drawable.yala));

            db.insertTour(
                    new Tour(
                            1,
                            "Dell Inspiron 7000 Core i5 7th Gen - (8 GB/1 TB HDD/Windows 10 Home)",
                            "14 inch, Gray, 1.659 kg",
                            4.5,
                            80000,
                            R.drawable.kandy));

            db.insertTour(
                    new Tour(
                            1,
                            "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                            "13.3 inch, Silver, 1.35 kg",
                            3.3,
                            10000,
                            R.drawable.singha));
        }
    }
}



