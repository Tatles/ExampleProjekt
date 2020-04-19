package com.example.exampleproj

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.activity_detail.view.textIdDetail

class DetailHeroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        detailInfoSet()
    }

    private fun detailInfoSet() {
        val hero = intent.extras?.getSerializable("model") as Hero
        textIdDetail.text = hero.id.toString()
        textNameDetail.text = hero.localized_name
        Picasso.get().load(mapAtr(hero.primary_attr)).into(attrImageDetail)
        textHistory.text = getHistory(hero.localized_name)
    }

    private fun mapAtr(attr: String): String {
        return when (attr) {
            "agi" ->
                "https://gamepedia.cursecdn.com/dota2_gamepedia/thumb/2/2d/Agility_attribute_symbol.png/20px-Agility_attribute_symbol.png?version=4022c3f8fd6aec761efd859358f8c078"
            "str" ->
                "https://gamepedia.cursecdn.com/dota2_gamepedia/thumb/7/7a/Strength_attribute_symbol.png/20px-Strength_attribute_symbol.png?version=ffbadd5d5525cbe8e2cf8e4c24a2d115"
            "int" ->
                "https://gamepedia.cursecdn.com/dota2_gamepedia/thumb/5/56/Intelligence_attribute_symbol.png/20px-Intelligence_attribute_symbol.png?version=794665f4224738e861793a93815a022b"
            else -> ""
        }
    }
    private fun getHistory (name:String):String
    {
        return when (name){
            "Anti-Mage" -> "Род Аверно питает купель — разлом в земной тверди, который испускает вещую энергию на протяжении поколений. Каждого новорождённого семьи окунают в этот тёмный туман, даруя им связь с их землёй и её загадочной силой. Дети непреклонно верят в защиту семейных ценностей и традиций земли, но на самом деле они охраняют саму туманную купель, истинные намерения которой неизвестны. Но когда очередной новорождённый проходил обряд крещения, что-то пошло не так. В глазах малыша сверкнула искра разумения, испугавшая всех присутствовавших и заставившая жрецов шептаться. Его растили, дабы он пошёл по пути других отпрысков рода: война и защита родины во главе семейной армии. Но юноша выбрал иное. Пока другие учились обращаться с оружием, он медитировал у купели. Он глубоко вдыхал тёмный туман, учась быть единым с той силой, что протекала глубоко под землёй его дома; он стал порождением черного тумана. Род Аверно не одобрял такой выбор: и стар, и млад упрекали молодого лорда в безответственности. Но обвинения прекратились, едва тот вступил в свою первую битву и показал дарованную туманом власть над жизнью и смертью, о которой другие лорды не могли и мечтать."
            else -> "История будет добавлена"
        }

    }
}