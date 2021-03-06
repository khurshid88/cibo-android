package com.cibo.cibo.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.cibo.cibo.R
import com.cibo.cibo.adapter.IntroPageItemAdapter
import com.cibo.cibo.databinding.ActivityIntroPageBinding
import com.cibo.cibo.manager.PrefsManager
import com.cibo.cibo.model.IntroPageItem

class IntroPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroPageBinding
    private var isFirstTime = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.apply {
            val adapter = IntroPageItemAdapter(this@IntroPageActivity)
            adapter.submitList(getItems())
            viewPager.adapter = adapter
            dotsIndicator.setViewPager2(viewPager)

            tvSkip.setOnClickListener {
                viewPager.currentItem = getItems().size - 1
            }

            btnGetStarted.setOnClickListener {
                if (btnGetStarted.text == getText(R.string.str_start)) {
                    saveLoggedState()
                    callMainActivity(this@IntroPageActivity)
                    finish()
                } else {
                    viewPager.currentItem++
                }
            }
        }
        applyPageStateChanges()
    }

    private fun callMainActivity(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun saveLoggedState() {
        isFirstTime = false
        PrefsManager.getInstance(this@IntroPageActivity)!!.setFirstTime("isFirstTime", isFirstTime)
    }

    private fun applyPageStateChanges() {
        binding.apply {
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    if (position == getItems().size - 1) {
                        btnGetStarted.setText(R.string.str_start)
                        tvSkip.visibility = View.INVISIBLE
                    } else {
                        btnGetStarted.setText(R.string.str_continue)
                        tvSkip.visibility = View.VISIBLE
                    }
                }
            })
        }
    }

    private fun getItems(): ArrayList<IntroPageItem> {
        val items = ArrayList<IntroPageItem>()
        items.add(
            IntroPageItem(
                R.raw.anim_order_phone,
                getString(R.string.str_intro_one_title),
                getString(R.string.str_intro_one_text)
            )
        )
        items.add(
            IntroPageItem(
                R.raw.anim_order_status,
                getString(R.string.str_intro_two_title),
                getString(R.string.str_intro_two_text)
            )
        )
        items.add(
            IntroPageItem(
                R.raw.anim_scan_order,
                getString(R.string.str_intro_three_title),
                getString(R.string.str_intro_three_text)
            )
        )
        return items
    }
}