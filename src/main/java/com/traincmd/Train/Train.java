package com.traincmd.Train;

import com.traincmd.TrainCar.FirstClassCar;
import com.traincmd.TrainCar.SecondClassCar;
import com.traincmd.TrainCar.ThirdClassCar;
import com.traincmd.TrainCar.TrainCar;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Train {
//    public ArrayList<TrainCar> cars = new ArrayList<TrainCar>();
//    private String From;
//    private String To;
//    private int Number;
    private final SimpleStringProperty From;
    private SimpleListProperty<TrainCar> cars = new SimpleListProperty<TrainCar>();
    private final SimpleStringProperty To;
    private final SimpleIntegerProperty Number;


    public Train(String From , String To , int Number) {
        this.From = new SimpleStringProperty(From);
        this.To = new SimpleStringProperty(To);
        this.Number = new SimpleIntegerProperty(Number);
        ObservableList<TrainCar> observableList = FXCollections.observableArrayList(new ArrayList<TrainCar>());
        cars = new SimpleListProperty<TrainCar>(observableList);
    }

    public void setFrom(String from) {
        From.set(from);
    }

    public String getFrom() {
        return From.get();
    }

    public String getTo() {
        return To.get();
    }

    public void setNumber(int number) {
        Number.set(number);
    }
   public void setCars(ArrayList<TrainCar> cars){
        for(TrainCar i:cars){
            this.cars.add(i);
        }

   }

    public int getNumber() {
        return Number.get();
    }


    public TrainCar getCar(int Number) {
        for(int i = 0; i < this.cars.size(); i++) {
            if(this.cars.get(i).getNumber() == Number) {
                return this.cars.get(i);
            }
        }
        System.out.println("No such car");
        return null;
    }

    public void showCars(){
        for(int i = 0; i < this.cars.size(); i++) {
            this.cars.get(i).showInfo();
        }
    }

    public ArrayList<TrainCar> showCarsByPassangersRange(int min, int max) {
        ArrayList<TrainCar> temp = new ArrayList<TrainCar>();
        for(int i = 0; i < this.cars.size(); i++) {
            if(this.cars.get(i).passengers.size() >= min && this.cars.get(i).passengers.size() <= max) {
                temp.add(this.cars.get(i));
            }
        }
        return temp;
    }
    

    public void showShortInfo() {
        System.out.println("№" + this.Number + "\t" + this.From + " - " + this.To);
    }
    public void showInfo() {
        System.out.println("№" + this.Number + "\t" + this.From + " - " + this.To + "\nCars available: " + this.cars.size());
        showSummary();
    }

    public void addCar(int confClass, int Number, String ConductorName) {
        for(int i = 0; i < this.cars.size(); i++) {
            if(this.cars.get(i).getNumber() == Number) {
                System.out.println("Car with this number already exists");
                return;
            }
        }
        if(confClass == 1) {
            this.cars.add(new FirstClassCar(Number, ConductorName));
        } else if(confClass == 2) {
            this.cars.add(new SecondClassCar(Number, ConductorName));
        } else if(confClass == 3) {
            this.cars.add(new ThirdClassCar(Number, ConductorName));
        }
    }

    public void removeCar(int Number) {
        this.cars.remove(this.getCar(Number));
    }

    public String getSeats(){
        int maxPassengers = 0, totalPassengers=0;
        for(int i = 0; i < this.cars.size(); i++) {
            maxPassengers += this.cars.get(i).MaxPassengers;
            totalPassengers += this.cars.get(i).passengers.size();
        }
        return totalPassengers+"/"+maxPassengers;
    }

    public double getLuggage(){
        double totalLuggage = 0;
        for(int i = 0; i < this.cars.size(); i++) {
            totalLuggage += this.cars.get(i).getTotalLuggage();
        }
        return totalLuggage;
    }

    public void sortCars(){
        for(int i = 0; i < this.cars.size(); i++) {
            for(int j = 0; j < this.cars.size(); j++) {
                if(this.cars.get(i).MaxPassengers < this.cars.get(j).MaxPassengers ||  (this.cars.get(i).MaxPassengers == this.cars.get(j).MaxPassengers && this.cars.get(i).passengers.size() < this.cars.get(j).passengers.size())) {
                    TrainCar temp = this.cars.get(i);
                    this.cars.set(i, this.cars.get(j));
                    this.cars.set(j, temp);
                }
            }
        }
    }


    public void showSummary() {
        double totalLuggage = 0;
        int totalPassengers = 0;
        int maxPassengers = 0;
        for(int i = 0; i < this.cars.size(); i++) {
            maxPassengers += this.cars.get(i).MaxPassengers;
            totalLuggage += this.cars.get(i).getTotalLuggage();
            totalPassengers += this.cars.get(i).passengers.size();
        }
        System.out.println("Total passengers: " + totalPassengers+"/"+maxPassengers);
        System.out.println("Total luggage: " + totalLuggage+"kg");
    }

    public SimpleListProperty<TrainCar> getCars(){
        return this.cars;
    }

    public void setTo(String to) {
        To.set(to);
    }
}
