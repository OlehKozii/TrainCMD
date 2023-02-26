package com.traincmd.Command;

import com.traincmd.Train.Train;

public class addCarCommand implements Command {
    private Train train;
    private int confClass;
    private int Number;
    private String ConductorName;


    public addCarCommand(Train train , int confClass , int Number , String ConductorName) {
        this.train = train;
        this.confClass = confClass;
        this.Number = Number;
        this.ConductorName = ConductorName;
    }

    public void execute() {
        this.train.addCar(confClass , Number , ConductorName);
    }
}
