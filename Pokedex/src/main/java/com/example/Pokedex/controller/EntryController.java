package com.example.Pokedex.controller;

import com.example.Pokedex.model.Entry;
import com.example.Pokedex.repository.PokedexRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EntryController {

    @Autowired
    private PokedexRepo pokedex;

    //Returns all pokedex entries
    @GetMapping("/getAllEntries")
    public ResponseEntity<List<Entry>> getAllEntries(){
        try {
            List<Entry> entryList = new ArrayList<>();
            pokedex.findAll().forEach(entryList::add);

            if(entryList.isEmpty()){
                return new ResponseEntity<>(entryList, HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(entryList, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Return a specific entry based on id
    @GetMapping("/getEntryById/{id}")
    public ResponseEntity<Entry> getEntryById(@PathVariable Long id){
        Optional<Entry> entryData = pokedex.findById(id);
        if (entryData.isPresent()){
            return new ResponseEntity<>(entryData.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Adds an entry to the pokedex
    @PostMapping("/addEntry")
    public ResponseEntity<Entry> addEntry(@RequestBody Entry entry){
        Entry entryObj = pokedex.save(entry);

        return new ResponseEntity<>(entryObj, HttpStatus.OK);
    }

    //Updates the name and description of an entry
    @PostMapping("/updateEntryById/{id}")
    public ResponseEntity<Entry> updateEntryByID(@PathVariable Long id,@RequestBody Entry newEntry){
        Optional<Entry> oldEntryData = pokedex.findById(id);

        if (oldEntryData.isPresent()){
            Entry updatedEntry = oldEntryData.get();
            //updatedEntry.setId(newEntry.getId());
            updatedEntry.setName(newEntry.getName());
            updatedEntry.setDescription(newEntry.getDescription());

            Entry entryObj = pokedex.save(updatedEntry);
            return new ResponseEntity<>(entryObj, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //Deletes an entry based on id
    @DeleteMapping("/deleteEntryById/{id}")
    public ResponseEntity<HttpStatus> deleteEntryByID(@PathVariable Long id){
        pokedex.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
