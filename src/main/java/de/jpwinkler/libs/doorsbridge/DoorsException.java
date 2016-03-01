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
package de.jpwinkler.libs.doorsbridge;

/**
 * A {@link DoorsException} is thrown when a DXL script aborts its normal
 * execution using the 'throw()' function provided in the file
 * 'dxl/pre/exception_handling.dxl'. The exception instance will contain a
 * message indicating the kind of error, which is provided by the dxl script.
 *
 * @author jonwink
 *
 */
public class DoorsException extends Exception {

    private static final long serialVersionUID = 1L;

    public DoorsException() {
        super();
    }

    public DoorsException(final String message) {
        super(message);
    }

}
