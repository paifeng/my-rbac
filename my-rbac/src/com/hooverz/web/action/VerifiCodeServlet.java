package com.hooverz.web.action;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hooverz.utils.VerifiCodeUtils;
import com.hooverz.utils.VerifiCodeUtils.VerifiCodeStyle;
public class VerifiCodeServlet extends HttpServlet {

	private static final long serialVersionUID = -6185605501661931316L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 设置样式
		VerifiCodeStyle vcStyle = new VerifiCodeStyle();
		// vcStyle.setBgColor(Color.gray);
		// vcStyle.setLineNum(12);
		// vcStyle.setCodeType(VerifiCodeStyle.EN_CODE);
		// vcStyle.setFontStyle(new Font("MS Song", Font.BOLD, 25));
		//vcStyle.setColorful(false);
		//vcStyle.setLineStroke(2.0F);
		vcStyle.setWidth(125);
		vcStyle.setHeight(80);

		// /创建验证码,输出验证码
		VerifiCodeUtils vcImgObj = new VerifiCodeUtils(vcStyle);
		BufferedImage vcImg = vcImgObj.getVCImg();
		//把验证码的值，保存到session中
		String regVerfiC = vcImgObj.getCodeValue().toString();
		request.getSession().setAttribute("regVerfiC", regVerfiC);

		//输出验证码图片
		vcImgObj.outPutVCImg(response, vcImg);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
