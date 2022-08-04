package com.pagefact.Models;
import com.fasterxml.jackson.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Center{
    public int center_id;
    public String name;
    public String address;
    public String state_name;
    public String district_name;
    public String block_name;
    public int pincode;
    public int lat;
    @JsonProperty("long")
    public int mylong;
    public String from;
    @JsonProperty("to")
    public String myto;
    public String fee_type;
    public ArrayList<Session> sessions;

    public Center()
    {

    }
    public Center(int center_id,String name,String address,String state_name,String district_name,String block_name,int pincode,int lat,int mylong,String from,String myto,String fee_type,ArrayList<Session> sessions)
    {
        this.center_id=center_id;
        this.name=name;
        this.address=address;
        this.state_name=state_name;
        this.district_name=district_name;
        this.block_name=block_name;
        this.pincode=pincode;
        this.lat=lat;
        this.mylong=mylong;
        this.from=from;
        this.myto=myto;
        this.fee_type=fee_type;
        this.sessions=sessions;
    }
}
