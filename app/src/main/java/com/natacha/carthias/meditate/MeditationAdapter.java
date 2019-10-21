package com.natacha.carthias.meditate;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by Natacha Carthias.
 */

public class MeditationAdapter extends RecyclerView.Adapter<MeditationAdapter.MeditationViewHolder> {

    ArrayList<Meditation> meditationList;
    Context context;

    // Initialize data list with inflater
    private LayoutInflater mInflater;
    public MeditationAdapter(Context context, ArrayList<Meditation> meditationList) {
        mInflater = LayoutInflater.from(context);
        this.meditationList = meditationList;
        this.context = context;
    }
    // View Holder inner class
    class MeditationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView meditationTextView;
        public ImageView meditationImageView;
        final MeditationAdapter mAdapter;

        // Constructors to initialize ViewHolder, reference views and set adapter
        public MeditationViewHolder (View itemView, MeditationAdapter adapter) {
            super(itemView);

            meditationTextView = itemView.findViewById(R.id.tv_meditationlist);
            meditationImageView = itemView.findViewById(R.id.img_meditationlist);

            this.mAdapter = adapter;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

//            int position = getAdapterPosition();
//            Meditation selectedMeditation = meditationList.get(position);

            Intent intent = new Intent(view.getContext(), MeditateActivity.class);
            intent.putExtra("Meditation", getAdapterPosition());
            view.getContext().startActivity(intent);

//            view.getContext().startActivity(new Intent(view.getContext(), MeditateActivity.class));

            mAdapter.notifyDataSetChanged();
        }
    }

    // Inflate RecyclerView with the XML row layout populated with data
    @Override
    public MeditationAdapter.MeditationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View mItemView = mInflater.inflate(R.layout.list_item, parent, false);
        return new MeditationViewHolder(mItemView, this);
    }

    // Bind the data to the views
    @Override
    public void onBindViewHolder(@NonNull MeditationAdapter.MeditationViewHolder holder, int position) {

        // Locate current meditation selected
        final Meditation meditation=meditationList.get(position);

        //Bind data from class to views in xml
        holder.meditationTextView.setText(meditation.getMeditation());
        holder.meditationImageView.setImageResource(meditation.getImage());

        holder.meditationImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, MeditateActivity.class);
                intent.putExtra("Meditation", meditation.getMeditation());
                intent.putExtra("Image", meditation.getImage());
                context.startActivity(intent);


            }
        });

    }


    @Override
    public int getItemCount() {
        return meditationList.size();
    }
}
