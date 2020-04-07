/*******************************************************************************
 * Copyright (C) 2020 김동우 Dongwoo Kim (https://github.com/superjoy0502)
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 ******************************************************************************/

package com.github.superjoy0502.covid19canadatracker.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.superjoy0502.covid19canadatracker.R;
import com.github.superjoy0502.covid19canadatracker.model.COVID19VirusData;

import java.util.ArrayList;

public class VirusAdapter extends RecyclerView.Adapter<VirusAdapter.ViewHolder> {
    private ArrayList<COVID19VirusData> body;

    public VirusAdapter(ArrayList<COVID19VirusData> body) {
        this.body = body;
    }

    @NonNull
    @Override
    public VirusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_updates, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VirusAdapter.ViewHolder holder, int position) {
        holder.tLocation.setText(body.get(position).getPlace() + "");
        holder.tNumber.setText("Confirmed: " + body.get(position).getNumber() + "");
        holder.tDeaths.setText("Deaths: " + body.get(position).getDeaths() + "");
        holder.tCured.setText("Recovered: " + body.get(position).getCured() + "");
    }

    @Override
    public int getItemCount() {
        return body.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tLocation;
        public TextView tNumber;
        public TextView tDeaths;
        public TextView tCured;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tLocation = itemView.findViewById(R.id.location_text);
            tNumber = itemView.findViewById(R.id.number_text);
            tDeaths = itemView.findViewById(R.id.death_text);
            tCured = itemView.findViewById(R.id.cured_text);
        }
    }
}