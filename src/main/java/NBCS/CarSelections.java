/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS;

import java.io.StringReader;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    private List<String> makes;
    private List<String> models;
    private Map<String,Integer> years;

    public CarSelections() {
        this.years = new LinkedHashMap<>();
        initializeYears();
        this.makes = new LinkedList<>();
        initializeMakes();
        this.models = new LinkedList<>();
    }

    public final void initializeMakes() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://vpic.nhtsa.dot.gov/api/vehicles/GetMakesForVehicleType");
        Response response = target
            .path("Passenger Car")
            .queryParam("format", "json")
            .request(MediaType.APPLICATION_JSON)
            .get();
        JsonObject jObject;
        try (JsonReader jReader = Json.createReader(new StringReader(response.readEntity(String.class)))) {
            jObject = jReader.readObject();
        }
        JsonArray jArray = jObject.getJsonArray("Results");
        for(int i=0; i < jArray.size(); i++) {
            JsonObject j = jArray.getJsonObject(i);
            String make = j.getString("MakeName").trim();
            this.makes.add(make);
        }
        Collections.sort(this.makes);
        this.makes.add(0, "Any");
        client.close();
    }

    public final void initializeYears() {
        for (int i = 1980; i <= LocalDate.now().getYear(); i++){
            this.years.put(String.valueOf(i), i);
        }
    }

    public List<String> getMakes() {
        return this.makes;
    }

    public Map<String, Integer> getYears() {
        return this.years;
    }

    public void setYearsMakesModelsByVIN(String VIN) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://vpic.nhtsa.dot.gov/api/vehicles/decodevin");
        Response response = target
            .path(VIN)
            .queryParam("format", "json")
            .request(MediaType.APPLICATION_JSON)
            .get();
        JsonObject jObject;
        try (JsonReader jReader = Json.createReader(new StringReader(response.readEntity(String.class)))) {
            jObject = jReader.readObject();
        }
        JsonArray jArray = jObject.getJsonArray("Results");
        int errorCode = Integer.parseInt(jArray.getJsonObject(1).getString("ValueId"));
        if (errorCode == 0) {
            String make = "Any";
            String model = "Any";
            String year = "1980";
            int attributesFound = 0;
            this.makes = new LinkedList<>();
            this.models = new LinkedList<>();
            this.years = new LinkedHashMap<>();
            for(int i=0; i < jArray.size(); i++) {
                JsonObject j = jArray.getJsonObject(i);
                String variable = j.getString("Variable").trim();
                if (variable.equals("Make")) {
                    make = j.getString("Value").trim();
                    attributesFound++;
                    continue;
                }
                if (variable.equals("Model")) {
                    model = j.getString("Value").trim();
                    attributesFound++;
                    continue;
                }
                if (variable.equals("Model Year")) {
                    year = j.getString("Value").trim();
                    attributesFound++;
                    continue;
                }
                if (attributesFound == 3) {
                    break;
                }
            }
            this.makes.add(0, make);
            this.models.add(0, model);
            this.years.put(year, Integer.parseInt(year));
        }
        client.close();
    }

    public void setModelsByYearAndMake(String year, String make) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://vpic.nhtsa.dot.gov/api/vehicles/getmodelsformakeyear"); // https://vpic.nhtsa.dot.gov/api/vehicles/getmodelsformakeyear/make/honda/modelyear/2015/vehicleType/truck?format=json
        Response response = target
            .path("make")
            .path(make)
            .path("modelyear")
            .path(year)
            .queryParam("format", "json")
            .request(MediaType.APPLICATION_JSON)
            .get();
        JsonObject jObject;
        try (JsonReader jReader = Json.createReader(new StringReader(response.readEntity(String.class)))) {
            jObject = jReader.readObject();
        }
        this.models = new LinkedList<>();
        JsonArray jArray = jObject.getJsonArray("Results");
        for(int i=0; i < jArray.size(); i++) {
            JsonObject j = jArray.getJsonObject(i);
            String modelName = j.getString("Model_Name").trim();
            this.models.add(modelName);
        }
        Collections.sort(this.models);
        this.models.add(0, "Any");
        client.close();
    }

    public List<String> getModels() {
        return models;
    }

}
