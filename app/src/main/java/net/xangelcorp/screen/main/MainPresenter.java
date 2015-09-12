package net.xangelcorp.screen.main;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.squareup.otto.Bus;

import android.os.Bundle;
import flow.Parcer;
import mortar.Blueprint;
import timber.log.Timber;
import net.xangelcorp.SharedPreferencesKeys;
import net.xangelcorp.model.User;
import net.xangelcorp.repository.JsonSharedPreferencesRepository;
import net.xangelcorp.screen.home.HomeScreen;
import net.xangelcorp.screen.splash.SplashScreen;
import net.xangelcorp.util.flow.FlowOwner;

@Singleton
public class MainPresenter extends FlowOwner<Blueprint, MainView> {

	private final JsonSharedPreferencesRepository sharedPreferences;
	private final Bus bus;

	@Inject
	public MainPresenter(Parcer<Object> parcer, JsonSharedPreferencesRepository sharedPreferences, Bus bus) {
		super(parcer);
		this.sharedPreferences = sharedPreferences;
		this.bus = bus;
	}

	@Override public void onLoad(Bundle savedInstanceState) {
		Timber.d("Registering with otto to receive bus events");
		bus.register(this);

		super.onLoad(savedInstanceState);
	}

	@Override public void dropView(MainView view) {
		Timber.d("Unregistering with otto");
		bus.unregister(this);

		super.dropView(view);
	}

	@Override
	protected Blueprint getFirstScreen() {
		if (userAccountExists()) {
			return new HomeScreen();
		} else {
			Timber.w("No user account found, redirecting to splash screen");
			return new SplashScreen();
		}
	}

	protected boolean userAccountExists() {
		return sharedPreferences.getObject(SharedPreferencesKeys.USER_ACCOUNT, User.class) != null;
	}
}
