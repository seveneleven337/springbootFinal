package fi.vamk.database.northwind.entity;

import javax.persistence.*;

@Entity
@Table(name = "strings")
public class Strings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "string_id", nullable = false)
    private Integer id;

    @Column(name = "string_data")
    private String stringData;

    public String getStringData() {
        return stringData;
    }

    public void setStringData(String stringData) {
        this.stringData = stringData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}