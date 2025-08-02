package com.mctryn.funnycombination.data.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Record(
    @PrimaryKey() val score: Int,
    @ColumnInfo(name = "date") val date: String
)