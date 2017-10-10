package NBCS;

import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 *
 * @author xaviermartinez
 */
@Named(value = "autoRequest")
@ApplicationScoped
public class AutoRequest {

    @Produces
    private ArrayList<String[]> requests;

    /**
     * Creates a new instance of AutoRequest
     */
    public AutoRequest() {
        this.requests = new ArrayList<>();
    }

    public ArrayList<String[]> getRequests() {
        return this.requests;
    }

    public void addRequest(String make, String model, String year) {
        String request[] = {make, model, year};
        this.requests.add(request);
    }

    public void printRequests() {
        System.out.println("### PRINTREQUESTS ###");
        for (String [] request: this.requests) {
            System.out.println("Make: " + request[0] + " Model: " + request[1] + " Year: " + request[2]);
        }
        System.out.println("### END PRINTREQUESTS ###");
    }

}
