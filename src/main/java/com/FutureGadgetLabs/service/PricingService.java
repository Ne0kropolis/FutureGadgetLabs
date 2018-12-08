package com.FutureGadgetLabs.service;

import com.FutureGadgetLabs.data.PricingJDBCDAO;
import com.FutureGadgetLabs.domain.Pricing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 *  Pricing Service Implementation.
 */

@Service
public class PricingService {
    private final PricingJDBCDAO pricingJdbcDao;

    @Autowired
    public PricingService(@Qualifier("pricingJDBCDAO") PricingJDBCDAO pricingJdbcDao) {
        this.pricingJdbcDao = pricingJdbcDao;
    }

    public List getAllPricings() { return (this.pricingJdbcDao.getAll()); }

    public Pricing getPricingById(int id) { return (this.pricingJdbcDao.get(id)); }

    public void createPricing(Pricing pricing) { this.pricingJdbcDao.insert(pricing); }

    public void createPricings(List<Pricing> pricingList) throws SQLException {this.pricingJdbcDao.batchInsert(pricingList);}

    public void updatePricing(Pricing pricing) { this.pricingJdbcDao.update(pricing);}

    public void deletePricing(int id) { this.pricingJdbcDao.delete(id);}

}
