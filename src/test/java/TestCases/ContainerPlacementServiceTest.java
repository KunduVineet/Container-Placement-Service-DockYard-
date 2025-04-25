package TestCases;

import model.*;
import org.junit.jupiter.api.Test;
import service.ContainerPlacementService;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

// Unit tests for ContainerPlacementService
class ContainerPlacementServiceTest {
    private final ContainerPlacementService service = new ContainerPlacementService();

    @Test
    void testPlaceContainerWithValidSlot() {
        // Create a container
        Container container = new Container();
        container.setId("C1");
        container.setSize("small");
        container.setNeedCold(false);
        container.setX(1);
        container.setY(1);

        // Create two slots
        Slot slot1 = new Slot();
        slot1.setX(1);
        slot1.setY(2);
        slot1.setSizeCap("small");
        slot1.setHasColdUnit(false);
        slot1.setOccupied(false);

        Slot slot2 = new Slot();
        slot2.setX(2);
        slot2.setY(2);
        slot2.setSizeCap("big");
        slot2.setHasColdUnit(true);
        slot2.setOccupied(false);

        // Create request
        PlacementRequest request = new PlacementRequest();
        request.setContainer(container);
        request.setYardMap(List.of(slot1, slot2));

        // Test the service
        PlacementResponse response = service.placeContainer(request);

        // Verify: should pick slot1 (closer, distance = 1)
        assertNull(response.getError());
        assertEquals("C1", response.getContainerId());
        assertEquals(1, response.getTargetX());
        assertEquals(2, response.getTargetY());
    }

    @Test
    void testPlaceContainerNoSuitableSlot() {
        // Create a container needing cold storage and big size
        Container container = new Container();
        container.setId("C1");
        container.setSize("big");
        container.setNeedCold(true);
        container.setX(1);
        container.setY(1);

        // Create an unsuitable slot
        Slot slot = new Slot();
        slot.setX(1);
        slot.setY(2);
        slot.setSizeCap("small"); // Too small
        slot.setHasColdUnit(false); // No cold storage
        slot.setOccupied(false);

        // Create request
        PlacementRequest request = new PlacementRequest();
        request.setContainer(container);
        request.setYardMap(List.of(slot));

        // Test the service
        PlacementResponse response = service.placeContainer(request);

        // Verify: should return error
        assertEquals("no suitable slot", response.getError());
        assertNull(response.getContainerId());
        assertNull(response.getTargetX());
        assertNull(response.getTargetY());
    }
}