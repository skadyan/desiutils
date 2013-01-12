package desi.mango.utils;

import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class SecureUtils {
	public static class EncrypterUtil {
		public static final EncrypterUtil INSTANCE = new EncrypterUtil();
		private final byte[] encryptionSalt = { (byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c, (byte) 0x7e,
				(byte) 0xc8, (byte) 0xee, (byte) 0x99 };
		private final int iterationCount = 10;
		private final String algorithName = "PBE";
		private PBEParameterSpec pbeParamSpec;
		private SecretKeyFactory secretKeyFactory;

		public EncrypterUtil() {
			init();
		}

		private void init() {
			try {
				pbeParamSpec = new PBEParameterSpec(encryptionSalt, iterationCount);
				secretKeyFactory = SecretKeyFactory.getInstance(algorithName);
			} catch (NoSuchAlgorithmException e) {
				throw new Error("Secure Algorith is not supported on your platofrm: " + algorithName);
			}
		}

		public byte[] encrypt(byte[] planData, char[] password) {
			return doCipher(Cipher.ENCRYPT_MODE, planData, password);
		}

		private byte[] doCipher(int encryptMode, byte[] planData, char[] password) {
			try {
				PBEKeySpec pbeKeySpec = new PBEKeySpec(password);
				SecretKey pbeKey = secretKeyFactory.generateSecret(pbeKeySpec);
				Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
				pbeCipher.init(encryptMode, pbeKey, pbeParamSpec);
				return pbeCipher.doFinal(planData);
			} catch (InvalidKeySpecException | InvalidAlgorithmParameterException | NoSuchAlgorithmException
					| NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
				throw new RuntimeException("encryption failed. Your data or Password may be wrong", e);
			}
		}

		public byte[] decrypt(byte[] cipherData, char[] password) {
			return doCipher(Cipher.DECRYPT_MODE, cipherData, password);
		}

	}

	public static String checkSum(byte[] data) {
		try {
			return checkSum(data, "SHA");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e);
		}
	}

	public static String checkSum(byte[] data, String algorith) throws NoSuchAlgorithmException {
		MessageDigest instance = MessageDigest.getInstance(algorith);

		return Hex.encode(instance.digest(data));
	}

	public static String encrypt(String data, String phase) {
		Charset charset = Charset.forName("utf-8");
		byte[] bytes = data.getBytes(charset);
		byte[] cipher = EncrypterUtil.INSTANCE.encrypt(bytes, phase.toCharArray());

		return Hex.encode(cipher);
	}

	public static String decrypt(String data, String phase) {
		Charset charset = Charset.forName("utf-8");
		byte[] bytes = Hex.decode(data);
		byte[] cipher = EncrypterUtil.INSTANCE.decrypt(bytes, phase.toCharArray());

		return new String(cipher, charset);
	}
}
