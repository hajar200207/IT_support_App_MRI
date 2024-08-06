package com.itsolutions.equipment_management.services;

import com.itsolutions.equipment_management.models.EtatTicket;
import com.itsolutions.equipment_management.models.Ticket;
import com.itsolutions.equipment_management.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createTicket(Ticket ticket) {
        ticket.setDateCreation(new Date());
        return ticketRepository.save(ticket);
    }


    public List<Ticket> getTicketsByUserId(Long userId) {
        return ticketRepository.findByUserId(userId);
    }

    public List<Ticket> getTicketsByTechnicienId(Long technicienId) {
        return ticketRepository.findByTechnicienId(technicienId);
    }

    public void updateTicketStatus(Long id, EtatTicket etatTicket) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        ticket.setEtatTicket(etatTicket);
        ticketRepository.save(ticket);
    }
}
