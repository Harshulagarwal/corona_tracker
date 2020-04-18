package com.example.coronatracker;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;

public class appdata {
    static int layout_type = 0;
    static int listsize = 0;
    static HashMap<String,Bitmap> b = new HashMap<>();
    static int curr_pos = 0,flag=0,prevflag=0;
    static ArrayList<countrydata> locallist = new ArrayList<>();
    static ArrayList<countrydata> locallist_listview = new ArrayList<>();
}
