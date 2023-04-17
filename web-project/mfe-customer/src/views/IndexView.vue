<template>
  <div>欢迎呀</div>
  <div>
    <el-button @click="logout()" class="danger" plain>退出登录</el-button>
  </div>
</template>

<script setup>
import { ElMessage } from 'element-plus'
import router from '../router'
import { get } from '../net'

const logout = () => {
  get('/api/auth/logout', (message) => {
    ElMessage.success(message)
    router.push('/')
  })
}
</script>

<style scoped></style>
