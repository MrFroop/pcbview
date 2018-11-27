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

class ConsoleStore(private val maxSize: Int = 100) {
    private val _lines = mutableListOf<String>()
    val lines: List<String>
        get() = _lines.toList()

    fun clear() {
        _lines.clear()
    }

    fun append(line: String) {
        if (_lines.size >= maxSize) {
            _lines.removeAt(0)
        }
        _lines.add(line)
    }

    fun getLast() = _lines[lines.lastIndex]

    fun getLast(numLines: Int) = _lines.takeLast(numLines).toList()
}
