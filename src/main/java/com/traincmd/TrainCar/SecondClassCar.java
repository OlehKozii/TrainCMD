package com.traincmd.TrainCar;

public class SecondClassCar extends TrainCar {


    public SecondClassCar(int Number , String ConductorName) {
        super(Number , ConductorName);
        this.CarClass = 2;
        this.MaxPassengers = 36;
    }
}
