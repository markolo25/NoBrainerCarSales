package NBCS;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.inject.Inject;

/**
 *
 * @author xaviermartinez
 */
public class InsertRequestServlet extends HttpServlet {

    @Inject
    private Request request;

    @Inject
    private RequestList autoRequest;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getMethod().equals("POST")) {
            String make = request.getParameter("make");
            String model = request.getParameter("model");
            String yearFromParam = request.getParameter("yearFrom");
            Integer yearFrom = !yearFromParam.isEmpty() ? Integer.parseInt(yearFromParam) : null;
            String yearToParam = request.getParameter("yearTo");
            Integer yearTo = !yearToParam.isEmpty() ? Integer.parseInt(yearToParam) : null;
            String priceLowParam = request.getParameter("priceLow");
            Double priceLow = !priceLowParam.isEmpty() ? Double.parseDouble(priceLowParam) : null;
            String priceHighParam = request.getParameter("priceHigh");
            Double priceHigh = !priceHighParam.isEmpty() ? Double.parseDouble(priceHighParam) : null;
            String rangeFromLocationParam = request.getParameter("rangeFromLocation");
            Double rangeFromLocation = !rangeFromLocationParam.isEmpty() ? Double.parseDouble(rangeFromLocationParam) : null;
            String titleStatus = request.getParameter("titleStatus");
            String milageParam = request.getParameter("milage");
            Double milage = !milageParam.isEmpty() ? Double.parseDouble(milageParam) : null;
            this.request = new Request();
            this.request.setMake(make);
            this.request.setModel(model);
            this.request.setYearFrom(yearFrom);
            this.request.setYearTo(yearTo);
            this.request.setPriceLow(priceLow);
            this.request.setPriceHigh(priceHigh);
            this.request.setRangeFromLocation(rangeFromLocation);
            this.request.setTitleStatus(titleStatus);
            this.request.setMilage(milage);
            this.autoRequest.addRequest(this.request);
            this.autoRequest.printRequests();
            response.sendRedirect("index.xhtml");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
