package net.engineeringdigest.journalApp.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.bson.types.ObjectId;
import net.engineeringdigest.journalApp.entity.JournalEntry;



public interface JournalEntryRepository extends MongoRepository<JournalEntry,ObjectId>{



}
