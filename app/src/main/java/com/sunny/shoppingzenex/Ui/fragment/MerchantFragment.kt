package com.sunny.shoppingzenex.Ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sunny.shoppingzenex.Model.Order.Orders
import com.sunny.shoppingzenex.Model.Product.Products
import com.sunny.shoppingzenex.R
import com.sunny.shoppingzenex.Ui.RecyclerClickInterface
import com.sunny.shoppingzenex.Ui.adapter.MerchantAdapter
import com.sunny.shoppingzenex.Ui.viewModel.ProductViewModel
import com.sunny.shoppingzenex.Util.toast
import com.sunny.shoppingzenex.databinding.FragmentMerchantBinding
import kotlinx.android.synthetic.main.invoice_dialog.*

class MerchantFragment : Fragment(), RecyclerClickInterface {


    private lateinit var mb: FragmentMerchantBinding

    private var productViewModel: ProductViewModel? = null
    private var activity: AppCompatActivity? = null

    private var orderList :List<Orders>?=null

    private var totalPrice :Long ?= 0
    private var gst :Int? =0;
    private var gstPrice:Long? =0;
    private var discount :Int? =0;
    private var discountPrice :Long?=0;
    private var grossTotal: Long?= 0
    private var productAvailable:MutableList<Products> ?=null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as AppCompatActivity;
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mb = FragmentMerchantBinding.inflate(inflater)

        return mb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // orderList = ArrayList()

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        productAvailable = mutableListOf();

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        activity?.let {
            productViewModel!!.orders!!.observe(it, { list ->


                orderList = list


                setProductlistView()


            })
        }


        mb.mProceedOrder.setOnClickListener {

            if(productAvailable!!.isNotEmpty()){

                setInvoiceDialog(productAvailable!!)


            }else{

                activity!!.toast("Please Mark Available Any One Product")
            }
        }

    }

    private fun setInvoiceDialog(productAvailable: MutableList<Products>) {

        val materialAlertDialogBuilder = MaterialAlertDialogBuilder(activity!!)
        val view = LayoutInflater.from(activity!!).inflate(R.layout.invoice_dialog, null)

        val invoiceLayout = view.findViewById(R.id.addInvoiceLayout) as LinearLayout

        val mTotalAmount = view.findViewById(R.id.mTotalAmount) as TextView
        val mGst = view.findViewById(R.id.mGst) as TextView

        val mGstAmount= view.findViewById(R.id.mGstAmount) as TextView
        val mDiscount = view.findViewById(R.id.mDiscount) as TextView

        val mDiscountAmount = view.findViewById(R.id.mDiscountAmount) as TextView
        val mGrossAmount = view.findViewById(R.id.mGrossAmount) as TextView





        val mainInvoiceLYT = view.findViewById(R.id.mainInvoiceLYT) as LinearLayout


        addInvoiceItems(productAvailable, invoiceLayout);

        gst = orderList!![0].gst
        gstPrice = gst!! * totalPrice!!.div(100)

        discount = orderList!![0].coupon
        discountPrice = discount!! * totalPrice!!.div(100)


        mTotalAmount.text =this.resources.getString(R.string.rupee_symbol)  + " " + totalPrice.toString();
        mGst.text = gst.toString() + " % "
        mGstAmount.text = this.resources.getString(R.string.rupee_symbol)  + " " + gstPrice.toString()
        mDiscount.text = discount.toString() + " % "
        mDiscountAmount.text =" - " + this.resources.getString(R.string.rupee_symbol) + discountPrice.toString()

        grossTotal = totalPrice!! + gstPrice!! - discountPrice!!

        mGrossAmount.text = grossTotal.toString()

        materialAlertDialogBuilder.setPositiveButton(resources.getString(R.string.logout)) { dialog, which ->

            dialog.dismiss();



        }

        materialAlertDialogBuilder.setView(view)
        materialAlertDialogBuilder.show()


    }

    private fun addInvoiceItems(
        productAvailable: MutableList<Products>,
        mainInvoiceLYT: LinearLayout
    ) {
        for (i in 0 until productAvailable.size){

            val inflater = activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.invoice_item_child, null) as LinearLayout

            val srNo = view.findViewById(R.id.serialNO) as TextView
            val productName = view.findViewById(R.id.productName) as TextView
            val productQty = view.findViewById(R.id.productQty) as TextView
            val productPrice = view.findViewById(R.id.productPrice) as TextView

            srNo.text = (i + 1).toString()
            productName.text = productAvailable[i].productName
            productQty.text = "1"
            productPrice.text =activity!!.resources.getString(R.string.rupee_symbol) + " "  +productAvailable[i].productPrice.toString()

            totalPrice = totalPrice!! + productAvailable[i].productPrice
            mainInvoiceLYT.addView(view)
        }
    }


    private fun setProductlistView() {



        val gridLayoutManager = LinearLayoutManager(activity)
        gridLayoutManager.orientation = RecyclerView.VERTICAL

        mb.orderRCY.layoutManager = gridLayoutManager
        mb.orderRCY.setItemViewCacheSize(orderList!!.size)

       val merchantAdapter  = MerchantAdapter(activity!!, orderList!![0].productsList, this)
        mb.orderRCY.adapter = merchantAdapter
    }

    override fun onRecyclerItemClick(view: View, position: Int) {
        when(view.id){

            R.id.productAvailable -> {

                productAvailable!!.add(orderList!![0].productsList[position])

            }

            R.id.productNotAvailable -> {

            }

        }
    }


}