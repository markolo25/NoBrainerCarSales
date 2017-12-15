/*
 * THIS CLASS IS IN PROGRESS...
 *
 */
package NBCS;

import java.io.StringReader;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * 
 * @author Xavier Martinez <xavier.martinez@student.csulb.edu>
 */
public class CarSelections {

    private Map<String, String> makes;
    private Map<String, String> models;
    private Map<String, Integer> years;

    public CarSelections() {
        this.years = new LinkedHashMap<>();
        initializeYears();
        this.makes = new LinkedHashMap<>();
        initializeMakes();
        this.models = new LinkedHashMap<>();
    }

    public final void initializeMakes() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://vpic.nhtsa.dot.gov/api/vehicles/GetMakesForVehicleType");
        Response responsePassengerCar = target
            .path("Passenger Car")
            .queryParam("format", "json")
            .request(MediaType.APPLICATION_JSON)
            .get();
        JsonObject jObjectPassengerCars;
        try (JsonReader jReader = Json.createReader(new StringReader(responsePassengerCar.readEntity(String.class)))) {
            jObjectPassengerCars = jReader.readObject();
        }
        JsonArray jArrayPassengerCars = jObjectPassengerCars.getJsonArray("Results");
        for(int i=0; i < jArrayPassengerCars.size(); i++) {
            JsonObject j = jArrayPassengerCars.getJsonObject(i);
            String make = j.getString("MakeName").trim();
            this.makes.put(make, make.toUpperCase());
        }
        Response responseTruck = target
                .path("Truck")
                .queryParam("format", "json")
                .request(MediaType.APPLICATION_JSON)
                .get();
        JsonObject jObjectTrucks;
        try (JsonReader jReader = Json.createReader(new StringReader(responseTruck.readEntity(String.class)))) {
            jObjectTrucks = jReader.readObject();
        }
        JsonArray jArrayTrucks = jObjectTrucks.getJsonArray("Results");
        for(int i=0; i < jArrayTrucks.size(); i++) {
            JsonObject j = jArrayTrucks.getJsonObject(i);
            String make = j.getString("MakeName").trim();
            this.makes.put(make, make.toUpperCase());
        }
        this.makes = new TreeMap<>(this.makes);
        client.close();
    }

    public final void initializeYears() {
        for (int i = 1980; i <= LocalDate.now().getYear(); i++){
            this.years.put(String.valueOf(i), i);
        }
    }

    public Map<String, String> getMakes() {
        return this.makes;
    }

    public Map<String, Integer> getYears() {
        return this.years;
    }

//    public void setYearsMakesModelsByVIN(String VIN) {
//        Client client = ClientBuilder.newClient();
//        WebTarget target = client.target("https://vpic.nhtsa.dot.gov/api/vehicles/decodevin");
//        Response responsePassengerCar = target
//            .path(VIN)
//            .queryParam("format", "json")
//            .request(MediaType.APPLICATION_JSON)
//            .get();
//        JsonObject jObjectPassengerCars;
//        try (JsonReader jReaderPassengerCars = Json.createReader(new StringReader(responsePassengerCar.readEntity(String.class)))) {
//            jObjectPassengerCars = jReaderPassengerCars.readObject();
//        }
//        JsonArray jArrayPassengerCars = jObjectPassengerCars.getJsonArray("Results");
//        int errorCode = Integer.parseInt(jArrayPassengerCars.getJsonObject(1).getString("ValueId"));
//        if (errorCode == 0) {
//            String make = "Any";
//            String model = "Any";
//            String year = "1980";
//            int attributesFound = 0;
//            this.makes = new LinkedList<>();
//            this.models = new LinkedList<>();
//            this.years = new LinkedHashMap<>();
//            for(int i=0; i < jArrayPassengerCars.size(); i++) {
//                JsonObject j = jArrayPassengerCars.getJsonObject(i);
//                String variable = j.getString("Variable").trim();
//                if (variable.equals("Make")) {
//                    make = j.getString("Value").trim();
//                    attributesFound++;
//                    continue;
//                }
//                if (variable.equals("Model")) {
//                    model = j.getString("Value").trim();
//                    attributesFound++;
//                    continue;
//                }
//                if (variable.equals("Model Year")) {
//                    year = j.getString("Value").trim();
//                    attributesFound++;
//                    continue;
//                }
//                if (attributesFound == 3) {
//                    break;
//                }
//            }
//            this.makes.add(0, make);
//            this.models.add(0, model);
//            this.years.put(year, Integer.parseInt(year));
//        }
//        client.close();
//    }

    public void setModelsByYearAndMake(String year, String make) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://vpic.nhtsa.dot.gov/api/vehicles/getmodelsformakeyear"); // https://vpic.nhtsa.dot.gov/api/vehicles/getmodelsformakeyear/make/honda/modelyear/2015/vehicleType/truck?format=json
        Response responsePassengerCar = target
            .path("make")
            .path(make)
            .path("modelyear")
            .path(year)
            .path("vehicleType")
            .path("Passenger Car")
            .queryParam("format", "json")
            .request(MediaType.APPLICATION_JSON)
            .get();
        JsonObject jObjectPassengerCars;
        try (JsonReader jReaderPassengerCars = Json.createReader(new StringReader(responsePassengerCar.readEntity(String.class)))) {
            jObjectPassengerCars = jReaderPassengerCars.readObject();
        }
        JsonArray jArrayPassengerCars = jObjectPassengerCars.getJsonArray("Results");
        for(int i=0; i < jArrayPassengerCars.size(); i++) {
            JsonObject j = jArrayPassengerCars.getJsonObject(i);
            String modelName = j.getString("Model_Name").trim();
            this.models.put(modelName, modelName.toUpperCase());
        }
        Response responseTruck = target
            .path("make")
            .path(make)
            .path("modelyear")
            .path(year)
            .path("vehicleType")
            .path("Truck")
            .queryParam("format", "json")
            .request(MediaType.APPLICATION_JSON)
            .get();
        JsonObject jObjectTrucks;
        try (JsonReader jReaderTrucks = Json.createReader(new StringReader(responseTruck.readEntity(String.class)))) {
            jObjectTrucks = jReaderTrucks.readObject();
        }
        JsonArray jArrayTrucks = jObjectTrucks.getJsonArray("Results");
        for(int i=0; i < jArrayTrucks.size(); i++) {
            JsonObject j = jArrayTrucks.getJsonObject(i);
            String modelName = j.getString("Model_Name").trim();
            this.models.put(modelName, modelName.toUpperCase());
        }
        this.models = new TreeMap<>(this.models);
        client.close();
    }

    public Map<String, String> getModels() {
        return this.models;
    }

}
