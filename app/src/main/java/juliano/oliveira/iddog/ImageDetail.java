package juliano.oliveira.iddog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class ImageDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        String path = getIntent().getStringExtra("IMAGEPATH");

        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        Picasso.get().load(path).into(photoView);
    }
}
