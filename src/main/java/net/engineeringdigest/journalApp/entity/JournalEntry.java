package net.engineeringdigest.journalApp.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import org.bson.types.ObjectId;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.NonNull;
import lombok.NoArgsConstructor;


@Document(collection = "journal_entries")

@Getter
@Setter
@Data
@NoArgsConstructor

public class JournalEntry {

    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;
}
