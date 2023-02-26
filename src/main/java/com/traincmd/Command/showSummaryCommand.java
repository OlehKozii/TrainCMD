package com.traincmd.Command;

import com.traincmd.Train.Train;
import com.traincmd.TrainCar.TrainCar;

public class showSummaryCommand implements Command {
    private Train train;
    private TrainCar car;

    public showSummaryCommand(Train train) {
        this.train = train;
    }

    public void execute() {
        train.showInfo();
    }
}
