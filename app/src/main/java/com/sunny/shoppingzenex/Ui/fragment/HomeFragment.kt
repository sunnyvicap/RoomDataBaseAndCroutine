package com.sunny.shoppingzenex.Ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sunny.shoppingzenex.Model.Product.Products
import com.sunny.shoppingzenex.R
import com.sunny.shoppingzenex.Ui.adapter.ProductAdapter
import com.sunny.shoppingzenex.Ui.RecyclerClickInterface
import com.sunny.shoppingzenex.Ui.viewModel.ProductViewModel
import com.sunny.shoppingzenex.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(), RecyclerClickInterface {

    private var textCartItemCount: TextView? = null
    private lateinit var hb: FragmentHomeBinding

    private var productViewModel: ProductViewModel? = null
    private var activity: AppCompatActivity? = null

    private var productList: List<Products>? = null

    private lateinit var productAdapter: ProductAdapter

    private var productCount: Int = 0
    private var productCosts: Long = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as AppCompatActivity;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        hb = FragmentHomeBinding.inflate(inflater)

        return hb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productList = ArrayList()

        mProceedOrder.setOnClickListener {

           productViewModel?.update(productList!!);


            val productArrayList : ArrayList<Products> = ArrayList();
            productArrayList.addAll(productList!!)


            val bundle = Bundle()

            bundle.putInt("count", productCount)
            bundle.putLong("price", productCosts)
            bundle.putParcelableArrayList("product", productArrayList)

            val fragmentManager = activity?.supportFragmentManager?.beginTransaction()
            val cartFragment = CartFragment()
            cartFragment.arguments = bundle

            fragmentManager?.replace(R.id.homeContainer, cartFragment, "CART")
            fragmentManager?.addToBackStack(null)
            fragmentManager?.commitAllowingStateLoss()
        }

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)


        activity?.let {

            productViewModel!!.products?.observe(it, { list ->

                if (!list.isNullOrEmpty()) {

                    if (productList!!.isNotEmpty()) {

                        productList!!.toMutableList().clear()
                    }

                    productList = list;
                    setProductlistView()
                    productCount = 0
                    productCosts = 0

                    for (product in list) {

                        if (product.inCart) {

                            productCount++

                            productCosts = productCosts.plus(product.productPrice)


                        }
                    }

                    if (productCount > 0) {

                        hb.cartLYT.visibility = View.VISIBLE
                        hb.itemCount.text =
                            StringBuilder().append("Item: ").append(productCount)
                        hb.totalPrice.text =
                            StringBuilder().append("Total: ").append(productCosts).append(" ")
                                .append(
                                    this.resources.getString(
                                        R.string.rupee_symbol
                                    )
                                )
                    }


                } else {


                    productViewModel!!.insert()
                }

            })
        }
    }

    private fun setProductlistView() {

        val gridLayoutManager = GridLayoutManager(activity, 2)
        gridLayoutManager.orientation = RecyclerView.VERTICAL

        hb.productRCY.layoutManager = gridLayoutManager
        hb.productRCY.setItemViewCacheSize(productList!!.size)

        productAdapter = ProductAdapter(activity!!, productList!!, this)
        hb.productRCY.adapter = productAdapter
    }

    override fun onRecyclerItemClick(view: View, position: Int) {


        val products = productList?.get(position)

        productCosts = if (products?.inCart == true) {

            productCount++

            productCosts.plus(products.productPrice)

        } else {

            productCount--

            productCosts.minus(products!!.productPrice)


        }

        if (productCount > 0) {


            hb.cartLYT.visibility = View.VISIBLE
            hb.itemCount.text = StringBuilder().append("Item: ").append(productCount)
            hb.totalPrice.text =
                StringBuilder().append("Total: ").append(productCosts).append(" ").append(
                    this.resources.getString(
                        R.string.rupee_symbol
                    )
                )
        } else {
            hb.cartLYT.visibility = View.GONE

        }


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.top_app_bar, menu)

        super.onCreateOptionsMenu(menu, inflater)

      /*  val menuItem = menu.findItem(R.id.cartFragment)

        val actionView = menuItem.actionView
        textCartItemCount = actionView.findViewById(R.id.cart_badge) as TextView

        textCartItemCount?.visibility = View.GONE

        actionView.setOnClickListener {
            onOptionsItemSelected(menuItem)

        }*/


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return true
    }


}