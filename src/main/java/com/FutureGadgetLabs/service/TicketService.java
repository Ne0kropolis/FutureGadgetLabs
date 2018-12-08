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

    @Autowired
    public TicketService(@Qualifier("ticketJDBCDAO") TicketJDBCDAO ticketJdbcDao) {
        this.ticketJdbcDao = ticketJdbcDao;
    }

    public List getAllTickets() {
        return (this.ticketJdbcDao.getAll());
    }

    public Ticket getTicketById(int id) { return (this.ticketJdbcDao.get(id)); }

    public void createTicket(Ticket ticket) { this.ticketJdbcDao.insert(ticket); }

    public void createTickets(List<Ticket> ticketList) throws SQLException {this.ticketJdbcDao.batchInsert(ticketList);}

    public void updateTicket(Ticket ticket) { this.ticketJdbcDao.update(ticket); }

    public void deleteTicket(int id) {ticketJdbcDao.delete(id);}

}

