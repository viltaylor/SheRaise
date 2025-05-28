class FriendsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        btnBack.setOnClickListener {
            onBackPressed()
        }

        // Set active nav item
        bottomNav.selectedItemId = R.id.nav_friends
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.nav_friends -> true
                R.id.nav_courses -> {
                    startActivity(Intent(this, CourseActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // Optional: Add click logic for friend cards
        val friendCard = findViewById<View>(R.id.cardFriendKatie)
        friendCard.setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }
    }
}
