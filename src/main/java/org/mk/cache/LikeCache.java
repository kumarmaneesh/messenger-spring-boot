package org.mk.cache;

import org.mk.model.Like;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1556780 on 1/18/2019.
 */
@Component
public class LikeCache {
    private List<Like> likes = new ArrayList<>();

    public List<Like> getAllLikes(){
        return likes;
    }

    public void addLike(Like like){
        likes.add(like);
    }

    public void remoteLike(Like like){
        likes.remove(like);
    }
}
