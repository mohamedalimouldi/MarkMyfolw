package tn.esprit.usecase.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.usecase.Service.TagsServiceImpl;
import tn.esprit.usecase.entity.Tags;
import tn.esprit.usecase.entity.savedSearches;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagsRestController {
    @Autowired
    TagsServiceImpl tagsService;

    @GetMapping("")
    public List<Tags> getAllTags() {
        return tagsService.getAllTags();
    }

    @PostMapping("")
    public Tags createTags(@RequestBody Tags tags) {
        return tagsService.createTags(tags);
    }
}
