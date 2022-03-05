package com.survivingcodingbootcamp.blog.controller;


import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HashtagController {
    private HashtagRepository hashtagRepo;

    public HashtagController(HashtagRepository hashtagRepo){
        this.hashtagRepo = hashtagRepo;
    }
    @RequestMapping("/hashtags")
    public String showHashTagPage(Model model){
        model.addAttribute("hashtags", hashtagRepo.findAll());
        return "all-hashtags-template";
    }

    @GetMapping("/hashtag/{id}")
    public String displaySingleHashtag(@PathVariable long id, Model model){
        model.addAttribute("hashtag",hashtagRepo.findById(id).get());
        return "single-hashtag-template";
    }
}
