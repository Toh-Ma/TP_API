package TPAPI.tp5.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Address {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreation(Date creation) {
        this.creation = creation;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreation() {
        return creation;
    }

    public String getContent() {
        return content;
    }

    @Id
    @GeneratedValue
    private Long id;
    private Date creation;
    private String content;
    private String autor;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
