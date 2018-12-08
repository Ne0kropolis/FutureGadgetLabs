package com.FutureGadgetLabs.service;

import com.FutureGadgetLabs.data.TicketJDBCDAO;
import com.FutureGadgetLabs.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Ticket Service Implementation.
 */

@Service
public class TicketService {

    private final TicketJDBCDAO ticketJdbcDao;
    private PriceService priceService;
    private DateService dateService;

    @Autowired
    public TicketService(@Qualifier("ticketJDBCDAO") TicketJDBCDAO ticketJdbcDao) {
        this.ticketJdbcDao = ticketJdbcDao;
    }

    @Autowired
    public void setPriceService(PriceService priceService) {
        this.priceService = priceService;
    }

    @Autowired
    public void setDateService(DateService dateService) {
        this.dateService = dateService;
    }

    public List getAllTickets() {
        return (this.ticketJdbcDao.getAll());
    }

    public Ticket getTicketById(int id) { return (this.ticketJdbcDao.get(id)); }

    /**
     * Retrieves a list of Ticket Object from DAO and processes them for outstanding payments.
     *
     * @return List<Ticket>: ArrayList of Tickets with calculated prices.
     * @throws SQLException
     *
     */
    public List calculateAllOutstandingTickets() throws SQLException {
        List<Ticket> ticketList = ticketJdbcDao.getAll();
        for (int i=0; i<ticketList.size();i++) {
            if (ticketList.get(i).getPrice() == 0) {
                ticketList.get(i).setPrice(priceService.calculateTicketPrice(
                        dateService.calculateDuration(ticketList.get(i)), ticketList.get(i).getLotId(), ticketList.get(i).getLost()));
            }
        }
        ticketJdbcDao.batchUpdate(ticketList);
        return ticketList;
    }

    /**
     * Retrieves a single ticket and processes it for any outstanding payment.
     * 
     * @param id: ticketId
     * @return Ticket with calculated price.
     *
     */
    public Ticket calculateOutstandingTicket(int id) {
        Ticket ticket = ticketJdbcDao.get(id);
        if (ticket.getPrice() == 0) {
            ticket.setPrice(priceService.calculateTicketPrice(dateService.calculateDuration(ticket), ticket.getLotId(), ticket.getLost()));
        }
        ticketJdbcDao.update(ticket);
        return ticket;
    }

    public void createTicket(Ticket ticket) { this.ticketJdbcDao.insert(ticket); }

    public void createTickets(List<Ticket> ticketList) throws SQLException {this.ticketJdbcDao.batchInsert(ticketList);}

    public void updateTicket(Ticket ticket) { this.ticketJdbcDao.update(ticket); }

    public void deleteTicket(int id) {ticketJdbcDao.delete(id);}

}

