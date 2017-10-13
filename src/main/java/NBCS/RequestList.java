package NBCS;

import java.util.ArrayList;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 *
 * @author xaviermartinez
 */
@Named(value = "requestList")
@ApplicationScoped
public class RequestList {

    @Produces
    private final ArrayList<Request> requests;

    public RequestList() {
        this.requests = new ArrayList<>();
    }

    public ArrayList<Request> getRequests() {
        return requests;
    }

    public void addRequest(Request request) {
        this.requests.add(request);
    }

    public void printRequests() {
        System.out.println("### PRINTREQUESTS ###");
        for (Request r: this.requests) {
            System.out.println(r);
        }
        System.out.println("### END PRINTREQUESTS ###");
    }

}
