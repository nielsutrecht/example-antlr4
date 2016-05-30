package com.nibado.example.antlr4.query;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class DataStore {
    private JdbcTemplate template;

    /**
     * Creates and filles an in-memory H2 based data store of projects.
     */
    public DataStore() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem::test;DB_CLOSE_DELAY=-1");

        template = new JdbcTemplate(dataSource);

        fill();
    }

    private void fill() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(DataStore.class.getResourceAsStream("/script.sql")))) {
            reader.lines().forEach(template::execute);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Query the datastore for a list of projects.
     * @param query the SQL SELECT query
     * @param params the query parameters
     * @return a list of projects
     */
    public List<Project> query(String query, Object... params) {
        if(!query.replace(" ", "").contains("id,name,teamlead,budget,startdate,enddate")) {
            throw new IllegalArgumentException("Query " + query + " needs to contain all Project properties.");
        }
        return template.query(query, params, rowMapper());
    }

    private static RowMapper<Project> rowMapper() {
        return (resultSet, i) -> {
            Project p = new Project();
            p.setId(resultSet.getInt(1));
            p.setName(resultSet.getString(2));
            p.setTeamlead(resultSet.getString(3));
            p.setBudget(resultSet.getInt(4));
            p.setStartdate(resultSet.getDate(5).toLocalDate());
            p.setEnddate(resultSet.getDate(6).toLocalDate());

            return p;
        };
    }
}
