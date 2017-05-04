package tlite.com.manisoft;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;

import pub.devrel.easypermissions.EasyPermissions;

public class Home_Fragment extends Fragment {

    TextView gallery_txt;
    RelativeLayout home_lay;
    int SELECT_FILE = 121;
    Comman comman = Comman.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.home_fragment, container, false);

        gallery_txt = (TextView) view.findViewById(R.id.gallery_txt);
        home_lay = (RelativeLayout) view.findViewById(R.id.home_lay);

        viewcontrler();

        return view;

    }

    void viewcontrler(){

        if(comman.bim_bitmap != null){
            home_lay.setBackgroundDrawable(new BitmapDrawable(comman.bim_bitmap));
        }

        gallery_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String[] perms = {
                        Manifest.permission.READ_EXTERNAL_STORAGE
                };
                boolean result= EasyPermissions.hasPermissions(getActivity(), perms);

                if(result) {

                    Intent intent = new Intent();
                    intent.setType("image/* video/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);//
                    startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);

                }else{
                    EasyPermissions.requestPermissions(getActivity(), "This app needs access your storage", 123, perms);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);

        }
    }

    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                comman.bim_bitmap = bm;
            } catch (IOException e) {
                e.printStackTrace();
            }

            home_lay.setBackgroundDrawable(new BitmapDrawable(bm));
        }
    }

}
