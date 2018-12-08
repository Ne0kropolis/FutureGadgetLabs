package com.FutureGadgetLabs.service;

import com.FutureGadgetLabs.data.LotJDBCDAO;
import com.FutureGadgetLabs.domain.Lot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * Lot Service Implementation
 */

@Service
public class LotService {

    private final LotJDBCDAO lotJdbcDao;

    @Autowired
    public LotService(@Qualifier("lotJDBCDAO") LotJDBCDAO lotJdbcDao) {
        this.lotJdbcDao = lotJdbcDao;
    }

    public List getAllLots() {
        return this.lotJdbcDao.getAll();
    }

    public Lot getLotById(int id) {
        return this.lotJdbcDao.get(id);
    }

    public void createLot(Lot lot) { this.lotJdbcDao.insert(lot); }

    public void createLots(List<Lot> lotList) throws SQLException { this.lotJdbcDao.batchInsert(lotList); }

    public void updateLot(Lot lot) { this.lotJdbcDao.update(lot); }

    public void deleteLot(int id) { this.lotJdbcDao.delete(id); }
}
