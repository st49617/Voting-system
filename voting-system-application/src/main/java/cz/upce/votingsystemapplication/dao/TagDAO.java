package cz.upce.votingsystemapplication.dao;

import cz.upce.votingsystemapplication.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDAO  extends JpaRepository<Tag, Long> {
//     Tag getTagOnSuggestion(Suggestion suggestionId);
     Tag findOneBySuggestion_Id(Long suggestionId);
    }

