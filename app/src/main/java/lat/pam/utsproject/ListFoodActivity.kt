package lat.pam.utsproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFoodActivity : AppCompatActivity(), FoodAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FoodAdapter
    private lateinit var foodList: List<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_food)


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Menyiapkan data makanan
        foodList = listOf(
            Food("Cumi Tepung", "Cumi yang dipotong-potong, dibalut tepung renyah, dan digoreng hingga keemasan. Gurih dan renyah di luar, lembut di dalam, cocok sebagai camilan atau lauk.", R.drawable.cumi),
            Food("Gurame Asam Manis", "Ikan gurame goreng yang disajikan dengan saus asam manis, memadukan rasa segar dan sedikit manis dengan tekstur daging ikan yang lembut dan renyah.\n" +
                    "\n", R.drawable.gurame),
            Food("Lobster Lada Hitam", "Lobster yang dimasak dengan saus lada hitam pedas dan gurih, menghasilkan rasa yang kaya dan tekstur daging lobster yang kenyal dan juicy.", R.drawable.lobster),
            Food("Kerang Bambu", "Kerang jenis bambu yang dimasak dengan bumbu spesial, biasanya memiliki rasa sedikit manis dan asin dengan tekstur kenyal.", R.drawable.kerang),
            Food("Kepiting Saos Padang", "Kepiting segar yang dimasak dengan saus Padang khas Minang yang pedas, manis, dan sedikit asam, memberikan cita rasa kaya rempah pada daging kepiting yang lembut.", R.drawable.kepiting),
        )

        adapter = FoodAdapter(
            foodList,
            this
        )
        recyclerView.adapter = adapter


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onItemClick(food: Food) {
        val intent = Intent(this, OrderActivity::class.java).apply {
            putExtra("FOOD_NAME", food.name)
            putExtra("FOOD_DESCRIPTION", food.description)
        }
        startActivity(intent)
    }

}