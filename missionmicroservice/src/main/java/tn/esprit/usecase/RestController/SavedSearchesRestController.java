package tn.esprit.usecase.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.usecase.Service.SavedSearchesServiceImpl;
import tn.esprit.usecase.entity.savedSearches;

import java.util.List;

@RestController
@RequestMapping("/savedsearches")
public class SavedSearchesRestController {

    @Autowired
    private SavedSearchesServiceImpl savedSearchesService;

    @GetMapping("")
    public List<savedSearches> getAllSavedSearches() {
        return savedSearchesService.getAllSavedSearches();
    }

    @PostMapping("/matching")
    public List<String> getAllMatchingSavedSearches(@RequestBody List<String> keywords) {
        return savedSearchesService.getAllMatchingl(keywords);
    }

    @GetMapping("/{id}")
    public savedSearches getSavedSearchById(@PathVariable Integer id) {
        return savedSearchesService.getSavedSearchById(id);
    }

    @GetMapping("/user/{id}")
    public List<savedSearches> getByUser(@PathVariable Integer id) {
        return savedSearchesService.getByUser(id);
    }

    @PostMapping("")
    public savedSearches createSavedSearch(@RequestBody savedSearches savedSearch) {
        return savedSearchesService.createSavedSearch(savedSearch);
    }

    @PutMapping("/{id}")
    public savedSearches updateSavedSearch(@PathVariable Integer id, @RequestBody savedSearches savedSearch) {
        return savedSearchesService.updateSavedSearch(id, savedSearch);
    }

    @DeleteMapping("/{id}")
    public void deleteSavedSearch(@PathVariable Integer id) {
        savedSearchesService.deleteSavedSearch(id);
    }
}
