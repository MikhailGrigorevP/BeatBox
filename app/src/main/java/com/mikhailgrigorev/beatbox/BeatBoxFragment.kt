package com.mikhailgrigorev.beatbox

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mikhailgrigorev.beatbox.databinding.FragmentBeatBoxBinding
import com.mikhailgrigorev.beatbox.databinding.ListItemSoundBinding


class BeatBoxFragment : Fragment() {
    private var mBeatBox: BeatBox? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentBeatBoxBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_beat_box, container, false)
        binding.recyclerView.layoutManager = GridLayoutManager(activity, 3)
        binding.recyclerView.adapter = SoundAdapter(mBeatBox!!.sounds)
        return binding.root
    }

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBeatBox = BeatBox(activity!!)
    }

    private inner class SoundHolder(private val mBinding: ListItemSoundBinding) :
        ViewHolder(mBinding.root) {
        fun bind(sound: Sound?) {
            mBinding.viewModel!!.sound = sound
            mBinding.executePendingBindings()
        }

        init {
            mBinding.viewModel = mBeatBox?.let { SoundViewModel(it) }
        }
    }

    private inner class SoundAdapter(private val mSounds: List<Sound>) :
        RecyclerView.Adapter<SoundHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): SoundHolder {
            val inflater = LayoutInflater.from(activity)
            val binding: ListItemSoundBinding = DataBindingUtil
                .inflate(inflater, R.layout.list_item_sound, parent, false)
            return SoundHolder(binding)
        }

        override fun onBindViewHolder(
            holder: SoundHolder,
            position: Int
        ) {
            val sound = mSounds[position]
            holder.bind(sound)
        }

        override fun getItemCount(): Int {
            return mSounds.size
        }

    }

    companion object {
        fun newInstance(): BeatBoxFragment {
            return BeatBoxFragment()
        }
    }
}
