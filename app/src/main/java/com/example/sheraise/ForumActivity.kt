class ForumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forum)

        val recycler = findViewById<RecyclerView>(R.id.recyclerForum)
        val btnBack = findViewById<ImageButton>(R.id.btnBack)

        val posts = listOf(
            ForumPost("Shannon Blake", "@shannonb", "6/30/22", "Just launched my new portfolio site. Feedback welcome!", R.drawable.sample_post, "Portfolio and Design"),
            ForumPost("Devon Kim", "@devkim22", "7/01/22", "Working on Kotlin layout for my Android app 🚀", R.drawable.sample_post, "Mobile Dev Thoughts"),
            ForumPost("Lina Chan", "@linachan", "7/02/22", "How do you stay motivated to code daily? Drop your tips 👇", R.drawable.sample_post, "Tech Life Hacks")
        )

        val adapter = ForumAdapter(posts)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        btnBack.setOnClickListener {
            onBackPressed()
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.selectedItemId = R.id.nav_forum
        bottomNav.setOnItemSelectedListener {
            // TODO: handle nav actions
            true
        }
    }
}
