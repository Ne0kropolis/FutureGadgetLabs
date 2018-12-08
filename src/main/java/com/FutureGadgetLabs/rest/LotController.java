package com.FutureGadgetLabs.rest;

import com.FutureGadgetLabs.domain.Lot;
import com.FutureGadgetLabs.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller for Lot Request Mappings.
 */

@Controller
@Path("/lot")
public class LotController {


    private final LotService lotService;

    @Autowired
    public LotController(LotService lotService) { this.lotService = lotService; }

    @Path("/all")
    @GET
    @Produces("application/json")
    public List getAllLots() {
        return this.lotService.getAllLots();
    }

    @Path("/{id}")
    @GET
    @Produces("application/json")
    public Lot getLotById(@PathParam("id") int id) {
        return this.lotService.getLotById(id);
    }

    @Path("/new/json")
    @POST
    @Produces("application/json")
    public void createLot(Lot lot) {
        this.lotService.createLot(lot);
    }

    @Path("/new/list/json")
    @POST
    @Produces("application/json")
    public void createLots(List<Lot> lotList) throws SQLException { this.lotService.createLots(lotList); }

    @Path("/json")
    @PUT
    @Consumes("application/json")
    public void updateLot(Lot lot) {
        this.lotService.updateLot(lot);
    }

    @Path("/capacity")
    @PUT
    public void updateLotCapacity(@QueryParam("id") int id, @QueryParam("capacity")int capacity) {
        this.lotService.updateLotCapacity(id, capacity);
    }

    @Path("/{id}")
    @DELETE
    public void deleteLot(@PathParam("id") int id) { this.lotService.deleteLot(id);}
}
