package org.rubik.sandbox.guava;

import java.io.File;
import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.collect.Iterables;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;

/**
 * Files测试用例.
 */
public class FilesTest extends TestCase {

	@Test
	public void testCopy() throws IOException {
		File from = new File("/Users/xiajinxin/Documents/scala");
		File to = new File("/Users/xiajinxin/Documents/scala-copy");
		Files.copy(from, to);

		List<String> content = Files.readLines(to, Charsets.UTF_8);
		assertNotNull(content);
		to.deleteOnExit();
	}

	@Test
	public void testMove() throws IOException {
		File from = new File("/Users/xiajinxin/Documents/guava-move.md");
		Files.append("Hello World", from, Charsets.UTF_8);
		File to = new File("/Users/xiajinxin/Documents/guava-rename.md");
		Files.move(from, to);

		List<String> content = Files.readLines(to, Charsets.UTF_8);
		assertNotNull(content);
		assertEquals("Hello World", Iterables.get(content, 0));
		to.deleteOnExit();
	}

	@Test
	public void testHash() throws IOException {
		File file = new File("/Users/xiajinxin/Documents/scala");
		HashCode hashCode = Files.hash(file, Hashing.md5());

		assertNotNull(hashCode);
		assertEquals("602b7127a7dded7c40cc15c1b72f2468", hashCode.toString());
	}

	@Test
	public void testDeleteOnExit() throws IOException {
		File file = new File("/Users/xiajinxin/Documents/Kevin.md");

		Files.touch(file);
		Files.append("Kevin is a elegant man", file, Charsets.UTF_8);

		List<String> content = Files.readLines(file, Charsets.UTF_8);
		assertNotNull(content);
		assertEquals("Kevin is a elegant man", Iterables.get(content, 0));
		file.deleteOnExit();
	}

	@Test
	public void testWriteAndAppend() throws IOException {
		File file = new File("/Users/xiajinxin/Documents/write.md");
		Files.append("write file", file, Charsets.UTF_8);

		List<String> before = Files.readLines(file, Charsets.UTF_8);
		assertNotNull(before);
		assertEquals("write file", Iterables.get(before, 0));

		Files.write("overwrite file", file, Charsets.UTF_8);
		List<String> after = Files.readLines(file, Charsets.UTF_8);
		assertNotNull(after);
		assertEquals("overwrite file", Iterables.get(after, 0));

		file.deleteOnExit();
	}

	@Test
	public void testByteSource() throws IOException {
		File file = new File("/Users/xiajinxin/Documents/byteSource.md");
		Files.append("write file", file, Charsets.UTF_8);

		ByteSource byteSource = Files.asByteSource(file);
		byte[] readBytes = byteSource.read();
		assertEquals(readBytes.length, Files.toByteArray(file).length);

		file.deleteOnExit();
	}
	
	@Test
	public void testByteSink() throws IOException {
		File source = new File("/Users/xiajinxin/Documents/architect_201408_final.pdf");
		File target = new File("/Users/xiajinxin/Documents/aaa.pdf");
		Files.touch(target);

		ByteSink byteSink = Files.asByteSink(target, FileWriteMode.APPEND);
		byteSink.write(Files.toByteArray(source));

		List<String> content = Files.readLines(target, Charsets.UTF_8);
		assertNotNull(content);
	}
}
