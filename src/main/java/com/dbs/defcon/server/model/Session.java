package com.dbs.defcon.server.model;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;


public class Session {
	private static final int MAX_VOTE_LEVEL = 5;
	
	private static long lastId = 23L;
	
    private long id;
    
    private int voteLevel = 0;
    private int voteTotal = 0;    
    private int voteCount = 0;
    private List<Vote> votes = new ArrayList<Vote>();

	String ownerId;
	
	
	public Session(HttpServletRequest request) {
		this( getOwnerId(request) );;
	}
	
	
	public Session(String ownerId) {
		this.ownerId = ownerId;
		this.id = lastId++;
	}
	
	
	private static String getOwnerId(HttpServletRequest request) {
		return request.getRemoteAddr() + ":" + request.getRemotePort();
	}
	
	
	public String getOwnerId() {
		return this.ownerId;
	}
	
	
	public Long getId() {
		return this.id;
	}
	
	
	public void addVote(int level) {
		if (level > MAX_VOTE_LEVEL) {
			throw new IllegalArgumentException("Error: Submitted level is greater than " + MAX_VOTE_LEVEL + ".");
		}
		
		if( level < 0) {
			throw new IllegalArgumentException("Error: Submitted level must be positive.");
		}
		
		votes.add( new Vote(level) );
		this.voteTotal += level;
		this.voteCount++;
		this.voteLevel = Math.round(voteTotal / voteCount);
	}
	
	
	public List<Vote> getVotes() {
		return votes;
	}
	
	
	public int getLevel() {
		return voteLevel;
	}
	
	
	public int getVoteCount() {
		return this.voteCount;
	}
}
