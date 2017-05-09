package tlite.com.manisoft;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends Activity {

    protected ImageView menubar_img;
    protected TextView home_txt, map_txt, list_txt, title_txt;
    protected DrawerLayout drawer_layout;
    protected RelativeLayout menulist_lay;
    protected FragmentManager fragmentManager;
    protected FragmentTransaction fragmentTransactions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewcontroler();

        final String[] perms = {
                Manifest.permission.READ_EXTERNAL_STORAGE
        };

        switchfragment(new Home_Fragment());

    }

    void viewcontroler() {

        menubar_img = (ImageView) findViewById(R.id.menubar_img);
        home_txt = (TextView) findViewById(R.id.home_txt);
        map_txt = (TextView) findViewById(R.id.map_txt);
        list_txt = (TextView) findViewById(R.id.list_txt);
        title_txt = (TextView) findViewById(R.id.title_txt);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menulist_lay = (RelativeLayout) findViewById(R.id.menulist_lay);

        home_txt.setBackgroundColor(getResources().getColor(R.color.appcolor));
        home_txt.setTextColor(Color.WHITE);

        menubar_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!drawer_layout.isDrawerOpen(menulist_lay))
                    drawer_layout.openDrawer(menulist_lay, true);
                else
                    drawer_layout.closeDrawer(menulist_lay, true);
            }
        });

        home_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!drawer_layout.isDrawerOpen(menulist_lay))
                    drawer_layout.openDrawer(menulist_lay, true);
                else
                    drawer_layout.closeDrawer(menulist_lay, true);

                switchfragment(new Home_Fragment());
                title_txt.setText("Home");
                home_txt.setBackgroundColor(getResources().getColor(R.color.appcolor));
                home_txt.setTextColor(Color.WHITE);
                map_txt.setBackgroundColor(Color.WHITE);
                map_txt.setTextColor(getResources().getColor(R.color.appcolor));
                list_txt.setBackgroundColor(Color.WHITE);
                list_txt.setTextColor(getResources().getColor(R.color.appcolor));

            }
        });
        map_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!drawer_layout.isDrawerOpen(menulist_lay))
                    drawer_layout.openDrawer(menulist_lay, true);
                else
                    drawer_layout.closeDrawer(menulist_lay, true);

                switchfragment(new Map_Fragment());
                title_txt.setText("Map");
                map_txt.setBackgroundColor(getResources().getColor(R.color.appcolor));
                map_txt.setTextColor(Color.WHITE);
                list_txt.setBackgroundColor(Color.WHITE);
                list_txt.setTextColor(getResources().getColor(R.color.appcolor));
                home_txt.setBackgroundColor(Color.WHITE);
                home_txt.setTextColor(getResources().getColor(R.color.appcolor));

            }
        });
        list_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!drawer_layout.isDrawerOpen(menulist_lay))
                    drawer_layout.openDrawer(menulist_lay, true);
                else
                    drawer_layout.closeDrawer(menulist_lay, true);

                switchfragment(new List_Fragment());
                title_txt.setText("List");
                list_txt.setBackgroundColor(getResources().getColor(R.color.appcolor));
                list_txt.setTextColor(Color.WHITE);
                home_txt.setBackgroundColor(Color.WHITE);
                home_txt.setTextColor(getResources().getColor(R.color.appcolor));
                map_txt.setBackgroundColor(Color.WHITE);
                map_txt.setTextColor(getResources().getColor(R.color.appcolor));

            }
        });
    }

    void switchfragment(Fragment fragment) {

        fragmentManager = getFragmentManager();
        fragmentTransactions = fragmentManager.beginTransaction();
        fragmentTransactions.replace(R.id.flContent, fragment);
        fragmentTransactions.commitAllowingStateLoss();
    }
}
