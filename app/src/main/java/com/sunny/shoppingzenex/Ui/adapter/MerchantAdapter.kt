package com.sunny.shoppingzenex.Ui.adapter;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sunny.shoppingzenex.Model.Order.Orders
import com.sunny.shoppingzenex.Model.Product.Products
import com.sunny.shoppingzenex.R
import com.sunny.shoppingzenex.Ui.RecyclerClickInterface
import com.sunny.shoppingzenex.databinding.MerchantChildBinding

class MerchantAdapter(
 context: Context,
 products: List<Products>,
 recyclerClickInterface: RecyclerClickInterface
) : RecyclerView.Adapter<MerchantAdapter.ProductViewHolder>() {


    private val context: Context = context;
    private val products: List<Products> = products
    private val recyclerClickInterface = recyclerClickInterface

    override fun onCreateViewHolder(
     parent: ViewGroup,
     viewType: Int
    ): ProductViewHolder {

        val view = LayoutInflater.from(context).inflate(R.layout.merchant_child, parent, false)

        return ProductViewHolder(view)
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val view = holder.binding

        view.productName.text = products[position].productName
        view.productPrice.text = products[position].productPrice.toString()

        view.productAvailable.setOnClickListener {

            view.mTextAvailable.text = context.resources.getString(R.string.available)
            view.mTextAvailable.visibility = View.VISIBLE
            view.availableLYT.visibility = View.GONE
          //  products[position].isOrderAvailable = 1
            recyclerClickInterface.onRecyclerItemClick(it, position)
        }

        view.productNotAvailable.setOnClickListener {

            view.mTextAvailable.text = context.resources.getString(R.string.not_available)
            view.availableLYT.visibility = View.GONE
           // products[position].isOrderAvailable = 2
            view.mTextAvailable.visibility = View.VISIBLE

            recyclerClickInterface.onRecyclerItemClick(it, position)
        }

    }


    override fun getItemCount(): Int {
        return products.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = MerchantChildBinding.bind(itemView)
    }
}