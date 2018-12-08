package com.FutureGadgetLabs.data;

import com.FutureGadgetLabs.domain.Pricing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PricingJDBCDAO implements DAO<Pricing> {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PricingJDBCDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Pricing get(int id) {
        String query = "SELECT * FROM PRICING WHERE Pricing_Id = ?";
        return (jdbcTemplate.queryForObject(query, new PricingMapper(), id));
    }

    @Override
    public List<Pricing> getAll() {
        String query = "SELECT * FROM PRICING";
        return (jdbcTemplate.query(query, new PricingMapper()));
    }

    @Override
    public void insert(Pricing pricing) {
        String query = "INSERT INTO PRICING VALUES(?,?,?,?,?);";
        jdbcTemplate.update(
                query,
                pricing.getPricingId(),
                pricing.getPricingSchemeNumber(),
                pricing.getDuration(),
                pricing.getGranularity(),
                pricing.getPrice()
        );
    }

    @Override
    public void batchInsert(List<Pricing> pricingList) throws SQLException {
        String query = "INSERT INTO PRICING VALUES(NEXT VALUE FOR pricing_id_seq,?,?,?,?);";
        this.jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setInt(1, pricingList.get(i).getPricingSchemeNumber());
                preparedStatement.setInt(2, pricingList.get(i).getDuration());
                preparedStatement.setString(3, pricingList.get(i).getGranularity());
                preparedStatement.setDouble(4, pricingList.get(i).getPrice());
            }

            @Override
            public int getBatchSize() {
                return pricingList.size();
            }
        });
    }

    @Override
    public void update(Pricing pricing) {
        String query = "UPDATE PRICING SET Pricing_Scheme_Number=?, Pricing_Duration=?, Pricing_Granularity=?, Pricing_Price=? WHERE Pricing_ID=?";
        jdbcTemplate.update(
                query,
                pricing.getPricingSchemeNumber(),
                pricing.getDuration(),
                pricing.getGranularity(),
                pricing.getPrice(),
                pricing.getPricingId()
        );
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM PRICING WHERE Pricing_ID=?";
        jdbcTemplate.update(query, id);
    }

    /**
     * Rowmapper for Pricing queries.
     */
    public class PricingMapper implements RowMapper<Pricing> {
        public Pricing mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Pricing(
                    rs.getInt("Pricing_ID"),
                    rs.getInt("Pricing_Scheme_Number"),
                    rs.getInt("Pricing_Duration"),
                    rs.getString("Pricing_Granularity"),
                    rs.getDouble("Pricing_Price"));
        }
    }
}
