package org.craftedsw.tripservicekata.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.trip.TripService;
import org.junit.Test;

public class TripServiceTest {

	// tu devrais installer moreunit => ok

	@Test
	public void should_raise_exception_when_no_user_logged() {
		TripService tripservice = new TripService();

		assertThatThrownBy(() -> {
			tripservice.getTripsByUser(null);
		}).isInstanceOf(UserNotLoggedInException.class);

	}

	// @Test
	// public void should_behavior_when_scenario() {
	//
	// }

}
