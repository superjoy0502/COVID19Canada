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

package com.kimdongwoo.covid19canada.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kimdongwoo.covid19canada.R;
import com.kimdongwoo.covid19canada.model.COVID19NewsData;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private ArrayList<COVID19NewsData> body;
    private Context context;
    private ViewHolder viewHolder;

    public NewsAdapter(Context context, ArrayList<COVID19NewsData> body) {
        this.context = context;
        this.body = body;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        viewHolder = new ViewHolder(view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        holder.tTitle.setText(body.get(position).getTitle() + "");
        viewHolder.item_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = body.get(position).getLink();
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return body.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tTitle;
        private LinearLayout item_news;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tTitle = itemView.findViewById(R.id.title_text);
            item_news = itemView.findViewById(R.id.item_news);
        }
    }
}