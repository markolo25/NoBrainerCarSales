/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NBCS;

import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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

    public CarSelections() {

        this.makes = new LinkedList<>();
        this.models = new LinkedList<>();
    }

    public List<String> getMakes() {
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
        return this.makes;
    }

    public void setModels(String year, String make) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://vpic.nhtsa.dot.gov/api/vehicles/getmodelsformakeyear"); // https://vpic.nhtsa.dot.gov/api/vehicles/getmodelsformakeyear/make/honda/modelyear/2015/vehicleType/truck?format=json
        Response response = target
            .path("make")
            .path(make)
            .path("modelyear")
            .path(year)
            .path("vehicletype")
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
