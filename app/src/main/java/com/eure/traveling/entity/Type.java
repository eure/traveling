package com.eure.traveling.entity;

public enum Type {
    ANIMATED(0),
    ATTACHMENTS(1),
    DEBUTS(2),
    PLAYOFFS(3),
    REBOUNDS(4),
    TEAMS(5);

    private final int id;

    Type(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static Type valueOf(int id) {
        for (Type num : values()) {
            if (num.getId() == id) {
                return num;
            }
        }
        throw new IllegalArgumentException("no such enum object for the id: " + id);
    }

}
