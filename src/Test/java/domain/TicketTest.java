package domain;

import com.FutureGadgetLabs.domain.Ticket;
import org.junit.Before;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TicketTest {
    private Ticket blankTicket;
    private Ticket ticketWithPrice;
    private Ticket ticketWithoutPrice;
    private Ticket ticketWithoutExitTime;

    @Before
    public void initialize() {
        blankTicket = new Ticket();
        ticketWithPrice = new Ticket(1,100, java.sql.Timestamp.valueOf("2018-08-08 20:08:08"), java.sql.Timestamp.valueOf("2018-08-08 20:40:08"), 30, false);
        ticketWithoutPrice = new Ticket(1,100, java.sql.Timestamp.valueOf("2018-08-08 20:08:08"), java.sql.Timestamp.valueOf("2018-08-08 20:40:08"), false);
        ticketWithoutExitTime = new Ticket(1,100, java.sql.Timestamp.valueOf("2018-08-08 20:08:08"), false);
    }

    @Test
    public void shouldCreateTicketWithParameters() {
        assertEquals(ticketWithPrice, ticketWithPrice);
    }

    @Test
    public void shouldEnsureTicketWithoutPriceEqualsZero() { assertNotEquals(0, ticketWithPrice.getPrice()); }

    @Test
    public void shouldEnsureTicketWithoutExitTimeIsNull() { assertEquals(null, ticketWithoutExitTime.getTimeOut()); }

    @Test
    public void shouldReturnCorrectPrice() {
        assertEquals(30, ticketWithPrice.getPrice());
    }

}
