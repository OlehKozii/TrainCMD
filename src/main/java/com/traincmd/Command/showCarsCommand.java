package com.traincmd.Command;

import com.traincmd.Train.Train;

public class showCarsCommand implements Command {
    private Train train;

    public showCarsCommand(Train train) {
        this.train = train;
    }

    public void execute() {
        train.showCars();
    }
}
