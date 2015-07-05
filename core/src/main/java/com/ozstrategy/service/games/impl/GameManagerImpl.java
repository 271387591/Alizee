package com.ozstrategy.service.games.impl;

import com.ozstrategy.dao.BaseDao;
import com.ozstrategy.dao.games.GameDao;
import com.ozstrategy.model.games.Game;
import com.ozstrategy.service.impl.BaseManagerImpl;
import com.ozstrategy.service.games.GameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("gameManager")
public class GameManagerImpl extends BaseManagerImpl<Game> implements GameManager {
    @Autowired
    private GameDao gameDao;

    @Override
    public BaseDao<Game> baseDao() {
        return gameDao;
    }
}
