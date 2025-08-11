package com.spring.henallux.firstSpringProject.dataAccess.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "magickey")
public class MagicKeyEntity implements Serializable {

    @Id
    @Column(name = "magicvalue")
    private String magicvalue;

    public MagicKeyEntity() {}

    public String getMagicvalue() {
        return magicvalue;
    }

    public void setMagicvalue(String magicvalue) {
        this.magicvalue = magicvalue;
    }
}
