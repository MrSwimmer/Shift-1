package com.example.server.repository

import com.example.server.db.dbQuery
import com.example.server.db.table.Persons
import com.example.server.db.table.toNote
import com.example.server.db.table.toPerson
import com.example.server.model.CreatePersonDto
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class PersonRepository {
    suspend fun getAll() =
        dbQuery {
            Persons.selectAll().map { it.toPerson() }
        }

    suspend fun getPage(start: Long, size: Int) =
        dbQuery {
            Persons.select { Persons.id.greater(start) }
                .limit(size).map { it.toPerson() }
        }

    suspend fun add(createPersonDto: CreatePersonDto) {
        dbQuery {
            Persons.insert { insertStatement ->
                insertStatement[name] = createPersonDto.name
                insertStatement[surname] = createPersonDto.surname
                insertStatement[age] = createPersonDto.age
                insertStatement[occupation] = createPersonDto.occupation
            }
        }
    }

    suspend fun delete(id: Long) {
        dbQuery {
            Persons.deleteWhere {
                Persons.id.eq(id)
            }
        }
    }
}