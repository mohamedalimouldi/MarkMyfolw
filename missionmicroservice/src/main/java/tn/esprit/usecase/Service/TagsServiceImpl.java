package tn.esprit.usecase.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.usecase.Repository.SavedSearchesRepository;
import tn.esprit.usecase.Repository.TagsRepository;
import tn.esprit.usecase.entity.Tags;
import tn.esprit.usecase.entity.savedSearches;

import java.util.List;

@Service
public class TagsServiceImpl implements ITAGS{

    @Autowired
    private TagsRepository tagsRepository;

    public List<Tags> getAllTags() {
        return tagsRepository.findAll();
    }

    public Tags createTags(Tags tags) {
        return tagsRepository.save(tags);
    }

}
