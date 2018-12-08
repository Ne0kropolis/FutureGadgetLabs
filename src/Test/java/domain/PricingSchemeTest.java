package domain;

import com.FutureGadgetLabs.domain.Pricing;
import com.FutureGadgetLabs.domain.PricingScheme;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PricingSchemeTest {
    private PricingScheme pricingScheme;

    private List<Pricing> expectedPricingList;

    private List<Pricing> durations;

    @Before
    public void initialize() {
        expectedPricingList = new ArrayList<>();
        expectedPricingList.add(new Pricing(1,1, 10, "M", 10));
        expectedPricingList.add(new Pricing(2,1, 1, "h", 80));
        expectedPricingList.add(new Pricing(3,1, -1, "L", 100));

        pricingScheme = new PricingScheme(expectedPricingList);

        durations =  pricingScheme.getDurations();
    }

    @Test
    public void shouldGetConvertDurationGranularityCorrectly() {
        assertEquals(60, durations.get(2).getDuration());
    }

    @Test
    public void shouldGetCorrectLostFee() {
        assertEquals(100, pricingScheme.getLostTicketPrice(), 0);
    }

    @Test
    public void shouldReturnSortedListOfDurations() {
        assertEquals(-1, durations.get(0).getDuration());
        assertEquals(10, durations.get(1).getDuration());
        assertEquals(60, durations.get(2).getDuration());
    }
}
