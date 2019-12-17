package com.not_a_name.helloworld;

import java.io.Serializable;

public class Bike  implements Serializable {
    public String name;
    public double speed;
    public Bike(){
        this.name = "default";
        speed =0.0;
    }
    public void upSpeed(){
        speed = (speed<1)?1:speed*1.2;
    }
    public void setName(String name){ this.name = name;}

}
