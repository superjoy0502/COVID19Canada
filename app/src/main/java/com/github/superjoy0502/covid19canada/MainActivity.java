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

package com.github.superjoy0502.covid19canada;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.github.superjoy052.covid19canada.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.github.superjoy052.covid19canada.adapter.NewsAdapter;
import com.github.superjoy052.covid19canada.adapter.VirusAdapter;
import com.github.superjoy052.covid19canada.model.COVID19NewsData;
import com.github.superjoy052.covid19canada.model.COVID19VirusData;
import com.github.superjoy052.covid19canada.network.GetNewsDataService;
import com.github.superjoy052.covid19canada.network.GetVirusDataService;
import com.github.superjoy052.covid19canada.network.NewsRetrofitClientInstance;
import com.github.superjoy052.covid19canada.network.VirusRetrofitClientInstance;
import com.github.superjoy052.covid19canada.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "App";
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUp(savedInstanceState);
        requestData(findViewById(R.id.tabs));
    }

    private void requestData(View view) {
        Snackbar.make(view, "Fetching Data from server...", Snackbar.LENGTH_INDEFINITE)
                .setAction("Action", null).show();
        requestVirusData(view);
        requestNewsData(view);
    }

    private void requestNewsData(View view) {
        GetNewsDataService service = NewsRetrofitClientInstance.getRetrofitInstance().create(GetNewsDataService.class);
        Call<ArrayList<COVID19NewsData>> call = service.getAllNews();
        call.enqueue(new Callback<ArrayList<COVID19NewsData>>() {
            @Override
            public void onResponse(Call<ArrayList<COVID19NewsData>> call, Response<ArrayList<COVID19NewsData>> response) {
                if (response.isSuccessful()) {
                    Snackbar.make(view, "Fetched News Data", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    setupNewsView(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<COVID19NewsData>> call, Throwable t) {
                if (t instanceof java.net.UnknownHostException) {
                    Snackbar.make(view, "Failure with news data :(\nMaybe checking you network connect would help?", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(view, "Failure with news data :(\n" + t.toString(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    private void requestVirusData(View view) {
        GetVirusDataService service = VirusRetrofitClientInstance.getRetrofitInstance().create(GetVirusDataService.class);
        Call<ArrayList<COVID19VirusData>> call = service.getAllCases();
        call.enqueue(new Callback<ArrayList<COVID19VirusData>>() {
            @Override
            public void onResponse(Call<ArrayList<COVID19VirusData>> call, Response<ArrayList<COVID19VirusData>> response) {
                if (response.isSuccessful()) {
                    Snackbar.make(view, "Fetched Live Updates", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    setupVirusView(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<COVID19VirusData>> call, Throwable t) {
                if (t instanceof java.net.UnknownHostException) {
                    Snackbar.make(view, "Failure with live data :(\nMaybe checking you network connect would help?", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Snackbar.make(view, "Failure with live data :(\n" + t.toString(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    private void setupVirusView(ArrayList<COVID19VirusData> body) {
        recyclerView = findViewById(R.id.updates_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new VirusAdapter(body);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setupNewsView(ArrayList<COVID19NewsData> body) {
        recyclerView = findViewById(R.id.news_recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new NewsAdapter(this, body);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setUp(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.setOffscreenPageLimit(10);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestData(view);
            }
        });
        Log.d("MainActivity", "onCreate: Log TEST");
    }
}