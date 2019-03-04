package lk.atig.sltourguide;

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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            textViewTitle.setText(extras.getString("title"));
            textViewShortDesc.setText(extras.getString("description"));
            textViewRating.setText(String.valueOf(extras.getDouble("rate")));
            textViewPrice.setText(String.valueOf(extras.getDouble("price")));
            imageView.setImageDrawable(this.getResources().getDrawable(extras.getInt("image")));
        }
    }

}
