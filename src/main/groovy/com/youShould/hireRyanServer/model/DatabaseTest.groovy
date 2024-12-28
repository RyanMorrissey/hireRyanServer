package com.youShould.hireRyanServer.model

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Entity
class DatabaseTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id

    String value
    Date dateCreated
    Date dateUpdated
    boolean isDeleted

    void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated ?: new Date()
    }

    void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated ?: new Date()
    }
}

public interface DatabaseTestRepository extends JpaRepository<DatabaseTest, Long> {}