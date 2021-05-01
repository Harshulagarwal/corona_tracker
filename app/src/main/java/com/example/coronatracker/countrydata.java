package com.example.coronatracker;

public class countrydata {
    String name;
    String flagurl;
    long cases;
    long todaycases;
    long deaths;
    long todaydeaths;
    long recoverd;
    long active;

    countrydata(String name, String flagurl, long cases, long todaycases, long deaths, long todaydeaths, long recovered, long active) {
        this.name = name;
        this.flagurl = flagurl;
        this.cases = cases;
        this.todaycases = todaycases;
        this.deaths = deaths;
        this.todaydeaths = todaydeaths;
        this.recoverd = recovered;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlagurl() {
        return flagurl;
    }

    public void setFlagurl(String flagurl) {
        this.flagurl = flagurl;
    }

    public long getCases() {
        return cases;
    }

    public void setCases(long cases) {
        this.cases = cases;
    }

    public long getTodaycases() {
        return todaycases;
    }

    public void setTodaycases(long todaycases) {
        this.todaycases = todaycases;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getTodaydeaths() {
        return todaydeaths;
    }

    public void setTodaydeaths(long todaydeaths) {
        this.todaydeaths = todaydeaths;
    }

    public long getRecoverd() {
        return recoverd;
    }

    public void setRecoverd(long recoverd) {
        this.recoverd = recoverd;
    }

    public long getActive() {
        return active;
    }

    public void setActive(long active) {
        this.active = active;
    }
}
