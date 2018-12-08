package rest;

import com.FutureGadgetLabs.domain.Ticket;
import com.FutureGadgetLabs.rest.TicketController;
import com.FutureGadgetLabs.service.TicketService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class TicketControllerTest {
    @Mock
    private TicketService mockTicketService;

    private TicketController ticketController;

    private Ticket expectedTicket;

    private List<Ticket> ticketList;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);

        ticketController = new TicketController(mockTicketService);

        Ticket expectedTicket =  new Ticket(1,100, java.sql.Timestamp.valueOf("2018-08-08 20:08:08"), java.sql.Timestamp.valueOf("2018-08-08 20:40:08"), 30, false);
        Ticket ticketTwo = new Ticket(2,101, java.sql.Timestamp.valueOf("2018-08-08 20:08:08"), java.sql.Timestamp.valueOf("2018-08-08 20:40:08"), 20, true);

        ticketList = new ArrayList<>();
        ticketList.add(expectedTicket);
        ticketList.add(ticketTwo);
    }

    @Test
    public void shouldGetATicketById() {
        when(mockTicketService.getTicketById(anyInt())).thenReturn(expectedTicket);

        assertEquals(expectedTicket, ticketController.getTicketById(1));
    }

    @Test
    public void shouldGetAllTickets() {
        when (mockTicketService.getAllTickets()).thenReturn(ticketList);

        assertEquals(ticketList, ticketController.getAllTickets());
    }

    @Test
    public void shouldCreateATicket() {

        ArgumentCaptor ticketCapture = ArgumentCaptor.forClass(Ticket.class);

        doNothing().when(mockTicketService).createTicket((Ticket) ticketCapture.capture());

        ticketController.createTicket(expectedTicket);

        assertEquals(expectedTicket, ticketCapture.getValue());
    }

    @Test
    public void shouldCreateABatchOfTickets() throws SQLException {

        ArgumentCaptor ticketCapture = ArgumentCaptor.forClass(Ticket.class);

        doNothing().when(mockTicketService).createTickets((List<Ticket>) ticketCapture.capture());

        ticketController.createTickets(ticketList);

        assertEquals(ticketList, ticketCapture.getValue());
    }

    @Test
    public void shouldUpdateATicket() {

        ArgumentCaptor ticketCapture = ArgumentCaptor.forClass(Ticket.class);

        doNothing().when(mockTicketService).updateTicket((Ticket) ticketCapture.capture());

        ticketController.updateTicket(expectedTicket);

        assertEquals(expectedTicket, ticketCapture.getValue());
    }

    @Test
    public void shouldCallCorrectIdWhenDeletingATicket() {

        ArgumentCaptor ticketCapture = ArgumentCaptor.forClass(Integer.class);

        doNothing().when(mockTicketService).deleteTicket((Integer) ticketCapture.capture());

        ticketController.deleteTicket(1);

        assertEquals(1, ticketCapture.getValue());

    }
}
