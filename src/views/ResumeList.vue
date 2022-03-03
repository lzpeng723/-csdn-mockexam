<script lang="ts" setup>
import {ref, reactive} from "vue"
import type {ElForm, ElTable} from 'element-plus'
import axios from "~/utils/request"

type FormInstance = InstanceType<typeof ElForm>
const filterFormRef = ref<FormInstance>()
type TableInstance = InstanceType<typeof ElTable>
const jobResumeTableRef = ref<TableInstance>()


type ResumeForm = {
  id: string | null,
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

const filterForm = reactive(defaultForm)

const resumeList = reactive<ResumeForm[]>([])

const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      axios.get('/user', ruleForm)
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
  Object.assign(filterForm, defaultForm)
}

submitForm(filterFormRef)
</script>

<template>

  <el-form
      :inline="true"
      ref="filterFormRef"
      :model="filterForm"
      :rules="rules"
  >
    <el-form-item label="姓名" prop="name" required>
      <el-input v-model="filterForm.name" placeholder="请输入姓名"></el-input>
    </el-form-item>
    <el-form-item label="性别" prop="sex" required>
      <el-select v-model="filterForm.sex" placeholder="请输入性别">
        <el-option label="男" :value="true"></el-option>
        <el-option label="女" :value="false"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="手机号" prop="phone" required>
      <el-input v-model="filterForm.phone" placeholder="请输入手机号"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm(filterFormRef)">查询</el-button>
      <el-button @click="resetForm(filterFormRef)">重置</el-button>
    </el-form-item>
  </el-form>

  <el-table
      ref="jobResumeTableRef"
      :data="resumeList"
      @selection-change="handleJobSelectionChange"
      border
      stripe
      style="width: 100%">
    <el-table-column type="selection" width="55"/>
    <el-table-column prop="name" label="姓名">
    </el-table-column>
    <el-table-column prop="sex" label="性别">
    </el-table-column>
    <el-table-column prop="phone" label="手机号">
    </el-table-column>
    <el-table-column prop="birthday" label="生日">
    </el-table-column>
  </el-table>
</template>
