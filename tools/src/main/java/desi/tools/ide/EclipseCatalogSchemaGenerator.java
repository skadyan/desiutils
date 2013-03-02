package desi.tools.ide;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class EclipseCatalogSchemaGenerator {
	final static String springVersionExtn = "3.1.3.RELEASE.jar";
	private String searchDirectory;
	private FileWriter out;
	private Map<String, String> systemEntries;

	public EclipseCatalogSchemaGenerator() {
	}

	public static void main(String[] args) throws IOException {
		String mvnLocalRepository = "D:\\data\\sharedlibs\\m2\\repository\\org\\springframework";
		String destination = "target/user_catalog.xml";

		EclipseCatalogSchemaGenerator generator = new EclipseCatalogSchemaGenerator();

		generator.setJarDirectory(mvnLocalRepository);
		generator.setOutputFile(destination);
		generator.generate();

	}

	private void setOutputFile(String destination) throws IOException {
		out = new FileWriter(destination);
	}

	public void generate() throws IOException {
		System.out.println("* * * * * STARTED * * * * ");
		Path dir = Paths.get(searchDirectory);
		systemEntries = new HashMap<>();

		FileVisitor<? super Path> visitor = new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				if (attrs.isRegularFile()) {
					String fileName = file.getFileName().toString();
					if (fileName.endsWith(".jar") && !fileName.endsWith("-sources.jar")) {
						if (fileName.endsWith(springVersionExtn))
							processJar(file);
					}
				}

				return super.visitFile(file, attrs);
			}
		};
		Files.walkFileTree(dir, visitor);
		generateUserCatalogFile();

		System.out.println("* * * * * FINISHED * * * * ");
	}

	private void generateUserCatalogFile() throws IOException {
		Map<String, Object> hash = new HashMap<>();
		hash.put("entries", systemEntries.entrySet());

		Configuration conf = new Configuration();
		conf.setTemplateLoader(new ClassTemplateLoader(getClass(), ""));
		BufferedWriter buff = new BufferedWriter(out);
		try {
			Template template = conf.getTemplate("user-catalog.xml.template");
			template.process(hash, buff);
		} catch (TemplateException e) {
			throw new IOException(e);
		} finally {
			buff.close();
		}
	}

	private void processJar(Path file) throws IOException {
		try (JarFile jarFile = new JarFile(file.toFile())) {
			String jarFilePath = file.toUri().toString();
			ZipEntry entry = jarFile.getEntry("META-INF/spring.schemas");
			if (entry != null) {
				Properties properties = loadSteamAsProperties(jarFile.getInputStream(entry));
				findSystemEntries(properties, "jar:" + jarFilePath + "!");
			}
		}
	}

	private void findSystemEntries(Map<Object, Object> properties, String jarFilePath) throws IOException {
		for (Map.Entry<Object, Object> e : properties.entrySet()) {
			String key = e.getKey().toString();
			String location = toFullPath(jarFilePath, e.getValue().toString());

			validateLocationAndAddEntry(key, location);
		}
	}

	private void validateLocationAndAddEntry(String key, String location) throws IOException {
		if (validateLocation(location)) {
			addSystemEntry(key, location);

		}
	}

	private void addSystemEntry(String key, String location) {
		systemEntries.put(key, location);
	}

	private boolean validateLocation(String location) throws IOException {
		try {
			URL url = new URL(location);
			try (InputStream openStream = url.openStream()) {
				int read = openStream.read();
				if (read == -1) {
					throw new IOException("not data found");
				}
			}
			return true;
		} catch (IOException e) {
			System.out.println(">>>>>>>>>>>>>invalid location :" + location);

		}
		return false;
	}

	private String toFullPath(String jarFilePath, String uri) {
		StringBuilder location = new StringBuilder(jarFilePath);
		if (uri.charAt(0) != '/') {
			location.append('/');
		}
		location.append(uri);
		return location.toString();
	}

	private Properties loadSteamAsProperties(InputStream stream) throws IOException {
		Properties properties = new Properties();
		properties.load(stream);
		return properties;
	}

	private void setJarDirectory(String directory) {
		this.searchDirectory = directory;
	}

}
