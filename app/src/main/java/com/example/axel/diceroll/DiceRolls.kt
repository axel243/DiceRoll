package com.example.axel.diceroll

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.axel.diceroll.R.mipmap.*
import kotlinx.android.synthetic.main.activity_dice_rolls.*
import java.util.*
import kotlin.collections.ArrayList

class DiceRolls : AppCompatActivity() {

    private var dice: ImageView? = null
    private var throwHistory: ListView? = null
    private var currentThrow: MutableList<String>? = null
    private var mAdapter: ArrayAdapter<String>? = null
    private var higherButton: FloatingActionButton ? = null
    private var lowerButton: FloatingActionButton ? = null
    private var randomNumber: Int = 1
    private var scoreText: TextView ? = null
    private var highScoreText: TextView ? = null
    private var score: Int = 0
    private var highScore: Int = 0
    private var a: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice_rolls)
        setSupportActionBar(toolbar)
        dice = findViewById(R.id.dice_roll)
        throwHistory = findViewById(R.id.throw_history)
        currentThrow = ArrayList()
        higherButton = findViewById(R.id.higher_guess_button)
        lowerButton = findViewById(R.id.lower_guess_button)
        scoreText = findViewById(R.id.current_score_number)
        highScoreText = findViewById((R.id.high_score_number))

        higherButton!!.setOnClickListener {
            rollDice()
            higherGuess()
        }

        lowerButton!!.setOnClickListener {
            rollDice()
            lowerGuess()
        }
    }

    private fun higherGuess(){
        if (a < randomNumber){
            score++
            Toast.makeText(this, "higher guess correct", Toast.LENGTH_SHORT).show()
        }else if (a > randomNumber){
            score = 0
            Toast.makeText(this, "higher guess incorrect", Toast.LENGTH_SHORT).show()
        }
        if(score > highScore){
            highScore++
            highScoreText!!.text = highScore.toString()
        }
        scoreText!!.text = score.toString()
        updateUI()

    }

    private fun lowerGuess(){
        if (a > randomNumber){
            score++
            Toast.makeText(this, "lower guess correct", Toast.LENGTH_SHORT).show()
        }else if (a < randomNumber){
            score = 0
            Toast.makeText(this, "lower guess incorrect", Toast.LENGTH_SHORT).show()
        }
        if(score >= highScore){
            highScore++
            highScoreText!!.text = highScore.toString()
        }
        scoreText!!.text = score.toString()
        updateUI()
    }

    private fun rollDice() {
        a = randomNumber
        randomNumber = Random().nextInt(6) + 1
        when (randomNumber) {
            1 -> {
                dice!!.setImageResource(d1)
                currentThrow!!.add("You threw 1")

            }
            2 -> {
                dice!!.setImageResource(d2)
                currentThrow!!.add("You threw 2")

            }
            3 -> {
                dice!!.setImageResource(d3)
                currentThrow!!.add("You threw 3")

            }
            4 -> {
                dice!!.setImageResource(d4)
                currentThrow!!.add("You threw 4")

            }
            5 -> {
                dice!!.setImageResource(d5)
                currentThrow!!.add("You threw 5")

            }
            6 -> {
                dice!!.setImageResource(d6)
                currentThrow!!.add("You threw 6")

            }
        }
    }


    private fun updateUI(){
        if (mAdapter == null) {
            mAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, currentThrow)
            throwHistory!!.adapter = mAdapter
        } else {
            mAdapter!!.notifyDataSetChanged()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_dice_rolls, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
