package app.arxapp.jonathan.arxapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class activity_administracion_ciudadela_lista extends AppCompatActivity {
    private ArrayList<UserInfo> userInfos;
    private CustomListAdapter customListAdapter;
    private ListView customListView;
    private String[] names={
            "Asif Khan","Tanvir Ahmed","Nafis Iqbal","Rahim Islam","Abir Hasan",
            "Asif Khan","Tanvir Ahmed","Nafis Iqbal","Rahim Islam","Abir Hasan",
            "Asif Khan","Tanvir Ahmed","Nafis Iqbal","Rahim Islam","Abir Hasan",
            "Asif Khan","Tanvir Ahmed","Nafis Iqbal","Rahim Islam","Abir Hasan",
            "Asif Khan","Tanvir Ahmed","Nafis Iqbal","Rahim Islam","Abir Hasan"
    };
    private String[] professions={
            "Android Developer","Web Developer","Python llllll","iOS Programmer","Data Analyst",
            "Android Developer","Web Developer","Python Programmer","iOS Programmer","Data Analyst",
            "Android Developer","Web Developer","Python Programmer","iOS Programmer","Data Analyst",
            "Android Developer","Web Developer","Python Programmer","iOS Programmer","Data Analyst",
            "Android Developer","Web Developer","Python Programmer","iOS Programmer","frada"
    };
    private int[] photos={
            R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo,
            R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo,
            R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo,
            R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo,
            R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo,R.drawable.logo
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administracion_ciudadela_lista);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        customListView=(ListView)findViewById(R.id.custom_list_view);
        userInfos=new ArrayList<>();
        customListAdapter=new CustomListAdapter(userInfos,this);
        customListView.setAdapter(customListAdapter);
        getDatas();
        customListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(activity_administracion_ciudadela_lista.this, "Name : " + names[i] + "\n Profession : " + professions[i], Toast.LENGTH_SHORT).show();
            }
        });

    }

    // getting all the datas
    private void getDatas(){
        for(int count=0;count<names.length;count++){
            userInfos.add(new UserInfo(names[count],professions[count],photos[count]));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_option,menu);
        MenuItem menuItem=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText=newText.toString();
                ArrayList<UserInfo> newUserInfos=new ArrayList<>();
                for(UserInfo userInfo:userInfos){
                    String name=userInfo.getName().toLowerCase();
                    String profession=userInfo.getProfession().toLowerCase();
                    if(name.contains(newText) || profession.contains(newText)){
                        newUserInfos.add(userInfo);
                    }
                }
                customListAdapter.filterResult(newUserInfos);
                customListAdapter.notifyDataSetChanged();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }



}