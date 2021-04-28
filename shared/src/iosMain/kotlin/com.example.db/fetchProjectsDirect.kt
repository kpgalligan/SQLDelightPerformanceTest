package com.example.db

import co.touchlab.sqliter.DatabaseConfiguration
import co.touchlab.sqliter.DatabaseConnection
import co.touchlab.sqliter.createDatabaseManager
import co.touchlab.sqliter.withTransaction
import com.squareup.sqldelight.drivers.native.wrapConnection



internal class NativeConnection {
    val db:DatabaseConnection

    init {
        val dbManager = createDatabaseManager(DatabaseConfiguration(
            name = "test.db",
            version = TestDatabase.Schema.version,
            create = { connection ->
                wrapConnection(connection) { TestDatabase.Schema.create(it) }
            },
            upgrade = { connection, oldVersion, newVersion ->
                wrapConnection(connection) { TestDatabase.Schema.migrate(it, oldVersion, newVersion) }
            }
        ))
        db = dbManager.createSingleThreadedConnection()
    }
}

internal val conn = NativeConnection()

actual fun createProjectsDirect() {
    conn.db.withTransaction {
        val stmt = conn.db.createStatement("INSERT INTO project (name) VALUES (?)")
        try {
            for (i in 0 until 100000) {
                stmt.bindString(1, "Project $i")
                stmt.execute()
            }
        } finally {
            stmt.finalizeStatement()
        }
    }
}

actual fun fetchProjectsDirect() {
//    conn.db.createStatement("SELECT * FROM project")
}

