class MentorActivity : AppCompatActivity() {

    private lateinit var mentorListContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mentor)

        mentorListContainer = findViewById(R.id.mentorListContainer)

        val mentors = listOf(
            Mentor("Clara Mentos", "Matemáticas", "Math Bachelor", "4.5", R.drawable.profile1),
            Mentor("Oliver Jarod", "E-commerce Specialist", "Economy Bachelor", "4.5", R.drawable.profile2),
            Mentor("Jessie Firl", "Digital Marketing", "Specialist", "4.5", R.drawable.profile3),
            Mentor("Mark Smith", "Photography", "Specialist", "4.5", R.drawable.profile4)
        )

        for (mentor in mentors) {
            val view = layoutInflater.inflate(R.layout.mentor_card, null)

            view.findViewById<TextView>(R.id.txtMentorName).text = mentor.name
            view.findViewById<TextView>(R.id.txtCourse).text = mentor.course
            view.findViewById<TextView>(R.id.txtDegree).text = mentor.degree
            view.findViewById<TextView>(R.id.txtRating).text = mentor.rating
            view.findViewById<ImageView>(R.id.imgMentor).setImageResource(mentor.imageRes)

            mentorListContainer.addView(view)
        }

        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }
    }

    data class Mentor(
        val name: String,
        val course: String,
        val degree: String,
        val rating: String,
        val imageRes: Int
    )
}
