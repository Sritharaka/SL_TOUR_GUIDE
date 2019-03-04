package lk.atig.sltourguide;

import android.content.Context;
import android.content.Intent;
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
    private TourItemClickListener listener;

    public ToursAdapter(Context mtcx, List<Tour> tourList, TourItemClickListener listener) {
        this.mtcx = mtcx;
        this.tourList = tourList;
        this.listener = listener;
    }


    @Override
    public ToursViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mtcx);
        View view = inflater.inflate(R.layout.activity_tours, null);
        return new ToursViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(ToursViewHolder toursViewHolder, int i) {

        Tour tour = tourList.get(i);
        toursViewHolder.id = tour.getId();
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

    class ToursViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        int id;
        ImageView imageView;
        TextView textViewTitle;
        TextView textViewDesc;
        TextView textViewRating;
        TextView textViewPrice;
        private TourItemClickListener listener;

        public ToursViewHolder(View itemView, TourItemClickListener listener) {

            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            this.listener = listener;
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view instanceof ImageView) {
                listener.OnTourClicked(this.getAdapterPosition());
            }
        }
    }

}
