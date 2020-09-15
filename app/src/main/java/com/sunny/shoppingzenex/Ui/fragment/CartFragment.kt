package com.sunny.shoppingzenex.Ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sunny.shoppingzenex.LocalData.LocalPrefrances
import com.sunny.shoppingzenex.Model.Order.Orders
import com.sunny.shoppingzenex.Model.Product.Products
import com.sunny.shoppingzenex.R
import com.sunny.shoppingzenex.Ui.activity.LoginActivity
import com.sunny.shoppingzenex.Ui.viewModel.ProductViewModel
import com.sunny.shoppingzenex.databinding.FragmentCartBinding
import kotlinx.android.synthetic.main.fragment_cart.*


class CartFragment : Fragment() {


    private lateinit var fcb: FragmentCartBinding


    private var activity: AppCompatActivity? = null

    private var productlist: ArrayList<Products>? = null
    private var itemCount: Int? = null
    private var price: Long? = null
    private var gst: Int? = 5
    private var discount: Int? = 50
    private var newTotal: Long? = null
    private var isCouponApplied: Boolean? = false
    private var productViewModel: ProductViewModel? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)

        activity = context as AppCompatActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            itemCount = arguments!!.getInt("count", 0)
            price = arguments!!.getLong("price", 0)
            productlist = arguments!!.getParcelableArrayList("product")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fcb = FragmentCartBinding.inflate(inflater)

        return fcb.root;

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fcb.mItemCount.text = itemCount.toString()
        fcb.mTotolPrice.text =
            activity!!.resources.getString(R.string.rupee_symbol) + " " + price.toString()
        fcb.mGst.text = gst.toString() + " %"


        val total = price!!.plus((price!!.div(100) * gst!!))

        fcb.mGrossCount.text =
            activity!!.resources.getString(R.string.rupee_symbol) + " " + total.toString();

        fcb.couponApplyBTN.setOnClickListener {


            if (isCouponApplied == false) {

                newTotal = total.minus((total.div(100) * discount!!));

                fcb.mGrossCount.text =
                    activity!!.resources.getString(R.string.rupee_symbol) + " " + newTotal.toString()

                fcb.couponApplyBTN.setIconResource(R.drawable.ic_baseline_clear);

                fcb.mDiscountLYT.visibility = View.VISIBLE
                fcb.mDiscount.text = discount.toString()

                isCouponApplied = true

            } else {

                newTotal = total

                fcb.mGrossCount.text =
                    activity!!.resources.getString(R.string.rupee_symbol) + " " + newTotal.toString()

                fcb.couponApplyBTN.setIconResource(R.drawable.ic_baseline_add);

                fcb.mDiscountLYT.visibility = View.GONE

                isCouponApplied = false
            }

        }


        fcb.mButtonBuy.setOnClickListener {





            var i = 0;

            val newProducts:MutableList<Products> = mutableListOf();

            while (i < productlist!!.size){

                if (productlist!![i].inCart == true) {



                    newProducts.add(productlist!![i])

                }

                i++

            }

            val order = Orders()
            order.gst= gst
            order.coupon = discount
            order.total =total
            order.totalItem = itemCount
            order.productsList = newProducts


            productViewModel!!.insertOrder(order)




            showSucessPopUp()
        }


    }

    private fun showSucessPopUp() {

        val localPrefrances  = LocalPrefrances(activity!!)

        val materialAlertDialogBuilder = MaterialAlertDialogBuilder(activity!!)
        val view = LayoutInflater.from(activity!!).inflate(R.layout.succes_dialog, null)

        val successLYT = view.findViewById(R.id.successFullLYT) as LinearLayout

        val animation = AnimationUtils.loadAnimation(activity!!, R.anim.popup_animation)
        successLYT.startAnimation(animation)


        materialAlertDialogBuilder.setPositiveButton(resources.getString(R.string.logout)) { dialog, which ->
            localPrefrances.reset(activity!!)
            dialog.dismiss();

            val  intent  = Intent(activity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
            activity!!.finish()


        }


        materialAlertDialogBuilder.setView(view)
        materialAlertDialogBuilder.show()

    }
}