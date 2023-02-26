package com.traincmd;

import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.traincmd.Command.*;
import com.traincmd.Passenger.Passenger;
import com.traincmd.Train.Train;
import com.traincmd.TrainCar.FirstClassCar;
import com.traincmd.TrainCar.SecondClassCar;
import com.traincmd.TrainCar.ThirdClassCar;
import com.traincmd.TrainCar.TrainCar;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.*;


public class Controller implements Initializable {

    private String url = "mongodb+srv://root:root@cluster0.qzllaja.mongodb.net/?retryWrites=true&w=majority";
    private String Number;

    private user command = new user();


    @FXML
    private TableView<TrainCar> carTable;
    @FXML
    private TableColumn<TrainCar, Integer> cclasscol;

    @FXML
    private TableColumn<TrainCar, String> cconductorcol;

    @FXML
    private TableColumn<TrainCar, Integer> cpassengerscol;

    @FXML
    private TableColumn<TrainCar, Integer> cnumcol;





    @FXML
    private Button delTrainbtn;

    @FXML
    private AnchorPane mainMenu;
    @FXML
    private AnchorPane trainInfo;

    @FXML
    private AnchorPane addPass;


    @FXML
    private AnchorPane trainMenu;
    @FXML
    private Label carsLabel;

    @FXML
    private Label fromtoLabel;

    @FXML
    private Label numLabel;


    @FXML
    private Label seatsLabel;

    @FXML
    private TableColumn<Train, Integer> tcarsCol;

    @FXML
    private TableColumn<Train, String> tfromCol;

    @FXML
    private TableColumn<Train, Integer> tnumcol;

    @FXML
    private TableColumn<Train, Integer>  tpasCol;

    @FXML
    private TableColumn<Train, String> ttoCol;

    @FXML
    private  TableView<Train> trainTable;
    @FXML
    private TextField toField;

    @FXML
    private TextField numField;

    @FXML
    private TextField fromField;
    @FXML
    private AnchorPane addTrain;

    @FXML
    private AnchorPane addCar;
    @FXML
    private AnchorPane passMenu;


    @FXML
    private TextField carclassField;

    @FXML
    private TextField carcondField;

    @FXML
    private TextField carnumField;

    @FXML
    private TextField maxPass;

    @FXML
    private TextField minPass;
    @FXML
    private Label luggageLabel;
    @FXML
    private TableView<Passenger> passTable;

    @FXML
    private TableColumn<Passenger, Double> pluggcol;

    @FXML
    private TableColumn<Passenger, String> pnamecol;

    @FXML
    private TableColumn<Passenger, Integer> pseatcol;
    @FXML
    private TextField passNameField;

    @FXML
    private TextField passSeatField;
    @FXML
    private TextField passLugField;




    ArrayList<Train> trains = new ArrayList<Train>();
    public ObservableList<Train> list;

    private int pos=0;
    Train selected;
    TrainCar selectedCar;


    //  create a mongodb connection
    MongoClient mongoClient = MongoClients.create(url);

    //  create a database name
    MongoDatabase mongoDatabase = mongoClient.getDatabase("Kursach");

    //  create a collection
    MongoCollection<Document> coll = mongoDatabase.getCollection("Train");
    //  call the find all method
    MongoCursor<Document> cursor = coll.find().iterator();




    @Override
    public void initialize(URL location, ResourceBundle resources){
        passSeatField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    numField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        numField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    numField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        carnumField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    numField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        carclassField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    numField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        minPass.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    numField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        maxPass.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    numField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        trainTable.setEditable(true);
        updateData();
        setTrainTable();
    }


    @FXML
    void onfilterCarbtnClicked(ActionEvent event) {
        if(Objects.equals(minPass.getText() , "") || Objects.equals(maxPass.getText() , "")){
            return;
        }
        carTable.getItems().clear();
        carTable.getItems().addAll(selected.showCarsByPassangersRange(Integer.parseInt(minPass.getText()), Integer.parseInt(maxPass.getText())));

    }

    @FXML
    void onresCarbtnClick(ActionEvent event) {
        setCarTable(selected);
    }

    @FXML
    void submitTrainbtnClicked(ActionEvent event) {
        if(Objects.equals(numField.getText() , "") || Objects.equals(fromField.getText() , "") || Objects.equals(toField.getText() , "")){
            return;
        }
        for(Train i:trains){
            if(i.getNumber()==Integer.parseInt(numField.getText())){
                return;
            }
        }
        Train newTrain = new Train(fromField.getText(), toField.getText(), Integer.parseInt(numField.getText()));
        trains.add(newTrain);
        setTrainTable();
        fromField.clear();
        toField.clear();
        numField.clear();
        addTrain.setVisible(false);
        mainMenu.setVisible(true);
        InsertOneResult result = coll.insertOne(new Document()
                .append("Number", newTrain.getNumber())
                .append("From", newTrain.getFrom())
                .append("To", newTrain.getTo())
                .append("cars", newTrain.getCars())
        );
        updateData();
    }

    @FXML
    void onbtnRmCarClicked(ActionEvent event){
        pos=carTable.getSelectionModel().getSelectedIndex();
        command.setCommand(new removeCarCommand(selected, carTable.getItems().get(pos).getNumber()));
        command.executeCommand();
        setCarTable(selected);
    }

    @FXML
    void submitCarbtnClicked(ActionEvent event) {
        if(Objects.equals(carnumField.getText() , "") || Objects.equals(carclassField.getText() , "") || Objects.equals(carcondField.getText() , "")){
            return;
        }
        command.setCommand(new addCarCommand(selected, Integer.parseInt(carclassField.getText()), Integer.parseInt(carnumField.getText()), carcondField.getText()));
        command.executeCommand();
        setCarTable(selected);
        carnumField.clear();
        carcondField.clear();
        carclassField.clear();
        carsLabel.setText("Всього вагонів: "+selected.getCars().size());
        seatsLabel.setText("Всього місць: "+selected.getSeats());
        addCar.setVisible(false);
        trainMenu.setVisible(true);
        updateData();
    }

    @FXML
    void onbtnAddCarClicked(ActionEvent event) throws IOException {
        trainMenu.setVisible(false);
        addCar.setVisible(true);
    }

    @FXML
    void onbtnAddTrainClicked(ActionEvent event) {
        mainMenu.setVisible(false);
        addTrain.setVisible(true);
    }

    @FXML
    void onbtnSelTrainClicked(ActionEvent event) {
        pos=trainTable.getSelectionModel().getSelectedIndex();
        selected = trains.get(pos);
        selected.showInfo();
        numLabel.setText("№"+selected.getNumber());
        fromtoLabel.setText(selected.getFrom()+" - "+selected.getTo());
        carsLabel.setText("Всього вагонів: "+selected.getCars().size());
        seatsLabel.setText("Всього місць: "+selected.getSeats());
        luggageLabel.setText("Всього багажу: "+selected.getLuggage()+"kg");
        trainInfo.setVisible(true);
        trainMenu.setVisible(true);
        mainMenu.setVisible(false);
        setCarTable(selected);
    }

    @FXML
    void onbtnRmTrainClicked(ActionEvent event) {
        pos=trainTable.getSelectionModel().getSelectedIndex();
        if(pos==-1){
            return;
        }
        trains.remove(pos);
        setTrainTable();
    }

    public void setTrainTable(){
        trainTable.setEditable(true);
        tfromCol.setCellFactory(TextFieldTableCell.forTableColumn());
        tfromCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Train, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Train, String> event) {

                ((Train)event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setFrom(event.getNewValue());

            }
        });

        ttoCol.setCellFactory(TextFieldTableCell.forTableColumn());

        ttoCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Train, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Train, String> event) {

                ((Train)event.getTableView().getItems().get(event.getTablePosition().getRow()))
                        .setTo(event.getNewValue());

            }
        });
        tnumcol.setCellValueFactory(new PropertyValueFactory<Train, Integer>("Number"));
        tfromCol.setCellValueFactory( new PropertyValueFactory<Train, String>("From"));
        ttoCol.setCellValueFactory( new PropertyValueFactory<Train, String>("To"));
        trainTable.getItems().clear();
        list = FXCollections.observableArrayList(trains);
        for (Train train : list) {
            trainTable.getItems().add(train);
        }
        trainTable.setEditable(false);
    }

    @FXML
    void onbtnCarBackClicked(MouseEvent event) {
        carTable.getItems().clear();
        trainInfo.setVisible(false);
        trainMenu.setVisible(false);
        mainMenu.setVisible(true);
    }

    @FXML
    void oncancelCarClicked(ActionEvent event) {
        carnumField.clear();
        carcondField.clear();
        carclassField.clear();
        addCar.setVisible(false);
        trainMenu.setVisible(true);
        trainInfo.setVisible(true);
    }

    @FXML
    void oncancelTrainClicked(ActionEvent event) {
        fromField.clear();
        toField.clear();
        numField.clear();
        addTrain.setVisible(false);
        mainMenu.setVisible(true);
    }

    @FXML
    void onchooseCarbtnClicked(ActionEvent event) {
        pos=carTable.getSelectionModel().getSelectedIndex();
        selectedCar = selected.getCars().get(pos);
        trainMenu.setVisible(false);
        passMenu.setVisible(true);
        setPassengerTable();
    }

    @FXML
    void onbtnAddPassClicked(ActionEvent event) {
        passMenu.setVisible(false);
        addPass.setVisible(true);
    }

    @FXML
    void onbtnRmPassClicked(ActionEvent event) {
        pos=passTable.getSelectionModel().getSelectedIndex();
        command.setCommand(new removePassengerCommand(selectedCar, selectedCar.getPassengers().get(pos).getSeat()));
        command.executeCommand();
        setPassengerTable();
    }

    @FXML
    void onbtnPassBackClicked(ActionEvent event) {
        passMenu.setVisible(false);
        trainMenu.setVisible(true);
    }

    @FXML
    void submitPassbtnClicked(ActionEvent event) {
        if(Objects.equals(passSeatField.getText() , "") || Objects.equals(passLugField.getText() , "") || Objects.equals(passNameField.getText() , "")){
            return;
        }
        double luggage;
        try
        {
            luggage=Double.parseDouble(passLugField.getText());
            // Valid double
        }
        catch (NullPointerException | NumberFormatException ex)
        {
            return;
        }
        command.setCommand(new addPassengerCommand(selectedCar, passNameField.getText(), luggage, Integer.parseInt(passSeatField.getText())));
        command.executeCommand();
        setPassengerTable();
        passNameField.clear();
        passSeatField.clear();
        passLugField.clear();
        seatsLabel.setText("Всього місць: "+selected.getSeats());
        luggageLabel.setText("Всього багажу: "+selected.getLuggage()+"kg");

        addPass.setVisible(false);
        passMenu.setVisible(true);
    }

    @FXML
    void oncancelPassClicked(ActionEvent event) {
        addPass.setVisible(false);
        passMenu.setVisible(true);
    }






    public void updateData(){
        try{
            cursor = coll.find().iterator();
            trains.clear();
            for(int i = 0; i < coll.countDocuments(); i++){
                Document doc = cursor.next();
                Number= String.valueOf(doc.getInteger("Number"));
                String From = doc.getString("From");
                String To = doc.getString("To");
                List<Document> mylist = doc.getList("cars", Document.class);

                ArrayList<TrainCar> Cars = new ArrayList<TrainCar>();
                for(Document elem:mylist){
                    if(elem.getInteger("CarClass")==1){
                        Cars.add(new FirstClassCar(elem.getInteger("Number"), elem.getString("ConductorName")));
                    }else if(elem.getInteger("CarClass")==2){
                        Cars.add(new SecondClassCar(elem.getInteger("Number"), elem.getString("ConductorName")));
                    }else {
                        Cars.add(new ThirdClassCar(elem.getInteger("Number"), elem.getString("ConductorName")));
                    }
                    List<Document> passengers = elem.getList("passengers", Document.class);
                    for(Document pass: passengers){
                        Cars.get(i).addPassenger(pass.getString("Name"), pass.getDouble("LuggageKg"), pass.getInteger("Seat"));
                    }
                    Cars.get(i).showInfo();
                }
                trains.add(new Train(From, To, Integer.parseInt(Number)));
                trains.get(i).setCars(Cars);
            }
            trains.get(0).showInfo();
            trains.add(new Train("Ternopil", "Kyiv", 1));
            trains.add(new Train("Ternopil", "Lviv", 3));
            trains.add(new Train("Ternopil", "Zhytomyr", 2));
            list = FXCollections.observableArrayList(trains);
        }
        finally {
            cursor.close();
        }
    }

    public void setCarTable(Train selected){
        carTable.setEditable(true);
        cnumcol.setCellValueFactory(new PropertyValueFactory<TrainCar, Integer>("Number"));
        cclasscol.setCellValueFactory( new PropertyValueFactory<TrainCar, Integer>("CarClass"));
        cconductorcol.setCellValueFactory( new PropertyValueFactory<TrainCar, String>("ConductorName"));
        cpassengerscol.setCellValueFactory( new PropertyValueFactory<TrainCar, Integer>("PassengersNum"));
        carTable.getItems().clear();
        list = FXCollections.observableArrayList(trains);
        for (TrainCar car : selected.getCars()) {
            car.updatePas();
            carTable.getItems().add(car);
        }
        carTable.setEditable(false);
    }
    public void setPassengerTable(){
        passTable.setEditable(true);
        pseatcol.setCellValueFactory(new PropertyValueFactory<Passenger, Integer>("Seat"));
        pluggcol.setCellValueFactory( new PropertyValueFactory<Passenger, Double>("LuggageKg"));
        pnamecol.setCellValueFactory( new PropertyValueFactory<Passenger, String>("Name"));
        passTable.getItems().clear();
        list = FXCollections.observableArrayList(trains);
        for (Passenger pass : selectedCar.getPassengers()) {
            passTable.getItems().add(pass);
        }
        carTable.setEditable(false);
    }


}
