package com.app.quantitymeasurement.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.app.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.app.quantitymeasurement.exception.DatabaseException;
import com.app.quantitymeasurement.util.ConnectionPool;

public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {

    private final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public void save(QuantityMeasurementEntity e) {

        String sql = "INSERT INTO quantity_measurement_entity " +
                "(operation, result, error, this_measurement_type) VALUES (?, ?, ?, ?)";

        try (Connection conn = pool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getOperation());
            ps.setString(2, e.getResult());
            ps.setString(3, e.getError());
            ps.setString(4, e.getMeasurementType());

            ps.executeUpdate();

        } catch (Exception ex) {
            throw new DatabaseException("Save failed", ex);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {

        List<QuantityMeasurementEntity> list = new ArrayList<>();

        String sql = "SELECT * FROM quantity_measurement_entity";

        try (Connection conn = pool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new QuantityMeasurementEntity(
                        rs.getString("operation"),
                        rs.getString("result"),
                        rs.getString("error"),
                        rs.getString("this_measurement_type")
                ));
            }

        } catch (Exception e) {
            throw new DatabaseException("Fetch failed", e);
        }

        return list;
    }

    @Override
    public List<QuantityMeasurementEntity> getByOperation(String op) {
        return getAllMeasurements();
    }

    @Override
    public List<QuantityMeasurementEntity> getByMeasurementType(String type) {
        return getAllMeasurements();
    }

    @Override
    public int getTotalCount() {
        return getAllMeasurements().size();
    }

    @Override
    public void deleteAll() {
        try (Connection conn = pool.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM quantity_measurement_entity")) {
            ps.executeUpdate();
        } catch (Exception e) {
            throw new DatabaseException("Delete failed", e);
        }
    }

    @Override
    public String getPoolStatistics() {
        return pool.getStats();
    }

    @Override
    public void releaseResources() {}
}