package com.sunny.shoppingzenex.Ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sunny.shoppingzenex.Model.Product.Products
import com.sunny.shoppingzenex.R
import com.sunny.shoppingzenex.Ui.RecyclerClickInterface
import com.sunny.shoppingzenex.databinding.ProductChildBinding

class ProductAdapter(context: Context, products: List<Products>, recyclerClickInterface: RecyclerClickInterface) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {


    private val context :Context= context;
    private val products:List<Products> = products
    private val recyclerClickInterface = recyclerClickInterface

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {

        val view  = LayoutInflater.from(context).inflate(R.layout.product_child,parent,false)

        return ProductViewHolder(view)
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val view = holder.binding

        view.productName.text = products[position].productName
       // view.productDescription.text = products[position].productName
        view.productPrice.text = context.resources.getString(R.string.rupee_symbol) + " " + products[position].productPrice.toString()

        if(products[position].inCart){


            view.mAddCart.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_clear, 0, 0, 0);
        }else{

            view.mAddCart.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_add, 0, 0, 0);

        }

        view.mAddCart.setOnClickListener{


            if(products[position].inCart == false){

                products[position].inCart = true

                view.mAddCart.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_clear, 0, 0, 0);


            }else{
                products[position].inCart = false

                view.mAddCart.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_add, 0, 0, 0);

            }

            recyclerClickInterface.onRecyclerItemClick(it,position)

        }



    }

    override fun getItemCount(): Int {
        return products.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = ProductChildBinding.bind(itemView)
    }
}