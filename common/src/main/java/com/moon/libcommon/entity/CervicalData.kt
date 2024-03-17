package com.moon.libcommon.entity

/*"everyday":[当天时间加上上一周的数据，如果是新账号，上一周的数据就不需要。新账号数据长度在1-7，旧账号数据长度8-14
{
    "checkTime":{"uptime":556,"bendtime":"52"},数据一，每日挺直时间和弯曲时间的总和
    "uptimehour":[10,0,0,0,0,55,66,77,20,1,2,3,5,4,5,54,86,12,55,21,22,36,55,36],//24小时每个时间点的数据，每小时数据为记录的每分钟的挺直数据百分比，没数据的就返回0
    "totaltarget": {"targettime":60,"uptime":30}//每天的目标时间，和挺直时间
},
null,//当天没数据
{
    "checkTime": {"uptime":556,"bendtime":"52"},
    "uptimehour": [10,0,0,0,0,55,66,77,20,1,2,3,5,4,5,54,86,12,55,21,22,36,55,36],
    "totaltarget": {"targettime":60,"uptime":30}
},
null,
{
    "checkTime": {"uptime":556,"bendtime":"52"},
    "uptimehour": [10,0,0,0,0,55,66,77,20,1,2,3,5,4,5,54,86,12,55,21,22,36,55,36],
    "totaltarget": {"targettime":60,"uptime":30}
}
],
"total":[
90,80,75,0,100,55//整体的百分比
],
  "fisrttime":1597075200000,//首次接收的数据时间戳
            "endtime":1597075200000//最后一次数据的时间戳
*/
data class CervicalData(
    var everyday: List<EverydayData?>,
    var total: List<Int>,
    var fisrttime: Long,
    var endtime: Long
)

data class EverydayData(//一天的数据
    var checkTime: CheckTime,//总额
    var uptimehour: List<Int>,//24小时的百分比
    var totaltarget: TotalTarget
)

data class CheckTime(
    var uptime: Long,
    var bendtime: Long
)

data class TotalTarget(
    var targettime: Long,
    var uptime: Long
)

//每小时的更新百分比
data class HourPercent(var hour: Int, var percent: Int)