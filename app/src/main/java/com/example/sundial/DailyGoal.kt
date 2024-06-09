package com.example.sundial

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class DailyGoal : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var drwLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var sBarMin: SeekBar
    private lateinit var sBarMax: SeekBar
    private lateinit var sBarMinDisplay: TextView
    private lateinit var sBarMaxDisplay: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_daily_goal)

            val toolbar: Toolbar = findViewById(R.id.toolBarDailyGoal)
            setSupportActionBar(toolbar)

            drwLayout = findViewById(R.id.DrwLayoutDailyGoal)
            toggle = ActionBarDrawerToggle(this, drwLayout, toolbar, R.string.open, R.string.close)
            drwLayout.addDrawerListener(toggle)
            toggle.syncState()

            navView = findViewById(R.id.nav_view_addDailyGoal)

            navView.setNavigationItemSelectedListener {
                drwLayout.closeDrawer(GravityCompat.START)
                when (it.itemId) {
                    R.id.itmAccount -> {
                        intent = Intent(this, AccountDetails::class.java)
                        startActivity(intent)
                    }

                    R.id.itmAddCategory -> {
                        intent = Intent(this, AddCategory::class.java)
                        startActivity(intent)
                    }

                    R.id.itmAddTimeSheet -> {
                        intent = Intent(this, AddTimesheet::class.java)
                        startActivity(intent)
                    }

                    R.id.itmDashboard -> {
                        intent = Intent(this, Dashboard::class.java)
                        startActivity(intent)
                    }

                    R.id.itmViewTimeSheetEntries -> {
                        intent = Intent(this, ViewTimesheetEntries::class.java)
                        startActivity(intent)
                    }

                    R.id.itmAddDailyGoal -> {
                        intent = Intent(this, DailyGoal::class.java)
                        startActivity(intent)
                    }

                    R.id.itmSettings -> Toast.makeText(
                        applicationContext,
                        "Open Settings Layout",
                        Toast.LENGTH_SHORT
                    ).show()

                    R.id.itmLogout -> {
                        FirebaseAuth.getInstance().signOut()
                        intent = Intent(this, MainActivity::class.java)
                        finish()
                    }

                }
                true
            }

        sBarProgressIndicator()
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return if (toggle.onOptionsItemSelected(item)) {
                true
            } else {
                super.onOptionsItemSelected(item)
            }
        }

        private fun sBarProgressIndicator(){
            sBarMin = findViewById(R.id.sBarMinHours)
            sBarMinDisplay = findViewById(R.id.sb_progress_min_indicator)
            sBarMax = findViewById(R.id.sBarMaxHours)
            sBarMaxDisplay = findViewById(R.id.sb_progress_max_indicator)

           //on change listener to link with the display textview above to indicate position of seek bar for max hours.
           sBarMin.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
               override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                  sBarMinDisplay.text = sBarMin.progress.toString()
               }

               override fun onStartTrackingTouch(seekBar: SeekBar?) {
               }

               override fun onStopTrackingTouch(seekBar: SeekBar?) {
               }

           })

            //on change listener to link with the display textview above to indicate position of seek bar for max hours.
           sBarMax.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
               override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                   sBarMaxDisplay.text = sBarMax.progress.toString()
               }

               override fun onStartTrackingTouch(seekBar: SeekBar?) {
               }

               override fun onStopTrackingTouch(seekBar: SeekBar?) {
               }

           })
        }

        override fun onConfigurationChanged(newConfig: Configuration) {
            super.onConfigurationChanged(newConfig)
            toggle.onConfigurationChanged(newConfig)
        }




}