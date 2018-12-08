package service;

import com.FutureGadgetLabs.domain.Pricing;
import com.FutureGadgetLabs.service.PricingService;
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
@ContextConfiguration(locations = "classpath:/spring/application-context.xml")
public class PricingServiceTest {

    @Autowired
    private PricingService pricingService;

    @Test
    public void shouldGetASingleTicket() {
        assertEquals(1, pricingService.getPricingById(1).getPricingId());
    }

    @Test
    public void shouldGetAllTickets() {
        assertEquals(10, pricingService.getAllPricings().size());
    }

    @Test
    public void shouldCreateASingleTicket() {
        pricingService.createPricing(new Pricing(20, 3, 40, "H", 300));
        assertEquals(20, pricingService.getPricingById(20).getPricingId());
    }

    @Test
    public void shouldCreateAListOfPricings() throws SQLException {
        List<Pricing> pricingList = new ArrayList<>();
        pricingList.add(new Pricing( 3, 5, "M", 20));
        pricingList.add(new Pricing( 3, 10, "M", 30));
        pricingList.add(new Pricing( 3, 15, "M", 40));

        pricingService.createPricings(pricingList);

        assertEquals(13, pricingService.getAllPricings().size());
    }

    @Test
    public void shouldUpdateASingleTicket() {
        Pricing updatePricing = new Pricing(1, 4, 30, "M", 400);

        pricingService.updatePricing(updatePricing);

        assertEquals(400, pricingService.getPricingById(1).getPrice());
    }

    @Test
    public void shouldDeleteASingleTicket() {
        pricingService.deletePricing(2);

        assertEquals(10, pricingService.getAllPricings().size());
    }
}
