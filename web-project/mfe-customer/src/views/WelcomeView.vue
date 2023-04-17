<template>
  <div style="width: 100vw; height: 100vh; overflow: hidden; display: flex">
    <div style="flex: 1">
      <el-image style="width: 100%; height: 100%" fit="cover" src="src\assets\images\bg.jpeg" />
    </div>
    <div style="width: 400px; background-color: white; text-align: center">
      <router-view />
    </div>
  </div>

  <div class="welcome-title">
    <div style="font-size: 30px; font-weight: bold">欢迎到来！</div>
    <div style="margin-top: 10px">全栈学习！</div>
  </div>
</template>

<script setup></script>

<style scoped>
.welcome-title {
  position: absolute;
  top: 30px;
  left: 30px;
  color: white;
  text-shadow: 0 0 10px black;
}
</style>
