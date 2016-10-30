package com.example.car.application;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by natalya blanco on 23/09/2016.
 * natalyablanco@gmail.com
 * <p>
 * RecyclerView provides a flexible view for large data set.
 * This custom Adapter is responsible of setting views that represent items in a data set.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PlacemarkViewHolder> {

    //List of placemarks to show in the view
    List<Placemark> placemarks;

    //constructor
    RVAdapter(List<Placemark> placemarks) {
        this.placemarks = placemarks;
    }

    /**
     * Method called when the ViewHolder must be initialized
     *
     * @param parent
     * @param viewType
     * @return PlacemarkViewHolder
     */
    @Override
    public PlacemarkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,
                parent, false);
        PlacemarkViewHolder nvh = new PlacemarkViewHolder(v);

        return nvh;
    }

    /**
     * @param holder
     * @param i
     * @desc specify the contents of each item of the RecyclerView
     */
    @Override
    public void onBindViewHolder(PlacemarkViewHolder holder, int i) {
        holder.address.setText(placemarks.get(i).getAddress());
        holder.coordinates.setText(placemarks.get(i).getCoordinates().toString());
        holder.engineType.setText(placemarks.get(i).getEngineType());
        holder.exterior.setText(placemarks.get(i).getExterior());
        holder.fuel.setText(String.format("%1$d", placemarks.get(i).getFuel()));
        holder.interior.setText(placemarks.get(i).getInterior());
        holder.name.setText(placemarks.get(i).getName());
        holder.vin.setText(placemarks.get(i).getVin());

    }

    /**
     * @return size of the placemarks list.
     */
    @Override
    public int getItemCount() {
        return placemarks.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    //class to hold your exact set of views.
    public static class PlacemarkViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView address;
        TextView coordinates;
        TextView engineType;
        TextView exterior;
        TextView fuel;
        TextView interior;
        TextView name;
        TextView vin;

        //populate the ViewHolder and store it inside the layout.
        PlacemarkViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cardView);
            address = (TextView) itemView.findViewById(R.id.address);
            coordinates = (TextView) itemView.findViewById(R.id.coordinates);
            engineType = (TextView) itemView.findViewById(R.id.engine_type);
            exterior = (TextView) itemView.findViewById(R.id.exterior);
            fuel = (TextView) itemView.findViewById(R.id.fuel);
            interior = (TextView) itemView.findViewById(R.id.interior);
            name = (TextView) itemView.findViewById(R.id.name);
            vin = (TextView) itemView.findViewById(R.id.vin);

        }
    }

}