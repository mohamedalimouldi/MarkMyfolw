package tn.esprit.usecase.Service;

import tn.esprit.usecase.entity.Tags;

import java.util.List;

public interface ITAGS {

    public Tags createTags(Tags tags) ;
    public List<Tags> getAllTags() ;

    }
