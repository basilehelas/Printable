package com.spring.henallux.firstSpringProject.model;

public class MagicKeyForm {

    private String magicKey;

    // Constructeur sans argument (obligatoire pour Spring)
    public MagicKeyForm() {
    }

    // Getter
    public String getMagicKey() {
        return magicKey;
    }

    // Setter
    public void setMagicKey(String magicKey) {
        this.magicKey = magicKey;
    }
}
