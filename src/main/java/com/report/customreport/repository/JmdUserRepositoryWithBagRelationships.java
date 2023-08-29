package com.report.customreport.repository;

import com.report.customreport.domain.JmdUser;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface JmdUserRepositoryWithBagRelationships {
    Optional<JmdUser> fetchBagRelationships(Optional<JmdUser> jmdUser);

    List<JmdUser> fetchBagRelationships(List<JmdUser> jmdUsers);

    Page<JmdUser> fetchBagRelationships(Page<JmdUser> jmdUsers);
}
