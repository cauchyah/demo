package com.example.mayn.scrolltest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.listener.OnItemClickListener
import kotlinx.android.synthetic.main.activity_scroll.*


import java.util.*

class ScrollActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)
        val list = ArrayList<String>().apply {
            add("dadas")
            add("dadas")
            add("dadas")
            add("dadas")
            add("dadas")
            add("dadas")
            add("dadas")
            add("dadas")
            add("dadas")
            add("dadas")
            add("dadas")
            add("dadas")
            add("dadas")
            add("dadas")
            add("dadas")
            add("dadas")
            add("dadas")
            add("dadas")
            add("dadas")
            add("dadas")}
        rv.adapter = DemoAdapter(list)
        rv.layoutManager = LinearLayoutManager(this)
        rv.addOnItemTouchListener(object :OnItemClickListener(){
            override fun onSimpleItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
                Toast.makeText(this@ScrollActivity, "toashsada", Toast.LENGTH_SHORT).show()
            }
        })
        val height = 320f
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                DisplayUtils.dipToPx(this,height))
        v_blank.layoutParams = layoutParams
        v_blank.setOnClickListener {
            Toast.makeText(this@ScrollActivity, "v_blank", Toast.LENGTH_SHORT).show()
        }
        sll.setBlankViewHeight(DisplayUtils.dipToPx(this,height))
    }

    class  DemoAdapter(list:List<String>):BaseQuickAdapter<String,BaseViewHolder>(R.layout.item_demo, list) {
        override fun convert(helper: BaseViewHolder, item: String) {
            helper.setText(R.id.tv_name, item)
        }

    }
}
