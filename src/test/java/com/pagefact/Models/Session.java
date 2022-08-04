package com.pagefact.Models;

import java.util.ArrayList;

public class Session{
    public String session_id;
    public String date;
    public int available_capacity;
    public int min_age_limit;
    public int max_age_limit;
    public boolean allow_all_age;
    public String vaccine;
    public ArrayList<Slot> slots;
    public int available_capacity_dose1;
    public int available_capacity_dose2;
}
