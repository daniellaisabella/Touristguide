package org.example.touristguide.repository;

import org.example.touristguide.model.City;
import org.example.touristguide.model.Tag;
import org.example.touristguide.model.TouristAttraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TouristRepository {

    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    private final JdbcTemplate jdbcTemplate;

    public TouristRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Retrieve all tourist attractions from the database
    public List<TouristAttraction> getAttractions() {
        String sql = """
                     SELECT TOURISTATTRACTION.ATTRACTION_ID, TOURISTATTRACTION.NAME, TOURISTATTRACTION.DESCRIPTION, CITY.NAME AS CITY_NAME
                     FROM TOURISTATTRACTION
                     JOIN CITY ON TOURISTATTRACTION.CITY_ID = CITY.CITY_ID
                     """;

        RowMapper<TouristAttraction> rowMapper = (rs, rowNum) -> new TouristAttraction(
                rs.getString("NAME"),
                rs.getString("DESCRIPTION"),
                City.valueOf(rs.getString("CITY_NAME")),
                getTagsForAttraction(rs.getInt("ATTRACTION_ID"))
        );

        return jdbcTemplate.query(sql, rowMapper);
    }

    // Retrieve a single attraction by name
    public TouristAttraction getAttractionByName(String name) {
        String sql = """
        SELECT TOURISTATTRACTION.ATTRACTION_ID, TOURISTATTRACTION.NAME, TOURISTATTRACTION.DESCRIPTION, CITY.NAME AS CITY_NAME
        FROM TOURISTATTRACTION
        JOIN CITY ON TOURISTATTRACTION.CITY_ID = CITY.CITY_ID
        WHERE TOURISTATTRACTION.NAME = ?
    """;

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{name}, (rs, rowNum) -> new TouristAttraction(
                    rs.getString("NAME"),
                    rs.getString("DESCRIPTION"),
                    City.valueOf(rs.getString("CITY_NAME")),
                    getTagsForAttraction(rs.getInt("ATTRACTION_ID"))
            ));
        } catch (Exception e) { // Catch when attraction is not found
            return null;
        }
    }

    public void saveAttraction(TouristAttraction attraction) {
        String insertAttractionSql = """
        INSERT INTO TOURISTATTRACTION (NAME, DESCRIPTION, CITY_ID)
        VALUES (?, ?, (SELECT CITY_ID FROM CITY WHERE NAME = ?))
        """;

        try {
            jdbcTemplate.update(insertAttractionSql, attraction.getName(), attraction.getDescription(), attraction.getCity().name());
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("An attraction with the name '" + attraction.getName() + "' already exists.");
        }

        // Insert tags for the new attraction
        String insertTagSql = """
        INSERT INTO ATTRACTION_TAG (ATTRACTION_ID, TAG_ID)
        VALUES (
            (SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = ?),
            (SELECT TAG_ID FROM TAG WHERE NAME = ?)
        )
        """;

        for (Tag tag : attraction.getTagList()) {
            jdbcTemplate.update(insertTagSql, attraction.getName(), tag.name());
        }
    }

    // Update an existing attraction
    public void updateAttraction(TouristAttraction updatedAttraction) {
        // Update the attraction's main data (description & city)
        String sql = """
                 UPDATE TOURISTATTRACTION
                 SET DESCRIPTION = ?, CITY_ID = (SELECT CITY_ID FROM CITY WHERE NAME = ?)
                 WHERE NAME = ?
                 """;
        jdbcTemplate.update(sql, updatedAttraction.getDescription(), updatedAttraction.getCity().name(), updatedAttraction.getName());

        // Simulate object replacement: Remove old tags
        String deleteTagsSql = """
                           DELETE FROM ATTRACTION_TAG 
                           WHERE ATTRACTION_ID = (SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = ?)
                           """;
        jdbcTemplate.update(deleteTagsSql, updatedAttraction.getName());

        // Re-add all tags (same behavior as replacing the whole object in memory)
        String insertTagSql = """
                          INSERT INTO ATTRACTION_TAG (ATTRACTION_ID, TAG_ID)
                          VALUES (
                              (SELECT ATTRACTION_ID FROM TOURISTATTRACTION WHERE NAME = ?),
                              (SELECT TAG_ID FROM TAG WHERE NAME = ?)
                          )
                          """;
        for (Tag tag : updatedAttraction.getTagList()) {
            jdbcTemplate.update(insertTagSql, updatedAttraction.getName(), tag.name());
        }
    }

    // Delete an attraction by name
    public TouristAttraction deleteAttraction(String name) {
        TouristAttraction attractionToDelete = getAttractionByName(name);
        if (attractionToDelete != null) {
            String sql = "DELETE FROM TOURISTATTRACTION WHERE NAME = ?";
            jdbcTemplate.update(sql, name);
        }
        return attractionToDelete;
    }

    // Fetch all tags for a given attraction
    private List<Tag> getTagsForAttraction(int attractionId) {
        String sql = """
                     SELECT TAG.NAME 
                     FROM TAG
                     JOIN ATTRACTION_TAG ON TAG.TAG_ID = ATTRACTION_TAG.TAG_ID
                     WHERE ATTRACTION_TAG.ATTRACTION_ID = ?
                     """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> Tag.valueOf(rs.getString("NAME")), attractionId);
    }
}