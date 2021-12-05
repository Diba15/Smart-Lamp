package org.d3ifcool.smartlamp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.d3ifcool.smartlamp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var database = Firebase.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        DrawableCompat.setTint(
            binding.lampImg.drawable,
            ContextCompat.getColor(applicationContext, R.color.black)
        )

        binding.tglLamp.setOnClickListener {
            toggleLed()
        }

        binding.autoLampTgl.setOnClickListener {
            toggleAuto()
        }
    }

    private fun toggleLed() {
        if (binding.tglLamp.isChecked) turnOnLed()
        else turnOffLed()
    }

    private fun turnOnLed() {
        binding.tglLamp.isEnabled = false
        database.getReference("manual")
            .setValue(1).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Lampu Menyala", Toast.LENGTH_SHORT).show()
                    binding.tglLamp.isChecked = true
                    DrawableCompat.setTint(
                        binding.lampImg.drawable,
                        ContextCompat.getColor(applicationContext, R.color.yellow)
                    )
                }
                binding.tglLamp.isEnabled = true
            }
    }

    private fun turnOffLed() {
        binding.tglLamp.isEnabled = false
        database.getReference("manual")
            .setValue(0).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Lampu Mati", Toast.LENGTH_SHORT).show()
                    binding.tglLamp.isChecked = false
                    DrawableCompat.setTint(
                        binding.lampImg.drawable,
                        ContextCompat.getColor(applicationContext, R.color.black)
                    )
                }
                binding.tglLamp.isEnabled = true
            }
    }

    private fun toggleAuto() {
        if (binding.autoLampTgl.isChecked) {
            binding.tglLamp.visibility = View.VISIBLE
            turnOnAuto()
        } else {
            turnOffAuto()
        }
    }

    private fun turnOnAuto() {
        binding.tglLamp.isEnabled = false
        database.getReference("otomatis")
            .setValue(1).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Lampu Mati", Toast.LENGTH_SHORT).show()
                    binding.tglLamp.isChecked = true
                    DrawableCompat.setTint(
                        binding.lampImg.drawable,
                        ContextCompat.getColor(applicationContext, R.color.yellow)
                    )
                }
                binding.tglLamp.isEnabled = true
            }
    }

    private fun turnOffAuto() {
        binding.tglLamp.isEnabled = false
        database.getReference("otomatis")
            .setValue(0).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Lampu Mati", Toast.LENGTH_SHORT).show()
                    binding.tglLamp.isChecked = false
                    DrawableCompat.setTint(
                        binding.lampImg.drawable,
                        ContextCompat.getColor(applicationContext, R.color.black)
                    )
                }
                binding.tglLamp.isEnabled = true
            }
    }


}