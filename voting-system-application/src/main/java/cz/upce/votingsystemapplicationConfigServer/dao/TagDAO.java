package cz.upce.votingsystemapplicationConfigServer.dao;

import cz.upce.votingsystemapplicationConfigServer.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDAO  extends JpaRepository<Tag, Long> {
//     Tag getTagOnSuggestion(Suggestion suggestionId);
     Tag findOneBySuggestion_Id(Long suggestionId);
    }

