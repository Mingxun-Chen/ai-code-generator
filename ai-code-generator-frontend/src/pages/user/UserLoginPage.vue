<template>
  <div id="userLoginPage">
    <div class="container">
      <div class="login-card">
        <div class="header">
          <h2 class="title">用户登录</h2>
          <p class="desc">欢迎回到 ZeroCodeX AI 应用生成平台</p>
        </div>

        <a-form
          :model="formState"
          name="basic"
          autocomplete="off"
          @finish="handleSubmit"
          class="login-form"
        >
          <a-form-item name="userAccount" :rules="[{ required: true, message: '请输入账号' }]">
            <div class="input-wrapper">
              <a-input
                v-model:value="formState.userAccount"
                placeholder="请输入账号"
                size="large"
              />
            </div>
          </a-form-item>
          <a-form-item
            name="userPassword"
            :rules="[
              { required: true, message: '请输入密码' },
              { min: 8, message: '密码长度不能小于 8 位' },
            ]"
          >
            <div class="input-wrapper">
              <a-input-password
                v-model:value="formState.userPassword"
                placeholder="请输入密码"
                size="small"
              />
            </div>
          </a-form-item>

          <a-form-item>
            <a-button type="primary" html-type="submit" size="large" class="login-btn"
              >登录</a-button
            >
          </a-form-item>

          <div class="tips">没有账号？<RouterLink to="/user/register">立即注册</RouterLink></div>
        </a-form>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { reactive } from 'vue'
import { userLogin } from '@/api/userController.ts'
import { useLoginUserStore } from '@/stores/loginUser.ts'
import { useRouter } from 'vue-router'
import { message } from 'ant-design-vue'

const formState = reactive<API.UserLoginRequest>({
  userAccount: '',
  userPassword: '',
})

const router = useRouter()
const loginUserStore = useLoginUserStore()

/**
 * 提交表单
 * @param values
 */
const handleSubmit = async (values: any) => {
  const res = await userLogin(values)
  // 登录成功，把登录态保存到全局状态中
  if (res.data.code === 0 && res.data.data) {
    await loginUserStore.fetchLoginUser()
    message.success('登录成功')
    router.push({
      path: '/',
      replace: true,
    })
  } else {
    message.error('登录失败，' + res.data.message)
  }
}
</script>

<style scoped>
#userLoginPage {
  width: 100%;
  margin: 0;
  padding: 0;
  min-height: 100vh;
  background:
    linear-gradient(180deg, #f8fafc 0%, #f1f5f9 8%, #e2e8f0 20%, #cbd5e1 100%),
    radial-gradient(circle at 20% 80%, rgba(59, 130, 246, 0.15) 0%, transparent 50%),
    radial-gradient(circle at 80% 20%, rgba(139, 92, 246, 0.12) 0%, transparent 50%),
    radial-gradient(circle at 40% 40%, rgba(16, 185, 129, 0.08) 0%, transparent 50%);
  background-image: linear-gradient(to top, #a8edea 0%, #fed6e3 100%);
  position: relative;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
}

/* 科技感网格背景 */
#userLoginPage::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background:
    linear-gradient(135deg, rgba(255, 255, 255, 0.05) 25%, transparent 25%) -20px 0/ 40px 40px,
    linear-gradient(225deg, rgba(255, 255, 255, 0.05) 25%, transparent 25%) -20px 0/ 40px 40px,
    linear-gradient(315deg, rgba(255, 255, 255, 0.05) 25%, transparent 25%) 0px 0/ 40px 40px,
    linear-gradient(45deg, rgba(255, 255, 255, 0.05) 25%, transparent 25%) 0px 0/ 40px 40px;
  animation: foldShimmer 15s ease-in-out infinite alternate;
  pointer-events: none;
}

@keyframes foldShimmer {
  0% {
    filter: brightness(1);
  }
  100% {
    filter: brightness(1.3);
  }
}

/* 动态光效 */
#userLoginPage::after {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background:
    radial-gradient(
      600px circle at 50% 50%,
      rgba(59, 130, 246, 0.08) 0%,
      rgba(139, 92, 246, 0.06) 40%,
      transparent 80%
    ),
    linear-gradient(45deg, transparent 30%, rgba(59, 130, 246, 0.04) 50%, transparent 70%),
    linear-gradient(-45deg, transparent 30%, rgba(139, 92, 246, 0.04) 50%, transparent 70%);
  pointer-events: none;
  animation: lightPulse 8s ease-in-out infinite alternate;
}

@keyframes lightPulse {
  0% {
    opacity: 0.3;
  }
  100% {
    opacity: 0.7;
  }
}

.container {
  position: relative;
  z-index: 2;
  width: 100%;
  max-width: 420px;
  padding: 20px;
}

.login-card {
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(20px);
  border-radius: 24px;
  padding: 40px;
  box-shadow:
    0 25px 50px rgba(0, 0, 0, 0.15),
    0 0 0 1px rgba(255, 255, 255, 0.2);
  border: 1px solid rgba(255, 255, 255, 0.2);
  position: relative;
  overflow: hidden;
}

.login-card::before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  height: 3px;
  background: linear-gradient(90deg, #3b82f6, #8b5cf6, #10b981);
  border-radius: 24px 24px 0 0;
}

.header {
  text-align: center;
  margin-bottom: 32px;
}

.title {
  font-size: 28px;
  font-weight: 700;
  margin: 0 0 12px;
  background: linear-gradient(135deg, #3b82f6 0%, #8b5cf6 50%, #10b981 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.desc {
  color: #64748b;
  font-size: 14px;
  margin: 0;
}

.tabs {
  display: flex;
  margin-bottom: 32px;
  background: rgba(243, 244, 246, 0.8);
  border-radius: 12px;
  padding: 4px;
  position: relative;
}

.tab {
  flex: 1;
  text-align: center;
  padding: 12px 16px;
  font-weight: 500;
  border-radius: 8px;
  cursor: pointer;
  transition: all 0.3s;
  font-size: 14px;
  color: #6b7280;
}

.tab.active {
  background: rgba(255, 255, 255, 0.9);
  color: #3b82f6;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.login-form {
  margin: 0;
}

.input-wrapper {
  position: relative;
  display: flex;
  align-items: center;
}

.input-icon {
  position: absolute;
  left: 16px;
  z-index: 3;
  font-size: 16px;
  opacity: 0.6;
}

.toggle-icon {
  position: absolute;
  right: 16px;
  z-index: 3;
  font-size: 16px;
  opacity: 0.6;
  cursor: pointer;
}

:deep(.ant-input) {
  border-radius: 12px;
  border: 1px solid rgba(209, 213, 219, 0.6);
  background: rgba(255, 255, 255, 0.8);
  padding: 12px 16px;
  font-size: 14px;
  transition: all 0.3s;
}

:deep(.ant-input-password) {
  border-radius: 12px;
  border: 1px solid rgba(209, 213, 219, 0.6);
  background: rgba(255, 255, 255, 0.8);
  transition: all 0.3s;
}

:deep(.ant-input-password .ant-input) {
  padding: 12px 16px;
}

:deep(.ant-input:focus) {
  background: rgba(255, 255, 255, 1);
  border-color: #3b82f6;
  box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.1);
}

:deep(.ant-input-password:focus-within) {
  background: rgba(255, 255, 255, 1);
  border-color: #3b82f6;
  box-shadow: 0 0 0 3px rgba(59, 130, 246, 0.1);
}

:deep(.ant-form-item) {
  margin-bottom: 20px;
}

:deep(.ant-form-item-explain-error) {
  color: #ef4444;
  font-size: 12px;
  margin-top: 4px;
}

.login-btn {
  width: 100%;
  height: 48px;
  border-radius: 12px;
  background: linear-gradient(135deg, #3b82f6 0%, #8b5cf6 100%);
  border: none;
  font-weight: 600;
  font-size: 16px;
  margin-bottom: 20px;
  transition: all 0.3s;
  box-shadow: 0 4px 12px rgba(59, 130, 246, 0.3);
}

.login-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 8px 20px rgba(59, 130, 246, 0.4);
  background: linear-gradient(135deg, #2563eb 0%, #7c3aed 100%);
}

.tips {
  text-align: center;
  color: #6b7280;
  font-size: 14px;
  margin: 0;
}

.tips a {
  color: #3b82f6;
  text-decoration: none;
  font-weight: 500;
  transition: color 0.3s;
}

.tips a:hover {
  color: #2563eb;
}

/* 响应式设计 */
@media (max-width: 480px) {
  .container {
    padding: 16px;
  }

  .login-card {
    padding: 32px 24px;
  }

  .title {
    font-size: 24px;
  }
}
</style>
