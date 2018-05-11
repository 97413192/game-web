package com.game.business.mapper;

import java.util.List;

import com.game.game.model.Player;

public interface PlayerRealNameApplyMapper {

	public void insertRealNameApply(Player player);
	
	public List<Player> getAllRealNameApply(int applyState);

	public Player getSinglePlayer(Integer gameId);

	public void updateApplyState(Player player);

	public List<Player> getAllApplyRecords();
}
