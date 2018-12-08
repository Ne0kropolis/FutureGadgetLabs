package data;

import com.FutureGadgetLabs.data.PricingJDBCDAO;
import com.FutureGadgetLabs.domain.Pricing;
import com.FutureGadgetLabs.domain.PricingScheme;
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
public class PricingJDBCDAOTest {

    @Autowired
    PricingJDBCDAO pricingJDBCDAO;

    @Test
    public void shouldGetASinglePricingObject() {
        assertEquals(pricingJDBCDAO.get(1).getPricingId(), 1);
    }

    @Test
    public void shouldGetAllPricings() {
        assertEquals(13, pricingJDBCDAO.getAll().size());
    }

    @Test
    public void shouldGetPricingScheme() {
        PricingScheme pricingScheme = pricingJDBCDAO.getPricingScheme(1);
        pricingScheme.getDurations();
        assertEquals(100, pricingScheme.getLostTicketPrice());
    }

    @Test
    public void shouoldGetAPricingSchemeList() {
        List<Pricing> pricingList = pricingJDBCDAO.getPricingBySchemeNumber(2);
        assertEquals(200, pricingList.get(4).getPrice());
    }

    @Test
    public void shouldInsertASinglePricing() {
        pricingJDBCDAO.insert(new Pricing(20, 3, 40, "H", 300));

        assertEquals(300, pricingJDBCDAO.get(20).getPrice());
    }

    @Test
    public void shouldBatchInsertPricings() throws SQLException {
        List<Pricing> pricingList = new ArrayList<>();
        pricingList.add(new Pricing( 3, 5, "M", 20));
        pricingList.add(new Pricing( 3, 10, "M", 30));
        pricingList.add(new Pricing( 3, 15, "M", 40));

        pricingJDBCDAO.batchInsert(pricingList);
        assertEquals(40, pricingJDBCDAO.get(13).getPrice());
    }

    @Test
    public void shouldUpdateAPricing() {
        Pricing newPricing = new Pricing(1, 4, 30, "M", 400);

        pricingJDBCDAO.update(newPricing);

        assertEquals(4, pricingJDBCDAO.get(1).getPricingSchemeNumber());
    }

    @Test
    public void shouldDeleteAPricing() {
       int expectedTotal = pricingJDBCDAO.getAll().size()-1;

       pricingJDBCDAO.delete(4);

       assertEquals(expectedTotal, pricingJDBCDAO.getAll().size());
    }
}
