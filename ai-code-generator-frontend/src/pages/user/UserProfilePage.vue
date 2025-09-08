<template>
  <div id="userProfilePage">
    <div class="page-header">
      <h2>更新个人信息</h2>
      <p>修改您的个人资料信息</p>
    </div>
    
    <div class="profile-content">
      <a-card class="profile-card">
        <a-form
          :model="form"
          :rules="rules"
          ref="formRef"
          layout="vertical"
          @finish="handleSubmit"
        >
          <!-- 头像上传 -->
          <a-form-item label="头像" name="userAvatar">
            <div class="avatar-upload">
              <a-avatar :size="80" :src="form.userAvatar">
                {{ form.userName?.charAt(0)?.toUpperCase() || 'U' }}
              </a-avatar>
              <div class="upload-info">
                <a-input
                  v-model:value="form.userAvatar"
                  placeholder="请输入头像URL"
                  size="large"
                />
                <p class="upload-tip">支持jpg、png格式，建议尺寸128x128</p>
              </div>
            </div>
          </a-form-item>

          <!-- 用户名 -->
          <a-form-item label="用户名" name="userName">
            <a-input
              v-model:value="form.userName"
              placeholder="请输入用户名"
              size="large"
              :maxlength="20"
            />
          </a-form-item>

          <!-- 个人简介 -->
          <a-form-item label="个人简介" name="userProfile">
            <a-textarea
              v-model:value="form.userProfile"
              placeholder="请输入个人简介"
              :rows="4"
              :maxlength="200"
              show-count
            />
          </a-form-item>

          <!-- 按钮组 -->
          <a-form-item>
            <a-space>
              <a-button
                type="primary"
                html-type="submit"
                size="large"
                :loading="loading"
              >
                <template #icon>
                  <SaveOutlined />
                </template>
                保存修改
              </a-button>
              <a-button size="large" @click="handleCancel">
                取消
              </a-button>
            </a-space>
          </a-form-item>
        </a-form>
      </a-card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, onBeforeUnmount } from 'vue'
import { useRouter } from 'vue-router'
import { message } from 'ant-design-vue'
import { SaveOutlined } from '@ant-design/icons-vue'
import { useLoginUserStore } from '@/stores/loginUser'
import { updateUser, getLoginUser } from '@/api/userController'
import type { FormInstance } from 'ant-design-vue'

const router = useRouter()
const loginUserStore = useLoginUserStore()
const formRef = ref<FormInstance>()
const loading = ref(false)

// 表单数据
const form = reactive<API.UserUpdateRequest>({
  id: undefined,
  userName: '',
  userAvatar: '',
  userProfile: '',
})

// 表单验证规则
const rules = {
  userName: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 2, max: 20, message: '用户名长度在 2 到 20 个字符', trigger: 'blur' },
  ],
  userAvatar: [
    {
      pattern: /^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$/,
      message: '请输入有效的URL地址',
      trigger: 'blur',
    },
  ],
  userProfile: [
    { max: 200, message: '个人简介不能超过200个字符', trigger: 'blur' },
  ],
}

// 获取用户信息
const fetchUserInfo = async () => {
  try {
    const res = await getLoginUser()
    if (res.data.code === 0 && res.data.data) {
      const userData = res.data.data
      form.id = userData.id
      form.userName = userData.userName || ''
      form.userAvatar = userData.userAvatar || ''
      form.userProfile = userData.userProfile || ''
    } else {
      message.error('获取用户信息失败')
    }
  } catch (error) {
    message.error('获取用户信息失败')
  }
}

// 提交表单
const handleSubmit = async () => {
  if (!formRef.value) return
  
  try {
    await formRef.value.validate()
    loading.value = true
    
    const res = await updateUser({
      id: form.id,
      userName: form.userName,
      userAvatar: form.userAvatar || undefined,
      userProfile: form.userProfile || undefined,
    })
    
    if (res.data.code === 0) {
      message.success('更新个人信息成功')
      
      // 更新本地用户信息
      await loginUserStore.fetchLoginUser()
      
      // 跳转回首页
      await router.push('/')
    } else {
      message.error('更新失败：' + (res.data.message || '未知错误'))
    }
  } catch (error) {
    console.error('更新用户信息失败:', error)
    message.error('更新失败，请重试')
  } finally {
    loading.value = false
  }
}

// 取消操作
const handleCancel = () => {
  router.back()
}

// 鼠标跟随光效
const handleMouseMove = (e: MouseEvent) => {
  const { clientX, clientY } = e
  const { innerWidth, innerHeight } = window
  const x = (clientX / innerWidth) * 100
  const y = (clientY / innerHeight) * 100

  document.documentElement.style.setProperty('--mouse-x', `${x}%`)
  document.documentElement.style.setProperty('--mouse-y', `${y}%`)
}

// 页面加载时获取用户信息
onMounted(() => {
  fetchUserInfo()
  document.addEventListener('mousemove', handleMouseMove)
})

onBeforeUnmount(() => {
  document.removeEventListener('mousemove', handleMouseMove)
})
</script>

<style scoped>
#userProfilePage {
  min-height: calc(100vh - 64px);
  background:
    linear-gradient(180deg, #f8fafc 0%, #f1f5f9 8%, #e2e8f0 20%, #cbd5e1 100%),
    radial-gradient(circle at 20% 80%, rgba(59, 130, 246, 0.15) 0%, transparent 50%),
    radial-gradient(circle at 80% 20%, rgba(139, 92, 246, 0.12) 0%, transparent 50%),
    radial-gradient(circle at 40% 40%, rgba(16, 185, 129, 0.08) 0%, transparent 50%);
  background-image: linear-gradient(to top, #a8edea 0%, #fed6e3 100%);
  padding: 24px;
  position: relative;
  overflow: hidden;
}

/* 科技感网格背景 */
#userProfilePage::before {
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
#userProfilePage::after {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background:
    radial-gradient(
      600px circle at var(--mouse-x, 50%) var(--mouse-y, 50%),
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

.page-header {
  text-align: center;
  margin-bottom: 32px;
  color: white;
}

.page-header h2 {
  font-size: 32px;
  font-weight: 600;
  margin-bottom: 8px;
  color: white;
}

.page-header p {
  font-size: 16px;
  opacity: 0.9;
  color: white;
}

.profile-content {
  max-width: 600px;
  margin: 0 auto;
  position: relative;
  z-index: 2;
}

.profile-card {
  border-radius: 16px;
  box-shadow: 0 8px 32px rgba(0, 0, 0, 0.1);
  border: none;
}

.avatar-upload {
  display: flex;
  align-items: center;
  gap: 16px;
}

.upload-info {
  flex: 1;
}

.upload-tip {
  font-size: 12px;
  color: #999;
  margin-top: 4px;
  margin-bottom: 0;
}

:deep(.ant-form-item-label > label) {
  font-weight: 500;
  color: #1a1a1a;
}

:deep(.ant-input),
:deep(.ant-input:focus),
:deep(.ant-input-focused) {
  border-radius: 8px;
}

:deep(.ant-btn) {
  border-radius: 8px;
  height: 40px;
  font-weight: 500;
}

:deep(.ant-btn-primary) {
  background: linear-gradient(135deg, #3b82f6 0%, #8b5cf6 100%);
  border: none;
  box-shadow: 0 4px 15px rgba(59, 130, 246, 0.3);
}

:deep(.ant-btn-primary:hover) {
  background: linear-gradient(135deg, #2563eb 0%, #7c3aed 100%);
  transform: translateY(-2px);
  box-shadow: 0 8px 25px rgba(59, 130, 246, 0.4);
}
</style>
