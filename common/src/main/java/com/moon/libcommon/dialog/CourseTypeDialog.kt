package com.moon.libcommon.dialog

import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.CenterPopupView
import com.moon.libcommon.R

/**
 * @Description TODO
 * @systemUser Zhuyuandong
 * @Author
 * @Date 02-06-2023 周一 14:02
 */
class CourseTypeDialog constructor(context: Context) : CenterPopupView(context) {

    var title:TextView?=null
    var recycler: RecyclerView?=null
    var cancelBtn:TextView?=null
    var adapter:CourseTypeAdapter?=null
    var clickListener:((String)->Unit)={}

    val list= arrayListOf<String>("语文","数学","英语","体育","音乐","美术","品德与社会","自习")

    override fun getImplLayoutId(): Int {
        return R.layout.xpopup_course_type
    }

    override fun onCreate() {
        super.onCreate()
        adapter= CourseTypeAdapter()


        recycler=findViewById(R.id.course_list)
        title=findViewById(R.id.title_dialog)
        cancelBtn=findViewById(R.id.btn_cancel)

        if (adapter!=null){
            adapter!!.clickListener={
                clickListener.invoke(it)
                dismiss()
            }
        }

        if (cancelBtn!=null){
            cancelBtn!!.setOnClickListener {
                dismiss()
            }
        }

    }

    override fun init() {
        super.init()
        recycler?.adapter =adapter
        recycler?.layoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        adapter?.submitList(list)
    }

    companion object {
        fun show(
            context: Context,
            clickListener:((String)->Unit)?
        ) {
            val normalDialogView = CourseTypeDialog(context)
            if (clickListener!=null){
                normalDialogView.clickListener = clickListener
            }

            XPopup.Builder(context).asCustom(normalDialogView).show()
        }
    }

}