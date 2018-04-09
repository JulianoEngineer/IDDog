package juliano.oliveira.iddog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class ImageDetail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_image_detail);
        String path = getIntent().getStringExtra("IMAGEPATH");
        RelativeLayout imageDetailLayout = (RelativeLayout) findViewById(R.id.image_detail_layout);
        imageDetailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        Picasso.get().load(path).into(photoView);

    }
}
