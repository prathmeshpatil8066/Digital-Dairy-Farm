package com.example.dfarm;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;


public class Home1 extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton mbutton;

    NavigationView Navigation;

    Fragment selectedFragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        drawerLayout=findViewById(R.id .dlayout);
        mbutton=findViewById(R.id.menubutton);
        Navigation=findViewById(R.id.navigation);

        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });

        Navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.profile) {
                    Toast.makeText(Home1.this, "Profile open", Toast.LENGTH_SHORT).show();
                    selectedFragment = new profileFragment();
                }
                else if (itemId == R.id.totalcollection) {
                    Toast.makeText(Home1.this, "Total Collection open", Toast.LENGTH_SHORT).show();
                    selectedFragment = new totalcollectionFragment();
                }

                else if (itemId == R.id.totalcoustemer) {
                    Toast.makeText(Home1.this, "Total coustemers open", Toast.LENGTH_SHORT).show();
                    selectedFragment = new totalcoustemerFragment();
                }

                else if (itemId == R.id.coustemerdetails) {
                    Toast.makeText(Home1.this, "coustemers Details open", Toast.LENGTH_SHORT).show();
                    selectedFragment = new coustemerDetails_Fragment();
                }

                else if (itemId == R.id.payment) {
                    Toast.makeText(Home1.this, "Payment Details open", Toast.LENGTH_SHORT).show();
                    selectedFragment = new payment_Fragment();
                }

                else if (itemId == R.id.todayscollection) {
                    Toast.makeText(Home1.this, "Today,s Collection open", Toast.LENGTH_SHORT).show();
                    selectedFragment = new todayCollection_Fragment();
                }

                if (selectedFragment != null) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment) // your container
                            .commit();
                    drawerLayout.closeDrawer(GravityCompat.START); // ✅ now works
                }
                return true;
            }
        });



    }
}