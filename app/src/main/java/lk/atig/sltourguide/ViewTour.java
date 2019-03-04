package lk.atig.sltourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewTour extends AppCompatActivity {

    TextView textViewTitle;
    TextView textViewShortDesc;
    TextView textViewRating;
    TextView textViewPrice;
    ImageView imageView;
    int id;
    int imageSrc;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tour);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewShortDesc = findViewById(R.id.textViewShortDesc);
        textViewRating = findViewById(R.id.textViewRating);
        textViewPrice = findViewById(R.id.textViewPrice);
        imageView = findViewById(R.id.imageView);

        db = new DatabaseHelper(this);

        //Binding clicked tour details in viewTour screen.
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            imageSrc = extras.getInt(Tour.COLUMN_IMAGE);
            textViewTitle.setText(extras.getString(Tour.COLUMN_TITLE));
            textViewShortDesc.setText(extras.getString(Tour.COLUMN_SHORT_DESCRIPTION));
            textViewRating.setText(String.valueOf(extras.getDouble(Tour.COLUMN_RATING)));
            textViewPrice.setText(String.valueOf(extras.getDouble(Tour.COLUMN_PRICE)));
            imageView.setImageDrawable(this.getResources().getDrawable(extras.getInt(Tour.COLUMN_IMAGE)));
            id = extras.getInt(Tour.COLUMN_ID);
        }


        //This is for deleting tour from the database.
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Deleted One Tour", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Tour tour = new Tour
                        (
                                id,
                                textViewTitle.getText().toString(),
                                textViewShortDesc.getText().toString(),
                                Double.parseDouble(textViewRating.getText().toString()),
                                Double.parseDouble(textViewPrice.getText().toString()),
                                imageSrc
                        );

                db.deleteTour(tour);

                //Once deleted the tour, app will navigate to the main screen.
                Intent intent = new Intent(ViewTour.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
