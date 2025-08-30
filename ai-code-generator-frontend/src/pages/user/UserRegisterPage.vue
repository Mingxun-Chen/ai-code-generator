<template>
  <div id="userRegisterPage">
    <a-form :model="formState" name="basic" autocomplete="off" @finish="handleSubmit">
      <h2 class="title">AI 应用生成平台 - 用户注册</h2>
      <div class="subtitle">不写一行代码，生成完整应用</div>

      <a-form-item name="userAccount" :rules="[{ required: true, message: '请输入用户名' }]">
        <a-input v-model:value="formState.userAccount" placeholder="用户名" />
      </a-form-item>

      <a-form-item
        name="userPassword"
        :rules="[
          { required: true, message: '请输入密码' },
          { min: 8, message: '密码长度不能少于8位' },
          { max: 16, message: '密码长度不能超过16位' },
        ]"
      >
        <a-input-password v-model:value="formState.userPassword" placeholder="密码" />
      </a-form-item>

      <a-form-item
        name="checkPassword"
        :rules="[
          { required: true, message: '请确认密码' },
          { validator: checkPasswordValidator, trigger: 'change' },
        ]"
      >
        <a-input-password v-model:value="formState.checkPassword" placeholder="确认密码" />
      </a-form-item>

      <div class="tips">
        已有账号？
        <router-link to="/user/login">立即登录!</router-link>
      </div>

      <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
        <a-button type="primary" html-type="submit">注册</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script lang="ts" setup>
import { userRegister } from '@/api/userController.ts'
import { message } from 'ant-design-vue'
import { reactive } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const formState = reactive<API.UserRegisterRequest>({
  userAccount: '',
  userPassword: '',
  checkPassword: '',
})

/**
 * 密码确认验证器
 */
const checkPasswordValidator = async (_rule: any, value: string) => {
  if (value !== formState.userPassword) {
    return Promise.reject(new Error('两次输入的密码不一致'))
  }
  return Promise.resolve()
}

/**
 * 提交表单
 * @param values
 */
const handleSubmit = async (values: any) => {
  try {
    const res = await userRegister(values)
    if (res.data.code === 0) {
      message.success('注册成功，即将跳转到登录页面')
      // 注册成功后自动跳转到登录页面
      setTimeout(() => {
        router.push({
          path: '/user/login',
          replace: true,
        })
      }, 1500)
    } else {
      message.error('注册失败, ' + res.data.message)
    }
  } catch (error) {
    message.error('注册失败，请重试')
  }
}
</script>

<style>
#userRegisterPage {
  max-width: 480px;
  margin: 0 auto;
}

.title {
  text-align: center;
  margin-bottom: 16px;
}

.subtitle {
  text-align: center;
  margin-bottom: 16px;
  color: #bbb;
}

.tips {
  text-align: right;
  margin-bottom: 16px;
  color: #bbb;
  font-size: 14px;
}
</style>
