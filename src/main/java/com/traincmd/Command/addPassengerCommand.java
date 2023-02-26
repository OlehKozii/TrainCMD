package com.traincmd.Command;

import com.traincmd.TrainCar.TrainCar;

public class addPassengerCommand implements Command {
    private TrainCar car;
    private String Name;
    private double LuggageKg;
    private int Seat;

    public addPassengerCommand(TrainCar car , String Name , double LuggageKg , int Seat) {
        this.car = car;
        this.Name = Name;
        this.LuggageKg = LuggageKg;
        this.Seat = Seat;
    }

    public void execute() {
        car.addPassenger(Name , LuggageKg , Seat);
    }
}
