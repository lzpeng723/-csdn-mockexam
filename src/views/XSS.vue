
<script lang="ts" setup>
import {ref} from 'vue'
import {ElForm} from 'element-plus';
import axios from '~/utils/request';

const testText = ref('')
const resultText = ref('')
type FormInstance = InstanceType<typeof ElForm>
const xssFormRef = ref<FormInstance>()
const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      axios.post(`/xss`, JSON.parse(testText.value))
          .then(res => {
            resultText.value = JSON.stringify(res.data)
          })
          .catch(res => {
            console.log(res)
          })
    } else {
      console.log('error submit!')
      return false
    }
  })
}
</script>
<template>
  <el-form
      ref="xssFormRef"
      :model="{}"
  >
    <el-form-item label="测试文本" prop="testText">
      <el-input
          v-model="testText"
          :autosize="{ minRows: 2, maxRows: 30 }"
          type="textarea"
          placeholder="请输入待测试的文本"
      />
    </el-form-item>
    <el-form-item label="测试结果" prop="resultText">
      <el-input
          v-model="resultText"
          :autosize="{ minRows: 2, maxRows: 30 }"
          type="textarea"
          placeholder="请点击测试按钮测试"
          disabled
      />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm(xssFormRef)">测试</el-button>
    </el-form-item>
  </el-form>
</template>
