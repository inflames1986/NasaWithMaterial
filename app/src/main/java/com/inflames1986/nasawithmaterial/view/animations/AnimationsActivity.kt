package com.inflames1986.nasawithmaterial.view.animations

import android.os.Bundle
import android.view.animation.AnticipateOvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.inflames1986.nasawithmaterial.R
import com.inflames1986.nasawithmaterial.databinding.ActivityAnimationsBonusStartBinding

class AnimationsActivity : AppCompatActivity() {

    private val duration: Long = 1000

    var isOpen: Boolean = false
    private lateinit var binding: ActivityAnimationsBonusStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimationsBonusStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backgroundImage.setOnClickListener {

            val constraintSet = ConstraintSet()
            constraintSet.clone(binding.constraintContainer)

            val transition = ChangeBounds()
            transition.interpolator = AnticipateOvershootInterpolator(5f)
            transition.duration = 1000
            TransitionManager.beginDelayedTransition(binding.constraintContainer, transition)

            isOpen = !isOpen
            if (isOpen) {
                //constraintSet.clear(R.id.title)
                constraintSet.connect(R.id.title,ConstraintSet.RIGHT, R.id.backgroundImage,ConstraintSet.LEFT)
            } else {
                constraintSet.connect(
                    R.id.title,
                    ConstraintSet.RIGHT, R.id.backgroundImage,
                    ConstraintSet.LEFT
                )
            }

            constraintSet.applyTo(binding.constraintContainer)
        }
    }


}