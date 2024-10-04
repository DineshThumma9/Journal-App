package net.engineeringdigest.journalApp.service;
import org.springframework.beans.factory.annotation.Autowired;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.repository.*;
import org.springframework.stereotype.Component;
import java.util.List;
import org.bson.types.ObjectId;
import java.util.Optional;
import net.engineeringdigest.journalApp.entity.User;
import java.io.IOException;
import java.io.*;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JournalEntryService {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final Logger logger  = LoggerFactory.getLogger(JournalEntryService.class);
    @Autowired
    private JournalEntryRepository  journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry,String userName)
    {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry save = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(save);
            userService.saveNewEntry(user);
        }
        catch(Exception e)
        {
            logger.error("Error while saving the entry",e);
            System.out.println(e);
        }
    }

    public void saveEntry(JournalEntry journalEntry)
    {

        journalEntryRepository.save(journalEntry);

    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }


    public Optional<JournalEntry> findById(ObjectId id)
    {
        return journalEntryRepository.findById(id);
    }


    @Transactional
    public boolean deleteById(ObjectId id,String userName)
    {
         boolean removed = false;
        try
        {
            User user = userService.findByUserName(userName);
             removed = user.getJournalEntries().removeIf(journalEntry -> journalEntry.getId().equals(id));
            if(removed)
            {
                userService.saveNewEntry(user);
                journalEntryRepository.deleteById(id);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("Error while deleting the entry");
        }
        return  removed;

    }


    public List<JournalEntry> findByUsername(String username) {
        User user = userService.findByUserName(username);
        return user.getJournalEntries();
    }

}
