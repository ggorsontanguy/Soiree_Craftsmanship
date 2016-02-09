package org.craftedsw.tripservicekata.trip;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.TripService;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceTest {

	// tu devrais installer moreunit => ok
	private static final User USER_NOT_LOGGED = null;
	private static final User GUEST_USER = null;
	private static final User LOGGED_USER = new User();

	@Test
	public void should_raise_exception_when_no_user_logged() {
		TripService tripservice = new TestableTripService(USER_NOT_LOGGED);

		assertThatThrownBy(() -> {
			tripservice.getTripsByUser(GUEST_USER);
		}).isInstanceOf(UserNotLoggedInException.class);

	}

	public final class TestableTripService extends TripService {

		TestableTripService(User loggedUser) {
			super();
			this.loggedUser = loggedUser;
		}

		User loggedUser;

		@Override
		User getLoggedUser() {
			return loggedUser;
		}
	}

	@Test
	public void should_return_no_trip_when_current_user_is_not_friend_with_logged_user() {
		TripService tripservice = new TestableTripService(LOGGED_USER);

		User notFriendUser = new User();
		List<Trip> tripsUser = tripservice.getTripsByUser(notFriendUser);
		
		assertThat(tripsUser).isEmpty();
	}
	
	//@Test
	public void should_return_trip_when_current_user_is_friend_with_logged_user() {
		TripService tripservice = new TestableTripService(LOGGED_USER);

		User friendUser = new User();
		friendUser.addFriend(LOGGED_USER);
		List<Trip> tripsUser = tripservice.getTripsByUser(friendUser);
		
		assertThat(tripsUser).isNotEmpty();
	}

}
