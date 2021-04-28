package com.example.db

import comexampledb.Project

class DatabaseTest {

    val driver = DriverFactory().createDriver()
    val database = TestDatabase(driver)
    val queries = database.dataQueries

    fun clearTable() {
        queries.clearProjectsTable()
    }

    fun createProjects() {
        database.transaction {
            for (i in 0 until 100000) {
                queries.createProject("Project $i")
            }
        }
    }

    fun fetchProjects(): List<Project> {
        return queries.fetchProjects().executeAsList()
    }

    fun createProjectsDirectEx() {
        createProjectsDirect()
    }
}

expect fun createProjectsDirect()

expect fun fetchProjectsDirect()