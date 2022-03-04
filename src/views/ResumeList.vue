<script lang="ts" setup>
import {ref, reactive} from 'vue'
import type {ElForm, ElTable} from 'element-plus'
import axios from '~/utils/request'
import qs from 'qs'
import {ElMessage} from 'element-plus';

type FormInstance = InstanceType<typeof ElForm>
const filterFormRef = ref<FormInstance>()
type TableInstance = InstanceType<typeof ElTable>
const resumeTableRef = ref<TableInstance>()


type ResumeForm = {
  id?: string | null,
  name: string | null,
  sex: boolean | null,
  phone: string | null,
  birthday?: Date | null,
}

const defaultForm: ResumeForm = {
  name: null,
  sex: null,
  phone: null,
}

const filterForm = reactive(defaultForm)

const resumeList = reactive<ResumeForm[]>([])

function getResumeData() {
  const filterFormStr = qs.stringify(filterForm);
  axios.get(`/user?${filterFormStr}`)
      .then(res => {
        resumeList.splice(0, resumeList.length)
        resumeList.push(...res.data)
      })
      .catch(err => {
        ElMessage.error(`查询失败: ${err.message}`)
      })
}

const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      getResumeData()
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

getResumeData();
</script>

<template>

  <el-form
      :inline="true"
      ref="filterFormRef"
      :model="filterForm"
      :rules="rules"
  >
    <el-form-item label="姓名" prop="name">
      <el-input v-model="filterForm.name" placeholder="请输入姓名"></el-input>
    </el-form-item>
    <el-form-item label="性别" prop="sex">
      <el-select v-model="filterForm.sex" placeholder="请输入性别">
        <el-option label="男" :value="true"></el-option>
        <el-option label="女" :value="false"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="手机号" prop="phone">
      <el-input v-model="filterForm.phone" placeholder="请输入手机号"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm(filterFormRef)">查询</el-button>
      <el-button @click="resetForm(filterFormRef)">重置</el-button>
    </el-form-item>
  </el-form>

  <el-table
      ref="resumeTableRef"
      :data="resumeList"
      @selection-change="handleJobSelectionChange"
      border
      stripe
      style="width: 100%">
    <el-table-column type="selection" width="55"/>
    <el-table-column prop="name" label="姓名">
      <template #default="scope">
        <router-link :to="'/resume/' + scope.row.id">{{ scope.row.name }}</router-link>
      </template>
    </el-table-column>
    <el-table-column prop="sex" label="性别">
      <template #default="scope">
        {{ scope.row.sex ? '男' : '女' }}
      </template>
    </el-table-column>
    <el-table-column prop="phone" label="手机号">
    </el-table-column>
    <el-table-column prop="birthday" label="生日">
    </el-table-column>
  </el-table>
</template>
