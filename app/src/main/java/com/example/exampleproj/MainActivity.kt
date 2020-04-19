package com.example.exampleproj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var adapter: Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvSetUp()
        val apiHero = RetrofImpl.create()
        apiHero.getHeroes()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                populateData(it)
            }, {
                Log.e(MainActivity::class.java.simpleName, "name :$it")
            })
    }

    private fun rvSetUp() {
        adapter = Adapter {ClickOnImage(it)}
        adapter.attachDelegete {ClickOnImage(it)}
        rv_heroes.layoutManager = LinearLayoutManager(this)
        rv_heroes.adapter = adapter
    }

    private fun populateData(data: List<Hero>) {
        adapter.setData(data)
    }

    private fun ClickOnImage(model: Hero) {
        val intent = Intent(this, DetailHeroActivity::class.java)
        intent.putExtra("model", model)
        startActivity(intent)
    }
}

