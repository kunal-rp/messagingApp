package msg.services;

import com.google.inject.AbstractModule;
import msg.services.auth.SSOAuthService;
import msg.services.auth.MainSSOAuthService;
import msg.services.database.DatabaseService;
import msg.services.database.MainDatabaseService;

public class AppInjector extends AbstractModule {

	@Override
	protected void configure() {
		bind(SSOAuthService.class).to(MainSSOAuthService.class);
		bind(DatabaseService.class).to(MainDatabaseService.class);
	}

}