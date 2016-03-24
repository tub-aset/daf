package de.jpwinkler.libs.doorsbridge;

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
