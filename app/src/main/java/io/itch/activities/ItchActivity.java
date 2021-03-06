package io.itch.activities;

import io.itch.R;
import io.itch.authentication.SessionHelper;
import io.itch.authentication.SessionHelper.SessionCallback;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ItchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itch);
        getActionBar().hide();

        final SessionCallback onLogin = new SessionCallback() {
            @Override
            public void onSuccess() {
                super.onSuccess();
                openMyGames();
            }
        };

        Button login = (Button) findViewById(R.id.buttonLogin);
        login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                SessionHelper.getInstance().login(ItchActivity.this, onLogin);
            }
        });

        if (SessionHelper.getInstance().isLoggedIn()) {
            openMyGames();
        } else {
            SessionHelper.getInstance().restoreSession(this, onLogin);
        }

    }

    private void openMyGames() {
        startActivity(new Intent(ItchActivity.this, MyGamesActivity.class));
        finish();
    }

    @Override
    protected String getScreenPath() {
        return "Start";
    }

}
