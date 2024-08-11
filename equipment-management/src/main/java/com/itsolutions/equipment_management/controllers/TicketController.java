package com.itsolutions.equipment_management.controllers;

import com.itsolutions.equipment_management.DTO.TicketDTO;
import com.itsolutions.equipment_management.models.EtatTicket;
import com.itsolutions.equipment_management.models.Ticket;
import com.itsolutions.equipment_management.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/create")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.createTicket(ticket));
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TicketDTO>> getTicketsByUserId(@PathVariable Long userId) {
        List<Ticket> tickets = ticketService.getTicketsByUserId(userId);
        List<TicketDTO> dtos = tickets.stream().map(ticket -> {
            TicketDTO dto = new TicketDTO();
            dto.setId(ticket.getId());
            dto.setDescription(ticket.getDescription());
            dto.setEtatTicket(String.valueOf(ticket.getEtatTicket()));
            dto.setUserId(ticket.getUser().getId());
            dto.setTechnicienId(ticket.getTechnicien() != null ? ticket.getTechnicien().getId() : null);
            dto.setPanneId(ticket.getPanne() != null ? ticket.getPanne().getId() : null);
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    @PreAuthorize("hasRole('TECHNICIEN')")
    @GetMapping("/technicien/{technicienId}")
    public ResponseEntity<List<Ticket>> getTicketsByTechnicienId(@PathVariable Long technicienId) {
        return ResponseEntity.ok(ticketService.getTicketsByTechnicienId(technicienId));
    }

    @PreAuthorize("hasRole('TECHNICIEN')")
    @PutMapping("/update-status/{technicienId}/{ticketId}")
    public ResponseEntity<String> updateTicketStatus(
            @PathVariable Long technicienId,
            @PathVariable Long ticketId,
            @RequestBody Map<String, String> requestBody) {
        String etatTicketStr = requestBody.get("etatTicket");
        EtatTicket etatTicket = EtatTicket.valueOf(etatTicketStr);
        ticketService.updateTicketStatus(ticketId, etatTicket);
        return ResponseEntity.ok("Ticket status updated successfully");
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TECHNICIAN')")
    @PutMapping("/assign/{ticketId}")
    public ResponseEntity<Ticket> assignTicket(@PathVariable Long ticketId, @RequestParam Long technicienId) {
        Ticket assignedTicket = ticketService.assignTicket(ticketId, technicienId);
        return ResponseEntity.ok(assignedTicket);
    }
    @GetMapping("/all")
    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return tickets.stream().map(ticket -> {
            TicketDTO dto = new TicketDTO();
            dto.setId(ticket.getId());
            dto.setDescription(ticket.getDescription());
            dto.setEtatTicket(String.valueOf(ticket.getEtatTicket()));
            dto.setUserId(ticket.getUser().getId());
            dto.setTechnicienId(ticket.getTechnicien().getId());
            dto.setPanneId(ticket.getPanne().getId());
            return dto;
        }).collect(Collectors.toList());
    }


}
