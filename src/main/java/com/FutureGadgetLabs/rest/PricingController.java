package com.FutureGadgetLabs.rest;
import com.FutureGadgetLabs.domain.Pricing;
import com.FutureGadgetLabs.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller for Pricing Request Mappings
 */

@Controller
@Path("/pricing")
public class PricingController {

    private final PricingService pricingService;

    @Autowired
    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @Path("/all")
    @GET
    @Produces("application/json")
    public List getAllPricings() {
        return this.pricingService.getAllPricings();
    }

    @Path("/{id}")
    @GET
    @Produces("application/json")
    public Pricing getPricingById(@PathParam("id") int id) {
        return this.pricingService.getPricingById(id);
    }

    @Path("/new/json")
    @POST
    @Consumes("application/json")
    public void createPricing(Pricing pricing) {
        this.pricingService.createPricing(pricing);
    }

    @Path("/new/list/json")
    @POST
    @Consumes("application/json")
    public void createPricings(List<Pricing> pricingList) throws SQLException {
        this.pricingService.createPricings(pricingList);
    }

    @Path("/json")
    @PUT
    @Consumes("application/json")
    public void updatePricing(Pricing pricing) {
        this.pricingService.updatePricing(pricing);
    }

    @Path("/{id}")
    @DELETE
    public void deletePricing(@PathParam("id") int id) { this.pricingService.deletePricing(id); }

}