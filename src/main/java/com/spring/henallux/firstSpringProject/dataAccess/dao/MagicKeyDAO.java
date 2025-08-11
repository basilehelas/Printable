package com.spring.henallux.firstSpringProject.dataAccess.dao;


import com.spring.henallux.firstSpringProject.dataAccess.entity.MagicKeyEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.MagicKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@Transactional // toutes les méthodes sont transactionnelles; readOnly précisé au besoin
public class MagicKeyDAO implements MagicKeyDataAccess {

    private final MagicKeyRepository magicKeyRepository;

    @Autowired // ou préférer l'injection par constructeur (ci-dessous)
    public MagicKeyDAO(MagicKeyRepository magicKeyRepository) {
        this.magicKeyRepository = magicKeyRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public ArrayList<String> getMagicKeys() {
        // findAll() vient du JpaRepository
        return magicKeyRepository.findAll()
                .stream()
                .map(MagicKeyEntity::getMagicvalue) // adapte au nom exact du getter
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

