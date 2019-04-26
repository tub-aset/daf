/*
 * doorsbridge - A library for Java to Doors interaction.
 * Copyright (C) 2016 Jonas Winkler
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.jpwinkler.daf.doorsdb.bridge;

public class DoorsRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DoorsRuntimeException(final String message) {
        super(message);
    }

    public DoorsRuntimeException(final Throwable cause) {
        super(cause);
    }

    public DoorsRuntimeException() {
        super();
    }

}
