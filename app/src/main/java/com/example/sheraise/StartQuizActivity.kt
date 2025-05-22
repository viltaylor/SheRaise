package com.example.sheraise

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.sheraise.model.Questions

class StartQuizActivity : AppCompatActivity() {

    private lateinit var questions: List<Questions>
    private var currentIndex = 0
    private var answerSelected = false

    private lateinit var questionText: TextView
    private lateinit var questionCounter: TextView
    private lateinit var answersContainer: LinearLayout
    private lateinit var btnNext: Button
    private val questionBank = mapOf(
        "Coding" to listOf(
            Questions("What does HTML stand for?", listOf("Hyper Text Markup Language", "Hyper Text Makeup Language", "Home Tool Markup Language", "Hyper Tool Main Language"), "Hyper Text Markup Language"),
            Questions("Which one of these is a programming language?", listOf("Bird", "Python", "Fish", "Dragon"), "Python"),
            Questions("What is the function of a loop in programming language?", listOf("To store data", "To perform operations repeatedly", "To stop the program", "To create a new variable"), "To perform operations repeatedly"),
            Questions("What is the output of the code: print(3 + 5 * 2) in Python?", listOf("16", "12", "13", "14"), "13"),
            Questions("Which of these data types is used to store decimal numbers?", listOf("int", "string", "bool", "float"), "float"),
            Questions("What is a variable in programming?", listOf("A location to store data", "A function that runs continuously", "A type of loop", "An error in the code"), "A location to store data"),
            Questions("What does 'if' do in most programming languages?", listOf("Declares a variable", "Loops a block of code", "Checks a condition and executes code if it is true", "Stops the program"), "Checks a condition and executes code if it is true"),
            Questions("Which of the following is NOT a valid Boolean value?", listOf("True", "False", "Yes", "all are valid"), "Yes"),
            Questions("In coding, what is indentation typically used for?", listOf("Making the code more readable", "Making the code colorful", "Hiding the errors", "Slowing down the execution"), "Making the code more readable"),
            Questions("What does CPU stand for in a computer?", listOf("Computer Power Unit", "Central Programming Utility", "Central Processing Unit", "Core Processor User"), "Central Processing Unit")
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
                Toast.makeText(this, "Quiz Completed!", Toast.LENGTH_SHORT).show()
                // Navigate to result page if needed
            }
        }
    }

    private fun loadQuestion() {
        val question = questions[currentIndex]
        questionText.text = question.text
        questionCounter.text = "Question ${currentIndex + 1} of ${questions.size}"
        answerSelected = false
        btnNext.isEnabled = false

        answersContainer.removeAllViews()
        question.options.forEach { option ->
            val button = Button(this).apply {
                text = option
                background = ContextCompat.getDrawable(this@StartQuizActivity, R.drawable.quiz_option_button)
                setTextColor(Color.BLACK)
                textSize = 16f
                typeface = Typeface.DEFAULT_BOLD
                isAllCaps = false
                elevation = 0f

                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(0, 16, 0, 16)
                layoutParams = params

                setOnClickListener {
                    if (!answerSelected) {
                        answerSelected = true
                        val isCorrect = option == question.correctAnswer
                        highlightAnswers(this, isCorrect, question.correctAnswer)
                    }
                }
            }

            answersContainer.addView(button)
        }

    }

    private fun highlightAnswers(selectedButton: Button, isCorrect: Boolean, correctAnswer: String) {
        for (i in 0 until answersContainer.childCount) {
            val btn = answersContainer.getChildAt(i) as Button
            btn.isEnabled = false
            answerSelected = false
            when {
                btn.text == correctAnswer -> {
                    btn.background = ContextCompat.getDrawable(this, R.drawable.correct_option_button)
                }
                btn == selectedButton && !isCorrect -> {
                    btn.background = ContextCompat.getDrawable(this, R.drawable.incorrect_option_button)
                }
            }
        }

        btnNext.isEnabled = true
    }

}
