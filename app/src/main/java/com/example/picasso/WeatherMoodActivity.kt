package com.example.picasso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import com.example.picasso.databinding.ActivityWeatherMoodBinding

class WeatherMoodActivity : AppCompatActivity() {

    var selectedButton:ImageButton? = null
    var scaleToBig: Animation ?= null
    var scaleToSmall:Animation ?= null
    private val binding by lazy {
        ActivityWeatherMoodBinding.inflate(layoutInflater)
    }
    private var selected = true
    override fun onCreate(savedInstanceState: Bundle?) {
        scaleToBig = AnimationUtils.loadAnimation(this, R.anim.btn_to_big_scale)
        scaleToSmall = AnimationUtils.loadAnimation(this, R.anim.btn_to_small_scale)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val snow = binding.imageButtonSnow
        val rainy = binding.imageButtonRainy
        val sunny = binding.imageButtonSunny
        val cloudy = binding.imageButtonCloudy
        val windy = binding.imageButtonWindy


        val imageArray = arrayOf<ImageButton>(
            sunny,rainy,snow,cloudy,windy
        )

        snow.setOnClickListener {
//            if (selected) {
//                snow.startAnimation(scaleToBig)
//                snow.setSelected(selected)
//
//                selected = false
//                Log.d("selected to true : ","$selected")
//
//            } else {
//                snow.startAnimation(scaleToSmall)
//                snow.setSelected(selected)
//                selected = true
//                Log.d("selected to false : ","$selected")
//                Log.d("isSeletec?" , "${snow.isSelected}")
//            }
            isSelected(imageArray,snow)
        }
        sunny.setOnClickListener{
            isSelected(imageArray,sunny)
        }
        rainy.setOnClickListener{
            isSelected(imageArray,rainy)
        }
        cloudy.setOnClickListener{
            isSelected(imageArray,cloudy)
        }
    }

    private fun isSelected(imageArray:Array<ImageButton>,imageButton:ImageButton){

        for ( i in 0..imageArray.size-1 ){
            if(imageArray[i] == imageButton){ // for?????? ?????? i????????? image????????? ?????? Imagebutton??? ????????????
                Log.d("if??? ???" , "$i ")
                imageArray[i].setSelected(true)
                imageArray[i].startAnimation(scaleToBig)
            }else{                           // for?????? ?????? i????????? image????????? ?????? Imagebutton??? ???????????? -> ???????????? ????????? ?????? 4??? ???
                Log.d("If else???" , "$i,${imageArray[i].isSelected}")
                if(imageArray[i].isSelected) { // ?????? i????????? ?????????????????????
                    imageArray[i].isSelected = false
                    Log.d("if else if???" , "${i},${imageArray[i].isSelected}")
                    imageArray[i].startAnimation(scaleToSmall)
                }
            }
        }
        Log.d("if======================","======================")
    }


}