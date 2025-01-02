package com.youShould.hireRyanServer.model

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Entity
class DatabaseTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id

    String note
    Date dateCreated
    Date dateUpdated
    boolean isDeleted
    String browserCookie
    int personalId

    void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated ?: new Date()
    }

    void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated ?: new Date()
    }
}

interface DatabaseTestRepository extends JpaRepository<DatabaseTest, Long> {
    @Query("SELECT d FROM DatabaseTest d WHERE d.personalId = :personalId AND d.browserCookie = :browserCookie")
    DatabaseTest findByPersonalIdAndBrowserCookie(@Param("personalId") int personalId, @Param("browserCookie") String browserCookie);

    @Query("SELECT d FROM DatabaseTest d WHERE d.browserCookie = :browserCookie ORDER BY d.personalId ASC")
    List<DatabaseTest> findAllByBrowserCookie(@Param("browserCookie") String browserCookie);
}