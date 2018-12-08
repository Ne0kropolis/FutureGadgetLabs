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
    public void shouldGetASinglePricing() {
        assertEquals(1, pricingService.getPricingById(1).getPricingId());
    }

    @Test
    public void shouldGetAllPricings() {
        assertEquals(13, pricingService.getAllPricings().size());
    }

    @Test
    public void shouldCreateASinglePricing() {
        pricingService.createPricing(new Pricing(30, 3, 40, "H", 300));
        assertEquals(30, pricingService.getPricingById(30).getPricingId());
    }

    @Test
    public void shouldCreateAListOfPricings() throws SQLException {
        List<Pricing> pricingList = new ArrayList<>();
        pricingList.add(new Pricing( 3, 5, "M", 20));
        pricingList.add(new Pricing( 3, 10, "M", 30));
        pricingList.add(new Pricing( 3, 15, "M", 40));

        pricingService.createPricings(pricingList);

        assertEquals(17, pricingService.getAllPricings().size());
    }

    @Test
    public void shouldUpdateASinglePricing() {
        Pricing updatePricing = new Pricing(1, 4, 30, "M", 400);

        pricingService.updatePricing(updatePricing);

        assertEquals(400, pricingService.getPricingById(1).getPrice());
    }

    @Test
    public void shouldDeleteASinglePricing() {
        pricingService.deletePricing(2);

        assertEquals(14, pricingService.getAllPricings().size());
    }
}
