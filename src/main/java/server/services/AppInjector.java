package msg.services;

import com.google.inject.AbstractModule;
import msg.services.user.UserService;
import msg.services.user.MainUserService;

public class AppInjector extends AbstractModule {

	@Override
	protected void configure() {
		bind(UserService.class).to(MainUserService.class);
	}

}