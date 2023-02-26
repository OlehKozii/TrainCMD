package com.traincmd.Command;

import com.traincmd.TrainCar.TrainCar;

public class removePassengerCommand implements Command {
    private TrainCar car;
    private int Seat;

    public removePassengerCommand(TrainCar car , int Seat) {
        this.car = car;
        this.Seat = Seat;
    }

    public void execute() {
        car.removePassenger(Seat);
    }
}
