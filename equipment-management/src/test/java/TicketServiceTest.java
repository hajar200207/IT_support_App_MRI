//
//import com.itsolutions.equipment_management.models.EtatTicket;
//import com.itsolutions.equipment_management.models.Technicien;
//import com.itsolutions.equipment_management.models.Ticket;
//import com.itsolutions.equipment_management.repositories.TechnicienRepository;
//import com.itsolutions.equipment_management.repositories.TicketRepository;
//import com.itsolutions.equipment_management.services.TicketService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class TicketServiceTest {
//
//    @Mock
//    private TicketRepository ticketRepository;
//
//    @Mock
//    private TechnicienRepository technicienRepository;
//
//    @InjectMocks
//    private TicketService ticketService;
//
//    private Ticket ticket;
//    private Technicien technicien;
//
//    @BeforeEach
//    public void setUp() {
//        ticket = new Ticket();
//        ticket.setId(1L);
//        ticket.setDateCreation(new Date());
//        ticket.setEtatTicket(EtatTicket.OUVERT);
//
//        technicien = new Technicien();
//        technicien.setId(1L);
//        technicien.setNom("Technicien Test");
//    }
//
//    @Test
//    public void testCreateTicket() {
//        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);
//
//        Ticket createdTicket = ticketService.createTicket(ticket);
//
//        assertNotNull(createdTicket);
//        assertEquals(EtatTicket.OUVERT, createdTicket.getEtatTicket());
//        assertNotNull(createdTicket.getDateCreation());
//        verify(ticketRepository, times(1)).save(ticket);
//    }
//
//    @Test
//    public void testGetTicketsByUserId() {
//        List<Ticket> tickets = List.of(ticket);
//        when(ticketRepository.findByUserId(1L)).thenReturn(tickets);
//
//        List<Ticket> foundTickets = ticketService.getTicketsByUserId(1L);
//
//        assertNotNull(foundTickets);
//        assertEquals(1, foundTickets.size());
//        verify(ticketRepository, times(1)).findByUserId(1L);
//    }
//
//    @Test
//    public void testGetTicketsByTechnicienId() {
//        List<Ticket> tickets = List.of(ticket);
//        when(ticketRepository.findByTechnicienId(1L)).thenReturn(tickets);
//
//        List<Ticket> foundTickets = ticketService.getTicketsByTechnicienId(1L);
//
//        assertNotNull(foundTickets);
//        assertEquals(1, foundTickets.size());
//        verify(ticketRepository, times(1)).findByTechnicienId(1L);
//    }
//
//    @Test
//    public void testUpdateTicketStatus() {
//        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
//
//        ticketService.updateTicketStatus(1L, EtatTicket.EN_COURS);
//
//        assertEquals(EtatTicket.EN_COURS, ticket.getEtatTicket());
//        verify(ticketRepository, times(1)).save(ticket);
//    }
//
//    @Test
//    public void testAssignTicket() {
//        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
//        when(technicienRepository.findById(1L)).thenReturn(Optional.of(technicien));
//        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);
//
//        Ticket assignedTicket = ticketService.assignTicket(1L, 1L);
//
//        assertNotNull(assignedTicket);
//        assertEquals(technicien, assignedTicket.getTechnicien());
//        verify(ticketRepository, times(1)).save(ticket);
//    }
//
//    @Test
//    public void testAssignTicketThrowsExceptionWhenTicketNotFound() {
//        when(ticketRepository.findById(1L)).thenReturn(Optional.empty());
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            ticketService.assignTicket(1L, 1L);
//        });
//
//        assertEquals("Ticket not found with id 1", exception.getMessage());
//    }
//
//    @Test
//    public void testAssignTicketThrowsExceptionWhenTechnicienNotFound() {
//        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
//        when(technicienRepository.findById(1L)).thenReturn(Optional.empty());
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            ticketService.assignTicket(1L, 1L);
//        });
//
//        assertEquals("Technician not found with id 1", exception.getMessage());
//    }
//}
