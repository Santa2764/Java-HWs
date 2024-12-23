package org.example.dao.personalScheduleDAO;

import org.example.dao.ConnectionFactory;
import org.example.exception.ConnectionDBException;
import org.example.model.PersonalSchedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonalScheduleDaoImpl implements PersonalScheduleDao {
    private static final String FIND_ALL_PERSONAL_SCHEDULES = "SELECT * FROM personalschedule";
    private static final String SAVE_PERSONAL_SCHEDULE = """
        INSERT INTO personalschedule(
            idPersonal, workDate, startTime, endTime
        )
            VALUES(?, ?, ?, ?)
        """;
    private static final String UPDATE_PERSONAL_SCHEDULE = """
            UPDATE personalschedule SET
                       idPersonal = ?,
                       workDate = ?,
                       startTime = ?,
                       endTime = ?
            WHERE personalschedule.id = ?
            """;
    private static final String DELETE_ALL_PERSONAL_SCHEDULES = "DELETE FROM personalschedule";
    private static final String DELETE_PERSONAL_SCHEDULE = "DELETE FROM personalschedule WHERE personalschedule.id = ?";

    @Override
    public void save(PersonalSchedule personalSchedule) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_PERSONAL_SCHEDULE)) {
            ps.setInt(1, personalSchedule.getIdPersonal());
            ps.setDate(2, java.sql.Date.valueOf(personalSchedule.getWorkDate()));
            ps.setTime(3, java.sql.Time.valueOf(personalSchedule.getStartTime()));
            ps.setTime(4, java.sql.Time.valueOf(personalSchedule.getEndTime()));
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    @Override
    public void saveMany(List<PersonalSchedule> personalSchedules) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(SAVE_PERSONAL_SCHEDULE)) {
            for (var currPersonalSchedule : personalSchedules) {
                ps.setInt(1, currPersonalSchedule.getIdPersonal());
                ps.setDate(2, java.sql.Date.valueOf(currPersonalSchedule.getWorkDate()));
                ps.setTime(3, java.sql.Time.valueOf(currPersonalSchedule.getStartTime()));
                ps.setTime(4, java.sql.Time.valueOf(currPersonalSchedule.getEndTime()));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(PersonalSchedule personalSchedule) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_PERSONAL_SCHEDULE)) {
            ps.setInt(1, personalSchedule.getIdPersonal());
            ps.setDate(2, java.sql.Date.valueOf(personalSchedule.getWorkDate()));
            ps.setTime(3, java.sql.Time.valueOf(personalSchedule.getStartTime()));
            ps.setTime(4, java.sql.Time.valueOf(personalSchedule.getEndTime()));
            ps.setInt(5, personalSchedule.getId());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(PersonalSchedule personalSchedule) {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_PERSONAL_SCHEDULE)) {
            ps.setInt(1, personalSchedule.getId());
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<PersonalSchedule> findAll() {
        List<PersonalSchedule> resultAddPersonalSchedules = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL_PERSONAL_SCHEDULES);
             ResultSet result = ps.executeQuery()) {

            while (result.next()) {
                PersonalSchedule addPersonalSchedule = new PersonalSchedule();
                addPersonalSchedule.setId(result.getInt(1));
                addPersonalSchedule.setIdPersonal(result.getInt(2));
                addPersonalSchedule.setWorkDate(result.getDate(3).toLocalDate());
                addPersonalSchedule.setStartTime(result.getTime(4).toLocalTime());
                addPersonalSchedule.setEndTime(result.getTime(5).toLocalTime());
                resultAddPersonalSchedules.add(addPersonalSchedule);
            }
            return resultAddPersonalSchedules;
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
        return resultAddPersonalSchedules;
    }

    @Override
    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getInstance().makeConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_ALL_PERSONAL_SCHEDULES)) {
            ps.execute();
        } catch (ConnectionDBException | SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
