package org.example.ebeans;

import org.springframework.stereotype.Component;

import com.avaje.ebean.config.CurrentUserProvider;

/**
 * Provides the current user to EbeanServer.
 */
@Component
public class CurrentUserProviderImpl implements CurrentUserProvider {

	@Override
	public Object currentUser() {
		return "testuser";
	}
}
