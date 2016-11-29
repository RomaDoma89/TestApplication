package testapp.zahorui.com.testapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by Roman Zahorui on 29.11.2016.
 */

public class UsersAdapter extends ArrayAdapter<UserObj> {

    private Context context;
    private int count = 0;
    private List<UserObj> users;
    private List<String> firstNames;
    private List<String> lastNames;

    public UsersAdapter(Context context, List<UserObj> users) {
        super(context, 0, users);
        this.context = context;
        this.users = users;
        firstNames = new ArrayList<>();
        lastNames = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public UserObj getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.user_item, parent, false);
        }

            ((TextView) convertView.findViewById(R.id.first_name_tv)).setText(firstNames.get(position));
            ((TextView) convertView.findViewById(R.id.last_name_tv)).setText(lastNames.get(position));
            Log.e("UserObj", "null");


        return convertView;
    }

    public void addAll(List<UserObj> collection) {
        users = collection;
        count += collection.size();
        for (UserObj user : collection) {
            firstNames.add(user.getFirstName());
            lastNames.add(user.getLastName());
        }
        notifyDataSetChanged();
        Log.e("users", String.valueOf(users.size()));
    }
}
