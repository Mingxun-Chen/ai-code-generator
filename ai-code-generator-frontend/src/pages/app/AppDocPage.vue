<script setup lang="ts">
// 应用文档页面组件
</script>

<template>
  <div id="appDocPage">
    <div class="container">
      <!-- 页面标题 -->
      <div class="doc-header">
        <h1 class="doc-title">应用文档</h1>
        <p class="doc-subtitle">学习如何使用AI应用生成平台，轻松创建你的专属网站应用</p>
      </div>

      <!-- 文档内容 -->
      <div class="doc-content">
        <!-- 快速开始 -->
        <section class="doc-section">
          <h2 class="section-title">🚀 快速开始</h2>
          <div class="section-content">
            <h3>什么是AI应用生成平台？</h3>
            <p>
              本平台是一个基于人工智能的网站应用生成工具，只需要用自然语言描述你想要的功能，AI就能自动为你生成完整的网站应用，包括前端界面、交互逻辑和样式设计。
            </p>

            <h3>主要特色</h3>
            <ul>
              <li><strong>智能理解</strong>：通过自然语言描述即可生成应用</li>
              <li><strong>实时预览</strong>：生成的应用可以立即在线预览</li>
              <li><strong>代码下载</strong>：支持下载完整的源代码包</li>
              <li><strong>持续优化</strong>：通过对话不断完善应用功能</li>
              <li><strong>多种模板</strong>：支持个人博客、企业官网、在线商城等多种应用类型</li>
            </ul>
          </div>
        </section>

        <!-- 用户指南 -->
        <section class="doc-section">
          <h2 class="section-title">👤 用户指南</h2>
          <div class="section-content">
            <h3>1. 注册与登录</h3>
            <p>首次使用需要注册账号：</p>
            <ul>
              <li>点击页面右上角的"登录"按钮</li>
              <li>在登录页面选择"注册新账号"</li>
              <li>填写用户名、账号和密码完成注册</li>
              <li>注册成功后自动登录到平台</li>
            </ul>

            <h3>2. 个人资料管理</h3>
            <p>登录后可以管理个人信息：</p>
            <ul>
              <li>点击用户头像进入个人中心</li>
              <li>可以修改用户名、头像和个人简介</li>
              <li>查看账号创建时间等基本信息</li>
            </ul>
          </div>
        </section>

        <!-- 创建应用 -->
        <section class="doc-section">
          <h2 class="section-title">⚡ 创建应用</h2>
          <div class="section-content">
            <h3>方式一：使用快捷模板</h3>
            <p>在首页提供了4个常用的应用模板：</p>
            <ul>
              <li><strong>个人博客网站</strong>：包含文章列表、详情页、分类标签、搜索和评论系统</li>
              <li><strong>企业官网</strong>：包含公司介绍、产品展示、新闻资讯和联系页面</li>
              <li><strong>在线商城</strong>：包含商品展示、购物车、用户系统和订单管理</li>
              <li><strong>作品展示网站</strong>：适合设计师、摄影师展示作品的精美画廊</li>
            </ul>
            <p>点击任一模板按钮，系统会自动填充详细的应用描述到输入框中。</p>

            <h3>方式二：自定义描述</h3>
            <p>在输入框中详细描述你想要的应用功能：</p>
            <div class="code-example">
              <h4>示例描述：</h4>
              <code>
                "创建一个在线教育平台，包含课程列表、视频播放、用户注册登录、
                课程购买、学习进度跟踪、在线考试、讨论区等功能。界面要简洁现代，
                支持响应式设计，适配手机和电脑端。"
              </code>
            </div>

            <h3>创建步骤：</h3>
            <ol>
              <li>在首页输入框中详细描述应用需求（支持1000字以内）</li>
              <li>点击"↑"按钮提交创建请求</li>
              <li>系统会自动跳转到应用对话页面</li>
              <li>AI开始分析需求并生成应用代码</li>
              <li>等待几分钟后即可预览生成的应用</li>
            </ol>
          </div>
        </section>

        <!-- 应用管理 -->
        <section class="doc-section">
          <h2 class="section-title">🔧 应用管理</h2>
          <div class="section-content">
            <h3>查看我的应用</h3>
            <p>在首页"我的作品"区域可以看到你创建的所有应用：</p>
            <ul>
              <li>每个应用卡片显示应用名称和封面图</li>
              <li>点击"查看对话"可以继续与AI对话优化应用</li>
              <li>点击"查看作品"可以在新窗口预览生成的应用</li>
            </ul>

            <h3>应用对话页面功能</h3>
            <p>在应用对话页面，你可以：</p>
            <ul>
              <li><strong>查看历史对话</strong>：查看与AI的完整对话记录</li>
              <li><strong>继续对话</strong>：提出新的需求或修改建议</li>
              <li><strong>实时预览</strong>：在右侧实时查看应用效果</li>
              <li><strong>部署应用</strong>：将应用发布到在线环境</li>
              <li><strong>下载代码</strong>：下载完整的应用源代码</li>
              <li><strong>编辑应用</strong>：修改应用名称等基本信息</li>
            </ul>

            <h3>应用部署</h3>
            <p>应用生成完成后，可以一键部署到线上：</p>
            <ol>
              <li>在应用对话页面点击"部署应用"按钮</li>
              <li>系统会自动构建并部署应用</li>
              <li>部署成功后会获得一个可访问的URL链接</li>
              <li>可以分享链接让其他人访问你的应用</li>
            </ol>
          </div>
        </section>

        <!-- 对话技巧 -->
        <section class="doc-section">
          <h2 class="section-title">💡 对话技巧</h2>
          <div class="section-content">
            <h3>如何写好应用描述</h3>
            <ul>
              <li><strong>功能要明确</strong>：清楚列出需要的功能模块</li>
              <li><strong>界面要具体</strong>：描述期望的界面风格和布局</li>
              <li><strong>交互要详细</strong>：说明用户如何与应用交互</li>
              <li><strong>数据要考虑</strong>：思考需要哪些数据和字段</li>
            </ul>

            <h3>优化应用的对话示例</h3>
            <div class="chat-examples">
              <div class="chat-example">
                <h4>添加新功能：</h4>
                <code>"请在博客中添加一个评论系统，用户可以对文章进行评论和回复"</code>
              </div>
              <div class="chat-example">
                <h4>修改样式：</h4>
                <code>"请把主题色改为蓝色系，整体风格更加现代简约"</code>
              </div>
              <div class="chat-example">
                <h4>优化布局：</h4>
                <code>"请优化移动端的响应式布局，在手机上查看更舒适"</code>
              </div>
              <div class="chat-example">
                <h4>修复问题：</h4>
                <code>"搜索功能似乎不太准确，请优化搜索算法"</code>
              </div>
            </div>
          </div>
        </section>

        <!-- 精选案例 -->
        <section class="doc-section">
          <h2 class="section-title">🌟 精选案例</h2>
          <div class="section-content">
            <p>在首页"精选案例"区域展示了平台上的优秀应用作品，这些案例可以给你提供创作灵感：</p>
            <ul>
              <li>浏览不同类型的应用设计</li>
              <li>学习其他用户的创意想法</li>
              <li>点击查看具体的实现效果</li>
              <li>参考优秀案例的功能设计</li>
            </ul>
          </div>
        </section>

        <!-- 常见问题 -->
        <section class="doc-section">
          <h2 class="section-title">❓ 常见问题</h2>
          <div class="section-content">
            <div class="faq-item">
              <h4>Q: 生成应用需要多长时间？</h4>
              <p>
                A:
                通常需要2-5分钟，具体时间取决于应用的复杂程度。简单的单页应用可能只需要1-2分钟，复杂的多页面应用可能需要5-8分钟。
              </p>
            </div>

            <div class="faq-item">
              <h4>Q: 生成的应用可以修改吗？</h4>
              <p>
                A:
                可以的。你可以继续与AI对话，提出修改需求，AI会基于你的反馈不断优化应用。也可以下载源代码进行手动修改。
              </p>
            </div>

            <div class="faq-item">
              <h4>Q: 应用支持哪些技术栈？</h4>
              <p>
                A:
                平台主要生成基于HTML、CSS、JavaScript的前端应用，支持Vue.js框架，样式使用现代CSS技术，确保良好的兼容性和用户体验。
              </p>
            </div>

            <div class="faq-item">
              <h4>Q: 生成的应用有访问限制吗？</h4>
              <p>
                A:
                部署后的应用可以公开访问，没有访问限制。你可以将链接分享给任何人，也可以嵌入到其他网站中使用。
              </p>
            </div>

            <div class="faq-item">
              <h4>Q: 如何下载应用代码？</h4>
              <p>
                A:
                在应用对话页面点击"下载代码"按钮，系统会打包所有源文件供你下载。下载的代码包含完整的项目结构，可以直接运行。
              </p>
            </div>

            <div class="faq-item">
              <h4>Q: 忘记密码怎么办？</h4>
              <p>A: 如果忘记密码，请联系平台管理员重置密码。建议妥善保管账号信息，避免数据丢失。</p>
            </div>
          </div>
        </section>

        <!-- 联系支持 -->
        <section class="doc-section">
          <h2 class="section-title">📞 联系支持</h2>
          <div class="section-content">
            <p>如果在使用过程中遇到问题，或者有功能建议，欢迎联系我们：</p>
            <ul>
              <li><strong>问题反馈</strong>：请详细描述遇到的问题和重现步骤</li>
              <li><strong>功能建议</strong>：我们会认真考虑每一个改进建议</li>
              <li><strong>Bug报告</strong>：帮助我们发现和修复系统问题</li>
            </ul>
            <p class="contact-note">
              我们致力于为用户提供最好的AI应用生成体验，你的反馈对我们非常重要！
            </p>
          </div>
        </section>
      </div>
    </div>
  </div>
</template>

<style scoped>
#appDocPage {
  width: 100%;
  margin: 0;
  padding: 0;
  min-height: 100vh;
  background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
  background-attachment: fixed;
}

.container {
  max-width: 1000px;
  margin: 0 auto;
  padding: 40px 20px;
  position: relative;
  z-index: 2;
}

/* 文档头部 */
.doc-header {
  text-align: center;
  padding: 40px 0 60px;
  margin-bottom: 40px;
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(20px);
  border-radius: 20px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.1);
}

.doc-title {
  font-size: 48px;
  font-weight: 700;
  margin: 0 0 16px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background-clip: text;
}

.doc-subtitle {
  font-size: 18px;
  color: #64748b;
  margin: 0;
  line-height: 1.6;
}

/* 文档内容 */
.doc-content {
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(20px);
  border-radius: 20px;
  padding: 60px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.1);
  border: 1px solid rgba(255, 255, 255, 0.3);
}

/* 文档章节 */
.doc-section {
  margin-bottom: 60px;
}

.doc-section:last-child {
  margin-bottom: 0;
}

.section-title {
  font-size: 32px;
  font-weight: 600;
  margin-bottom: 24px;
  color: #1e293b;
  border-left: 4px solid #667eea;
  padding-left: 20px;
  display: flex;
  align-items: center;
}

.section-content h3 {
  font-size: 20px;
  font-weight: 600;
  margin: 32px 0 16px 0;
  color: #334155;
}

.section-content h4 {
  font-size: 16px;
  font-weight: 600;
  margin: 24px 0 12px 0;
  color: #475569;
}

.section-content p {
  font-size: 16px;
  line-height: 1.8;
  color: #64748b;
  margin-bottom: 16px;
}

.section-content ul,
.section-content ol {
  margin: 16px 0 24px 0;
  padding-left: 24px;
}

.section-content li {
  font-size: 16px;
  line-height: 1.8;
  color: #64748b;
  margin-bottom: 8px;
}

.section-content li strong {
  color: #475569;
  font-weight: 600;
}

/* 代码示例 */
.code-example {
  background: #f8fafc;
  border: 1px solid #e2e8f0;
  border-radius: 12px;
  padding: 20px;
  margin: 24px 0;
}

.code-example h4 {
  margin-top: 0;
  margin-bottom: 12px;
  color: #374151;
}

.code-example code {
  display: block;
  background: #1f2937;
  color: #f9fafb;
  padding: 16px;
  border-radius: 8px;
  font-family: 'Monaco', 'Consolas', monospace;
  font-size: 14px;
  line-height: 1.6;
  white-space: pre-wrap;
  word-break: break-word;
}

/* 对话示例 */
.chat-examples {
  margin-top: 24px;
}

.chat-example {
  background: #f1f5f9;
  border: 1px solid #cbd5e1;
  border-radius: 12px;
  padding: 20px;
  margin-bottom: 16px;
}

.chat-example h4 {
  margin-top: 0;
  margin-bottom: 12px;
  color: #334155;
}

.chat-example code {
  display: block;
  background: #0f172a;
  color: #e2e8f0;
  padding: 12px;
  border-radius: 6px;
  font-family: 'Monaco', 'Consolas', monospace;
  font-size: 14px;
  line-height: 1.5;
}

/* FAQ */
.faq-item {
  background: #f8fafc;
  border: 1px solid #e2e8f0;
  border-radius: 12px;
  padding: 24px;
  margin-bottom: 20px;
}

.faq-item h4 {
  margin-top: 0;
  margin-bottom: 12px;
  color: #1e293b;
  font-size: 18px;
}

.faq-item p {
  margin-bottom: 0;
  color: #64748b;
}

/* 联系信息 */
.contact-note {
  background: linear-gradient(135deg, #667eea, #ece5f2);
  color: white;
  padding: 20px;
  border-radius: 12px;
  margin-top: 24px;
  font-weight: 500;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .container {
    padding: 20px 16px;
  }

  .doc-header {
    padding: 30px 20px 40px;
    margin-bottom: 30px;
  }

  .doc-title {
    font-size: 32px;
  }

  .doc-subtitle {
    font-size: 16px;
  }

  .doc-content {
    padding: 40px 24px;
  }

  .section-title {
    font-size: 24px;
  }

  .section-content h3 {
    font-size: 18px;
  }

  .code-example code,
  .chat-example code {
    font-size: 12px;
  }
}

@media (max-width: 480px) {
  .doc-content {
    padding: 30px 20px;
  }

  .section-title {
    font-size: 20px;
    padding-left: 16px;
  }

  .section-content h3 {
    font-size: 16px;
  }
}
</style>
