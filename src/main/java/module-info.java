module com.traincmd {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;

    requires com.google.common;
    requires com.google.gson;
    requires org.mongodb.bson;
    opens com.traincmd.Train to javafx.fxml;
    opens com.traincmd.TrainCar to javafx.fxml;
    opens com.traincmd.Passenger to javafx.fxml;

    exports com.traincmd.Train;
    exports com.traincmd.TrainCar;
    exports com.traincmd.Passenger;

    opens com.traincmd to javafx.fxml;
    exports com.traincmd;
}