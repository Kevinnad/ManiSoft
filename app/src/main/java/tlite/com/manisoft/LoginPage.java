package tlite.com.manisoft;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class LoginPage extends Activity {

    TextView Login_txt, dob_txt, name_txt;
    ImageView user_img;
    LinearLayout profile_lay;
    RelativeLayout facebook_lay;
    LoginButton login_button;
    CallbackManager callbackManager;
    String email = "";
    String name = "";
    String userid = "";
    LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login_page);
        viewcontroller();
    }

    void viewcontroller(){

        Login_txt = (TextView) findViewById(R.id.Login_txt);
        dob_txt = (TextView) findViewById(R.id.dob_txt);
        name_txt = (TextView) findViewById(R.id.name_txt);
        user_img = (ImageView) findViewById(R.id.user_img);
        login_button = (LoginButton) findViewById(R.id.login_button);
        profile_lay = (LinearLayout) findViewById(R.id.profile_lay);
        facebook_lay = (RelativeLayout) findViewById(R.id.facebook_lay);
        callbackManager = CallbackManager.Factory.create();

        loginManager.getInstance().logOut();


        Login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(LoginPage.this,MainActivity.class));
                finish();
            }
        });

        login_button.setReadPermissions("email");


        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.d("FaceBook:", loginResult.toString());

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("LoginActivity", response.toString());

                                try {
                                    email = object.getString("email");
                                    name = object.getString("name");
                                    userid = object.getString("id");

                                    profile_lay.setVisibility(View.VISIBLE);
                                    facebook_lay.setVisibility(View.GONE);

                                    name_txt.setText(name);
                                    dob_txt.setText(email);
                                    thread.start();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {
                // App code
                Log.d("FaceBook:", "cancel");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d("FaceBook:", exception.toString());
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    Thread thread = new Thread(new Runnable() {
        Bitmap bitmap = null;
        @Override
        public void run() {


            try  {
                URL imageURL = new URL("https://graph.facebook.com/" + userid + "/picture?type=large");
                bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(bitmap != null) user_img.setImageBitmap(bitmap);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });
}
