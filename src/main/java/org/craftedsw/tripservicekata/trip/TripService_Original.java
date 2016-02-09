package org.craftedsw.tripservicekata.trip;

import java.util.ArrayList;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService_Original {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = UserSession.getInstance().getLoggedUser();
		if (loggedUser == null) {
			throw new UserNotLoggedInException();
		}
		
		boolean isFriend = false;
		for (User friend : user.getFriends()) {
			if (friend.equals(loggedUser)) {
				isFriend = true;
				break;
			}
		}
		
		List<Trip> tripList = new ArrayList<Trip>();
		if (isFriend) {
			tripList = TripDAO.findTripsByUser(user);
		}
		return tripList;

	}

}
