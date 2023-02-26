package com.traincmd.Command;

import com.traincmd.Train.Train;

public class showCarsByPassangersRangeCommand implements Command {
    private Train train;
    private int min;
    private int max;

    public showCarsByPassangersRangeCommand(Train train , int min , int max) {
        this.train = train;
        this.min = min;
        this.max = max;
    }

    public void execute() {
        train.showCarsByPassangersRange(min , max);
    }
}
