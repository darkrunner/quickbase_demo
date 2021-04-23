package com.quickbase.devint.db;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This DBManager implementation provides a connection to the database containing population data.
 *
 * Created by ckeswani on 9/16/15.
 */
public class DBManagerImpl implements DBManager {

    private static final Logger logger = LoggerFactory.getLogger(DBManagerImpl.class);

    private static final String DB_DATA_FILE = "resources/data/citystatecountry.db";

    private static final String DATA_QUERY = "SELECT cntr.CountryName, cast(sum(c.Population) AS INTEGER) AS Total FROM Country cntr\n" +
            "INNER JOIN State s ON  s.CountryId = cntr.CountryId \n" +
            "INNER JOIN City c ON c.StateId = s.StateId\n" +
            "WHERE c.Population IS NOT NULL \n" +
            "AND c.Population > 0 \n" +
            "GROUP BY cntr.CountryId \n" +
            "ORDER BY cntr.CountryName";

    private Connection con = null;

    public Connection getConnection() {
        logger.debug("Getting DB Connection...");
        if (con == null) {
            try {
                con = DriverManager.getConnection("jdbc:sqlite:" + DB_DATA_FILE);
                logger.debug("Opened database successfully");
            } catch (SQLException sqle) {
                logger.error("Exception occurred on opening database connection: {}", sqle);
            }
        }

        return con;
    }

    public List<Pair<String, Integer>> getData() {
        List<Pair<String, Integer>> result = new ArrayList<>();

        Connection con = getConnection();
        try (PreparedStatement stm = con.prepareStatement(DATA_QUERY)) {
            ResultSet resultSet = stm.executeQuery();
            if(resultSet != null) {
                while (resultSet.next()) {
                    result.add(new ImmutablePair<>(resultSet.getString("CountryName"), resultSet.getInt("Total")));
                }
            }
        } catch (SQLException exception) {
            logger.error("Failed to execute DB query! {}", exception);
        }
        return result;
    }

    @Override
    public void closeConnection() {

        logger.debug("Closing database connection!");

        if (con == null) {
            try {
                con.close();
            } catch (SQLException sqle) {
                logger.error("Exception occurred on closing connection: {}", sqle);
            }
        }
    }

}
