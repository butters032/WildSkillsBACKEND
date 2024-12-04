package com.teamwiski.wildskills.database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class AlterTableRunner implements CommandLineRunner {

    private final DataSource dataSource;

    public AlterTableRunner(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            String sql = "ALTER TABLE tblstudent CHANGE avatar avatar MEDIUMBLOB";
            statement.execute(sql);
            System.out.println("Table altered successfully!");
        }
    }
}