package com.yangfan.springbootmybatisrabbitmq.entity;

import java.io.Serializable;

public class Order implements Serializable {
    private static final long serialVersionUID = 9111357402963030257L;

    private String id;

    private String name;

    private String message_id;

    public Order() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }
}
