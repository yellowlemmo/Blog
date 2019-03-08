/*
 Navicat Premium Data Transfer

 Source Server         : myself
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : springboot

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 08/03/2019 17:39:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------
BEGIN;
INSERT INTO `persistent_logins` VALUES ('admin', '0xy5N4T6NJHZR5cjn1a6QQ==', 'YihpUnFl8FFHJGaDVP7pmg==', '2019-03-08 01:32:33');
INSERT INTO `persistent_logins` VALUES ('cuizhichao', 'd5AjQTRCSUwETyhWth6teQ==', 'BzSfsql8n14hpnR99bClvw==', '2019-01-27 01:38:12');
INSERT INTO `persistent_logins` VALUES ('admin', 'fJvht9iIsqFi+b+yrxzmqw==', 'tiGwilwf1YCnLgKj6sWOMQ==', '2019-03-08 01:34:20');
INSERT INTO `persistent_logins` VALUES ('cuizhichao', 'M7b6/C7H/oOBTgaQz8uD8g==', 'AZqICJFkh+VPhZjJuXCOCA==', '2019-01-16 07:35:10');
INSERT INTO `persistent_logins` VALUES ('admin', 'PQopI7iQISRV3ifREB8kOg==', '2hHukA167msQtmoqtQXdLQ==', '2019-03-08 01:34:17');
INSERT INTO `persistent_logins` VALUES ('admin', 'yvEqMCddoAGYhr1DiE8ZTw==', 'ICH1GneVFwOlRHbD9uzcGQ==', '2019-02-11 08:41:13');
COMMIT;

-- ----------------------------
-- Table structure for sys_article
-- ----------------------------
DROP TABLE IF EXISTS `sys_article`;
CREATE TABLE `sys_article` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `context` text,
  `create_date` datetime NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `article_classify` varchar(255) DEFAULT NULL,
  `click_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKs98shd73yedmbngjam3qfg0q` (`article_classify`),
  KEY `FKd0pvk2xc83n5ttf8mpmevdtq` (`author`),
  CONSTRAINT `FKd0pvk2xc83n5ttf8mpmevdtq` FOREIGN KEY (`author`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKs98shd73yedmbngjam3qfg0q` FOREIGN KEY (`article_classify`) REFERENCES `sys_article_classify` (`classify_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_article
-- ----------------------------
BEGIN;
INSERT INTO `sys_article` VALUES ('402880ee68b7c1fd0168b7c2e7650000', 'eee', '<p>eerererer</p>\r\n', '2019-02-04 03:07:10', '402881e4682dd0ea01682dd1fdbe0001', '402881e667eabc210167eabc7e1d0000', 0);
INSERT INTO `sys_article` VALUES ('40288184688e7f3501688e80ca180000', 'new index', '<p>@<a href=\"%E8%BF%99%E9%87%8C%E5%86%99%E8%87%AA%E5%AE%9A%E4%B9%89%E7%9B%AE%E5%BD%95%E6%A0%87%E9%A2%98\">TOC</a></p>\r\n<h1 id=\"-markdown-\">欢迎使用Markdown编辑器</h1>\r\n<p>你好！ 这是你第一次使用 <strong>Markdown编辑器</strong> 所展示的欢迎页。如果你想学习如何使用Markdown编辑器, 可以仔细阅读这篇文章，了解一下Markdown的基本语法知识。</p>\r\n<h2 id=\"-\">新的改变</h2>\r\n<p>我们对Markdown编辑器进行了一些功能拓展与语法支持，除了标准的Markdown编辑器功能，我们增加了如下几点新功能，帮助你用它写博客：</p>\r\n<ol>\r\n<li><strong>全新的界面设计</strong> ，将会带来全新的写作体验；</li>\r\n<li>在创作中心设置你喜爱的代码高亮样式，Markdown <strong>将代码片显示选择的高亮样式</strong> 进行展示；</li>\r\n<li>增加了 <strong>图片拖拽</strong> 功能，你可以将本地的图片直接拖拽到编辑区域直接展示；</li>\r\n<li>全新的 <strong>KaTeX数学公式</strong> 语法；</li>\r\n<li>增加了支持<strong>甘特图的mermaid语法<a href=\"%5Bmermaid%E8%AF%AD%E6%B3%95%E8%AF%B4%E6%98%8E%5D(https://mermaidjs.github.io/)\">^1</a></strong> 功能；</li>\r\n<li>增加了 <strong>多屏幕编辑</strong> Markdown文章功能；</li>\r\n<li>增加了 <strong>焦点写作模式、预览模式、简洁写作模式、左右区域同步滚轮设置</strong> 等功能，功能按钮位于编辑区域与预览区域中间；</li>\r\n<li>增加了 <strong>检查列表</strong> 功能。</li>\r\n</ol>\r\n<h2 id=\"-\">功能快捷键</h2>\r\n<p>撤销：<kbd>Ctrl/Command</kbd> + <kbd>Z</kbd>\r\n重做：<kbd>Ctrl/Command</kbd> + <kbd>Y</kbd>\r\n加粗：<kbd>Ctrl/Command</kbd> + <kbd>B</kbd>\r\n斜体：<kbd>Ctrl/Command</kbd> + <kbd>I</kbd>\r\n标题：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>H</kbd>\r\n无序列表：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>U</kbd>\r\n有序列表：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>O</kbd>\r\n检查列表：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>C</kbd>\r\n插入代码：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>K</kbd>\r\n插入链接：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>L</kbd>\r\n插入图片：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>G</kbd></p>\r\n<h2 id=\"-\">合理的创建标题，有助于目录的生成</h2>\r\n<p>直接输入1次<kbd>#</kbd>，并按下<kbd>space</kbd>后，将生成1级标题。\r\n输入2次<kbd>#</kbd>，并按下<kbd>space</kbd>后，将生成2级标题。\r\n以此类推，我们支持6级标题。有助于使用<code>TOC</code>语法后生成一个完美的目录。</p>\r\n<h2 id=\"-\">如何改变文本的样式</h2>\r\n<p><em>强调文本</em> <em>强调文本</em></p>\r\n<p><strong>加粗文本</strong> <strong>加粗文本</strong></p>\r\n<p>==标记文本==</p>\r\n<p><del>删除文本</del></p>\r\n<blockquote>\r\n<p>引用文本</p>\r\n</blockquote>\r\n<p>H<del>2</del>O is是液体。</p>\r\n<p>2^10^ 运算结果是 1024.</p>\r\n<h2 id=\"-\">插入链接与图片</h2>\r\n<p>链接: <a href=\"https://mp.csdn.net\">link</a>.</p>\r\n<p>图片: <img src=\"https://avatar.csdn.net/7/7/B/1_ralf_hx163com.jpg\" alt=\"Alt\"></p>\r\n<p>带尺寸的图片: ![Alt](<a href=\"https://avatar.csdn.net/7/7/B/1_ralf_hx163com.jpg\">https://avatar.csdn.net/7/7/B/1_ralf_hx163com.jpg</a> =30x30)</p>\r\n<p>当然，我们为了让用户更加便捷，我们增加了图片拖拽功能。</p>\r\n<h2 id=\"-\">如何插入一段漂亮的代码片</h2>\r\n<p>去<a href=\"https://mp.csdn.net/configure\">博客设置</a>页面，选择一款你喜欢的代码片高亮样式，下面展示同样高亮的 <code>代码片</code>.</p>\r\n<pre><code class=\"language-javascript\">// An highlighted block\r\nvar foo = \'bar\';</code></pre>\r\n<h2 id=\"-\">生成一个适合你的列表</h2>\r\n<ul>\r\n<li><p>项目</p>\r\n<ul>\r\n<li>项目<ul>\r\n<li>项目</li>\r\n</ul>\r\n</li>\r\n</ul>\r\n</li>\r\n<li><p>项目1</p>\r\n</li>\r\n<li><p>项目2</p>\r\n</li>\r\n<li><p>项目3</p>\r\n</li>\r\n<li><input disabled=\"\" type=\"checkbox\"> <p>计划任务</p>\r\n</li>\r\n<li><input checked=\"\" disabled=\"\" type=\"checkbox\"> <p>完成任务</p>\r\n</li>\r\n</ul>\r\n<h2 id=\"-\">创建一个表格</h2>\r\n<p>一个简单的表格是这么创建的：\r\n项目     | Value\r\n-------- | -----\r\n电脑  | $1600\r\n手机  | $12\r\n导管  | $1</p>\r\n<h3 id=\"-\">设定内容居中、居左、居右</h3>\r\n<p>使用<code>:---------:</code>居中\r\n使用<code>:----------</code>居左\r\n使用<code>----------:</code>居右\r\n| 第一列       | 第二列         | 第三列        |\r\n|:-----------:| -------------:|:-------------|\r\n| 第一列文本居中 | 第二列文本居右  | 第三列文本居左 | </p>\r\n<h3 id=\"smartypants\">SmartyPants</h3>\r\n<p>SmartyPants将ASCII标点字符转换为“智能”印刷标点HTML实体。例如：\r\n|    TYPE   |ASCII                          |HTML<br>|----------------|-------------------------------|-----------------------------|\r\n|Single backticks|<code>\'Isn\'t this fun?\'</code>            |\'Isn\'t this fun?\'            |\r\n|Quotes          |<code>\"Isn\'t this fun?\"</code>            |\"Isn\'t this fun?\"            |\r\n|Dashes          |<code>-- is en-dash, --- is em-dash</code>|-- is en-dash, --- is em-dash|</p>\r\n<h2 id=\"-\">创建一个自定义列表</h2>\r\n<p>Markdown\r\n:  Text-to-HTML conversion tool</p>\r\n<p>Authors\r\n:  John\r\n:  Luke</p>\r\n<h2 id=\"-\">如何创建一个注脚</h2>\r\n<p>一个具有注脚的文本。<a href=\"%E6%B3%A8%E8%84%9A%E7%9A%84%E8%A7%A3%E9%87%8A\">^2</a></p>\r\n<h2 id=\"-\">注释也是必不可少的</h2>\r\n<p>Markdown将文本转换为 HTML。</p>\r\n<p>*[HTML]:   超文本标记语言</p>\r\n<h2 id=\"katex-\">KaTeX数学公式</h2>\r\n<p>您可以使用渲染LaTeX数学表达式 <a href=\"https://khan.github.io/KaTeX/\">KaTeX</a>:</p>\r\n<p>Gamma公式展示 $\\Gamma(n) = (n-1)!\\quad\\forall\r\nn\\in\\mathbb N$ 是通过欧拉积分</p>\r\n<p>$$\r\n\\Gamma(z) = \\int_0^\\infty t^{z-1}e^{-t}dt,.\r\n$$</p>\r\n<blockquote>\r\n<p>你可以找到更多关于的信息 <strong>LaTeX</strong> 数学表达式<a href=\"http://meta.math.stackexchange.com/questions/5020/mathjax-basic-tutorial-and-quick-reference\">here</a>.</p>\r\n</blockquote>\r\n<h2 id=\"-\">新的甘特图功能，丰富你的文章</h2>\r\n<pre><code class=\"language-mermaid\">gantt\r\n        dateFormat  YYYY-MM-DD\r\n        title Adding GANTT diagram functionality to mermaid\r\n        section 现有任务\r\n        已完成               :done,    des1, 2014-01-06,2014-01-08\r\n        进行中               :active,  des2, 2014-01-09, 3d\r\n        计划一               :         des3, after des2, 5d\r\n        计划二               :         des4, after des3, 5d</code></pre>\r\n<ul>\r\n<li>关于 <strong>甘特图</strong> 语法，参考 <a href=\"https://mermaidjs.github.io/\">这儿</a>,</li>\r\n</ul>\r\n<h2 id=\"uml-\">UML 图表</h2>\r\n<p>可以使用UML图表进行渲染。 <a href=\"https://mermaidjs.github.io/\">Mermaid</a>. 例如下面产生的一个序列图：:</p>\r\n<pre><code class=\"language-mermaid\">sequenceDiagram\r\n张三 -&gt;&gt; 李四: 你好！李四, 最近怎么样?\r\n李四--&gt;&gt;王五: 你最近怎么样，王五？\r\n李四--x 张三: 我很好，谢谢!\r\n李四-x 王五: 我很好，谢谢!\r\nNote right of 王五: 李四想了很长时间, 文字太长了&lt;br/&gt;不适合放在一行.\r\n\r\n李四--&gt;&gt;张三: 打量着王五...\r\n张三-&gt;&gt;王五: 很好... 王五, 你怎么样?</code></pre>\r\n<p>这将产生一个流程图。:</p>\r\n<pre><code class=\"language-mermaid\">graph LR\r\nA[长方形] -- 链接 --&gt; B((圆))\r\nA --&gt; C(圆角长方形)\r\nB --&gt; D{菱形}\r\nC --&gt; D</code></pre>\r\n<ul>\r\n<li>关于 <strong>Mermaid</strong> 语法，参考 <a href=\"https://mermaidjs.github.io/\">这儿</a>,</li>\r\n</ul>\r\n<h2 id=\"flowchart-\">FLowchart流程图</h2>\r\n<p>我们依旧会支持flowchart的流程图：</p>\r\n<pre><code class=\"language-mermaid\">flowchat\r\nst=&gt;start: 开始\r\ne=&gt;end: 结束\r\nop=&gt;operation: 我的操作\r\ncond=&gt;condition: 确认？\r\n\r\nst-&gt;op-&gt;cond\r\ncond(yes)-&gt;e\r\ncond(no)-&gt;op</code></pre>\r\n<ul>\r\n<li>关于 <strong>Flowchart流程图</strong> 语法，参考 <a href=\"http://adrai.github.io/flowchart.js/\">这儿</a>.</li>\r\n</ul>\r\n<h2 id=\"-\">导出与导入</h2>\r\n<h3 id=\"-\">导出</h3>\r\n<p>如果你想尝试使用此编辑器, 你可以在此篇文章任意编辑。当你完成了一篇文章的写作, 在上方工具栏找到 <strong>文章导出</strong> ，生成一个.md文件或者.html文件进行本地保存。</p>\r\n<h3 id=\"-\">导入</h3>\r\n<p>如果你想加载一篇你写过的.md文件或者.html文件，在上方工具栏可以选择导入功能进行对应扩展名的文件导入，\r\n继续你的创作。</p>\r\n', '2019-01-27 02:50:32', '402881e4682dd0ea01682dd1fdbe0001', '402881e667eabc210167eabc7e1d0000', 0);
INSERT INTO `sys_article` VALUES ('402881e4682dd0ea01682dd177890000', 'csdn模版', '<p>@<a href=\"%E8%BF%99%E9%87%8C%E5%86%99%E8%87%AA%E5%AE%9A%E4%B9%89%E7%9B%AE%E5%BD%95%E6%A0%87%E9%A2%98\">TOC</a></p>\r\n<h1 id=\"-markdown-\">欢迎使用Markdown编辑器</h1>\r\n<p>你好！ 这是你第一次使用 <strong>Markdown编辑器</strong> 所展示的欢迎页。如果你想学习如何使用Markdown编辑器, 可以仔细阅读这篇文章，了解一下Markdown的基本语法知识。</p>\r\n<h2 id=\"-\">新的改变</h2>\r\n<p>我们对Markdown编辑器进行了一些功能拓展与语法支持，除了标准的Markdown编辑器功能，我们增加了如下几点新功能，帮助你用它写博客：</p>\r\n<ol>\r\n<li><strong>全新的界面设计</strong> ，将会带来全新的写作体验；</li>\r\n<li>在创作中心设置你喜爱的代码高亮样式，Markdown <strong>将代码片显示选择的高亮样式</strong> 进行展示；</li>\r\n<li>增加了 <strong>图片拖拽</strong> 功能，你可以将本地的图片直接拖拽到编辑区域直接展示；</li>\r\n<li>全新的 <strong>KaTeX数学公式</strong> 语法；</li>\r\n<li>增加了支持<strong>甘特图的mermaid语法<a href=\"%5Bmermaid%E8%AF%AD%E6%B3%95%E8%AF%B4%E6%98%8E%5D(https://mermaidjs.github.io/)\">^1</a></strong> 功能；</li>\r\n<li>增加了 <strong>多屏幕编辑</strong> Markdown文章功能；</li>\r\n<li>增加了 <strong>焦点写作模式、预览模式、简洁写作模式、左右区域同步滚轮设置</strong> 等功能，功能按钮位于编辑区域与预览区域中间；</li>\r\n<li>增加了 <strong>检查列表</strong> 功能。</li>\r\n</ol>\r\n<h2 id=\"-\">功能快捷键</h2>\r\n<p>撤销：<kbd>Ctrl/Command</kbd> + <kbd>Z</kbd>\r\n重做：<kbd>Ctrl/Command</kbd> + <kbd>Y</kbd>\r\n加粗：<kbd>Ctrl/Command</kbd> + <kbd>B</kbd>\r\n斜体：<kbd>Ctrl/Command</kbd> + <kbd>I</kbd>\r\n标题：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>H</kbd>\r\n无序列表：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>U</kbd>\r\n有序列表：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>O</kbd>\r\n检查列表：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>C</kbd>\r\n插入代码：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>K</kbd>\r\n插入链接：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>L</kbd>\r\n插入图片：<kbd>Ctrl/Command</kbd> + <kbd>Shift</kbd> + <kbd>G</kbd></p>\r\n<h2 id=\"-\">合理的创建标题，有助于目录的生成</h2>\r\n<p>直接输入1次<kbd>#</kbd>，并按下<kbd>space</kbd>后，将生成1级标题。\r\n输入2次<kbd>#</kbd>，并按下<kbd>space</kbd>后，将生成2级标题。\r\n以此类推，我们支持6级标题。有助于使用<code>TOC</code>语法后生成一个完美的目录。</p>\r\n<h2 id=\"-\">如何改变文本的样式</h2>\r\n<p><em>强调文本</em> <em>强调文本</em></p>\r\n<p><strong>加粗文本</strong> <strong>加粗文本</strong></p>\r\n<p>==标记文本==</p>\r\n<p><del>删除文本</del></p>\r\n<blockquote>\r\n<p>引用文本</p>\r\n</blockquote>\r\n<p>H<del>2</del>O is是液体。</p>\r\n<p>2^10^ 运算结果是 1024.</p>\r\n<h2 id=\"-\">插入链接与图片</h2>\r\n<p>链接: <a href=\"https://mp.csdn.net\">link</a>.</p>\r\n<p>图片: <img src=\"https://avatar.csdn.net/7/7/B/1_ralf_hx163com.jpg\" alt=\"Alt\"></p>\r\n<p>带尺寸的图片: ![Alt](<a href=\"https://avatar.csdn.net/7/7/B/1_ralf_hx163com.jpg\">https://avatar.csdn.net/7/7/B/1_ralf_hx163com.jpg</a> =30x30)</p>\r\n<p>当然，我们为了让用户更加便捷，我们增加了图片拖拽功能。</p>\r\n<h2 id=\"-\">如何插入一段漂亮的代码片</h2>\r\n<p>去<a href=\"https://mp.csdn.net/configure\">博客设置</a>页面，选择一款你喜欢的代码片高亮样式，下面展示同样高亮的 <code>代码片</code>.</p>\r\n<pre><code class=\"language-javascript\">// An highlighted block\r\nvar foo = \'bar\';</code></pre>\r\n<h2 id=\"-\">生成一个适合你的列表</h2>\r\n<ul>\r\n<li><p>项目</p>\r\n<ul>\r\n<li>项目<ul>\r\n<li>项目</li>\r\n</ul>\r\n</li>\r\n</ul>\r\n</li>\r\n<li><p>项目1</p>\r\n</li>\r\n<li><p>项目2</p>\r\n</li>\r\n<li><p>项目3</p>\r\n</li>\r\n<li><input disabled=\"\" type=\"checkbox\"> <p>计划任务</p>\r\n</li>\r\n<li><input checked=\"\" disabled=\"\" type=\"checkbox\"> <p>完成任务</p>\r\n</li>\r\n</ul>\r\n<h2 id=\"-\">创建一个表格</h2>\r\n<p>一个简单的表格是这么创建的：\r\n项目     | Value\r\n-------- | -----\r\n电脑  | $1600\r\n手机  | $12\r\n导管  | $1</p>\r\n<h3 id=\"-\">设定内容居中、居左、居右</h3>\r\n<p>使用<code>:---------:</code>居中\r\n使用<code>:----------</code>居左\r\n使用<code>----------:</code>居右\r\n| 第一列       | 第二列         | 第三列        |\r\n|:-----------:| -------------:|:-------------|\r\n| 第一列文本居中 | 第二列文本居右  | 第三列文本居左 | </p>\r\n<h3 id=\"smartypants\">SmartyPants</h3>\r\n<p>SmartyPants将ASCII标点字符转换为“智能”印刷标点HTML实体。例如：\r\n|    TYPE   |ASCII                          |HTML<br>|----------------|-------------------------------|-----------------------------|\r\n|Single backticks|<code>\'Isn\'t this fun?\'</code>            |\'Isn\'t this fun?\'            |\r\n|Quotes          |<code>\"Isn\'t this fun?\"</code>            |\"Isn\'t this fun?\"            |\r\n|Dashes          |<code>-- is en-dash, --- is em-dash</code>|-- is en-dash, --- is em-dash|</p>\r\n<h2 id=\"-\">创建一个自定义列表</h2>\r\n<p>Markdown\r\n:  Text-to-HTML conversion tool</p>\r\n<p>Authors\r\n:  John\r\n:  Luke</p>\r\n<h2 id=\"-\">如何创建一个注脚</h2>\r\n<p>一个具有注脚的文本。<a href=\"%E6%B3%A8%E8%84%9A%E7%9A%84%E8%A7%A3%E9%87%8A\">^2</a></p>\r\n<h2 id=\"-\">注释也是必不可少的</h2>\r\n<p>Markdown将文本转换为 HTML。</p>\r\n<p>*[HTML]:   超文本标记语言</p>\r\n<h2 id=\"katex-\">KaTeX数学公式</h2>\r\n<p>您可以使用渲染LaTeX数学表达式 <a href=\"https://khan.github.io/KaTeX/\">KaTeX</a>:</p>\r\n<p>Gamma公式展示 $\\Gamma(n) = (n-1)!\\quad\\forall\r\nn\\in\\mathbb N$ 是通过欧拉积分</p>\r\n<p>$$\r\n\\Gamma(z) = \\int_0^\\infty t^{z-1}e^{-t}dt,.\r\n$$</p>\r\n<blockquote>\r\n<p>你可以找到更多关于的信息 <strong>LaTeX</strong> 数学表达式<a href=\"http://meta.math.stackexchange.com/questions/5020/mathjax-basic-tutorial-and-quick-reference\">here</a>.</p>\r\n</blockquote>\r\n<h2 id=\"-\">新的甘特图功能，丰富你的文章</h2>\r\n<pre><code class=\"language-mermaid\">gantt\r\n        dateFormat  YYYY-MM-DD\r\n        title Adding GANTT diagram functionality to mermaid\r\n        section 现有任务\r\n        已完成               :done,    des1, 2014-01-06,2014-01-08\r\n        进行中               :active,  des2, 2014-01-09, 3d\r\n        计划一               :         des3, after des2, 5d\r\n        计划二               :         des4, after des3, 5d</code></pre>\r\n<ul>\r\n<li>关于 <strong>甘特图</strong> 语法，参考 <a href=\"https://mermaidjs.github.io/\">这儿</a>,</li>\r\n</ul>\r\n<h2 id=\"uml-\">UML 图表</h2>\r\n<p>可以使用UML图表进行渲染。 <a href=\"https://mermaidjs.github.io/\">Mermaid</a>. 例如下面产生的一个序列图：:</p>\r\n<pre><code class=\"language-mermaid\">sequenceDiagram\r\n张三 -&gt;&gt; 李四: 你好！李四, 最近怎么样?\r\n李四--&gt;&gt;王五: 你最近怎么样，王五？\r\n李四--x 张三: 我很好，谢谢!\r\n李四-x 王五: 我很好，谢谢!\r\nNote right of 王五: 李四想了很长时间, 文字太长了&lt;br/&gt;不适合放在一行.\r\n\r\n李四--&gt;&gt;张三: 打量着王五...\r\n张三-&gt;&gt;王五: 很好... 王五, 你怎么样?</code></pre>\r\n<p>这将产生一个流程图。:</p>\r\n<pre><code class=\"language-mermaid\">graph LR\r\nA[长方形] -- 链接 --&gt; B((圆))\r\nA --&gt; C(圆角长方形)\r\nB --&gt; D{菱形}\r\nC --&gt; D</code></pre>\r\n<ul>\r\n<li>关于 <strong>Mermaid</strong> 语法，参考 <a href=\"https://mermaidjs.github.io/\">这儿</a>,</li>\r\n</ul>\r\n<h2 id=\"flowchart-\">FLowchart流程图</h2>\r\n<p>我们依旧会支持flowchart的流程图：</p>\r\n<pre><code class=\"language-mermaid\">flowchat\r\nst=&gt;start: 开始\r\ne=&gt;end: 结束\r\nop=&gt;operation: 我的操作\r\ncond=&gt;condition: 确认？\r\n\r\nst-&gt;op-&gt;cond\r\ncond(yes)-&gt;e\r\ncond(no)-&gt;op</code></pre>\r\n<ul>\r\n<li>关于 <strong>Flowchart流程图</strong> 语法，参考 <a href=\"http://adrai.github.io/flowchart.js/\">这儿</a>.</li>\r\n</ul>\r\n<h2 id=\"-\">导出与导入</h2>\r\n<h3 id=\"-\">导出</h3>\r\n<p>如果你想尝试使用此编辑器, 你可以在此篇文章任意编辑。当你完成了一篇文章的写作, 在上方工具栏找到 <strong>文章导出</strong> ，生成一个.md文件或者.html文件进行本地保存。</p>\r\n<h3 id=\"-\">导入</h3>\r\n<p>如果你想加载一篇你写过的.md文件或者.html文件，在上方工具栏可以选择导入功能进行对应扩展名的文件导入，\r\n继续你的创作。</p>\r\n', '2019-01-08 08:15:29', '402881e4682dc9da01682dca511c0000', '402881e667eabc210167eabc7e1d0000', 0);
COMMIT;

-- ----------------------------
-- Table structure for sys_article_classify
-- ----------------------------
DROP TABLE IF EXISTS `sys_article_classify`;
CREATE TABLE `sys_article_classify` (
  `classify_id` varchar(255) NOT NULL,
  `classify_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`classify_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_article_classify
-- ----------------------------
BEGIN;
INSERT INTO `sys_article_classify` VALUES ('402881e667eabc210167eabc7e1d0000', 'java');
INSERT INTO `sys_article_classify` VALUES ('402881e667eabc210167eabcf0a20001', 'js');
COMMIT;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES ('402881e46832f363016832f4a92c0000', '用于管理用户', '用户管理', NULL, '/admin/users');
INSERT INTO `sys_permission` VALUES ('402881e46832f363016832f5bdb70001', '管理博客的分类', '博客分类', NULL, '/admin/initArticleClassify');
COMMIT;

-- ----------------------------
-- Table structure for sys_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_role`;
CREATE TABLE `sys_permission_role` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `permission_id` varchar(255) DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKj7dnbgvr47c4nvns9yb9bdrfd` (`role_id`),
  KEY `FK1db5xvu0eeo36h9wnu71awtun` (`permission_id`),
  CONSTRAINT `FK1db5xvu0eeo36h9wnu71awtun` FOREIGN KEY (`permission_id`) REFERENCES `sys_permission` (`id`),
  CONSTRAINT `FKj7dnbgvr47c4nvns9yb9bdrfd` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission_role` VALUES ('889542c4-1418-11e9-9b13-b8e9684b426a', '402881e46832f363016832f4a92c0000', 'cc2bd8f09bb88b5dd20f9b432631b8ca');
INSERT INTO `sys_permission_role` VALUES ('b2c48b2c-1418-11e9-9b13-b8e9684b426a', '402881e46832f363016832f5bdb70001', '6195cf4156d66097d20ce375110f19c5');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(255) NOT NULL,
  `ch_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('6195cf4156d66097d20ce375110f19c5', '普通用户', 'role_user');
INSERT INTO `sys_role` VALUES ('cc2bd8f09bb88b5dd20f9b432631b8ca', '管理员', 'role_admin');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4dm5kxn3potpfgdigehw7pdyu` (`role_id`),
  CONSTRAINT `FK4dm5kxn3potpfgdigehw7pdyu` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('402881e4682dc9da01682dca511c0000', 'czzkyd777@163', '$2a$10$YDfO2wvHNNO9Lrp2k2lS9umI.oSwCd1ST2nN35nt3QhuS7/m6tE..', 'lemontree', '6195cf4156d66097d20ce375110f19c5');
INSERT INTO `sys_user` VALUES ('402881e4682dd0ea01682dd1fdbe0001', 'czzkyd777@163', '$2a$10$05/QXn.jwRanx9ho9vWf4.P7.WoTOpNietFQU7V6SinKiy4V6377e', 'admin', 'cc2bd8f09bb88b5dd20f9b432631b8ca');
COMMIT;

-- ----------------------------
-- Triggers structure for table sys_permission_role
-- ----------------------------
DROP TRIGGER IF EXISTS `tri_auto_uuid`;
delimiter ;;
CREATE TRIGGER `tri_auto_uuid` BEFORE INSERT ON `sys_permission_role` FOR EACH ROW BEGIN
if new.id = '' THEN set new.id = (select uuid());
end if;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
