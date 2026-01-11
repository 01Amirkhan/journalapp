package net.engineeringtest.journalapp.controller;

import net.engineeringtest.journalapp.controller.service.JournalEntryService;
import net.engineeringtest.journalapp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {//class

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping()
    public List<JournalEntry> getAll()//method
    {
        return journalEntryService.getAll();


    }

    @PostMapping()
    public JournalEntry createEntries(@RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("id/{myid}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myid) {
        return journalEntryService.findById(myid).orElse(null);

    }

    @DeleteMapping("id/{myid}")
    public boolean deleteJournalEntryById(@PathVariable ObjectId myid) {
        journalEntryService.deleteById(myid);
        return true;

    }

    @PutMapping("/id/{id}")
    public JournalEntry updateJournalEntryById(
            @PathVariable ObjectId id,
            @RequestBody JournalEntry newEntry) {

        JournalEntry old = journalEntryService.findById(id).orElse(null);

        if (old == null) {
            return null;   // record not found
        }

        if (newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()) {
            old.setTitle(newEntry.getTitle());
        }

        if (newEntry.getContent() != null && !newEntry.getContent().isEmpty()) {
            old.setContent(newEntry.getContent());
        }

        journalEntryService.saveEntry(old);
        return old;
    }
    }





