package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {
    private PostRepository postRepo;
    private HashtagRepository hashtagRepo;

    public PostController(PostRepository postRepo, HashtagRepository hashtagRepo) {
        this.postRepo = postRepo;
        this.hashtagRepo = hashtagRepo;
    }

    @GetMapping("/{id}")
    public String displaySinglePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postRepo.findById(id).get());
        return "single-post-template";
    }

    @PostMapping("/{id}/addhashtag")
    public String addHashtag(@PathVariable long id, @RequestParam String hashtag){
        Post myPost = postRepo.findById(id).get();
        Optional<Hashtag> optHashtag = hashtagRepo.findByName(hashtag);

        if( optHashtag.isPresent() && myPost.getHashtags().contains(optHashtag.get())){
            return "redirect:/posts/" + id;
        } else if (optHashtag.isPresent()){
            myPost.addHashtag(optHashtag.get());
            postRepo.save(myPost);
        }
        else{
            Hashtag hashtag1 = new Hashtag(hashtag);
            hashtagRepo.save(hashtag1);
            myPost.addHashtag(hashtag1);
            postRepo.save(myPost);
        }
        return "redirect:/posts/" +id;
    }


}
