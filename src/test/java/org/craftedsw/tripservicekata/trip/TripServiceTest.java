package org.craftedsw.tripservicekata.trip;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceTest {

	// tu devrais installer moreunit => ok
	private static final User USER_NOT_LOGGED = null;
	private static final User GUEST_USER = null;
	private static final User LOGGED_USER = new User();

	@Test
	public void should_raise_exception_when_no_user_logged() {
		
		//GIVEN
		TripService tripservice = new TripService();

		assertThatThrownBy(() -> {
			// WHEN
			tripservice.getTripsByUser(GUEST_USER,USER_NOT_LOGGED);
			
			//THEN
		}).isInstanceOf(UserNotLoggedInException.class);

	}

	@Test
	public void should_return_no_trip_when_current_user_is_not_friend_with_logged_user() {
		TripService tripservice = new TripService();

		User notFriendUser = new User();
		List<Trip> tripsUser = tripservice.getTripsByUser(notFriendUser,LOGGED_USER);

		assertThat(tripsUser).isEmpty();
	}

	@Test
	public void should_return_trips_when_current_user_is_friend_with_logged_user() {

		Trip paris = new Trip();
		Trip london = new Trip();
		List<Trip> expectedTrips = Arrays.asList(paris, london);
		
		User friendUser = buildFriendUser(expectedTrips);
		
		TripDAO mockTripDAO = makeFakeDao(expectedTrips, friendUser);
		
		TripService tripservice = new TripService(mockTripDAO);

		List<Trip> tripsUser = tripservice.getTripsByUser(friendUser,LOGGED_USER);

		assertThat(tripsUser).containsExactlyElementsOf(expectedTrips);
	}

	private TripDAO makeFakeDao(List<Trip> expectedTrips, User friendUser) {
		TripDAO mockTripDAO = mock(TripDAO.class);
		when(mockTripDAO.retrieveripsByUser(friendUser)).thenReturn(expectedTrips);
		return mockTripDAO;
	}

	private User buildFriendUser(List<Trip> expectedTrips) {
		User friendUser = new User();
		for (Trip trip : expectedTrips) {
			friendUser.addTrip(trip);
		}
		friendUser.addFriend(LOGGED_USER);
		return friendUser;
	}

}
