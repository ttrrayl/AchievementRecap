package com.example.achievementrecap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvPiala : RecyclerView
    private val list = ArrayList<piala>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvPiala = findViewById(R.id.rv_piala)
        rvPiala.setHasFixedSize(true)

        list.addAll(getListPiala())
        showRecylerList()
    }

    private fun getListPiala(): ArrayList<piala>{
        val dataPeringkat = resources.getStringArray(R.array.data_peringkat)
        val dataKegiatan = resources.getStringArray(R.array.data_kegiatan)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataKesan = resources.getStringArray(R.array.data_kesan)
        val dataKendala = resources.getStringArray(R.array.data_kendala)
        val dataSaran = resources.getStringArray(R.array.data_saran)
        val dataTanggal = resources.getStringArray(R.array.data_tanggal)
        val listPiala = ArrayList<piala>()
        for (i in dataPeringkat.indices) {
            val prestasi = piala(dataPeringkat[i],dataKegiatan[i],dataPhoto.getResourceId(i,-1),dataKesan[i],dataKendala[i],dataSaran[i],dataTanggal[i])
            listPiala.add(prestasi)
        }
        return listPiala
    }

    private fun showRecylerList(){
        rvPiala.layoutManager = LinearLayoutManager(this)
        val listPialaAdapter = ListPialaAdapter(list)
        rvPiala.adapter = listPialaAdapter

        listPialaAdapter.setOnItemClickCallback(object : ListPialaAdapter.OnItemClickCallback{
            override fun onItemClicked(data: piala){
                showSelectedPiala(data)
            }
        })
    }

    private fun showSelectedPiala(Piala: piala){
        val moveWithObjectIntent = Intent(this@MainActivity, DetailPrestasi::class.java)
        moveWithObjectIntent.putExtra(DetailPrestasi.EXTRA_RANK, Piala)
        startActivity(moveWithObjectIntent)
        //Toast.makeText(this, "Kamu memlihi " + Piala.kegiatan, Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val profil = Intent(this@MainActivity, Profile::class.java)
        startActivity(profil)

        return super.onOptionsItemSelected(item)
    }
}