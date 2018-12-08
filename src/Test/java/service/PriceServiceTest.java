package service;

import com.FutureGadgetLabs.domain.PricingScheme;
import com.FutureGadgetLabs.service.PriceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = "classpath:/spring/application-context.xml")
public class PriceServiceTest {

    @Autowired
    private PriceService priceService;

    @Test
    public void shouldInsertAPricingSchemeIntoMap() {
        PricingScheme pricingScheme = priceService.checkPricingScheme(100);
        pricingScheme.getDurations();

        assertEquals(200, pricingScheme.getLostTicketPrice());
    }

    @Test
    public void shouldCalculateCorrectTicketPrice() {
        assertEquals(100, priceService.calculateTicketPrice(15, 100, false));
    }

    @Test
    public void shouldCalculatePriceForLostTicket() {
        assertEquals(200, priceService.calculateTicketPrice(9, 100, true));
    }

    @Test
    public void shouldCalculatePriceForTicketDurationOutOfBounds() {
        assertEquals(100, priceService.calculateTicketPrice(900, 100, false));
    }
}
