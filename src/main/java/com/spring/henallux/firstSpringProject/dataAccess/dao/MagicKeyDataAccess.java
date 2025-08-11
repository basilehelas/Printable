package com.spring.henallux.firstSpringProject.dataAccess.dao;


import java.util.ArrayList;

public interface MagicKeyDataAccess {
    /**
     * Retourne toutes les clés magiques (valeurs de la colonne identifiante magicvalue).
     */
    ArrayList<String> getMagicKeys();
}


