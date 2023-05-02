package dev.nhason.nivhasonfinalproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import dev.nhason.nivhasonfinalproject.data.models.placesModel.Places
import dev.nhason.nivhasonfinalproject.databinding.GApiItemBinding

class GApiAdapter(private val places : List<Places>,
                  private val callBack : (places : Places) -> Unit)
    : RecyclerView.Adapter<GApiAdapter.VH>() {

    class VH(val binding: GApiItemBinding) : ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GApiAdapter.VH {
        val binding = GApiItemBinding.inflate(
            LayoutInflater.from(parent.context), parent,
            false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val place = places[position]

        holder.binding.textView.text = place.name
        Picasso.get().load(place.photo)
            .into(holder.binding.imageView2)

        holder.binding.root.setOnClickListener {
            callBack(place)
        }

    }

    override fun getItemCount() = places.size


}