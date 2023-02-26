package com.traincmd.Command;

import com.traincmd.TrainCar.TrainCar;

public class showCarInfoCommand implements Command {
    private TrainCar car;

    public showCarInfoCommand(TrainCar car) {
        this.car = car;
    }

    public void execute() {
        car.showInfo();
    }
}
