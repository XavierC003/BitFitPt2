package com.example.bitfitpt2

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {
    private lateinit var foodTextView: EditText
    private lateinit var caloriesTextView: EditText
    private lateinit var recordFoodBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        foodTextView = findViewById(R.id.foodInput)
        caloriesTextView = findViewById(R.id.caloriesInput)
        recordFoodBtn = findViewById(R.id.foodSubmitButton)

        recordFoodBtn.setOnClickListener {
            lifecycleScope.launch(IO) {
//                (application as FoodApplication).db.foodDao().deleteAll()
                (application as FoodApplication).db.foodDao().insert(
                    FoodEntity(
                        name = foodTextView.text.toString(),
                        calories = caloriesTextView.text.toString()
                    )
                )
            }
            this.finish()
        }
    }
}