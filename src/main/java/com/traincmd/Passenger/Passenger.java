package com.traincmd.Passenger;

public class Passenger {
    private String Name;
    private double LuggageKg;
    private int Seat;
    public Passenger(String Name , double LuggageKg , int Seat){
        this.LuggageKg=LuggageKg;
        this.Name=Name;
        this.Seat=Seat;
    }

    public String getName() {
        return Name;
    }

    public String toString() {
        String str = this.Name + "\t" + this.LuggageKg + "kg\t" + this.Seat;
        return str;
    }

    public int getSeat() {
        return this.Seat;
    }


    public double getLuggageKg() {
        return this.LuggageKg;
    }
}
