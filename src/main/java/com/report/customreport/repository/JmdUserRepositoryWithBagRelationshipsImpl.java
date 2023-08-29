package com.report.customreport.repository;

import com.report.customreport.domain.JmdUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class JmdUserRepositoryWithBagRelationshipsImpl implements JmdUserRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<JmdUser> fetchBagRelationships(Optional<JmdUser> jmdUser) {
        return jmdUser.map(this::fetchRetailers);
    }

    @Override
    public Page<JmdUser> fetchBagRelationships(Page<JmdUser> jmdUsers) {
        return new PageImpl<>(fetchBagRelationships(jmdUsers.getContent()), jmdUsers.getPageable(), jmdUsers.getTotalElements());
    }

    @Override
    public List<JmdUser> fetchBagRelationships(List<JmdUser> jmdUsers) {
        return Optional.of(jmdUsers).map(this::fetchRetailers).orElse(Collections.emptyList());
    }

    JmdUser fetchRetailers(JmdUser result) {
        return entityManager
            .createQuery("select jmdUser from JmdUser jmdUser left join fetch jmdUser.retailers where jmdUser.id = :id", JmdUser.class)
            .setParameter("id", result.getId())
            .getSingleResult();
    }

    List<JmdUser> fetchRetailers(List<JmdUser> jmdUsers) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, jmdUsers.size()).forEach(index -> order.put(jmdUsers.get(index).getId(), index));
        List<JmdUser> result = entityManager
            .createQuery("select jmdUser from JmdUser jmdUser left join fetch jmdUser.retailers where jmdUser in :jmdUsers", JmdUser.class)
            .setParameter("jmdUsers", jmdUsers)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
