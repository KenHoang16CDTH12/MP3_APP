package it.hueic.kenhoang.mp3_app.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import it.hueic.kenhoang.mp3_app.Common.Common;
import it.hueic.kenhoang.mp3_app.R;

public class LoginActivity extends AppCompatActivity {
    TextView title, subtitle;
    EditText email, password;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //Before setContentView
        setContentView(R.layout.activity_login);
        //Init view
        initView();
        //Init event
        initEvent();
    }

    /**
     * Init event
     */
    private void initEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                //overridePendingTransition(R.anim.slide_in, R.anim.slide_up);
            }
        });
    }

    /**
     * Init view
     */
    private void initView() {
        title = findViewById(R.id.tittle);
        subtitle = findViewById(R.id.subtitle);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        defaultFont();
    }


    /**
     * Set TypeFace
     */
    private void defaultFont() {
        title.setTypeface(Common.setTypeFaceFonts(this, Common.ARKHIP_FONT));
        subtitle.setTypeface(Common.setTypeFaceFonts(this, Common.ARKHIP_FONT));
        email.setTypeface(Common.setTypeFaceFonts(this, Common.NABILA_FONT));
        password.setTypeface(Common.setTypeFaceFonts(this, Common.NABILA_FONT));
        btnLogin.setTypeface(Common.setTypeFaceFonts(this, Common.NABILA_FONT));
    }
}
