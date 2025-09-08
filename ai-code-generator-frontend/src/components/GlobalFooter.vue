<template>
  <a-layout-footer class="footer">
    <div class="footer-container">
      <div class="footer-column about-platform">
        <h3 class="column-title">ZeroCodeX</h3>
        <p class="description">AI 应用生成平台，为您的创意赋能</p>
      </div>

      <div class="footer-column">
        <h3 class="column-title">产品服务</h3>
        <ul class="link-list">
          <li><a href="#">应用开发</a></li>
          <li><a href="#">产品文档</a></li>
          <li><a href="#">常见问题</a></li>
          <li><a href="#">技术支持</a></li>
        </ul>
      </div>

      <div class="footer-column">
        <h3 class="column-title">联系方式</h3>
        <ul class="link-list">
          <li>
            <a
              href="https://github.com/Mingxun-Chen/ai-code-generator"
              target="_blank"
              rel="noopener noreferrer"
              class="icon-text-link"
              ><GithubOutlined /> <span>GitHub</span></a
            >
          </li>
        </ul>
      </div>

      <div class="footer-column">
        <h3 class="column-title">关于</h3>
        <ul class="link-list">
          <li><a href="#">关于我们</a></li>
          <li><a href="#">服务条款</a></li>
          <li><a href="#">隐私政策</a></li>
        </ul>
      </div>
    </div>
  </a-layout-footer>
</template>

<script setup lang="ts">
import { GithubOutlined } from '@ant-design/icons-vue'

// 无需额外的响应式数据
</script>

<style scoped>
/* Footer 整体容器样式 */
.footer {
  background: #ffffff;
  padding: 50px 20px; /* 增加上下内边距 */
  border-top: 1px solid #e8e8e8;
  color: #555;
}

/* 内容主区域，使用 Flexbox 布局 */
.footer-container {
  max-width: 1200px;
  margin: 0 auto; /* 居中显示 */
  display: flex;
  flex-wrap: wrap; /* 在小屏幕上换行 */
  justify-content: space-between;
  gap: 0px; /* 定义列之间的最小间距 */
  text-align: left;
}

/* 每一列的样式 */
.footer-column {
  flex: 1; /* 每列默认占据1份空间 */
  min-width: 100px;
}

/* 【修改点1】给第一列分配更多空间，使其更宽 */
.about-platform {
  flex-grow: 2; /* 让第一列的增长系数为2，其他列为1，使其更宽 */
}

.column-title {
  font-size: 16px;
  font-weight: 600;
  color: #333;
  margin-bottom: 16px;
}

.description {
  font-size: 14px;
  line-height: 1.6;
  color: #666;
}

/* 社交图标样式 */
.social-icons {
  display: flex;
  gap: 12px;
  margin-top: 16px;
}

.icon-link {
  display: inline-block;
}

.icon {
  display: block;
  width: 32px;
  height: 32px;
  background-color: #f0f2f5;
  border-radius: 50%;
  transition: background-color 0.3s;
}

.icon-link:hover .icon {
  background-color: #e0e4e8;
}

/* 链接列表样式 */
.link-list {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.link-list li {
  margin-bottom: 10px;
}

.link-list a {
  color: #55555581;
  text-decoration: none;
  font-size: 14px;
  transition: color 0.3s;
}

.link-list a:hover {
  color: #1890ff;
}

/* 【修改点2】已删除 .footer-bottom, .copyright, .powered-by 的所有样式 */
.icon-text-link {
  display: flex;
  align-items: center;
  gap: 8px; /* 图标和文字之间的间距 */
}
</style>
