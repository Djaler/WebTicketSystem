package com.moracle.webticketsystem.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by dmitry on 8/10/2016.
 */
@Entity
@Table(name = "attachment")
public class Attachment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "path", length = 110)
    private String path;

    @Column(name = "size")
    private int size;

    public Attachment(int id, String path, int size) {
        this(path, size);
        this.id = id;
    }

    public Attachment(String path, int size) {
        this();
        this.path = path;
        this.size = size;
    }

    public Attachment() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        Path path = Paths.get(getPath());
        return path.getName(path.getNameCount() - 1).toString();
    }
}
