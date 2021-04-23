package com.quickbase;

import com.quickbase.devint.services.ConcreteStatService;
import com.quickbase.devint.db.DBManager;
import com.quickbase.devint.db.DBManagerImpl;
import com.quickbase.devint.services.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The main method of the executable JAR generated from this repository. This is to let you
 * execute something from the command-line or IDE for the purposes of demonstration, but you can choose
 * to demonstrate in a different way (e.g. if you're using a framework)
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main( String args[] ) {

        logger.info("Starting!");

        DBManager dbm = new DBManagerImpl();

        StatisticService statisticService = new StatisticService(dbm, new ConcreteStatService());
        statisticService.showStatistics();

        dbm.closeConnection();

        logger.info("Stopping!");

    }
}
