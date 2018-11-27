/*
 *     Copyright (C) 2018  Fredrik Jambrén
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.jambren.pcbview.model

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldEqualTo
import org.junit.jupiter.api.Test

class ConsoleStoreTest {

    @Test
    fun `append line should add lines last to the list`() {
        val consoleStore = createStore(2, 5)
        consoleStore.lines shouldEqual listOf("Item1", "Item2")
    }

    @Test
    fun `get last should return the last added line`() {
        val consoleStore = createStore(2, 5)
        consoleStore.getLast() shouldBeEqualTo "Item2"
    }

    @Test
    fun `get last should return the given amount of lines`() {
        val consoleStore = createStore(4, 5)
        consoleStore.getLast(2) shouldEqual listOf("Item3", "Item4")
    }

    @Test
    fun `appending to a full buffer should start dropping lines from the front of the queue`() {
        val consoleStore = createStore(7, 5)
        consoleStore.lines shouldEqual listOf("Item3", "Item4", "Item5", "Item6", "Item7")
    }

    @Test
    fun `clear should empty the store`() {
        val consoleStore = createStore(7, 5)
        consoleStore.clear()
        consoleStore.lines.size shouldEqualTo 0
    }

    private fun createStore(numItems: Int, maxSize: Int): ConsoleStore {
        val store = ConsoleStore(maxSize)
        for (i in 1..numItems) {
            store.append("Item$i")
        }
        return store
    }
}