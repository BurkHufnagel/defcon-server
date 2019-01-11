package com.dbs.defcon.server.board;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dbs.defcon.server.model.Session;


@CrossOrigin
@RestController
@RequestMapping("/v1")
public class SessionController {
	public static int DEFAULT_LEVEL;
	private Map<Long, Session> sessions = new HashMap<>();
	
		
	public SessionController() {
	}
		
	
	@GetMapping("/sessions")
	public String createSession(HttpServletRequest request) {
		Session session = new Session(request); 
		sessions.put(session.getId(), session);
		
		return session.getId().toString();
	}
	
	
	@GetMapping("/sessions/{id}/data")
	public Session getSessionData(@PathVariable("id") Long id) {
		Session session = sessions.get(id);

		return session;
	}
	
	
	@GetMapping("/sessions/{id}/vote/{level}")
	public String vote(@PathVariable("id") Long id, @PathVariable("level") int level) {
		Session session = sessions.get(id);
		
		System.out.println("Vote received. Session: " + session.getId() + " Vote: " + level);
		
		try {
			session.addVote(level);
		} catch( IllegalArgumentException exception) {
			return exception.getMessage();
		}
		
		return "Vote " + level + " accepted.";
	}
}
