package com.example.fitnessapp.homepage

import android.os.Bundle
import android.graphics.Color
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.fitnessapp.R
import com.example.fitnessapp.db.WeightLog
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first



class GraphsActivity: AppCompatActivity() {

    private lateinit var chart: LineChart
    private val viewModel: WeightLogViewModel by viewModels()

    val dao= com.example.fitnessapp.db.AppDatabase.getDatabase(this).weightLogDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graphview)

        val chart = findViewById<LineChart>(R.id.lineChart)

        lifecycleScope.launch {
            val existing = dao.getAllWeightLogs().first()
            if (existing.isEmpty()) {
                dao.insertWeightLog(WeightLog(weight = 180f))
                dao.insertWeightLog(WeightLog(weight = 178.5f))
                dao.insertWeightLog(WeightLog(weight = 176f))
                dao.insertWeightLog(WeightLog(weight = 174f))
            }
        }

        viewModel.weightLogs.observe(this) {logs ->
            val entries = logs.mapIndexed { index, weightLog ->
                Entry(index.toFloat() + 1, weightLog.weight) //x= week#, y= weight
            }

            val dataSet= LineDataSet(entries, "Weight (lbs)").apply {
                color= Color.BLUE
                valueTextColor= Color.BLACK
                lineWidth= 2f
                circleRadius= 4f
                setDrawFilled(true)
                fillAlpha= 100
                fillColor= Color.CYAN

            }
            val lineData= LineData(dataSet)
            chart.data= lineData

            chart.xAxis.apply {
                textColor = Color.BLACK
                textSize = 20f
                setDrawGridLines(false)
                position = XAxis.XAxisPosition.BOTTOM
            }

            chart.axisLeft.apply {
                textColor = Color.BLACK
                textSize = 20f
                setDrawGridLines(true)
            }

            chart.description.text = "Weekly Weight"
            chart.setTouchEnabled(true)
            chart.setPinchZoom(true)
            //chart.animate(1000)
            chart.invalidate()
        }

//        //Mock data: x= week, y= weight in pounds
//        val entries = listOf(
//            Entry(1f, 180f),
//            Entry(2f, 182f),
//            Entry(3f, 184f),
//            Entry(4f, 186f),
//            Entry(5f, 184f)
//        )
//
//
//
//        val lineData= LineData(dataSet)
//        chart.data= lineData
//
//        chart.description.text = "Weekly Weight"
//        chart.setTouchEnabled(true)
//        chart.setPinchZoom(true)
//        chart.invalidate()
    }
}