package service;

import com.FutureGadgetLabs.domain.Ticket;
import com.FutureGadgetLabs.service.TicketService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:/spring/application-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @Test
    public void shouldGetASingleTicket() {
        assertEquals(1, ticketService.getTicketById(1).getTicketId());
    }

    @Test
    public void shouldGetAllTickets() {
        Ticket expectedTicket = (Ticket) ticketService.getAllTickets().get(0);
        assertEquals(1, expectedTicket.getTicketId());
    }

    @Test
    public void shouldCreateASingleTicket() {
        ticketService.createTicket(new Ticket(30,100, java.sql.Timestamp.valueOf("2018-08-08 20:08:08"), java.sql.Timestamp.valueOf("2018-08-08 20:40:08"), 300, false));
        assertEquals(30, ticketService.getTicketById(30).getTicketId());
    }

    @Test
    public void shouldCreateAListOfTickets() throws SQLException {
        int expectedSize = ticketService.getAllTickets().size()+3;
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(new Ticket(6,100, java.sql.Timestamp.valueOf("2018-08-08 20:08:08"), java.sql.Timestamp.valueOf("2018-08-08 20:40:08"), 30, false));
        ticketList.add(new Ticket(7,101, java.sql.Timestamp.valueOf("2018-08-08 20:08:08"), null, 40, true));
        ticketList.add(new Ticket(8,101, java.sql.Timestamp.valueOf("2018-08-08 20:08:08"), java.sql.Timestamp.valueOf("2018-08-08 20:40:08"), 50, false));

        ticketService.createTickets(ticketList);

        assertEquals(expectedSize, ticketService.getAllTickets().size());
    }

    @Test
    public void shouldUpdateASingleTicket() {
        Ticket updateTicket = new Ticket(1,101, java.sql.Timestamp.valueOf("2018-08-08 20:08:08"), java.sql.Timestamp.valueOf("2018-08-08 20:40:08"), 300, false);

        ticketService.updateTicket(updateTicket);

        assertEquals(300, ticketService.getTicketById(1).getPrice());
    }

    @Test
    public void shouldDeleteASingleTicket() {
        int expectedSize = ticketService.getAllTickets().size()-1;
        ticketService.deleteTicket(7);

        assertEquals(expectedSize, ticketService.getAllTickets().size());
    }
}
