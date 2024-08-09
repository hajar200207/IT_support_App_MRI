
import com.itsolutions.equipment_management.models.*;
import com.itsolutions.equipment_management.repositories.PersonneRepository;
import com.itsolutions.equipment_management.services.PersonneService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonneServiceTest {

    @Mock
    private PersonneRepository personneRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private PersonneService personneService;

    private Personne personne;

    @BeforeEach
    public void setUp() {
        personne = new User();
        personne.setId(1L);
        personne.setEmail("test@example.com");
        personne.setMotDePasse("password");
        personne.setNom("Test");
        personne.setPrenom("User");
    }

    @Test
    public void testRegisterPersonne() {
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(personneRepository.save(any(Personne.class))).thenReturn(personne);

        Personne savedPersonne = personneService.registerPersonne(personne);

        assertNotNull(savedPersonne);
        assertEquals("encodedPassword", savedPersonne.getMotDePasse());
        verify(personneRepository, times(1)).save(personne);
    }

    @Test
    public void testFindByEmail() {
        when(personneRepository.findByEmail(any(String.class))).thenReturn(Optional.of(personne));

        Optional<Personne> foundPersonne = personneService.findByEmail("test@example.com");

        assertTrue(foundPersonne.isPresent());
        assertEquals("test@example.com", foundPersonne.get().getEmail());
    }

    @Test
    public void testFindAll() {
        List<Personne> personnes = List.of(personne);
        when(personneRepository.findAll()).thenReturn(personnes);

        List<Personne> allPersonnes = personneService.findAll();

        assertEquals(1, allPersonnes.size());
        verify(personneRepository, times(1)).findAll();
    }

    @Test
    public void testDeletePersonne() {
        doNothing().when(personneRepository).deleteById(1L);

        personneService.deletePersonne(1L);

        verify(personneRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testCreateAdminUserIfNotExist() {
        when(personneRepository.findByEmail("admin@example.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");

        personneService.createAdminUserIfNotExist();

        verify(personneRepository, times(1)).save(any(Admin.class));
    }

    @Test
    public void testFindById() {
        when(personneRepository.findById(1L)).thenReturn(Optional.of(personne));

        Optional<Personne> foundPersonne = personneService.findById(1L);

        assertTrue(foundPersonne.isPresent());
        assertEquals(1L, foundPersonne.get().getId());
    }

    @Test
    public void testUpdatePersonne() {
        when(personneRepository.findById(1L)).thenReturn(Optional.of(personne));
        when(personneRepository.save(any(Personne.class))).thenReturn(personne);

        Personne updatedPersonne = personneService.updatePersonne(1L, personne);

        assertNotNull(updatedPersonne);
        assertEquals(personne.getEmail(), updatedPersonne.getEmail());
        verify(personneRepository, times(1)).save(personne);
    }

    @Test
    public void testUpdatePersonneThrowsExceptionWhenNotFound() {
        when(personneRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            personneService.updatePersonne(1L, personne);
        });

        assertEquals("Personne not found with id: 1", exception.getMessage());
    }
}
