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
package com.github.superjoy0502.covid19canadatracker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class COVID19VirusData {

    @SerializedName("cured")
    @Expose
    private Integer cured;
    @SerializedName("deaths")
    @Expose
    private Integer deaths;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("place")
    @Expose
    private String place;

    public Integer getCured() {
        return cured;
    }

    public void setCured(Integer cured) {
        this.cured = cured;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}