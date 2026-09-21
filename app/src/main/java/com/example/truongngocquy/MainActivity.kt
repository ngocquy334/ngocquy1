package com.example.truongngocquy
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.truongngocquy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayStudent("Trương Ngọc Quý", 3.8, "quy@example.com")
        openDetailActivity("SV12345")
        processInput("Chào mừng bạn đến với ViewBinding")
        calculateAndAudit(3.2)
    }

    private fun displayStudent(name: String, gpa: Double, email: String) {
        with(binding) {
            tvWelcome.text = name
            tvWelcome.visibility = View.VISIBLE
        }
    }

    private fun openDetailActivity(studentId: String) {
        val detailIntent = Intent(this, MainActivity::class.java).apply {
            putExtra("KEY_STUDENT_ID", studentId)
            putExtra("KEY_TIMESTAMP", System.currentTimeMillis())
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        startActivity(detailIntent)
    }

    private fun processInput(rawText: String?) {
        rawText?.takeIf { it.isNotBlank() }?.let { validText ->
            binding.tvWelcome.text = validText
            Toast.makeText(this, "Nội dung: $validText", Toast.LENGTH_SHORT).show()
        } ?: run {
            Toast.makeText(this, "Chuỗi rỗng hoặc null", Toast.LENGTH_SHORT).show()
        }
    }

    private fun calculateAndAudit(rawScore: Double): Double {
        return (rawScore * 10.0 / 4.0)
            .also { finalScore ->
                Log.d("STUDENT_AUDIT", "Điểm hệ 10 quy đổi: $finalScore")
            }
            .also {
                Toast.makeText(this, "Đã tính xong điểm: $it", Toast.LENGTH_SHORT).show()
            }
    }
}