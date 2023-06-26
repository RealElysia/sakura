package com.Shy.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Shy.blog.entity.player;
import com.Shy.blog.mapper.playMapper;
import java.util.List;
@Service
public class playServices {
    @Autowired
    private playMapper play;

    public player getInfo(int id){
        return play.getInfo(id);
    }

    public int deleteInfo(int id){
        return play.deleteInfo(id);
    }

    public int updateInfo(player pa){
        return play.updateInfo(pa);
    }

    public int saveNew(player pa){
        return play.saveNew(pa);
    }

    public List<player> All(){return  play.selectAll();}
}
