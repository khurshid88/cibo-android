package com.cibo.cibo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cibo.cibo.adapter.NotificationHistoryAdapter
import com.cibo.cibo.databinding.FragmentNotificationBinding
import com.cibo.cibo.model.NotificationHistory

class NotificationFragment : BaseFragment() {

    private var _bn: FragmentNotificationBinding? = null
    private val bn get() = _bn!!

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _bn = FragmentNotificationBinding.inflate(inflater, container, false)
        return bn.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _bn = null
    }

    private fun initView() {
        bn.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        recyclerView = bn.rvNotification
        recyclerView.setLayoutManager(LinearLayoutManager(activity))
        refreshAdapter(notification())
    }

    private fun notification(): ArrayList<NotificationHistory> {
        val  items : ArrayList<NotificationHistory>  = ArrayList()
        items.add(NotificationHistory("https://www.afisha.uz/ui/catalog/2016/07/0039781.jpeg", "Evos", "22.05.22","18:30","Your order is ready!",true))
        items.add(NotificationHistory("data:image/png;base64,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", "Look", "18.10.21","20:00","Your order is ready!",false))
        items.add(NotificationHistory("https://www.pasta.uz/upload/products/%D0%BB%D0%BE%D0%B3%D0%BE%20%D0%BC%D0%B0%D0%BA%D1%81%D0%B2%D1%8D%D0%B9.jpg", "MaxWay", "20.01.20","18:23","Your order is ready!",false))

        return  items
    }

    private fun refreshAdapter(items: ArrayList<NotificationHistory>) {
        val adapter = NotificationHistoryAdapter(this, items)
        recyclerView.adapter = adapter
    }


}