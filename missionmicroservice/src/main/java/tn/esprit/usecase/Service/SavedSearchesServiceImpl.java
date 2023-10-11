package tn.esprit.usecase.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.usecase.Repository.ParticipatedUsersRepository;
import tn.esprit.usecase.Repository.SavedSearchesRepository;
import tn.esprit.usecase.entity.savedSearches;

import java.util.List;

@Service
public class SavedSearchesServiceImpl implements ISAVEDSEARCHES{

    @Autowired
    private SavedSearchesRepository savedSearchesRepository;

    public List<savedSearches> getAllSavedSearches() {
        return savedSearchesRepository.findAll();
    }

    public List<savedSearches> getByUser(Integer id) {
        return savedSearchesRepository.findByIdUser(id);
    }

    public List<String> getAllMatchingl(List<String> keywords) {
        return savedSearchesRepository.findByKeywords(keywords);
    }

    public savedSearches getSavedSearchById(Integer id) {
        return savedSearchesRepository.findById(id).orElse(null);
    }

    public savedSearches createSavedSearch(savedSearches savedSearches) {
        return savedSearchesRepository.save(savedSearches);
    }

    public savedSearches updateSavedSearch(Integer id, savedSearches savedSearches) {
        savedSearches existingSavedSearch = savedSearchesRepository.findById(id).orElse(null);
        if (existingSavedSearch != null) {
            existingSavedSearch.setUserEmail(savedSearches.getUserEmail());
            existingSavedSearch.setSavedTitle(savedSearches.getSavedTitle());
            existingSavedSearch.setTags(savedSearches.getTags());
            return savedSearchesRepository.save(existingSavedSearch);
        }
        return null;
    }

    public void deleteSavedSearch(Integer id) {
        savedSearchesRepository.deleteById(id);
    }


}