package com.itsolutions.equipment_management.controllers;

import com.itsolutions.equipment_management.models.EtatTicket;
import com.itsolutions.equipment_management.models.Ticket;
import com.itsolutions.equipment_management.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.createTicket(ticket));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Ticket>> getTicketsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(ticketService.getTicketsByUserId(userId));
    }

    @GetMapping("/technicien/{technicienId}")
    public ResponseEntity<List<Ticket>> getTicketsByTechnicienId(@PathVariable Long technicienId) {
        return ResponseEntity.ok(ticketService.getTicketsByTechnicienId(technicienId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateTicketStatus(@PathVariable Long id, @RequestBody EtatTicket etatTicket) {
        ticketService.updateTicketStatus(id, etatTicket);
        return ResponseEntity.ok("Ticket status updated successfully");
    }
}
