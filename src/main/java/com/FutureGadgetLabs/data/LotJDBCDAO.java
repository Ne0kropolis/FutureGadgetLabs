package com.FutureGadgetLabs.data;

import com.FutureGadgetLabs.domain.Lot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
public class LotJDBCDAO implements DAO<Lot> {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LotJDBCDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Lot get(int id) {
        String query = "SELECT * FROM  LOT WHERE Lot_Id = ?;";
        return ( jdbcTemplate.queryForObject(query, new LotMapper (), id));
    }

    @Override
    public List<Lot> getAll() {
        String query = "SELECT * FROM LOT;";
        return (jdbcTemplate.query(query, new LotMapper()));
    }

    /**
     * Retrieves a pricing scheme number for a given lot id.
     * @param id: lotId
     * @return int pricingSchemeNumber
     */
    public Integer getPricingSchemeNumber(int id) {
        String query = "SELECT * FROM LOT WHERE Lot_Id = " + id;
        return Objects.requireNonNull(this.jdbcTemplate.queryForObject(query, new LotMapper())).getPricingSchemeNumber();
    }

    @Override
    public void insert(Lot lot) {
        String query = "INSERT INTO LOT VALUES (?,?,?,?,?)";
        jdbcTemplate.update(
                query,
                lot.getLotId(),
                lot.getPricingSchemeNumber(),
                lot.getLotName(),
                lot.getLotAddress(),
                lot.getlotCapacity()
        );
    }

    @Override
    public void batchInsert(List<Lot> lotList) throws SQLException {
        String query = "INSERT INTO LOT VALUES (NEXT VALUE FOR lot_id_seq,?,?,?,?);";
        this.jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setInt(1, lotList.get(i).getPricingSchemeNumber());
                preparedStatement.setString(2, lotList.get(i).getLotName());
                preparedStatement.setString(3, lotList.get(i).getLotAddress());
                preparedStatement.setInt(4, lotList.get(i).getlotCapacity());
            }

            @Override
            public int getBatchSize() {
                return lotList.size();
            }
        });
    }

    @Override
    public void update(Lot lot) {
        String query = "UPDATE LOT SET Pricing_Scheme_Number=?, Lot_Name=?, Lot_Address=?, Lot_Capacity=? WHERE Lot_Id=?";
        jdbcTemplate.update(
                query,
                lot.getPricingSchemeNumber(),
                lot.getLotName(),
                lot.getLotAddress(),
                lot.getlotCapacity(),
                lot.getLotId()
        );
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM LOT WHERE Lot_Id=?";
        jdbcTemplate.update(query, id);
    }

    /**
     * Rowmapper for Lot queries
     */
    public class LotMapper implements RowMapper<Lot> {
        public Lot mapRow(ResultSet rs, final int rowNum) throws SQLException {
            return new Lot(
                    rs.getInt("Lot_Id"),
                    rs.getInt("Pricing_Scheme_Number"),
                    rs.getString("Lot_Name"),
                    rs.getString("Lot_Address"),
                    rs.getInt("Lot_Capacity"));
        }
    }
}
