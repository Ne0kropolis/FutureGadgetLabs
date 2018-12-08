package service;

import com.FutureGadgetLabs.domain.Lot;
import com.FutureGadgetLabs.domain.Ticket;
import com.FutureGadgetLabs.service.LotService;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:/spring/application-context.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LotServiceTest {

    @Autowired
    private LotService lotService;

    @Test
    public void shouldGetASingleLot() {
        assertEquals(100, lotService.getLotById(100).getLotId());
    }

    @Test
    public void shouldGetAllLots() {
        Lot expectedLot = (Lot) lotService.getAllLots().get(1);
        assertEquals(101, expectedLot.getLotId());
    }

    @Test
    public void shouldCreateASingleLot() {
        lotService.createLot(new Lot(110, 2, "The Yard", "Nowhere", 100));
        assertEquals(110, lotService.getLotById(110).getLotId());
    }

    @Test
    public void shouldCreateAListOfLots() throws SQLException {
        int expectedSize = lotService.getAllLots().size()+3;
        List<Lot> lotList = new ArrayList<>();
        lotList.add(new Lot( 3, "The Yard", "Nowhere", 20));
        lotList.add(new Lot( 3, "The Pit", "Nevada", 30));
        lotList.add(new Lot( 3, "Boons", "Utah", 40));

        lotService.createLots(lotList);

        assertEquals(expectedSize, lotService.getAllLots().size());
    }

    @Test
    public void shouldUpdateASingleLot() {
        Lot updateLot = new Lot(100, 2, "Cindys Garage", "Hammerhead", 200);

        lotService.updateLot(updateLot);

        assertEquals(200, lotService.getLotById(100).getlotCapacity());
    }

    @Test
    public void shouldDeleteASingleLot() {
        int expectedSize = lotService.getAllLots().size()-1;
        lotService.deleteLot(110);

        assertEquals(expectedSize, lotService.getAllLots().size());
    }
}
