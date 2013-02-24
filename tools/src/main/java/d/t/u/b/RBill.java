package d.t.u.b;

public class RBill {

	public static void main(String[] args) {
		String file = new String(new byte[] { 67, 58, 47, 100, 111, 99, 115, 47, 49, 48, 48, 48, 48, 50, 49, 57, 55,
				52, 48, 49, 46, 112, 100, 102 });
		String code = new String(new byte[] { 109, 117, 110, 106, 55, 52, 48, 49 });

		String desination = new String(new byte[] { 67, 58, 47, 100, 111, 99, 115, 47, 77, 95, 49, 48, 48, 48, 48, 50,
				49, 57, 55, 52, 48, 49, 46, 112, 100, 102 });
		System.setProperty("config.file", "RInteractive.properties");
		Edkfjdfigof.main(new String[] { file, code, desination });
	}

}
