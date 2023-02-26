package com.traincmd.Command;

import com.traincmd.Train.Train;

public class removeCarCommand implements Command {
    private Train train;
    private int Number;

    public removeCarCommand(Train train , int Number) {
        this.train = train;
        this.Number = Number;
    }

    public void execute() {
        train.removeCar(Number);
    }
}
