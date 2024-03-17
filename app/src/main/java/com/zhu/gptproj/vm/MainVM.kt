package com.zhu.gptproj.vm

import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moon.libcommon.db.MessageType
import com.moon.libcommon.db.entity.ChatMessages
import com.moon.libcommon.db.entity.ChatType
import com.moon.libcommon.db.entity.SendType
import com.moon.libcommon.db.entity.Status
import com.moon.libcommon.http.CommonObserver
import com.moon.libcommon.utils.MMKVManage
import com.moon.libcommon.utils.PathUtils
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

class MainVM @Inject constructor() :
    ViewModel() {
}
