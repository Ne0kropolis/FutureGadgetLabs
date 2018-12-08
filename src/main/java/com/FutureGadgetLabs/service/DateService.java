package com.FutureGadgetLabs.service;

import com.FutureGadgetLabs.domain.Ticket;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Service dealing with date calculations required for ticket and report handling.
 */

@Service
public class DateService {
    /**
     * Subtracts exit time from entry time for a given ticket and gets a parking duration in minutes.
     *
     * @param ticket: ticket object retrieved from service.
     * @return duration of type Long in minutes.
     *
     */
    public long calculateDuration(final Ticket ticket) {
        checkTimeOut(ticket);
        if (!ticket.getLost()) {
            LocalDateTime timeIn = localDateFromTimestamp(ticket.getTimeIn());
            LocalDateTime timeOut = localDateFromTimestamp(ticket.getTimeOut());
            Duration duration = Duration.between(timeIn, timeOut);
            return (duration.toMinutes());
        } else {
            return 0;
        }

    }

    /**
     * Converts a Timestamp to a localDateTime.
     *
     * @param timestamp
     * @return LocalDateTime
     *
     */
    public static LocalDateTime localDateFromTimestamp(final Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }

    /**
     * Sets the lost property of a given ticket to true if it's exit time is null upon being called for a fee calculation.
     *
     * @param ticket: ticket object retrieved from service.
     *
     */
    public void checkTimeOut(final Ticket ticket) {
        if (ticket.getTimeOut() == null) {
            ticket.setLost(true);
        }
    }
}
