package com.abc.elasticsearch.integration.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.abc.elasticsearch.integration.model.entity.MobileIndex;
import com.abc.elasticsearch.integration.repository.MobileIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MobileIndexService {

    private final MobileIndexRepository repo;

    @Autowired
    GitOperations gitOperations;

    @Autowired
    public MobileIndexService(MobileIndexRepository repo) {
        this.repo = repo;
    }

    public void createMobileIndex (MobileIndex employee) {
        repo.save(employee);
    }

    public MobileIndex getMobileIndexById(String id) {
        Optional<MobileIndex> result = repo.findById(id);
        return result.isPresent() ? result.get() : null;
    }

    public List<MobileIndex> getMobileIndexByInteractionId(String interactionId) throws Exception{
        Iterable<MobileIndex> resultIterable = repo.findByApiinteractionid(interactionId);
        List<MobileIndex> listMobileIndex = new ArrayList<MobileIndex>();
        for (MobileIndex mIndex : resultIterable) {
            mIndex.setGitCodeURL(gitOperations.getRepoURL());
            listMobileIndex.add(mIndex);
        }
        return listMobileIndex;
    }

}