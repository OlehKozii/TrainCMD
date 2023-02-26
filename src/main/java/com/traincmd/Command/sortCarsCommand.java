package com.traincmd.Command;

import com.traincmd.Train.Train;

public class sortCarsCommand implements Command {
    private Train train;

    public sortCarsCommand(Train train) {
        this.train = train;
    }

    public void execute() {
        train.sortCars();
    }
}
