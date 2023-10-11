package tn.esprit.usecase.Service;

import tn.esprit.usecase.entity.savedSearches;

import java.util.List;

public interface ISAVEDSEARCHES {
    List<savedSearches> getAllSavedSearches();

    savedSearches getSavedSearchById(Integer id);

    savedSearches createSavedSearch(savedSearches savedSearch);

    savedSearches updateSavedSearch(Integer id, savedSearches savedSearch);

    void deleteSavedSearch(Integer id);

    public List<String> getAllMatchingl(List<String>keywords);

    public List<savedSearches> getByUser(Integer id) ;



}
