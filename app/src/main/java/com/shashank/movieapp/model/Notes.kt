package com.shashank.movieapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "notes")
class Notes(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title: String = "",
     var subTitle: String="",
     var notes: String="",
     var date: String= "",
     var priority: String =""
) : Parcelable

//    // Getter and Setter for id
//
//    // Getter and Setter for title
//    fun getTitle(): String {
//        return title
//    }
//
//    fun setTitle(title: String) {
//        this.title = title
//    }
//
//    // Getter and Setter for subTitle
//    fun getSubTitle(): String {
//        return subTitle
//    }
//
//    fun setSubTitle(subTitle: String) {
//        this.subTitle = subTitle
//    }
//
//    // Getter and Setter for notes
//    fun getNotes(): String {
//        return notes
//    }
//
//    fun setNotes(notes: String) {
//        this.notes = notes
//    }
//
//    // Getter and Setter for date
//    fun getDate(): String {
//        return date
//    }
//
//    fun setDate(date: String) {
//        this.date = date
//    }
//
//    // Getter and Setter for priority
//    fun getPriority(): String {
//        return priority
//    }
//
//    fun setPriority(priority: String) {
//        this.priority = priority
//    }

