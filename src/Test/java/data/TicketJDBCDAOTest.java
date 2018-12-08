package data;

import com.FutureGadgetLabs.data.TicketJDBCDAO;
import com.FutureGadgetLabs.domain.Ticket;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context.xml")
@FlywayTest
public class TicketJDBCDAOTest {

    @Autowired
    TicketJDBCDAO ticketJDBCDAO;

    @Test
    public void shouldGetASingleTicketObject() {
        assertEquals(ticketJDBCDAO.get(1).getTicketId(), 1);
    }

    @Test
    public void shouldGetAllTickets() {
        assertEquals(2, ticketJDBCDAO.getAll().size());
    }

    @Test
    public void shouldInsertASingleTicket() {
        ticketJDBCDAO.insert(new Ticket(10,100, java.sql.Timestamp.valueOf("2018-08-08 20:08:08"), java.sql.Timestamp.valueOf("2018-08-08 20:40:08"), 300, false));

        assertEquals(300, ticketJDBCDAO.get(10).getPrice());
    }

    @Test
    public void shouldBatchInsertTickets() throws SQLException {
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(new Ticket(3,100, java.sql.Timestamp.valueOf("2018-08-08 20:08:08"), java.sql.Timestamp.valueOf("2018-08-08 20:40:08"), 30, false));
        ticketList.add(new Ticket(4,101, java.sql.Timestamp.valueOf("2018-08-08 20:08:08"), null, 40, true));
        ticketList.add(new Ticket(5,101, java.sql.Timestamp.valueOf("2018-08-08 20:08:08"), java.sql.Timestamp.valueOf("2018-08-08 20:40:08"), 50, false));

        ticketJDBCDAO.batchInsert(ticketList);
        assertEquals(30, ticketJDBCDAO.get(3).getPrice());
    }

    @Test
    public void shouldUpdateATicket() {
        Ticket newTicket = new Ticket(1,101, java.sql.Timestamp.valueOf("2018-08-08 20:08:08"), java.sql.Timestamp.valueOf("2018-08-08 20:40:08"), 300, false);

        ticketJDBCDAO.update(newTicket);

        assertEquals(300, ticketJDBCDAO.get(1).getPrice());
    }

    @Test
    public void shouldDeleteATicket() {
        int expectedTotal = ticketJDBCDAO.getAll().size()-1;

        ticketJDBCDAO.delete(2);

        assertEquals(expectedTotal, ticketJDBCDAO.getAll().size());
    }
}
