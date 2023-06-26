package com.Shy.blog.mapper;

import com.Shy.blog.entity.player;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface playMapper {
    player getInfo(int id);

    int saveNew(player pa);

    int updateInfo(player pa);
    int deleteInfo(int id);

    List<player> selectAll();
}
