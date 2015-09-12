package net.xangelcorp.screen.register;

import net.xangelcorp.R;

import flow.HasParent;
import flow.Layout;
import mortar.Blueprint;
import net.xangelcorp.screen.main.MainScreen;
import net.xangelcorp.screen.splash.SplashScreen;

@Layout(R.layout.view_register)
public class RegisterScreen implements Blueprint, HasParent<SplashScreen> {
	@Override
	public String getMortarScopeName() {
		return getClass().getName();
	}

	@Override
	public Object getDaggerModule() {
		return new Module();
	}

	@Override
	public SplashScreen getParent() {
		return new SplashScreen();
	}

	@dagger.Module(
			injects = RegisterView.class,
			addsTo = MainScreen.Module.class
	)
	class Module {}
}
