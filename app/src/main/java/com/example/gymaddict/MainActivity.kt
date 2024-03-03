package com.example.gymaddict

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvMuscle: RecyclerView
    private val list = ArrayList<Muscle>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar = supportActionBar
        actionBar!!.title = "GYM Addict"
        actionBar.setBackgroundDrawable(ColorDrawable(Color.WHITE))

        rvMuscle = findViewById(R.id.rv_muscle)
        rvMuscle.setHasFixedSize(true)

        list.addAll(getListMuscle())
        showRecyclerList()

    }

    private fun getListMuscle(): ArrayList<Muscle> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataVariant = resources.getStringArray(R.array.variant_data)

        val listMuscle = ArrayList<Muscle>()
        for (i in dataName.indices) {
            val muscle = Muscle(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataVariant[i])
            listMuscle.add(muscle)
        }
        return listMuscle
    }

    private fun showSelectedMuscle(muscle: Muscle){
//        Toast.makeText(this, "Kamu memilih " + muscle.name, Toast.LENGTH_SHORT).show()

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("muscle_name", muscle.name)
        intent.putExtra("muscle_img", muscle.photo)
        intent.putExtra("muscle_description", muscle.description)
        intent.putExtra("muscle_variant", muscle.step)

        startActivity(intent)
    }

    private fun showRecyclerList() {
        rvMuscle.layoutManager = LinearLayoutManager(this)
        val listMuscleAdapter = ListMuscleAdapter(list)
        rvMuscle.adapter = listMuscleAdapter

        listMuscleAdapter.setOnItemClickCallback(object: ListMuscleAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Muscle) {
                showSelectedMuscle(data)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.miAbout -> {
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}