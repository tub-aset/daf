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
package de.jpwinkler.daf.bridge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class ItemName implements Comparable<ItemName> {

    private final List<String> pathSegments;

    public ItemName(final String fullName) {
        pathSegments = Arrays.asList(fullName.split("/")).stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());

    }

    public ItemName(final ItemName parent, final String child) {
        pathSegments = new ArrayList<>(parent.pathSegments);
        pathSegments.add(child);
    }

    private ItemName(final List<String> pathSegments) {
        this.pathSegments = pathSegments;
    }

    public String getName() {
        return pathSegments.size() > 0 ? pathSegments.get(pathSegments.size() - 1) : "/";
    }

    public String getPath() {
        return pathSegments.size() > 0 ? "/" + StringUtils.join(pathSegments.subList(0, pathSegments.size() - 1), "/") : "";
    }

    public String getFullName() {
        return "/" + StringUtils.join(pathSegments, "/");
    }

    public ItemName getParent() {
        return pathSegments.size() > 0 ? new ItemName(pathSegments.subList(0, pathSegments.size() - 1)) : null;
    }

    @Override
    public int compareTo(final ItemName o) {
        if (o == null) {
            return -1;
        } else {
            return getFullName().compareTo(o.getFullName());
        }
    }

    public List<String> getPathSegments() {
        return pathSegments;
    }

    @Override
    public String toString() {
        return getName();
    }
}
