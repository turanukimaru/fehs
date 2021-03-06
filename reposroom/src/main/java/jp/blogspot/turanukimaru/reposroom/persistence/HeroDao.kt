/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.blogspot.turanukimaru.reposroom.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query


/**
 * Data Access Object for the users table.
 */
@Dao
interface HeroDao {

    /**
     * Get a user by id.
     * @return the user from the table with a specific id.
     */
    @Query("SELECT * FROM heroes WHERE nickname = :id")
    fun getHeroById(id: String): RoomArmedHero

    /**
     * Get a user by id.
     * @return the user from the table with a specific id.
     */
    @Query("SELECT * FROM heroes")
    fun allHeroes(): List<RoomArmedHero>

    /**
     * Insert a user in the database. If the user already exists, replace it.

     * @param user the user to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHero(hero: RoomArmedHero)

    /**
     * Delete all users.
     */
    @Query("DELETE FROM heroes")
    fun deleteAllHeroes()

    /**
     * Delete all users.
     */
    @Query("DELETE FROM heroes WHERE nickname = :id")
    fun deleteHero(id: String)
}
