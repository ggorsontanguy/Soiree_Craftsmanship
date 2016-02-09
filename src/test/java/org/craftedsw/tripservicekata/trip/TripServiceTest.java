package org.craftedsw.tripservicekata.trip;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.TripService;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripServiceTest {

	// tu devrais installer moreunit => ok


	@Test
	public void should_raise_exception_when_no_user_logged() {
		TripService tripservice = new TestableTripService();

		assertThatThrownBy(() -> {
			tripservice.getTripsByUser(null);
		}).isInstanceOf(UserNotLoggedInException.class);

	}
	
	public final class TestableTripService extends TripService {
		@Override
		User getLoggedUser() {
			return null;
		}
	}

	// @Test
	// public void should_behavior_when_scenario() {
	//
	// }

}
