package com.example.sheraise

import android.graphics.Color
import android.graphics.Typeface
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.sheraise.model.Questions

class StartQuizActivity : AppCompatActivity() {

    private lateinit var questions: List<Questions>
    private var currentIndex = 0
    private var answerSelected = false

    private lateinit var questionText: TextView
    private lateinit var questionCounter: TextView
    private lateinit var answersContainer: LinearLayout
    private lateinit var btnNext: ImageButton
    private val questionBank = mapOf(
        "Coding" to listOf(
            Questions(
                "What does HTML stand for?",
                listOf(
                    "Hyper Text Markup Language",
                    "Hyper Text Makeup Language",
                    "Home Tool Markup Language",
                    "Hyper Tool Main Language"
                ),
                "Hyper Text Markup Language"
            ),
            Questions(
                "Which one of these is a programming language?",
                listOf("Bird", "Python", "Fish", "Dragon"),
                "Python"
            ),
            Questions(
                "What is the function of a loop in programming language?",
                listOf(
                    "To store data",
                    "To perform operations repeatedly",
                    "To stop the program",
                    "To create a new variable"
                ),
                "To perform operations repeatedly"
            ),
            Questions(
                "What is the output of the code: print(3 + 5 * 2) in Python?",
                listOf("16", "12", "13", "14"),
                "13"
            ),
            Questions(
                "Which of these data types is used to store decimal numbers?",
                listOf("int", "string", "bool", "float"),
                "float"
            ),
            Questions(
                "What is a variable in programming?",
                listOf(
                    "A location to store data",
                    "A function that runs continuously",
                    "A type of loop",
                    "An error in the code"
                ),
                "A location to store data"
            ),
            Questions(
                "What does 'if' do in most programming languages?",
                listOf(
                    "Declares a variable",
                    "Loops a block of code",
                    "Checks a condition and executes code if it is true",
                    "Stops the program"
                ),
                "Checks a condition and executes code if it is true"
            ),
            Questions(
                "Which of the following is NOT a valid Boolean value?",
                listOf("True", "False", "Yes", "all are valid"),
                "Yes"
            ),
            Questions(
                "In coding, what is indentation typically used for?",
                listOf(
                    "Making the code more readable",
                    "Making the code colorful",
                    "Hiding the errors",
                    "Slowing down the execution"
                ),
                "Making the code more readable"
            ),
            Questions(
                "What does CPU stand for in a computer?",
                listOf(
                    "Computer Power Unit",
                    "Central Programming Utility",
                    "Central Processing Unit",
                    "Core Processor User"
                ),
                "Central Processing Unit"
            )
        )
//        "Marketing" to listOf(
//            Questions("What does SEO stand for?", R.drawable.sample_image, listOf("Search Engine Optimization", "Sales Engine Output", "System Efficiency Order"), "Search Engine Optimization")
//        ),
//        "Ecommerce" to listOf(
//            Questions("Which platform is known for online selling?", R.drawable.sample_image, listOf("Instagram", "Amazon", "Spotify"), "Amazon")
//        )
        // Add more course-question mappings
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_quiz)

        questionText = findViewById(R.id.questionText)
        questionCounter = findViewById(R.id.questionCounter)
        answersContainer = findViewById(R.id.answersContainer)
        btnNext = findViewById(R.id.btnNext)

        // Sample questions
        val selectedTopics = intent.getStringArrayExtra("selected_topics")?.toList() ?: emptyList()

        questions = selectedTopics.flatMap { topic ->
            questionBank[topic] ?: emptyList()
        }.shuffled().take(10) // Shuffle and take first 10


        loadQuestion()

        btnNext.setOnClickListener {
            if (currentIndex < questions.size - 1) {
                currentIndex++
                loadQuestion()
            } else {
                val intent = Intent(this, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finishAffinity() // Optional: closes all previous activities

            }
        }

    }

    private fun loadQuestion() {
        val question = questions[currentIndex]
        questionText.text = question.text
        questionCounter.text = "Question ${currentIndex + 1} of ${questions.size}"
        answerSelected = false
        btnNext.isEnabled = false
        btnNext.visibility = View.INVISIBLE

        answersContainer.removeAllViews()

        question.options.forEach { option ->
            val radiusInPx = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
            48f,
            resources.displayMetrics
            )

            val card = CardView(this).apply {
                radius = radiusInPx
                cardElevation = 8f
                setCardBackgroundColor(ContextCompat.getColor(context, R.color.white))
                useCompatPadding = true
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 10, 0, 10)
                }
            }


            val button = Button(this).apply {
                text = option
                background = null
                setTextColor(Color.BLACK)
                textSize = 16f
                typeface = Typeface.DEFAULT_BOLD
                isAllCaps = false
                setPadding(32, 32, 32, 32)

                setOnClickListener {
                    if (!answerSelected) {
                        answerSelected = true
                        val isCorrect = option == question.correctAnswer
                        highlightAnswers(this, isCorrect, question.correctAnswer)
                    }
                }
            }

            card.addView(button)
            answersContainer.addView(card)
        }
    }

    private fun highlightAnswers(
        selectedButton: Button,
        isCorrect: Boolean,
        correctAnswer: String
    ) {
        answerSelected = true

        for (i in 0 until answersContainer.childCount) {
            val card = answersContainer.getChildAt(i) as CardView
            val btn = card.getChildAt(0) as Button
            btn.isEnabled = false

            when {
                btn.text == correctAnswer -> {
                    btn.background =
                        ContextCompat.getDrawable(this, R.drawable.correct_option_button)
                }

                btn == selectedButton && !isCorrect -> {
                    btn.background =
                        ContextCompat.getDrawable(this, R.drawable.incorrect_option_button)
                }
            }
        }

        btnNext.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate().alpha(1f).setDuration(300).start()
            isEnabled = true
        }
    }

}




