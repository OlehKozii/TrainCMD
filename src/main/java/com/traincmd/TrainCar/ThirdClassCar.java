package com.traincmd.TrainCar;

public class ThirdClassCar extends TrainCar {


    public ThirdClassCar(int Number , String ConductorName) {
        super(Number , ConductorName);
        this.CarClass = 3;
        this.MaxPassengers = 48;
    }
}
