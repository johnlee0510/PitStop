package PitStop.Model;

/**
 * Created by John on 2017-11-05.
 */

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import PitStop.R;

public class ImageListAdapter extends ArrayAdapter<String> {
    private String[] truckNames;
    private String[] urls;
    private Bitmap[] bitmaps;
    private Activity context;
    public ImageListAdapter(Activity context, String[] truckNames, Bitmap[] bitmaps) {
        super(context, R.layout.listviewtruck_layout, truckNames);
        this.context = context;
        // this.urls = urls;
        this.bitmaps = bitmaps;
        this.truckNames = truckNames;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.listviewtruck_layout, null, true);
        TextView  trucks = (TextView) listViewItem.findViewById(R.id.trucksnames);
        trucks.setText(truckNames[position] );
        ImageView image = (ImageView) listViewItem.findViewById(R.id.imgvw);
        image.setImageBitmap(Bitmap.createScaledBitmap(bitmaps[position], 120, 110, false));
        return  listViewItem;
    }
}
