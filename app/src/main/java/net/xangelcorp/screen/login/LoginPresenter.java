package net.xangelcorp.screen.login;

import javax.inject.Inject;

import android.os.Bundle;
import net.xangelcorp.actionbar.ActionBarConfig;
import net.xangelcorp.actionbar.ActionBarOwner;
import net.xangelcorp.util.mortar.BaseViewPresenter;

class LoginPresenter extends BaseViewPresenter<LoginView> {

	private ActionBarOwner actionBarOwner;

	@Inject
	LoginPresenter(ActionBarOwner actionBarOwner) {
		this.actionBarOwner = actionBarOwner;
	}

	@Override
	protected void onLoad(Bundle savedInstanceState) {
		super.onLoad(savedInstanceState);

		LoginView view = getView();
		if (view != null) {
			configureActionBar();
		}
	}

	private void configureActionBar() {
		ActionBarConfig config = new ActionBarConfig.Builder()
				.title("Login")
				.build();
		actionBarOwner.setConfig(config);
	}
}
