//
//import com.itsolutions.equipment_management.models.Equipment;
//import com.itsolutions.equipment_management.models.EtatEquipement;
//import com.itsolutions.equipment_management.repositories.EquipmentRepository;
//import com.itsolutions.equipment_management.services.EquipmentService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class EquipmentServiceTest {
//
//    @Mock
//    private EquipmentRepository equipmentRepository;
//
//    @InjectMocks
//    private EquipmentService equipmentService;
//
//    private Equipment equipment;
//
//    @BeforeEach
//    public void setUp() {
//        equipment = new Equipment();
//        equipment.setId(1L);
//        equipment.setNom("Laptop");
//        equipment.setType("Electronics");
//        equipment.setEtatEquipement(EtatEquipement.valueOf("FONCTIONNEL"));
//    }
//
//    @Test
//    public void testAddEquipment() {
//        when(equipmentRepository.save(any(Equipment.class))).thenReturn(equipment);
//
//        Equipment createdEquipment = equipmentService.addEquipment(equipment);
//
//        assertNotNull(createdEquipment);
//        assertEquals("Laptop", createdEquipment.getNom());
//        verify(equipmentRepository, times(1)).save(equipment);
//    }
//
//    @Test
//    public void testFindByNom() {
//        when(equipmentRepository.findByNom("Laptop")).thenReturn(Arrays.asList(equipment));
//
//        List<Equipment> equipments = equipmentService.findByNom("Laptop");
//
//        assertFalse(equipments.isEmpty());
//        assertEquals(1, equipments.size());
//        assertEquals("Laptop", equipments.get(0).getNom());
//        verify(equipmentRepository, times(1)).findByNom("Laptop");
//    }
//
//    @Test
//    public void testUpdateEquipment() {
//        Equipment updatedDetails = new Equipment();
//        updatedDetails.setNom("Desktop");
//        updatedDetails.setType("Electronics");
//        updatedDetails.setEtatEquipement(EtatEquipement.valueOf("FONCTIONNEL"));
//
//        when(equipmentRepository.findById(1L)).thenReturn(Optional.of(equipment));
//        when(equipmentRepository.save(any(Equipment.class))).thenReturn(updatedDetails);
//
//        Equipment updatedEquipment = equipmentService.updateEquipment(1L, updatedDetails);
//
//        assertNotNull(updatedEquipment);
//        assertEquals("Desktop", updatedEquipment.getNom());
//        verify(equipmentRepository, times(1)).findById(1L);
//        verify(equipmentRepository, times(1)).save(equipment);
//    }
//
//    @Test
//    public void testDeleteEquipment() {
//        doNothing().when(equipmentRepository).deleteById(1L);
//
//        equipmentService.deleteEquipment(1L);
//
//        verify(equipmentRepository, times(1)).deleteById(1L);
//    }
//
//    @Test
//    public void testGetAllEquipments() {
//        when(equipmentRepository.findAll()).thenReturn(Arrays.asList(equipment));
//
//        List<Equipment> equipments = equipmentService.getAllEquipments();
//
//        assertNotNull(equipments);
//        assertEquals(1, equipments.size());
//        verify(equipmentRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testGetEquipmentById() {
//        when(equipmentRepository.findById(1L)).thenReturn(Optional.of(equipment));
//
//        Equipment foundEquipment = equipmentService.getEquipmentById(1L);
//
//        assertNotNull(foundEquipment);
//        assertEquals("Laptop", foundEquipment.getNom());
//        verify(equipmentRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    public void testGetEquipmentByIdNotFound() {
//        when(equipmentRepository.findById(1L)).thenReturn(Optional.empty());
//
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            equipmentService.getEquipmentById(1L);
//        });
//
//        assertEquals("Equipment not found", exception.getMessage());
//        verify(equipmentRepository, times(1)).findById(1L);
//    }
//}
