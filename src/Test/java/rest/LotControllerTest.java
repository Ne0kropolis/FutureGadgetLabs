package rest;


import com.FutureGadgetLabs.domain.Lot;
import com.FutureGadgetLabs.rest.LotController;
import com.FutureGadgetLabs.service.LotService;
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

public class LotControllerTest {

    @Mock
    private LotService mockLotService;

    private LotController lotController;

    private Lot expectedLot, lotTwo;
    private List<Lot> lotList;

    @Before
    public void initialize() {
        MockitoAnnotations.initMocks(this);

        lotController = new LotController(mockLotService);

        expectedLot = new Lot(100, 1, "Cid's Test Garage", "Hammerhead", 30);
        lotTwo = new Lot(101, 1, "Cindys Test Garage", "Hammerhead", 10);

        lotList = new ArrayList<>();
        lotList.add(expectedLot);
        lotList.add(lotTwo);
    }

    @Test
    public void shouldGetALotById() {
        when(mockLotService.getLotById(anyInt())).thenReturn(expectedLot);

        assertEquals(expectedLot, lotController.getLotById(1));
    }

    @Test
    public void shouldGetAllLots() {
        when (mockLotService.getAllLots()).thenReturn(lotList);

        assertEquals(lotList, lotController.getAllLots());
    }

    @Test
    public void shouldCreateALot() {

        ArgumentCaptor lotCapture = ArgumentCaptor.forClass(Lot.class);

        doNothing().when(mockLotService).createLot((Lot) lotCapture.capture());

        lotController.createLot(expectedLot);

        assertEquals(expectedLot, lotCapture.getValue());
    }

    @Test
    public void shouldCreateABatchOfLots() throws SQLException {

        ArgumentCaptor lotCapture = ArgumentCaptor.forClass(Lot.class);

        doNothing().when(mockLotService).createLots((List<Lot>) lotCapture.capture());

        lotController.createLots(lotList);

        assertEquals(lotList, lotCapture.getValue());
    }

    @Test
    public void shouldUpdateALot() {

        ArgumentCaptor lotCapture = ArgumentCaptor.forClass(Lot.class);

        doNothing().when(mockLotService).updateLot((Lot) lotCapture.capture());

        lotController.updateLot(expectedLot);

        assertEquals(expectedLot, lotCapture.getValue());
    }

    @Test
    public void shouldCallCorrectIdWhenDeletingALot() {

        ArgumentCaptor lotCapture = ArgumentCaptor.forClass(Integer.class);

        doNothing().when(mockLotService).deleteLot((Integer) lotCapture.capture());

        lotController.deleteLot(1);

        assertEquals(1, lotCapture.getValue());

    }

}
