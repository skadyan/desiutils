package desi.tools.ide;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;
import java.util.Properties;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class EclipseCatalogSchemaGenerator {

	private String searchDirectory;

	public EclipseCatalogSchemaGenerator() {
	}

	public static void main(String[] args) throws IOException {
		String mvnLocalRepository = "D:\\data\\sharedlibs\\m2\\repository\\org\\springframework";
		String destination = "target/entieris.txt";

		EclipseCatalogSchemaGenerator generator = new EclipseCatalogSchemaGenerator();

		generator.setJarDirectory(mvnLocalRepository);
		generator.setOutputFile(destination);
		generator.generate();

	}

	private void setOutputFile(String destination) throws IOException {
		PrintStream out = new PrintStream(destination);
		System.setOut(out);
		System.setErr(out);
	}

	public void generate() throws IOException {
		System.out.println("* * * * * STARTED * * * * ");
		Path dir = Paths.get(searchDirectory);

		FileVisitor<? super Path> visitor = new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file,
					BasicFileAttributes attrs) throws IOException {
				if (attrs.isRegularFile()) {
					String fileName = file.getFileName().toString();
					if (fileName.endsWith(".jar")
							&& !fileName.endsWith("-sources.jar")) {
						processJar(file);
					}
				}

				return super.visitFile(file, attrs);
			}
		};
		System.out.println("* * * * * FINISHED * * * * ");

		Files.walkFileTree(dir, visitor);
	}

	private void processJar(Path file) throws IOException {
		try (JarFile jarFile = new JarFile(file.toFile())) {
			String jarFilePath = file.toUri().toString();
			ZipEntry entry = jarFile.getEntry("META-INF/spring.schemas");
			if (entry != null) {
				Properties properties = loadSteamAsProperties(jarFile
						.getInputStream(entry));
				writeToCatalogSchema(properties, "jar:" + jarFilePath + "!");
			}
		}
	}

	private void writeToCatalogSchema(Map<Object, Object> properties,
			String jarFilePath) throws IOException {
		for (Map.Entry<Object, Object> e : properties.entrySet()) {
			String key = e.getKey().toString();
			String location = fullPath(jarFilePath, e.getValue().toString());

			writeEntry(key, location);
		}
	}

	private void writeEntry(String key, String location) throws IOException {
		validateLocation(location);
		String entry = "<system systemId=\"" + key + "\" uri=\"" + location
				+ "\" />";

		System.out.println(entry);
	}

	private void validateLocation(String location) throws IOException {
		try {
			URL url = new URL(location);
			try (InputStream openStream = url.openStream()) {
				int read = openStream.read();
				if (read == -1) {
					throw new IOException("not data found");
				}
			}
		} catch (IOException e) {
			System.out.println(">>>>>>>>>>>>>invalid location :" + location);
		}
	}

	private String fullPath(String jarFilePath, String uri) {
		StringBuilder location = new StringBuilder(jarFilePath);
		if (uri.charAt(0) != '/') {
			location.append('/');
		}
		location.append(uri);
		return location.toString();
	}

	private Properties loadSteamAsProperties(InputStream stream)
			throws IOException {
		Properties properties = new Properties();
		properties.load(stream);
		return properties;
	}

	private void setJarDirectory(String directory) {
		this.searchDirectory = directory;
	}

}
