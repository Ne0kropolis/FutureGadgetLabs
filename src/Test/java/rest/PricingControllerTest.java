package rest;

import com.FutureGadgetLabs.domain.Pricing;
import com.FutureGadgetLabs.rest.PricingController;
import com.FutureGadgetLabs.service.PricingService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class PricingControllerTest {

    @Mock
    private PricingService mockPricingService;

    private PricingController pricingController;

    private Pricing expectedPricing;
    private Pricing pricingTwo;

    private List<Pricing> pricingList;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);

        pricingController = new PricingController(mockPricingService);

        expectedPricing = new Pricing(1, 1, 10, "M", 30);
        pricingTwo = new Pricing(1, 1, 30, "H", 80);

        pricingList = new ArrayList<>();
        pricingList.add(expectedPricing);
        pricingList.add(pricingTwo);
    }

    @Test
    public void shouldGetAPricingById() {
        when(mockPricingService.getPricingById(anyInt())).thenReturn(expectedPricing);

        assertEquals(expectedPricing, pricingController.getPricingById(1));
    }

    @Test
    public void shouldGetAllPricings() {
        when (mockPricingService.getAllPricings()).thenReturn(pricingList);

        assertEquals(pricingList, pricingController.getAllPricings());
    }

    @Test
    public void shouldCreateAPricing() {

        ArgumentCaptor pricingCapture = ArgumentCaptor.forClass(Pricing.class);

        doNothing().when(mockPricingService).createPricing((Pricing) pricingCapture.capture());

        pricingController.createPricing(expectedPricing);

        assertEquals(expectedPricing, pricingCapture.getValue());
    }

    @Test
    public void shouldCreateABatchOfPricings() throws SQLException {

        ArgumentCaptor pricingCapture = ArgumentCaptor.forClass(Pricing.class);

        doNothing().when(mockPricingService).createPricings((List<Pricing>) pricingCapture.capture());

        pricingController.createPricings(pricingList);

        assertEquals(pricingList, pricingCapture.getValue());
    }

    @Test
    public void shouldUpdateAPricing() {

        ArgumentCaptor pricingCapture = ArgumentCaptor.forClass(Pricing.class);

        doNothing().when(mockPricingService).updatePricing((Pricing) pricingCapture.capture());

        pricingController.updatePricing(expectedPricing);

        assertEquals(expectedPricing, pricingCapture.getValue());
    }

    @Test
    public void shouldCallCorrectIdWhenDeletingAPricing() {

        ArgumentCaptor pricingCapture = ArgumentCaptor.forClass(Integer.class);

        doNothing().when(mockPricingService).deletePricing((Integer) pricingCapture.capture());

        pricingController.deletePricing(1);

        assertEquals(1, pricingCapture.getValue());

    }
}
