package ru.micron;

public class CatalogItem {
    private final String name;
    private String description;

    public CatalogItem(String name) {
        this.name = name;
    }

    public CatalogItem(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "CatalogItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
