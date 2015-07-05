package com.ozstrategy.model.games;

import com.ozstrategy.annotations.Id;
import com.ozstrategy.annotations.Table;
import com.ozstrategy.model.BaseEntity;

/**
 * Created by lihao1 on 6/28/15.
 */
@Table(name = "t_gamebroadcast")
public class GameBroadcast extends BaseEntity {
    private Long id;
    private String title;
    private String content;

}
