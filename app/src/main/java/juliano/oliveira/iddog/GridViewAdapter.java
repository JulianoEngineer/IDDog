package juliano.oliveira.iddog;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.widget.ImageView.ScaleType.CENTER_CROP;

public class GridViewAdapter extends BaseAdapter {
    private final Context context;
    private final List<String> urls = new ArrayList<String>();

    public GridViewAdapter(Context context) {
        this.context = context;

        // Ensure we get a different ordering of images on each run.
//        Collections.addAll(urls, ContactsContract.Contacts.Data.URLS);
//        Collections.shuffle(urls);

        // Triple up the list.
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        SquaredImageView view = (SquaredImageView) convertView;
        if (view == null) {
            view = new SquaredImageView(context);
            view.setScaleType(CENTER_CROP);
        }

        // Get the image URL for the current position.
        String url = getItem(position);

        // Trigger the download of the URL asynchronously into the image view.
        Picasso.get()
                .load(url) //
                .placeholder(R.mipmap.ic_launcher) //
                .error(R.mipmap.error) //
                .fit() //
                .tag(context) //
                .into(view);

        return view;
    }

    @Override public int getCount() {
        return urls.size();
    }

    @Override public String getItem(int position) {
        return urls.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    public void setUrls(List<String> urlsSource) {
        if(urls.size()>0)
            urlsSource.clear();

        this.urls.addAll(urlsSource);
    }
}