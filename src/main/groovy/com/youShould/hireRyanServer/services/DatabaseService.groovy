package com.youShould.hireRyanServer.services

import com.youShould.hireRyanServer.dto.DatabaseTestDTO
import com.youShould.hireRyanServer.model.DatabaseTest
import com.youShould.hireRyanServer.model.DatabaseTestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DatabaseService {

    @Autowired
    private DatabaseTestRepository databaseTestRepository

    List<DatabaseTest> getAllDatabaseTestByCookie(String browserCookie) {
        List<DatabaseTest> allDatabaseTestsByCookie = databaseTestRepository.findAllByBrowserCookie(browserCookie)
        return allDatabaseTestsByCookie.findAll { !it.isDeleted }
    }

    Boolean createOrUpdateDatabaseTest(DatabaseTestDTO databaseTestDTO) {
        Boolean wasCreatedOrUpdated = false
        if (databaseTestDTO.personalId == 0) { // create flow
            List<DatabaseTest> allDatabaseTestsByCookie = databaseTestRepository.findAllByBrowserCookie(databaseTestDTO.browserCookie)
            DatabaseTest newDatabaseTest = convertDatabaseTestDTOToNon(databaseTestDTO)
            newDatabaseTest.personalId = allDatabaseTestsByCookie.size() + 1
            databaseTestRepository.save(newDatabaseTest)
            wasCreatedOrUpdated = true
        } else { // update flow
            DatabaseTest existingDatabaseTest = databaseTestRepository.findByPersonalIdAndBrowserCookie(databaseTestDTO.personalId, databaseTestDTO.browserCookie)
            if (existingDatabaseTest != null) {
                existingDatabaseTest.note = databaseTestDTO.note
                existingDatabaseTest.dateUpdated = new Date()
                databaseTestRepository.save(existingDatabaseTest)
                wasCreatedOrUpdated = true
            }
        }
        return wasCreatedOrUpdated
    }

    Boolean deleteDatabaseTestByIdAndCookie(String personalId, String browserCookie) {
        Boolean wasDeleted = false
        DatabaseTest existingDatabaseTest = databaseTestRepository.findByPersonalIdAndBrowserCookie(personalId as int, browserCookie)
        if (existingDatabaseTest != null) {
            existingDatabaseTest.isDeleted = true
            databaseTestRepository.save(existingDatabaseTest)
            wasDeleted = true
        }
        return wasDeleted
    }

    // utility methods
    DatabaseTestDTO convertDatabaseTestToDTO(DatabaseTest dbt) {
        return new DatabaseTestDTO(
                personalId: dbt.personalId,
                note: dbt.note,
                dateCreated: dbt.dateCreated,
                dateUpdated: dbt.dateUpdated,
                browserCookie: dbt.browserCookie
        )
    }

    DatabaseTest convertDatabaseTestDTOToNon(DatabaseTestDTO dbt) {
        return new DatabaseTest(
                personalId: dbt.personalId,
                note: dbt.note,
                dateCreated: dbt.dateCreated ?: new Date(),
                dateUpdated: dbt.dateUpdated ?: new Date(),
                browserCookie: dbt.browserCookie
        )
    }
}
