package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	TripDAO tripDAO;
	
	// Parametrized Constructor
	TripService() {
		this (new TripDAO());
	}
	
	TripService(TripDAO tripDAO) {
		this.tripDAO = tripDAO;
	}
	
	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		
		// Extract & Override Call
		User loggedUser = getLoggedUser();
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
		
		// Feature Envy
		boolean isFriend = user.isFriendWith(loggedUser);
		
		List<Trip> tripList = new ArrayList<Trip>();
		if (isFriend) {
			tripList = tripDAO.retrieveripsByUser(user);
		}
		return tripList;

	}

	User getLoggedUser() {
		User loggedUser = UserSession.getInstance().getLoggedUser();
		return loggedUser;
	}

}
