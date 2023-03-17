package com.example.achievementrecap

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.achievementrecap.databinding.ActivityDetailPrestasiBinding

class DetailPrestasi : AppCompatActivity(){

    companion object {
        const val EXTRA_RANK = "extra_rank"
        private const val STATE_RESULT = "state_result"
    }

    private lateinit var binding: ActivityDetailPrestasiBinding

   // private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailPrestasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val ImageDetail: ImageView = findViewById(R.id.image_detail)
//        val tvPeringkat:TextView = findViewById(R.id.tv_detailperingkat)
//        val tvKegiatan:TextView = findViewById(R.id.tv_detailkegiatan)
//        val tvKesan:TextView = findViewById(R.id.tv_detailkesan)
//        val tvKendala:TextView = findViewById(R.id.tv_detailkendala)
//        val tvSaran:TextView = findViewById(R.id.tv_detailsaran)
//        val tvTanggal:TextView = findViewById(R.id.tv_tanggal)
//        button = findViewById(R.id.bt_action_saran)



        val prestasidata = if (Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra<piala>(EXTRA_RANK)
        } else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_RANK)
        }

        if(prestasidata != null){
            val peringkat = prestasidata.peringkat
            binding.tvDetailperingkat.text = peringkat
            val kegiatan = prestasidata.kegiatan
            binding.tvDetailkegiatan.text = kegiatan
            val imagedetail = prestasidata.photo
            binding.imageDetail.setImageResource(imagedetail)
            val kesan = prestasidata.kesan
            binding.tvDetailkesan.text = kesan
            val kendala = prestasidata.kendala
            binding.tvDetailkendala.text = kendala
            val saran = prestasidata.saran
            binding.tvDetailsaran.text = saran
            val tanggal = prestasidata.tanggal
            binding.tvTanggal.text =tanggal
        }

        binding.btActionSaran.setOnClickListener{
            val intent = Intent(Intent.ACTION_SEND)
            val list = prestasidata.toString()
            intent.type = "text/plain"
            intent.putExtra("share", list)
            val chooser = Intent.createChooser(intent,"Share using..")
            startActivity(chooser)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about_page -> {
                val profil = Intent(this@DetailPrestasi, Profile::class.java)
                startActivity(profil)
            }
        }
        return true
    }


}