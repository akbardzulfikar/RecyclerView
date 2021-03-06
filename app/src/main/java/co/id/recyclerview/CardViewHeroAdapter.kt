package co.id.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_cardview_hero.view.*

class CardViewHeroAdapter(private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<CardViewHeroAdapter.CardViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(hero: Hero) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(hero.photo)
                    .apply(RequestOptions()).override(350,550)
                    .into(img_item_photo)

                tv_item_name.text = hero.name
                tv_item_description.text = hero.description

                btn_set_favorite.setOnClickListener { onItemClickCallback?.onItemClicked(hero) }
                btn_set_share.setOnClickListener { onItemClickCallback?.onItemClicked(hero) }

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(hero) }
            }
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_cardview_hero, viewGroup, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) = holder.bind(listHero[position])

    override fun getItemCount(): Int = listHero.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Hero)
    }
}