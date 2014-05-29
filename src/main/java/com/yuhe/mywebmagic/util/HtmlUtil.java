package com.yuhe.mywebmagic.util;

import gui.ava.html.image.generator.HtmlImageGenerator;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.lobobrowser.html.gui.HtmlPanel;
import org.lobobrowser.html.test.SimpleHtmlRendererContext;
import org.lobobrowser.html.test.SimpleUserAgentContext;

public class HtmlUtil {
	public static void generateOutput() {
		try {
			// load the webpage into the editor
			// JEditorPane ed = new JEditorPane(new URL("http://www.google.com"));
			JEditorPane ed = new JEditorPane(new URL("http://www.baidu.com"));
			ed.setSize(200, 200);

			// create a new image
			BufferedImage image = new BufferedImage(ed.getWidth(), ed.getHeight(), BufferedImage.TYPE_INT_ARGB);

			// paint the editor onto the image
			SwingUtilities.paintComponent(image.createGraphics(), ed, new JPanel(), 0, 0, image.getWidth(), image.getHeight());
			// save the image to file
			ImageIO.write((RenderedImage) image, "png", new File("html.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void html2imageByCobra(String html) {
		try {
			JFrame window = new JFrame();
			HtmlPanel panel = new HtmlPanel();
			window.getContentPane().add(panel);
			window.setSize(600, 400);
			window.setVisible(true);
			new SimpleHtmlRendererContext(panel, new SimpleUserAgentContext()).navigate("http://www.baidu.com");
			BufferedImage image = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);

			// paint the editor onto the image
			SwingUtilities.paintComponent(image.createGraphics(), panel, new JPanel(), 0, 0, image.getWidth(), image.getHeight());
			// save the image to file
			ImageIO.write((RenderedImage) image, "png", new File("html.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void html2image(String html) {
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		imageGenerator.loadHtml(html);
		// imageGenerator.loadUrl("http://www.baidu.com");
		imageGenerator.saveAsImage("hello-world.png");
	}

	public static void convertToStaticHtml() {

	}

	public static void makeHtml() {
		try {
			URL url = new URL("http://www.baidu.com/");

			// 本实事例通过读取百度页面来生成静态页面，你要是想把自己程序里面要生成的jsp页面转化成静态页面，则可以在url构造器的参数中的参数写上：如http://localhost:8081/NEW/new.do?cmd=list&sortId=2
			URLConnection conn = url.openConnection();// 获得连接
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			BufferedWriter bw = new BufferedWriter(new FileWriter("D:/baidu.html"));
			String str = null;
			while ((str = br.readLine()) != null) {
				bw.write(str);
			}
			bw.close();
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		 html2image(FileUtil.loadFile("D:/Eclipse4_clear/workspace/mywebmagic/src/main/java/com/yuhe/mywebmagic/zhenai/zhenai_email_view.html"));
//		generateOutput();
		html2imageByCobra("");
	}
}