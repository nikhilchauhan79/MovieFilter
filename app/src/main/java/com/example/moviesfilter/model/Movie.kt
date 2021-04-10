package com.example.moviesfilter.model

data class Movie(val year:Int,val title:String,val info: info)

data class info(val release_date:String,val rating:Float,val genres:ArrayList<String>,val image_url:String,val directors:ArrayList<String>,
val plot:String,val actors:ArrayList<String>,val rank:Int,val running_time_secs:String)