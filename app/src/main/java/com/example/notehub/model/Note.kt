package com.example.notehub.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Notes")
data class Note(
    @PrimaryKey(autoGenerate = true)

    var id: Int?= null,
    var title: String,
    var subTitle: String,
    var content: String,
    var date: String,
    var priority:String
):Parcelable
