package com.ozstrategy.dao.games.impl;

import com.ozstrategy.dao.impl.BaseDaoImpl;
import com.ozstrategy.dao.games.GameDao;
import com.ozstrategy.model.games.Game;
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on 2015-06-28.
*/
@Repository("gameDao")
public class GameDaoImpl extends BaseDaoImpl<Game> implements GameDao{

    public GameDaoImpl() {
    super(Game.class);
    }

}