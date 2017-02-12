package com.hooverz.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;


/**
 * 创建验证码类
 * 
 * @author love5
 * 
 */
public class VerifiCodeUtils {

	// 验证码中显示的值
	private StringBuilder codeValue = null;

	// 预选词的字典
	private String dictEn = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
	// 预选词中文版
	private String dictZh = "\u7684\u4e00\u4e86\u662f\u6211\u4e0d\u5728\u4eba\u4eec\u6709\u6765\u4ed6\u8fd9\u4e0a\u7740\u4e2a\u5730\u5230\u5927\u91cc\u8bf4\u5c31\u53bb\u5b50\u5f97\u4e5f\u548c\u90a3\u8981\u4e0b\u770b\u5929\u65f6\u8fc7\u51fa\u5c0f\u4e48\u8d77\u4f60\u90fd\u628a\u597d\u8fd8\u591a\u6ca1\u4e3a\u53c8\u53ef\u5bb6\u5b66\u53ea\u4ee5\u4e3b\u4f1a\u6837\u5e74\u60f3\u751f\u540c\u8001\u4e2d\u5341\u4ece\u81ea\u9762\u524d\u5934\u9053\u5b83\u540e\u7136\u8d70\u5f88\u50cf\u89c1\u4e24\u7528\u5979\u56fd\u52a8\u8fdb\u6210\u56de\u4ec0\u8fb9\u4f5c\u5bf9\u5f00\u800c\u5df1\u4e9b\u73b0\u5c71\u6c11\u5019\u7ecf\u53d1\u5de5\u5411\u4e8b\u547d\u7ed9\u957f\u6c34\u51e0\u4e49\u4e09\u58f0\u4e8e\u9ad8\u624b\u77e5\u7406\u773c\u5fd7\u70b9\u5fc3\u6218\u4e8c\u95ee\u4f46\u8eab\u65b9\u5b9e\u5403\u505a\u53eb\u5f53\u4f4f\u542c\u9769\u6253\u5462\u771f\u5168\u624d\u56db\u5df2\u6240\u654c\u4e4b\u6700\u5149\u4ea7\u60c5\u8def\u5206\u603b\u6761\u767d\u8bdd\u4e1c\u5e2d\u6b21\u4eb2\u5982\u88ab\u82b1\u53e3\u653e\u513f\u5e38\u6c14\u4e94\u7b2c\u4f7f\u5199\u519b\u5427\u6587\u8fd0\u518d\u679c\u600e\u5b9a\u8bb8\u5feb\u660e\u884c\u56e0\u522b\u98de\u5916\u6811\u7269\u6d3b\u90e8\u95e8\u65e0\u5f80\u8239\u671b\u65b0\u5e26\u961f\u5148\u529b\u5b8c\u5374\u7ad9\u4ee3\u5458\u673a\u66f4\u4e5d\u60a8\u6bcf\u98ce\u7ea7\u8ddf\u7b11\u554a\u5b69\u4e07\u5c11\u76f4\u610f\u591c\u6bd4\u9636\u8fde\u8f66\u91cd\u4fbf\u6597\u9a6c\u54ea\u5316\u592a\u6307\u53d8\u793e\u4f3c\u58eb\u8005\u5e72\u77f3\u6ee1\u65e5\u51b3\u767e\u539f\u62ff\u7fa4\u7a76\u5404\u516d\u672c\u601d\u89e3\u7acb\u6cb3\u6751\u516b\u96be\u65e9\u8bba\u5417\u6839\u5171\u8ba9\u76f8\u7814\u4eca\u5176\u4e66\u5750\u63a5\u5e94\u5173\u4fe1\u89c9\u6b65\u53cd\u5904\u8bb0\u5c06\u5343\u627e\u4e89\u9886\u6216\u5e08\u7ed3\u5757\u8dd1\u8c01\u8349\u8d8a\u5b57\u52a0\u811a\u7d27\u7231\u7b49\u4e60\u9635\u6015\u6708\u9752\u534a\u706b\u6cd5\u9898\u5efa\u8d76\u4f4d\u5531\u6d77\u4e03\u5973\u4efb\u4ef6\u611f\u51c6\u5f20\u56e2\u5c4b\u79bb\u8272\u8138\u7247\u79d1\u5012\u775b\u5229\u4e16\u521a\u4e14\u7531\u9001\u5207\u661f\u5bfc\u665a\u8868\u591f\u6574\u8ba4\u54cd\u96ea\u6d41\u672a\u573a\u8be5\u5e76\u5e95\u6df1\u523b\u5e73\u4f1f\u5fd9\u63d0\u786e\u8fd1\u4eae\u8f7b\u8bb2\u519c\u53e4\u9ed1\u544a\u754c\u62c9\u540d\u5440\u571f\u6e05\u9633\u7167\u529e\u53f2\u6539\u5386\u8f6c\u753b\u9020\u5634\u6b64\u6cbb\u5317\u5fc5\u670d\u96e8\u7a7f\u5185\u8bc6\u9a8c\u4f20\u4e1a\u83dc\u722c\u7761\u5174\u5f62\u91cf\u54b1\u89c2\u82e6\u4f53\u4f17\u901a\u51b2\u5408\u7834\u53cb\u5ea6\u672f\u996d\u516c\u65c1\u623f\u6781\u5357\u67aa\u8bfb\u6c99\u5c81\u7ebf\u91ce\u575a\u7a7a\u6536\u7b97\u81f3\u653f\u57ce\u52b3\u843d\u94b1\u7279\u56f4\u5f1f\u80dc\u6559\u70ed\u5c55\u5305\u6b4c\u7c7b\u6e10\u5f3a\u6570\u4e61\u547c\u6027\u97f3\u7b54\u54e5\u9645\u65e7\u795e\u5ea7\u7ae0\u5e2e\u5566\u53d7\u7cfb\u4ee4\u8df3\u975e\u4f55\u725b\u53d6\u5165\u5cb8\u6562\u6389\u5ffd\u79cd\u88c5\u9876\u6025\u6797\u505c\u606f\u53e5\u533a\u8863\u822c\u62a5\u53f6\u538b\u6162\u53d4\u80cc\u7ec6";
	// 预选词混合版
	private String dictMIXD = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890\u7684\u4e00\u4e86\u662f\u6211\u4e0d\u5728\u4eba\u4eec\u6709\u6765\u4ed6\u8fd9\u4e0a\u7740\u4e2a\u5730\u5230\u5927\u91cc\u8bf4\u5c31\u53bb\u5b50\u5f97\u4e5f\u548c\u90a3\u8981\u4e0b\u770b\u5929\u65f6\u8fc7\u51fa\u5c0f\u4e48\u8d77\u4f60\u90fd\u628a\u597d\u8fd8\u591a\u6ca1\u4e3a\u53c8\u53ef\u5bb6\u5b66\u53ea\u4ee5\u4e3b\u4f1a\u6837\u5e74\u60f3\u751f\u540c\u8001\u4e2d\u5341\u4ece\u81ea\u9762\u524d\u5934\u9053\u5b83\u540e\u7136\u8d70\u5f88\u50cf\u89c1\u4e24\u7528\u5979\u56fd\u52a8\u8fdb\u6210\u56de\u4ec0\u8fb9\u4f5c\u5bf9\u5f00\u800c\u5df1\u4e9b\u73b0\u5c71\u6c11\u5019\u7ecf\u53d1\u5de5\u5411\u4e8b\u547d\u7ed9\u957f\u6c34\u51e0\u4e49\u4e09\u58f0\u4e8e\u9ad8\u624b\u77e5\u7406\u773c\u5fd7\u70b9\u5fc3\u6218\u4e8c\u95ee\u4f46\u8eab\u65b9\u5b9e\u5403\u505a\u53eb\u5f53\u4f4f\u542c\u9769\u6253\u5462\u771f\u5168\u624d\u56db\u5df2\u6240\u654c\u4e4b\u6700\u5149\u4ea7\u60c5\u8def\u5206\u603b\u6761\u767d\u8bdd\u4e1c\u5e2d\u6b21\u4eb2\u5982\u88ab\u82b1\u53e3\u653e\u513f\u5e38\u6c14\u4e94\u7b2c\u4f7f\u5199\u519b\u5427\u6587\u8fd0\u518d\u679c\u600e\u5b9a\u8bb8\u5feb\u660e\u884c\u56e0\u522b\u98de\u5916\u6811\u7269\u6d3b\u90e8\u95e8\u65e0\u5f80\u8239\u671b\u65b0\u5e26\u961f\u5148\u529b\u5b8c\u5374\u7ad9\u4ee3\u5458\u673a\u66f4\u4e5d\u60a8\u6bcf\u98ce\u7ea7\u8ddf\u7b11\u554a\u5b69\u4e07\u5c11\u76f4\u610f\u591c\u6bd4\u9636\u8fde\u8f66\u91cd\u4fbf\u6597\u9a6c\u54ea\u5316\u592a\u6307\u53d8\u793e\u4f3c\u58eb\u8005\u5e72\u77f3\u6ee1\u65e5\u51b3\u767e\u539f\u62ff\u7fa4\u7a76\u5404\u516d\u672c\u601d\u89e3\u7acb\u6cb3\u6751\u516b\u96be\u65e9\u8bba\u5417\u6839\u5171\u8ba9\u76f8\u7814\u4eca\u5176\u4e66\u5750\u63a5\u5e94\u5173\u4fe1\u89c9\u6b65\u53cd\u5904\u8bb0\u5c06\u5343\u627e\u4e89\u9886\u6216\u5e08\u7ed3\u5757\u8dd1\u8c01\u8349\u8d8a\u5b57\u52a0\u811a\u7d27\u7231\u7b49\u4e60\u9635\u6015\u6708\u9752\u534a\u706b\u6cd5\u9898\u5efa\u8d76\u4f4d\u5531\u6d77\u4e03\u5973\u4efb\u4ef6\u611f\u51c6\u5f20\u56e2\u5c4b\u79bb\u8272\u8138\u7247\u79d1\u5012\u775b\u5229\u4e16\u521a\u4e14\u7531\u9001\u5207\u661f\u5bfc\u665a\u8868\u591f\u6574\u8ba4\u54cd\u96ea\u6d41\u672a\u573a\u8be5\u5e76\u5e95\u6df1\u523b\u5e73\u4f1f\u5fd9\u63d0\u786e\u8fd1\u4eae\u8f7b\u8bb2\u519c\u53e4\u9ed1\u544a\u754c\u62c9\u540d\u5440\u571f\u6e05\u9633\u7167\u529e\u53f2\u6539\u5386\u8f6c\u753b\u9020\u5634\u6b64\u6cbb\u5317\u5fc5\u670d\u96e8\u7a7f\u5185\u8bc6\u9a8c\u4f20\u4e1a\u83dc\u722c\u7761\u5174\u5f62\u91cf\u54b1\u89c2\u82e6\u4f53\u4f17\u901a\u51b2\u5408\u7834\u53cb\u5ea6\u672f\u996d\u516c\u65c1\u623f\u6781\u5357\u67aa\u8bfb\u6c99\u5c81\u7ebf\u91ce\u575a\u7a7a\u6536\u7b97\u81f3\u653f\u57ce\u52b3\u843d\u94b1\u7279\u56f4\u5f1f\u80dc\u6559\u70ed\u5c55\u5305\u6b4c\u7c7b\u6e10\u5f3a\u6570\u4e61\u547c\u6027\u97f3\u7b54\u54e5\u9645\u65e7\u795e\u5ea7\u7ae0\u5e2e\u5566\u53d7\u7cfb\u4ee4\u8df3\u975e\u4f55\u725b\u53d6\u5165\u5cb8\u6562\u6389\u5ffd\u79cd\u88c5\u9876\u6025\u6797\u505c\u606f\u53e5\u533a\u8863\u822c\u62a5\u53f6\u538b\u6162\u53d4\u80cc\u7ec6";

	// 颜色
	private int[] color = { 15070207, 16706672, 5863595, 14172751, 16515866,
			144998, 1249550, 5016565, 6184883 };

	// 验证码样式
	private VerifiCodeStyle style = null;

	/**
	 * 带参构造
	 * 
	 * @param style
	 *            验证码样式
	 */
	public VerifiCodeUtils(VerifiCodeStyle style) {
		this.style = style;
		this.codeValue = new StringBuilder();
	}

	/**
	 * 获取验证码
	 * 
	 * @return 返回验证码图片
	 */
	public BufferedImage getVCImg() {

		BufferedImage img = new BufferedImage(style.getWidth(),
				style.getHeight(), BufferedImage.TYPE_INT_RGB); // 创建一个画布
		Graphics2D g = (Graphics2D) img.getGraphics(); // 拿到画笔对象,需要强转
		Random random = new Random();

		// 设置画笔颜色，填充图片的背景色
		g.setColor(style.getBgColor());
		g.fillRect(0, 0, style.getWidth(), style.getHeight());

		// 画干扰线
		// 设置颜色
		g.setColor(new Color(this.color[random.nextInt(this.color.length)]));
		g.setStroke(new BasicStroke(style.getLineStroke()));
		int x1, x2, y1, y2;
		// 画干扰线
		for (int i = 0; i < style.getLineNum(); i++) {
			x1 = random.nextInt(style.getWidth());
			y1 = random.nextInt(style.getHeight());
			x2 = random.nextInt(style.getWidth());
			y2 = random.nextInt(style.getHeight());
			g.drawLine(x1, y1, x2, y2);
		}

		// 设置画笔颜色和字体，在画布上写文字
		g.setColor(style.getWordColor());
		g.setFont(style.getFontStyle());

		// 验证码类型
		String words = null;
		switch (style.getCodeType()) {
		case VerifiCodeStyle.CH_CODE:
			words = this.dictZh;
			break;
		case VerifiCodeStyle.EN_CODE:
			words = this.dictEn;
			break;
		case VerifiCodeStyle.MIXED_CODE:
			words = this.dictMIXD;
			break;
		}

		// 计算x,y
		FontMetrics fm = g.getFontMetrics(style.getFontStyle());
		int ascent = fm.getAscent();
		int descent = fm.getDescent();

		// 文本的宽度
		int textWidth = fm.stringWidth(words.charAt(0) + "");
		int x = (style.getWidth() - textWidth * style.getWordLen()) / 2;
		// int x = (clip.width - textWidth) / 2;//单个文字完全居中
		int y = (style.getHeight() - (ascent + descent)) / 2 + ascent;

		for (int i = 0; i < style.getWordLen(); i++) {
			String str = words.charAt(random.nextInt(words.length())) + "";

			this.codeValue.append(str);//保存到codeValue中
			
			// 设置字体颜色
			if (style.isColorful()) {
				g.setColor(new Color(this.color[random
						.nextInt(this.color.length)]));
			}

			// 设置旋转角度
			int angle = random.nextInt(60) - 30; // 角度
			double theta = angle * Math.PI / 180; // 弧度
			g.rotate(theta, x, y);
			g.drawString(str, x, y);
			g.rotate(-theta, x, y);

			x += textWidth;
		}

		return img;
	}

	/**
	 * 获取到验证码的值
	 * 
	 * @return
	 */
	public StringBuilder getCodeValue() {

		return this.codeValue;
	}

	/**
	 * 向客户端输出验证码图片
	 * 
	 * @param response
	 * @param vcImg
	 */
	public void outPutVCImg(HttpServletResponse response, BufferedImage vcImg) {
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			response.setContentType("image/jpeg");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			ImageIO.write((RenderedImage) vcImg, "jpg", out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 验证码样式类,内部类 
	 * 
	 * @author love5
	 * 
	 */
	public static class VerifiCodeStyle {

		public static final int EN_CODE = 0; // 英文验证码
		public static final int CH_CODE = 1; // 中文验证码
		public static final int MIXED_CODE = 2; // 混合验证码

		// 验证码宽
		private int width = 100;
		// 验证码高
		private int height = 40;
		// 验证的类型(中文验证码，英文验证码， 混合验证码)
		private int codeType = VerifiCodeStyle.EN_CODE;
		// 验证码的文字颜色
		private Color wordColor = Color.BLUE;
		// 设置验证码释放是彩色
		private boolean isColorful = true;
		// 验证码的背景颜色
		private Color bgColor = Color.WHITE;
		// 验证码的干扰线颜色
		private Color lineColor = wordColor;
		// 验证码的干扰线条数
		private int lineNum = 4;
		// 设置干扰线的粗细
		private float lineStroke = 1.0F;
		// 是否设置验证码文字的旋转
		private boolean isRotate = true;
		// 验证码文字个数
		private int wordLen = 4;
		// 验证码文字样式
		private Font fontStyle = new Font("MS Song", Font.BOLD, 25);

		/**
		 * 使用默认值的构造
		 */
		public VerifiCodeStyle() {
		}

		/**
		 * 带参的构造
		 * 
		 * @param width
		 *            验证码宽
		 * @param height
		 *            验证码高
		 * @param codeType
		 *            验证的类型(中文验证码，英文验证码， 混合验证码)
		 * @param wordColor
		 *            验证码的文字颜色
		 * @param bgColor
		 *            验证码的背景颜色
		 * @param lineColor
		 *            验证码的干扰线颜色
		 * @param isRotate
		 *            是否设置验证码文字的旋转
		 * @param wordLen
		 *            验证码的文字个数
		 * @param fontStyle
		 *            文字字体样式
		 * @param lineNum
		 *            干扰线的个数
		 * @param isColorful
		 *            是否彩色文字
		 * @param lineStroke
		 *            干扰线的粗细
		 */
		public VerifiCodeStyle(int width, int height, int codeType,
				Color wordColor, Color bgColor, Color lineColor, boolean isRotate,
				int wordLen, Font fontStyle, int lineNum, boolean isColorful,
				float lineStroke) {
			super();
			this.width = width;
			this.height = height;
			this.codeType = codeType;
			this.wordColor = wordColor;
			this.bgColor = bgColor;
			this.lineColor = lineColor;
			this.isRotate = isRotate;
			this.wordLen = wordLen;
			this.fontStyle = fontStyle;
			this.lineNum = lineNum;
			this.isColorful = isColorful;
			this.lineStroke = lineStroke;
		}

		/**
		 * get set
		 * 
		 * @return
		 */
		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public int getCodeType() {
			return codeType;
		}

		public void setCodeType(int codeType) {
			this.codeType = codeType;
		}

		public Color getWordColor() {
			return wordColor;
		}

		public void setWordColor(Color wordColor) {
			this.wordColor = wordColor;
		}

		public Color getBgColor() {
			return bgColor;
		}

		public void setBgColor(Color bgColor) {
			this.bgColor = bgColor;
		}

		public Color getLineColor() {
			return lineColor;
		}

		public void setLineColor(Color lineColor) {
			this.lineColor = lineColor;
		}

		public boolean isRotate() {
			return isRotate;
		}

		public void setRotate(boolean isRotate) {
			this.isRotate = isRotate;
		}

		public int getWordLen() {
			return wordLen;
		}

		public void setWordLen(int wordLen) {
			this.wordLen = wordLen;
		}

		public boolean isColorful() {
			return isColorful;
		}

		public void setColorful(boolean isColorful) {
			this.isColorful = isColorful;
		}

		public int getLineNum() {
			return lineNum;
		}

		public void setLineNum(int lineNum) {
			this.lineNum = lineNum;
		}

		public float getLineStroke() {
			return lineStroke;
		}

		public void setLineStroke(float lineStroke) {
			this.lineStroke = lineStroke;
		}

		public Font getFontStyle() {
			return fontStyle;
		}

		public void setFontStyle(Font fontStyle) {
			this.fontStyle = fontStyle;
		}

	}

}