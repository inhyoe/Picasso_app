package com.example.picasso

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.picasso.databinding.ActivityStatisticsBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.gson.Gson
import org.json.JSONObject
import java.util.Objects


class StatisticsActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityStatisticsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        val pieChart1: PieChart = binding.test1.piechart
        val pieChart2: PieChart = binding.test2.piechart

        //차트 디자인 설정
        pieChart1.apply {
            data = makePieData("Mood")
            data.setDrawValues(false)
            setDrawSliceText(false)
            setHoleColor(Color.LTGRAY)

            description.isEnabled = false
            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            legend.textSize = 17f
        }
        pieChart2.apply {
            data = makePieData("Weather")
            data.setDrawValues(false)
            setDrawSliceText(false)
            setHoleColor(Color.LTGRAY)
            //색설명 없앰?
            //이거 시발 내가 만들어야되는거같은데 아닌가
            description.isEnabled = false
            //legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            legend.textSize = 17f
        }
    }


    data class mooddata(val happy: Float,val good: Float,val neutral: Float,val bad: Float,val confused: Float,val angry: Float, val nervous: Float, val sad: Float,val sick: Float,)
    fun getDataMood(): mooddata {
        //http통신
        return mooddata(12.9f, 9.68f, 45.16f, 16.13f, 0f,6.45f,0f,9.68f, 0f)
    }
    fun getListMood(): Array<String>{
        return arrayOf("happy", "good", "neutral", "bad", "confused", "angry", "nervous", "sad", "sick")
    }


    data class weatherdata(val sunny: Float, val rainy: Float, val snowy: Float, val cloudy: Float, val windy: Float)
    fun getDataWeather(): weatherdata {
        //http통신
        return weatherdata(48.39F, 12.90F, 0F, 35.48F, 3.23F)
    }
    fun getListWeater(): Array<String>{
        return arrayOf("sunny", "rainy", "snowy", "cloudy", "windy")
    }

    fun makeDataJson(data: Any): JSONObject{
        val jsonString = Gson().toJson(data)
        return JSONObject(jsonString)
    }

    private fun makePieData(type: String):PieData{
        val Piedata = mutableListOf<PieEntry>()

        var list: Array<String> = arrayOf("")
        var jsonData: JSONObject =  JSONObject()

        // 데이터 받아옴   감정이랑  날씨
        if(type == "Mood"){
            list = getListMood()
            jsonData = makeDataJson(getDataMood())
        }else if(type == "Weather"){
            list = getListWeater()
            jsonData = makeDataJson(getDataWeather())
        }

        for(i in list){
            Piedata.add(PieEntry(jsonData.getString(i).toFloat(), i))
        }

        val pieDataset = PieDataSet(Piedata, "")
        // add a lot of colors
        val colorsItems = ArrayList<Int>()
        for (c in ColorTemplate.VORDIPLOM_COLORS) colorsItems.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colorsItems.add(c)
        for (c in ColorTemplate.COLORFUL_COLORS) colorsItems.add(c)
        for (c in ColorTemplate.LIBERTY_COLORS) colorsItems.add(c)
        for (c in ColorTemplate.PASTEL_COLORS) colorsItems.add(c)
        colorsItems.add(ColorTemplate.getHoloBlue())

        pieDataset.apply {
            colors = colorsItems
        }
        val pieData: PieData = PieData(pieDataset)
        return pieData
    }
}