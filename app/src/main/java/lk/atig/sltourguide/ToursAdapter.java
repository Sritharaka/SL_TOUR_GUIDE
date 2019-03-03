package lk.atig.sltourguide;
/*
  Recycleview.Adaper
  Recycleview.viewHolder

  */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ToursAdapter extends RecyclerView.Adapter<ToursAdapter.ToursViewHolder> {


     private Context mtcx;
    private List<Tour> tourList;

    public ToursAdapter(Context mtcx, List<Tour> tourList) {
        this.mtcx = mtcx;
        this.tourList = tourList;
    }


    @Override
    public ToursViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mtcx);
        View view = inflater.inflate(R.layout.activity_tours, null);
        return new ToursViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ToursViewHolder toursViewHolder, int i) {

        Tour tour = tourList.get(i);

        toursViewHolder.textViewTitle.setText(tour.getTitle());
        toursViewHolder.textViewDesc.setText(tour.getShortdesc());
        toursViewHolder.textViewRating.setText(String.valueOf(tour.getRating()));
        toursViewHolder.textViewPrice.setText(String.valueOf(tour.getPrice()));

        toursViewHolder.imageView.setImageDrawable(mtcx.getResources().getDrawable(tour.getImage()));


    }

    @Override
    public int getItemCount() {
        return tourList.size();
    }

    class ToursViewHolder extends RecyclerView.ViewHolder {

       ImageView imageView;
       TextView textViewTitle, textViewDesc, textViewRating, textViewPrice;

        public ToursViewHolder(View itemView) {

            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);

        }
    }

}
