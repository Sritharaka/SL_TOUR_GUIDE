package lk.atig.sltourguide;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class AddTour extends AppCompatActivity {

    private DatabaseHelper db;
    EditText textViewTitle;
    EditText textViewShortDesc;
    EditText textViewRating;
    EditText textViewPrice;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tour);

        Toolbar toolbar = findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewShortDesc = findViewById(R.id.textViewShortDesc);
        textViewRating = findViewById(R.id.textViewRating);
        textViewPrice = findViewById(R.id.textViewPrice);


        db = new DatabaseHelper(this);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Added One Tour", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                db.insertTour(
                        new Tour(
                                db.getTourCount() + 1,
                                textViewTitle.getText().toString(),
                                textViewShortDesc.getText().toString(),
                                Double.parseDouble(textViewRating.getText().toString()),
                                Double.parseDouble(textViewPrice.getText().toString()),
                                R.drawable.yala));

                Intent intent = new Intent(AddTour.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
