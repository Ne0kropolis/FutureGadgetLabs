package domain;

import com.FutureGadgetLabs.domain.Lot;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LotTest {
    private Lot expectedLot;
    private Lot lot;

    @Before
    public void initialize() {
        expectedLot = new Lot(100, 1, "Cids Garage", "HammerHead", 10);
        lot = new Lot(100, 1, "Cids Garage", "HammerHead", 10);
    }

    @Test
    public void shouldReturnLotId() {
        assertEquals(expectedLot.getLotId(), lot.getLotId());
    }

    @Test
    public void shouldReturnLotSchemeNumber() {
        assertEquals(expectedLot.getPricingSchemeNumber(), lot.getPricingSchemeNumber());
    }

    @Test
    public void shouldReturnDuration() {
        assertEquals(expectedLot.getLotName(), lot.getLotName());
    }

    @Test
    public void shouldReturnGranularity() {
        assertEquals(expectedLot.getLotAddress(), lot.getLotAddress());
    }

    @Test
    public void shouldReturnPrice() {
        assertEquals(expectedLot.getlotCapacity(), lot.getlotCapacity());
    }

}
