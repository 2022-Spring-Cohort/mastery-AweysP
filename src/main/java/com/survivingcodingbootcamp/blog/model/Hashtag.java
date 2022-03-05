package com.survivingcodingbootcamp.blog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class Hashtag {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
//    public String hashtag;
    @ManyToMany(mappedBy = "hashtags")
    private Collection<Post> posts;

    public Hashtag() {
    }

    public Long getId() {
        return id;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    public Hashtag(String name, Post... posts) {
        this.name = name;
        this.posts = Arrays.asList(posts);
    }
//    public String getHashtag(){
//        return hashtag;
//    }

    public Hashtag(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hashtag hashtag = (Hashtag) o;

        return id != null ? id.equals(hashtag.id) : hashtag.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

}
