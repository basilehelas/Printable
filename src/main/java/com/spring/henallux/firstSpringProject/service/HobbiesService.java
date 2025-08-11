package com.spring.henallux.firstSpringProject.service;

import com.spring.henallux.firstSpringProject.model.Hobby;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service // ⚠️ annotation indispensable
public class HobbiesService {
    private ArrayList<Hobby> hobbies;

    public HobbiesService() {
        hobbies = new ArrayList<>();
        hobbies.add(new Hobby("1", "Sport"));
        hobbies.add(new Hobby("2", "Nature"));
        hobbies.add(new Hobby("3", "Reading"));
        hobbies.add(new Hobby("4", "Music"));
        hobbies.add(new Hobby("5", "Rien faire"));
    }

    public ArrayList<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(ArrayList<Hobby> hobbies) {
        this.hobbies = hobbies;
    }
}
