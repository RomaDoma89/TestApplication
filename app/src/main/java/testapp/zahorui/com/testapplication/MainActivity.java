package testapp.zahorui.com.testapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int DEFAULT_MAX_ELEMENTS_IN_MEMORY = 50;
    private int dbStartPosition = 0;
    private DatabaseEditor dbEditor;
    private UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbEditor = new DatabaseEditor(this);

        ListView usersListView = (ListView) findViewById(R.id.list);
        usersListView.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                loadUsersFromDB(dbStartPosition, DEFAULT_MAX_ELEMENTS_IN_MEMORY);
                return true;
            }
        });
        ArrayList<UserObj> usersList = new ArrayList<>();
        adapter = new UsersAdapter(this, usersList);
        usersListView.setAdapter(adapter);
        loadUsersFromDB(dbStartPosition, DEFAULT_MAX_ELEMENTS_IN_MEMORY);
    }

    private void loadUsersFromDB(int startPosition, int amount) {

        ArrayList<UserObj> moreUsers = new ArrayList<>();
        moreUsers.addAll(dbEditor.getUsersFromDB(startPosition, amount));
        if (!moreUsers.isEmpty()) adapter.addAll(moreUsers);

        dbStartPosition += DEFAULT_MAX_ELEMENTS_IN_MEMORY;

        if (dbEditor.isEmpty()) {
            for(int i=0; i<1000; i++){
                String firstName = "First name" + i;
                String lastName =  "Last name" + i;
                dbEditor.addUser(firstName, lastName);
            }
            loadUsersFromDB(startPosition, amount);
        }
    }
}
