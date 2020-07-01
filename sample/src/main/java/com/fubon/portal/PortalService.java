package com.fubon.portal;


import java.sql.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fubon.portal.repository.Agent;
import com.fubon.portal.repository.AgentMapper;
import com.fubon.portal.repository.AnnounceInfo;
import com.fubon.portal.repository.AnnounceInfoMapper;
import com.fubon.portal.repository.MyFavoriteMenu;
import com.fubon.portal.repository.MyFavoriteMenuMapper;
import com.github.pagehelper.Page;

@Service
public class PortalService {
	
	@Autowired
	private AgentMapper agentRepository;
			
	@Autowired
	private MyFavoriteMenuMapper myFavoriteMenuRepository;
	
	@Autowired
	private AnnounceInfoMapper announceInfoRepository;
		
	public List<Agent> findAgent(String agentId, String checkDate) {
		
		return this.agentRepository.findAgent(agentId, checkDate);
	}

	public MyFavoriteMenu AddMyFavoriteMenu(MyFavoriteMenu myFavorite) {
		this.myFavoriteMenuRepository.insert(myFavorite);
		return myFavorite;
	}

	public void deleteFavoriteMenu(String userId, Long itemId) {

		this.myFavoriteMenuRepository.deleteByPrimaryKey(userId, itemId);
		
	}

	public MyFavoriteMenu updateMyFavoriteMenu(MyFavoriteMenu myFavorite) {
		
		this.myFavoriteMenuRepository.updateByPrimaryKey(myFavorite);
		return myFavorite;
	}

	public  Page<AnnounceInfo> findAnnounceInfoList(boolean showExpired, String checkDate) {
		return this.announceInfoRepository.findAnnounceInfoList(showExpired, checkDate);
	}

	
}
