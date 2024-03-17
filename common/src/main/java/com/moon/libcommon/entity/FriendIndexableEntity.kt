package com.moon.libcommon.entity

import com.moon.libcommon.db.entity.BindUserEnity
import me.yokeyword.indexablerv.IndexableEntity


class FriendIndexableEntity(val bindUser: BindUserEnity) : IndexableEntity {
    var pinyin: String? = null
    override fun getFieldIndexBy(): String {
        return bindUser.showName()
    }

    override fun setFieldIndexBy(indexField: String) {
        bindUser.setFieldIndexBy(indexField)
    }

    override fun setFieldPinyinIndexBy(pinyin: String) {
        this.pinyin = pinyin
    }
}