package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.bson.types.ObjectId;

public interface UserRepository extends MongoRepository<User, ObjectId> {
   User findByUsername(String username);

   User deleteByUsername(String username);
}