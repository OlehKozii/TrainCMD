package com.traincmd.TrainCar;

public class FirstClassCar extends TrainCar {

    public FirstClassCar(int Number , String ConductorName) {
        super(Number , ConductorName);
        this.CarClass = 1;
        this.MaxPassengers = 24;
    }
}
