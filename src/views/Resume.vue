<script lang="ts" setup>
import {ref, reactive} from "vue"
import type {ElForm, ElTable} from 'element-plus'
import axios from "~/utils/request"

type FormInstance = InstanceType<typeof ElForm>
const ruleFormRef = ref<FormInstance>()
type TableInstance = InstanceType<typeof ElTable>
const jobTableRef = ref<TableInstance>()
const educationTableRef = ref<TableInstance>()
const projectTableRef = ref<TableInstance>()

type Job = {
  index?: number
  startTime?: Date
  endTime?: Date
  companyName?: string
  positionName?: string
  positionResponsibility?: string
}
const jobSelection = ref<Job[]>([])
const defaultJob: Job = {}

type Education = {
  index?: number
  startTime?: Date
  endTime?: Date
  schoolName?: string
  major?: string
  educationLimit?: string
}
const educationSelection = ref<Education[]>([])
const defaultEducation: Education = {}

type Project = {
  index?: number
  startTime?: Date
  endTime?: Date
  projectName?: string
  projectDescription?: string
  achievement?: string
}
const projectSelection = ref<Education[]>([])
const defaultProject: Project = {}

type ResumeForm = {
  name: string | null,
  sex: boolean | null,
  phone: string | null,
  birthday: Date | null,
  jobList: Job[],
  educationList: Education[],
  projectList: Project[],
}
const defaultForm: ResumeForm = {
  name: null,
  sex: null,
  phone: null,
  birthday: null,
  jobList: [],
  educationList: [],
  projectList: [],
}

const ruleForm = reactive(defaultForm)

const rules = reactive({
  name: [
    {
      required: true,
      message: '请输入姓名',
      trigger: 'blur',
    },
    {
      min: 2,
      max: 5,
      message: '姓名必须在2 ~ 5字符之间',
      trigger: 'blur',
    },
  ],
  sex: [
    {
      required: true,
      message: '请输入性别',
      trigger: 'change',
    },
  ],
  phone: [
    {
      required: true,
      message: '请输入手机号',
      trigger: 'change',
    },
  ],
  birthday: [
    {
      type: 'date',
      required: true,
      message: '请输入出生日期',
      trigger: 'change',
    },
  ],
  jobList: [
    {
      type: 'array',
      required: true,
      message: '请输入至少一行工作信息',
      trigger: 'change',
    },
  ],
  educationList: [
    {
      type: 'array',
      required: true,
      message: '请输入至少一行教育经历',
      trigger: 'change',
    },
  ],
  projectList: [
    {
      type: 'array',
      required: true,
      message: '请输入至少一行项目经验',
      trigger: 'change',
    },
  ],
})

const activeNames = ref(['1', '2', '3', '4']);

const disabledDate = (time: Date) => {
  return time.getTime() > Date.now()
}

const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      axios.post('/user', ruleForm)
          .then(res => {
            console.log(res)
          })
          .catch(res => {
            console.log(res)
          })
      console.log(ruleForm)
    } else {
      console.log('error submit!')
      return false
    }
  })
}

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
  Object.assign(ruleForm, defaultForm)
}

const handleJobSelectionChange = (val: any[]) => {
  jobSelection.value = val
}
const handleEducationSelectionChange = (val: any[]) => {
  educationSelection.value = val
}
const handleProjectSelectionChange = (val: any[]) => {
  projectSelection.value = val
}

const addEntry = <T>(list: T[], data: T) => {
  list.push(Object.assign({}, data))
}

const removeEntry = <T>(list: T[], removeData: T[]) => {
  // console.log(list.filter(o => removeData.indexOf(o) === -1))
  for (let removeDatum of removeData) {
    list.splice(list.indexOf(removeDatum), 1);
  }
}


</script>

<template>

  <el-form
      ref="ruleFormRef"
      :model="ruleForm"
      :rules="rules"
  >
    <el-collapse v-model="activeNames">
      <el-collapse-item title="基本信息" name="1">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="ruleForm.name" placeholder="请输入您的姓名"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="ruleForm.sex" placeholder="请选择您的性别">
            <el-radio :label="true">男</el-radio>
            <el-radio :label="false">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="ruleForm.phone" placeholder="请输入您的手机号"></el-input>
        </el-form-item>
        <el-form-item label="生日" prop="birthday">
          <el-date-picker v-model="ruleForm.birthday"
                          type="date"
                          :disabled-date="disabledDate"
                          format="YYYY-MM-DD"
                          value-format="YYYY-MM-DD HH:mm:ss"
                          placeholder="请选择您的生日"></el-date-picker>
        </el-form-item>
      </el-collapse-item>
      <el-collapse-item title="工作经历" name="2">
        <el-form-item prop="jobList">
          <div class="resume-entry-toolbar">
            <el-space wrap>
              <el-link type="primary" @click="addEntry(ruleForm.jobList, defaultJob)">新增</el-link>
              <el-link type="danger" @click="removeEntry(ruleForm.jobList, jobSelection)">删除</el-link>
            </el-space>
          </div>
          <el-table
              ref="jobTableRef"
              :data="ruleForm.jobList"
              @selection-change="handleJobSelectionChange"
              border
              stripe
              style="width: 100%">
            <el-table-column type="selection" width="55"/>
            <el-table-column prop="startTime" label="开始时间">
              <template #default="scope">
                <el-date-picker v-model="scope.row.startTime"
                                type="date"
                                :disabled-date="disabledDate"
                                format="YYYY-MM-DD"
                                value-format="YYYY-MM-DD HH:mm:ss"
                                placeholder="请输入开始时间"></el-date-picker>
              </template>
            </el-table-column>
            <el-table-column prop="endTime" label="结束时间">
              <template #default="scope">
                <el-date-picker v-model="scope.row.endTime"
                                type="date"
                                :disabled-date="disabledDate"
                                format="YYYY-MM-DD"
                                value-format="YYYY-MM-DD HH:mm:ss"
                                placeholder="请输入结束时间"></el-date-picker>
              </template>
            </el-table-column>
            <el-table-column prop="companyName" label="公司名称">
              <template #default="scope">
                <el-input v-model="scope.row.companyName" placeholder="请输入公司名称"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="positionName" label="岗位名称">
              <template #default="scope">
                <el-input v-model="scope.row.positionName" placeholder="请输入岗位名称"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="positionResponsibility" label="岗位职责">
              <template #default="scope">
                <el-input v-model="scope.row.positionResponsibility" placeholder="请输入岗位职责"></el-input>
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
      </el-collapse-item>
      <el-collapse-item title="教育经历" name="3">
        <el-form-item prop="educationList">
          <div class="resume-entry-toolbar">
            <el-space wrap>
              <el-link type="primary" @click="addEntry(ruleForm.educationList, defaultEducation)">新增</el-link>
              <el-link type="danger" @click="removeEntry(ruleForm.educationList, educationSelection)">删除</el-link>
            </el-space>
          </div>
          <el-table
              ref="educationTableRef"
              :data="ruleForm.educationList"
              @selection-change="handleEducationSelectionChange"
              border
              stripe>
            <el-table-column type="selection" width="55"/>
            <el-table-column prop="startTime" label="开始时间">
              <template #default="scope">
                <el-date-picker v-model="scope.row.startTime"
                                type="date"
                                :disabled-date="disabledDate"
                                format="YYYY-MM-DD"
                                value-format="YYYY-MM-DD HH:mm:ss"
                                placeholder="请输入开始时间"></el-date-picker>
              </template>
            </el-table-column>
            <el-table-column prop="endTime" label="结束时间">
              <template #default="scope">
                <el-date-picker v-model="scope.row.endTime"
                                type="date"
                                :disabled-date="disabledDate"
                                format="YYYY-MM-DD"
                                value-format="YYYY-MM-DD HH:mm:ss"
                                placeholder="请输入结束时间"></el-date-picker>
              </template>
            </el-table-column>
            <el-table-column prop="schoolName" label="学校名称">
              <template #default="scope">
                <el-input v-model="scope.row.schoolName" placeholder="请输入学校名称"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="major" label="专业">
              <template #default="scope">
                <el-input v-model="scope.row.major" placeholder="请输入专业"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="educationLimit" label="学历">
              <template #default="scope">
                <el-input v-model="scope.row.educationLimit" placeholder="请输入学历"></el-input>
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
      </el-collapse-item>
      <el-collapse-item title="项目经验" name="4">

        <el-form-item prop="projectList">
          <div class="resume-entry-toolbar">
            <el-space wrap>
              <el-link type="primary" @click="addEntry(ruleForm.projectList, defaultProject)">新增</el-link>
              <el-link type="danger" @click="removeEntry(ruleForm.projectList, projectSelection)">删除</el-link>
            </el-space>
          </div>
          <el-table
              ref="projectTableRef"
              :data="ruleForm.projectList"
              @selection-change="handleProjectSelectionChange"
              border
              stripe
          >
            <el-table-column type="selection" width="55"/>
            <el-table-column prop="startTime" label="开始时间">
              <template #default="scope">
                <el-date-picker v-model="scope.row.startTime"
                                type="date"
                                :disabled-date="disabledDate"
                                format="YYYY-MM-DD"
                                value-format="YYYY-MM-DD HH:mm:ss"
                                placeholder="请输入开始时间"></el-date-picker>
              </template>
            </el-table-column>
            <el-table-column prop="endTime" label="结束时间">
              <template #default="scope">
                <el-date-picker v-model="scope.row.endTime"
                                type="date"
                                :disabled-date="disabledDate"
                                format="YYYY-MM-DD"
                                value-format="YYYY-MM-DD HH:mm:ss"
                                placeholder="请输入结束时间"></el-date-picker>
              </template>
            </el-table-column>
            <el-table-column prop="projectName" label="项目名称">
              <template #default="scope">
                <el-input v-model="scope.row.projectName" placeholder="请输入项目名称"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="projectDescription" label="项目描述">
              <template #default="scope">
                <el-input v-model="scope.row.projectDescription" placeholder="请输入项目描述"></el-input>
              </template>
            </el-table-column>
            <el-table-column prop="achievement" label="你的成就">
              <template #default="scope">
                <el-input v-model="scope.row.achievement" placeholder="请输入你的成就"></el-input>
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
      </el-collapse-item>
    </el-collapse>
    <el-form-item>
      <el-button type="primary" @click="submitForm(ruleFormRef)">提交</el-button>
      <el-button @click="resetForm(ruleFormRef)">重置</el-button>
    </el-form-item>
  </el-form>
</template>
<style scoped>
.resume-entry-toolbar {
  display: flex;
  flex-direction: row-reverse;
  width: 100%;
}
</style>
