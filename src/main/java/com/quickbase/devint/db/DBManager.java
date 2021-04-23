package com.quickbase.devint.db;

import org.apache.commons.lang3.tuple.Pair;

import java.sql.Connection;
import java.util.List;

/**
 * Created by ckeswani on 9/16/15.
 */
public interface DBManager {
    Connection getConnection();
    List<Pair<String, Integer>> getData();
    void closeConnection();
}
