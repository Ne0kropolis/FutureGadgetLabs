import com.FutureGadgetLabs.domain.Pricing;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingTest {
    private Pricing expectedPricing;
    private Pricing pricing;

    @Before
    public void initialize() {
        expectedPricing = new Pricing(1, 1, 10, "M", 10);
        pricing = new Pricing(1, 1, 10, "M", 10);
    }

    @Test
    public void shouldReturnPricingId() {
        assertEquals(expectedPricing.getPricingId(), pricing.getPricingId());
    }

    @Test
    public void shouldReturnPricingSchemeNumber() {
        assertEquals(expectedPricing.getPricingSchemeNumber(), pricing.getPricingSchemeNumber());
    }

    @Test
    public void shouldReturnDuration() {
        assertEquals(expectedPricing.getDuration(), pricing.getDuration());
    }

    @Test
    public void shouldReturnGranularity() {
        assertEquals(expectedPricing.getGranularity(), pricing.getGranularity());
    }

    @Test
    public void shouldReturnPrice() {
        assertEquals(expectedPricing.getPrice(), pricing.getPrice());
    }

    @Test
    public void shouldSortByDuration() {
        List<Pricing> pricings = new ArrayList<>();
        pricings.add(new Pricing(2, 1, 20, "M", 20));
        pricings.add(pricing);

        pricings.sort(Pricing.sortByDuration);

        assertEquals(pricings.get(0).getDuration(), 10);
    }
}
