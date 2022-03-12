(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-53707446"],{"4e19":function(t,e,a){},"50ff":function(t,e,a){},"59e2":function(t,e,a){"use strict";a("50ff")},"645b":function(t,e,a){"use strict";a.r(e);var l=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],staticClass:"wrap"},[a("header",[a("div",{staticClass:"input_box"},[a("el-date-picker",{staticStyle:{width:"294px"},attrs:{size:"medium",type:"daterange","range-separator":"至","start-placeholder":"开始日期","end-placeholder":"结束日期","picker-options":t.pickerOptions,"value-format":"yyyy-MM-dd"},model:{value:t.time,callback:function(e){t.time=e},expression:"time"}}),a("el-select",{staticStyle:{width:"272px"},attrs:{placeholder:"选择部门",size:"medium"},model:{value:t.department,callback:function(e){t.department=e},expression:"department"}},[a("el-option",{attrs:{label:"全部",value:-1}}),t._l(t.deptList,(function(t){return a("el-option",{key:t.id,attrs:{label:t.name,value:t.id}})}))],2)],1),a("div",{staticClass:"btn_box"},[a("el-button",{staticClass:"filter-item",attrs:{size:"small",type:"primary",icon:"el-icon-search"},on:{click:t.init}},[t._v(" 搜索 ")])],1)]),a("div",{staticClass:"main"},[a("div",{staticClass:"main-body"},[a("div",{staticClass:"mian-body-table"},[a("el-table",{ref:"ruleList",staticStyle:{width:"100%"},attrs:{data:t.tableData,"cell-style":{"padding-top":"12px","padding-bottom":"12px"}}},[a("template",{slot:"empty"},[a("span",[t._v("当前数据为空，请搜索")])]),a("el-table-column",{attrs:{prop:"real_name",label:"姓名"}}),a("el-table-column",{attrs:{prop:"rule_name",label:"打卡规则","show-overflow-tooltip":""}}),a("el-table-column",{attrs:{label:"应打卡天数"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.should_days)+"天 ")]}}])}),a("el-table-column",{attrs:{label:"正常天数"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.normal_days)+"天 ")]}}])}),a("el-table-column",{attrs:{label:"异常天数"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.abnormal_days)+"天 ")]}}])}),a("el-table-column",{attrs:{label:"标准工作时长"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.standard_duration)+"小时 ")]}}])}),a("el-table-column",{attrs:{label:"实际工作时长"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.actual_duration)+"小时 ")]}}])}),a("el-table-column",{attrs:{label:"异常合计"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.abnormal_total.total)+"次 "),e.row.abnormal_total.total?a("span",[a("br"),t._v("共"+t._s(e.row.abnormal_total.time)+"分钟")]):t._e()]}}])}),a("el-table-column",{attrs:{label:"迟到合计"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.late_total.total)+"次"),e.row.late_total.total?a("span",[a("br"),t._v("共"+t._s(e.row.late_total.time)+"分钟")]):t._e()]}}])}),a("el-table-column",{attrs:{label:"早退合计"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.early_total.total)+"次"),e.row.early_total.total?a("span",[a("br"),t._v("共"+t._s(e.row.early_total.time)+"分钟")]):t._e()]}}])}),a("el-table-column",{attrs:{label:"缺卡次数"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.missing)+"次 ")]}}])}),a("el-table-column",{attrs:{label:"地点异常"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.abnormal_location)+"次 ")]}}])})],2),a("div",{staticClass:"table-pagination"},[a("el-pagination",{attrs:{"current-page":t.currentPage,"page-sizes":[5,10,30,50],"page-size":t.size,layout:"total, sizes, prev, pager, next, jumper",total:t.num},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1)],1)])]),a("PersonalStatistics",{ref:"personalStatistics"})],1)},o=[],n=a("2909"),i=(a("d3b7"),a("b775"));function r(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};return Object(i["a"])({url:"/analyse/month/search",method:"post",data:t})}var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("el-dialog",{attrs:{title:"收货地址",visible:t.dialogTableVisible,fullscreen:t.dialogFull,"close-on-press-escape":!1,width:"1200px"},on:{"update:visible":function(e){t.dialogTableVisible=e}}},[a("template",{slot:"title"},[a("div",{staticClass:"dialog-header"},[a("span",{staticClass:"el-dialog-title"},[t._v(" 个人统计 ")]),a("div",{staticClass:"dialog-menu",on:{click:function(e){t.dialogFull?t.dialogFull=!1:t.dialogFull=!0}}},[a("i",{staticClass:"el-icon-full-screen"})])])]),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:t.loading,expression:"loading"}],ref:"ruleList",staticStyle:{width:"100%"},attrs:{data:t.tableData,"cell-style":{"padding-top":"12px","padding-bottom":"12px"}}},[a("el-table-column",{attrs:{prop:"date",label:"日期","show-overflow-tooltip":""}}),a("el-table-column",{attrs:{label:"星期"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(t.week[e.row.weekday])+" ")]}}])}),a("el-table-column",{attrs:{prop:"real_name",label:"姓名"}}),a("el-table-column",{attrs:{prop:"rule_name",label:"打卡规则","show-overflow-tooltip":""}}),a("el-table-column",{attrs:{label:"打卡时段",width:"100"},scopedSlots:t._u([{key:"default",fn:function(e){return[3==e.row.option_type?a("span",[t._v("休息")]):t._e(),2==e.row.option_type?a("span",[t._v("-")]):t._e(),1==e.row.option_type?a("span",[t._v(t._s(e.row.start_time)+"-"+t._s(e.row.end_time))]):t._e()]}}])}),a("el-table-column",{attrs:{label:"标准工作时长"},scopedSlots:t._u([{key:"default",fn:function(e){return[3==e.row.option_type?a("span",[t._v("-")]):t._e(),2==e.row.option_type?a("span",[t._v(t._s(e.row.duration)+"小时")]):t._e(),1==e.row.option_type?a("span",[t._v(t._s(t.changeTime(e.row.should_time)))]):t._e()]}}])}),a("el-table-column",{attrs:{label:"实际工作时长"},scopedSlots:t._u([{key:"default",fn:function(e){return[3==e.row.option_type?a("div",[t._v("-")]):a("div",[2===e.row.go_work.type||2===e.row.off_work.type?a("div",[t._v(" - ")]):a("div",[t._v(" "+t._s(t.changeTime(e.row.normal_time))+" ")])])]}}])}),a("el-table-column",{attrs:{label:"上班1"},scopedSlots:t._u([{key:"default",fn:function(e){return[3==e.row.option_type?a("div",[t._v("-")]):a("div",[a("span",[t._v(" "+t._s(2===e.row.go_work.type?"-":e.row.go_work.time))]),a("br"),a("span",[t._v(t._s(t.type[e.row.go_work.type]))])])]}}])}),a("el-table-column",{attrs:{label:"下班1"},scopedSlots:t._u([{key:"default",fn:function(e){return[3==e.row.option_type?a("div",[t._v("-")]):a("div",[a("span",[t._v(" "+t._s(2===e.row.off_work.type?"-":e.row.off_work.time))]),a("br"),a("span",[t._v(t._s(t.type[e.row.off_work.type]))])])]}}])}),a("el-table-column",{attrs:{label:"异常合计"},scopedSlots:t._u([{key:"default",fn:function(e){return[t._v(" "+t._s(e.row.abnormal_total.total)+"次 "),e.row.abnormal_total.total?a("span",[a("br"),t._v("共"+t._s(t.changeTime(e.row.abnormal_total.time))+" ")]):t._e()]}}])}),a("el-table-column",{attrs:{label:"迟到"},scopedSlots:t._u([{key:"default",fn:function(e){return[3==e.row.option_type||2==e.row.option_type?a("div",[t._v("-")]):a("div",[t._v(" "+t._s(2===e.row.go_work.type?"-":e.row.late?t.changeTime(e.row.late):"无")+" ")])]}}])}),a("el-table-column",{attrs:{label:"早退"},scopedSlots:t._u([{key:"default",fn:function(e){return[3==e.row.option_type?a("div",[t._v("-")]):a("div",[t._v(" "+t._s(2===e.row.off_work.type?"-":e.row.early?t.changeTime(e.row.early):"无")+" ")])]}}])}),a("el-table-column",{attrs:{label:"缺卡情况"},scopedSlots:t._u([{key:"default",fn:function(e){return[3==e.row.option_type?a("div",[t._v("-")]):a("div",[t._v(" "+t._s(e.row.missing)+"次 ")])]}}])}),a("el-table-column",{attrs:{label:"地点异常"},scopedSlots:t._u([{key:"default",fn:function(e){return[3==e.row.option_type?a("div",[t._v("-")]):a("div",[t._v(" "+t._s(e.row.abnormal_location)+"次 ")])]}}])})],1)],2)},u=[],c=(a("99af"),["星期日","星期一","星期二","星期三","星期四","星期五","星期六"]),d=["正常","异常","早退"],_={name:"PersonalStatistics",data:function(){return{week:c,type:d,tableData:[],dialogFull:!1,loading:!1,dialogTableVisible:!1}},mounted:function(){this.closeScreenFull()},methods:{changeTime:function(t){return t/60<1?t+"分钟":t%60===0?Math.floor(t/60)+"小时":"".concat(Math.floor(t/60),"小时").concat(t%60,"分钟")},init:function(t,e,a){var l=this;this.loading=this.dialogTableVisible=!0,new Promise((function(t,e){setTimeout((function(){l.tableData=l.tableData.concat([{date:"2021-07-01",weekday:"3",real_name:"白月光",rule_name:"长沙CSDN",option_type:1,start_time:"09:00",end_time:"12:00",should_time:180,normal_time:160,go_work:{time:"09:15",type:"1"},off_work:{time:"11:55",type:"1"},abnormal_total:{total:2,time:20},late:15,early:5,missing:0,abnormal_location:0},{date:"2021-07-01",weekday:"3",real_name:"白月光",rule_name:"长沙CSDN",option_type:2,duration:"8",start_time:"09:00",end_time:"12:00",should_time:180,normal_time:160,go_work:{time:"09:15",type:"1"},off_work:{time:"11:55",type:"1"},abnormal_total:{total:2,time:20},late:15,early:320,missing:0,abnormal_location:0},{date:"2021-07-01",weekday:"3",real_name:"白月光",rule_name:"长沙CSDN",option_type:3,start_time:"09:00",end_time:"12:00",should_time:180,normal_time:160,go_work:{time:"09:15",type:"1"},off_work:{time:"11:55",type:"1"},abnormal_total:{total:0,time:20},late:15,early:5,missing:0,abnormal_location:0}]),t()}),1e3)})).then((function(){l.loading=!1}))},closeScreenFull:function(){var t=this;this.$nextTick((function(){document.addEventListener("keyup",(function(e){27===e.keyCode&&t.dialogTableVisible&&(t.dialogFull=!1)}))}))}}},p=_,m=(a("dcf5"),a("2877")),f=Object(m["a"])(p,s,u,!1,null,"b66b0ec8",null),b=f.exports,w=a("c24f"),v={components:{PersonalStatistics:b},data:function(){return{time:[],timeTemp:[],department:-1,deptList:[],tableData:[],size:10,num:0,currentPage:1,multipleSelection:[],loading:!1,pickerOptions:{disabledDate:function(t){return t.getTime()>Date.now()}}}},created:function(){this.listInit()},methods:{showPerStac:function(t){this.$refs.personalStatistics.init(t,this.timeTemp[0],this.timeTemp[1])},handleDownload:function(){},listInit:function(){var t=this;this.loading=!0,Object(w["a"])().then((function(e){t.deptList=e.deptList})).catch((function(t){console.log(t)})).finally((function(){t.loading=!1}))},init:function(){var t=this;if(2!==this.time.length)return this.$message.closeAll(),void this.$message.error("请选择时间");this.loading=!0,r({startDate:this.time[0],endDate:this.time[1],departId:this.department,start:this.currentPage,size:this.size}).then((function(e){t.timeTemp=Object(n["a"])(t.time),t.tableData=e.list,t.num=e.count})).catch((function(t){console.log(t)})).finally((function(){t.loading=!1}))},handleSizeChange:function(t){this.size=t,this.init()},handleCurrentChange:function(t){this.currentPage=t,this.init()},handleSelectionChange:function(t){this.multipleSelection=t}}},y=v,h=(a("59e2"),Object(m["a"])(y,l,o,!1,null,"6c202596",null));e["default"]=h.exports},dcf5:function(t,e,a){"use strict";a("4e19")}}]);