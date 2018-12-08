package data;

import com.FutureGadgetLabs.data.LotJDBCDAO;
import com.FutureGadgetLabs.domain.Lot;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context.xml")
@FlywayTest
public class LotJDBCDAOTest {

    @Autowired
    LotJDBCDAO lotJDBCDAO;

    @Test
    public void shouldGetASingleLotObject() {
        assertEquals(100,lotJDBCDAO.get(100).getLotId());
    }

    @Test
    public void shouldGetAllLots() {
        assertEquals(2, lotJDBCDAO.getAll().size());
    }

    @Test
    public void shouldInsertASingleLot() {
        lotJDBCDAO.insert(new Lot(200, 3, "Zombieland", "Texas", 300));

        assertEquals(300, lotJDBCDAO.get(200).getlotCapacity());
    }

    @Test
    public void shouldBatchInsertLots() throws SQLException {
        List<Lot> lotList = new ArrayList<>();
        lotList.add(new Lot( 3, "The Yard", "Nowhere", 20));
        lotList.add(new Lot( 3, "The Pit", "Nevada", 30));
        lotList.add(new Lot( 3, "Boons", "Utah", 40));

        lotJDBCDAO.batchInsert(lotList);
        assertEquals(30, lotJDBCDAO.get(103).getlotCapacity());
    }

    @Test
    public void shouldUpdateALot() {
        Lot newLot = new Lot(100, 4, "Cindys Garage", "HammerHead", 400);

        lotJDBCDAO.update(newLot);

        assertEquals(4, lotJDBCDAO.get(100).getPricingSchemeNumber());
    }

    @Test
    public void shouldDeleteALot() {
        int expectedTotal = lotJDBCDAO.getAll().size()-1;

        lotJDBCDAO.delete(102);

        assertEquals(expectedTotal, lotJDBCDAO.getAll().size());
    }
}
