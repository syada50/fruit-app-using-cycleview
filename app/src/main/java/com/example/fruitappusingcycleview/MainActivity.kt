package com.example.fruitappusingcycleview


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fruitappusingcycleview.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var fruitAdapter:FruitAdapter
    val fruit = ArrayList<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fruitRv.layoutManager = LinearLayoutManager(this)


        fruit.add(Fruit("Apple", 20, 4.0, R.drawable.apple, "Apple"))
        fruit.add(Fruit("Orange", 30, 3.0, R.drawable.orange, "Orange"))
        fruit.add(Fruit("Banana", 100, 1.0, R.drawable.banana, "Banana"))
        fruit.add(Fruit("Strawberry", 200, 5.0, R.drawable.strawberry, "Strawberry"))
        fruit.add(Fruit("Mango", 20, 4.0, R.drawable.mango, "Mango"))
        fruit.add(Fruit("Kiwi", 50, 6.0, R.drawable.kiwi, "Kiwi"))
        fruit.add(Fruit("Dragon Fruit", 10, 2.0, R.drawable.dragon, "Dragon Fruit"))
        fruit.add(Fruit("Berry", 2023, 7.0, R.drawable.berry, "Berry"))
        fruit.add(Fruit("Grape", 3000, 5.0, R.drawable.grape, "Grape"))
        fruit.add(Fruit("Jack Fruit", 20, 8.0, R.drawable.jackfruit, "Jack Fruit"))
        fruit.add(Fruit("Apple", 20, 4.0, R.drawable.apple, "Apple"))
        fruit.add(Fruit("Orange", 30, 3.0, R.drawable.orange, "Orange"))
        fruit.add(Fruit("Banana", 100, 1.0, R.drawable.banana, "Banana"))
        fruit.add(Fruit("Strawberry", 200, 5.0, R.drawable.strawberry, "Strawberry"))
        fruit.add(Fruit("Mango", 20, 4.0, R.drawable.mango, "Mango"))
        fruit.add(Fruit("Kiwi", 50, 6.0, R.drawable.kiwi, "Kiwi"))
        fruit.add(Fruit("Dragon Fruit", 10, 2.0, R.drawable.dragon, "Dragon Fruit"))
        fruit.add(Fruit("Berry", 2023, 7.0, R.drawable.berry, "Berry"))
        fruit.add(Fruit("Grape", 3000, 5.0, R.drawable.grape, "Grape"))
        fruit.add(Fruit("Jack Fruit", 20, 8.0, R.drawable.jackfruit, "Jack Fruit"))
        fruit.add(Fruit("Apple", 20, 4.0, R.drawable.apple, "Apple"))
        fruit.add(Fruit("Orange", 30, 3.0, R.drawable.orange, "Orange"))
        fruit.add(Fruit("Banana", 100, 1.0, R.drawable.banana, "Banana"))
        fruit.add(Fruit("Strawberry", 200, 5.0, R.drawable.strawberry, "Strawberry"))
        fruit.add(Fruit("Mango", 20, 4.0, R.drawable.mango, "Mango"))
        fruit.add(Fruit("Kiwi", 50, 6.0, R.drawable.kiwi, "Kiwi"))
        fruit.add(Fruit("Dragon Fruit", 10, 2.0, R.drawable.dragon, "Dragon Fruit"))
        fruit.add(Fruit("Berry", 2023, 7.0, R.drawable.berry, "Berry"))
        fruit.add(Fruit("Grape", 3000, 5.0, R.drawable.grape, "Grape"))
        fruit.add(Fruit("Jack Fruit", 20, 8.0, R.drawable.jackfruit, "Jack Fruit"))

        fruitAdapter = FruitAdapter(fruit)
        binding.fruitRv.adapter = fruitAdapter

        fruitAdapter.onClick={
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("name", it.fruitName)
            intent.putExtra("price", it.fruitPrice)
            intent.putExtra("quantity", it.fruitQnt)
            intent.putExtra("desc", it.fruitDesc)
            intent.putExtra("image", it.fruitImg)
            startActivity(intent)
        }

        binding.addBtn.setOnClickListener {
            showFruitAddDialog()
        }

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                fruit.removeAt(viewHolder.adapterPosition)
                fruitAdapter.notifyItemRemoved(viewHolder.adapterPosition)
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.fruitRv)



    }

    private fun showFruitAddDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.add_fruit_layout, null)
        val nameEt = dialogView.findViewById<EditText>(R.id.fruitNameEt)
        val priceEt = dialogView.findViewById<EditText>(R.id.fruitPriceEt)
        val qntEt = dialogView.findViewById<EditText>(R.id.fruitQntEt)
        val descEt = dialogView.findViewById<EditText>(R.id.fruitDescEt)

        AlertDialog.Builder(this)
            .setTitle("Add Fruit")
            .setView(dialogView)
            .setPositiveButton("Add") { _, _ ->
                val name = nameEt.text.toString()
                val price = priceEt.text.toString().toDouble()
                val qnt = qntEt.text.toString().toInt()
                val desc = descEt.text.toString()
                val img = R.drawable.banana
                fruit.add(Fruit(name, qnt, price, img, desc))
                fruitAdapter.notifyItemInserted(fruit.size - 1)
            }
            .setNegativeButton("Cancel", null)
            .show()

    }


}