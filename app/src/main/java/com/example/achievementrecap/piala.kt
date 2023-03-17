package com.example.achievementrecap

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class piala(
    val peringkat: String,
    val kegiatan: String,
    val photo: Int,
    val kesan: String,
    val kendala: String,
    val saran: String,
    val tanggal: String
) : Parcelable
