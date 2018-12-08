package com.FutureGadgetLabs.rest;

import com.FutureGadgetLabs.domain.Ticket;
import com.FutureGadgetLabs.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller for Ticket Request Mappings.
 */

@Controller
@Path("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Path("/all")
    @GET
    @Produces("application/json")
    public List getAllTickets() {
        return this.ticketService.getAllTickets();
    }

    @Path("/{id}")
    @GET
    @Produces("application/json")
    public Ticket getTicketById(@PathParam("id") int id) {
        return this.ticketService.getTicketById(id);
    }

    @Path("/new/json")
    @POST
    @Consumes("application/json")
    public void createTicket(Ticket ticket) {
        this.ticketService.createTicket(ticket);
    }

    @Path("/new/list/json")
    @POST
    @Consumes("application/json")
    public void createTickets(List<Ticket> ticketList) throws SQLException { this.ticketService.createTickets(ticketList);}

    @Path("/json")
    @PUT
    @Consumes
    public void updateTicket(Ticket ticket) { this.ticketService.updateTicket(ticket);}

    @Path("/{id}")
    @DELETE
    public void deleteTicket(@PathParam("id") int id) {this.ticketService.deleteTicket(id);}

}