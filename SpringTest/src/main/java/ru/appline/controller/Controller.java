package ru.appline.controller;

import org.springframework.web.bind.annotation.*;
import ru.appline.logic.CompassModel;
import ru.appline.logic.Direction;
import ru.appline.logic.Pet;
import ru.appline.logic.PetModel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {
    private static final PetModel petModel = PetModel.getInstance();
    private static final AtomicInteger newID = new AtomicInteger(1);
    private static final CompassModel compass = CompassModel.getInstance();

    @PostMapping(value = "/createPet", consumes = "application/json", produces = "application/json")
    public String createPet(@RequestBody Pet pet) {
        petModel.add(pet,newID.getAndIncrement());
        return "Успешно создан";
    }

    @GetMapping(value = "/getAll", produces = "application/json")
    public Map<Integer, Pet> getAll() {
        return petModel.getAll();
    }

    @GetMapping(value = "/getPet", consumes = "application/json", produces = "application/json")
    public Pet getPet(@RequestBody Map<String, Integer> id) {
        return petModel.getFromList(id.get("id"));
    }

    @DeleteMapping(value = "/removePet", consumes = "application/json", produces = "application/json")
    public Map<Integer, Pet> removePet(@RequestBody Map<String, Integer> id) {
        petModel.removePet(id.get("id"));
        return petModel.getAll();
    }

    @PutMapping(value = "/updatePet", consumes = "application/json", produces = "application/json")
    public Map<Integer, Pet> updatePet(@RequestBody Map<String, String> newPet) {
        petModel.updatePet(Integer.parseInt(newPet.get("id")),newPet.get("name"), newPet.get("type"), Integer.parseInt(newPet.get("age")));
        return petModel.getAll();
    }

    @PostMapping(value = "/postCompass", consumes = "application/json", produces = "application/json")
    public Map<String, Direction> postCompass(@RequestBody Map<String, String> postCompass) {

        String dirN[] = postCompass.get("North").split("-");
        compass.add("North", dirN[0], dirN[1]);

        String dirNE[] = postCompass.get("North-East").split("-");
        compass.add("North-East", dirNE[0], dirNE[1]);

        String dirE[] = postCompass.get("East").split("-");
        compass.add("East", dirE[0], dirE[1]);

        String dirSE[] = postCompass.get("South-East").split("-");
        compass.add("South-East",  dirSE[0], dirSE[1]);

        String dirS[] = postCompass.get("South").split("-");
        compass.add("South", dirS[0], dirS[1]);

        String dirSW[] = postCompass.get("South-West").split("-");
        compass.add("South-West", dirSW[0], dirSW[1]);

        String dirW[] = postCompass.get("West").split("-");
        compass.add("West", dirW[0], dirW[1]);

        String dirNW[] = postCompass.get("North-West").split("-");
        compass.add("North-West", dirNW[0], dirNW[1]);

        return compass.getAll();
    }

    @PostMapping(value = "/getDirection", consumes = "application/json", produces = "application/json")
    public Map<String, String> getDir(@RequestBody Map<String, Integer> degree) {
        Map<String, String> Direction = new HashMap<String, String>();
        int request = degree.get("Degree");
        Direction.put("Direction", compass.getDir(request));
        return Direction;
    }
}
