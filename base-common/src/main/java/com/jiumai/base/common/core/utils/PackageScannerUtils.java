package com.jiumai.base.common.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import com.jiumai.base.common.core.SysLog;
import com.jiumai.base.common.core.SysLogFactory;

public class PackageScannerUtils {

	private static SysLog logger = SysLogFactory.getLogger(PackageScannerUtils.class);
	private String basePackage ;
	private ClassLoader cl;

	public PackageScannerUtils() {
	}

	/**
	 * 初始化
	 * 
	 * @param basePackage
	 */
	public PackageScannerUtils(String basePackage) {
		this.basePackage = basePackage;
		this.cl = getClass().getClassLoader();
	}

	public PackageScannerUtils(String basePackage, ClassLoader cl) {
		this.basePackage = basePackage;
		this.cl = cl;
	}

	/**
	 * 获取所有该包下的所有类的全限定名
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<String> getFullyQualifiedClassNameList() throws IOException {

		logger.info("开始扫描包{}下的所有类", basePackage);
		return doScan(basePackage, new ArrayList<String>());
	}

	/**
	 * doScan函数
	 * 
	 * @param basePackage
	 * @param nameList
	 * @return
	 * @throws IOException
	 */
	private List<String> doScan(String basePackage, List<String> nameList) throws IOException {
		String splashPath = StringUtils.dotToSplash(basePackage);
		Enumeration<URL> urls = cl.getResources(splashPath);
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			// System.out.println(url.toString());

			// URL url = cl.getResource(splashPath); //
			// file:/D:/WorkSpace/java/ScanTest/target/classes/com/scan
			String filePath = StringUtils.getRootPath(url);
			List<String> names = null; // contains the name of the class file. e.g., Apple.class will be stored as
										// "Apple"
			if (isJarFile(filePath)) {// 先判断是否是jar包，如果是jar包，通过JarInputStream产生的JarEntity去递归查询所有类
				if (logger.isDebugEnabled()) {
					logger.debug("{} 是一个JAR包", filePath);
				}
				names = readFromJarFile(filePath, splashPath);
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("{} 是一个目录", filePath);
				}
				names = readFromDirectory(filePath);
			}
			for (String name : names) {
				if (isClassFile(name)) {
					nameList.add(toFullyQualifiedName(name, basePackage));
				} else {
					File file = new File(basePackage + "." + name);
					if (file.isFile()) {
						continue;
					}
					doScan(basePackage + "." + name, nameList);
				}
			}
			if (logger.isDebugEnabled()) {
				for (String n : nameList) {
					logger.debug("找到{}", n);
				}
			}
		}
		return nameList;
	}

	private String toFullyQualifiedName(String shortName, String basePackage) {
		StringBuilder sb = new StringBuilder(basePackage);
		sb.append('.');
		sb.append(StringUtils.trimExtension(shortName));
		return sb.toString();
	}

	private List<String> readFromJarFile(String jarPath, String splashedPackageName) throws IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("从JAR包中读取类: {}", jarPath);
		}
		JarInputStream jarIn = null;
		List<String> nameList = new ArrayList<String>();
		try {
			jarIn = new JarInputStream(new FileInputStream(jarPath));
			JarEntry entry = jarIn.getNextJarEntry();
			while (null != entry) {
				String name = entry.getName();
				if (name.startsWith(splashedPackageName) && isClassFile(name)) {
					nameList.add(name);
				}

				entry = jarIn.getNextJarEntry();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			jarIn.close();
		}
		return nameList;
	}

	private List<String> readFromDirectory(String path) {
		File file = new File(path);
		String[] names = file.list();
		List<String> list = new ArrayList<>();
		for (int i = 0; i < names.length; i++) {
			if (names[i].toLowerCase().endsWith(".ftl") || names[i].endsWith(".btl") || names[i].endsWith(".txt")) {
				continue;
			} else {
				list.add(names[i]);
			}

		}

		return list;
	}

	private boolean isClassFile(String name) {
		return name.endsWith(".class");
	}

	private boolean isJarFile(String name) {
		return name.endsWith(".jar");
	}

}
