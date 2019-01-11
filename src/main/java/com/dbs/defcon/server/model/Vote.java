package com.dbs.defcon.server.model;

public class Vote {
	private int level;
	

	public Vote(int level) {
		this.level = level;
	}
	
	
	public int getLevel() {
		return level;
	}
	
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public boolean equals(Object other) {
		if (other instanceof Vote ) {
			return ((Vote)other).getLevel() == this.level;
		}
		
		return false;
	}
}
