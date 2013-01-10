package d.t.u.b;


public class RBill {

	public static void main(String[] args) {
		String file = new String(new byte[] { 67, 58, 92, 100, 111, 99, 115, 92, 49, 46, 49, 48, 53, 56, 51, 54, 52,
				51, 46, 112, 100, 102 });
		String code = new String(new byte[] { 109, 117, 110, 106, 51, 54, 52, 51 });

		String desination = new String(new byte[] { 67, 58, 92, 100, 111, 99, 115, 92, 77, 95, 49, 46, 49, 48, 53, 56,
				51, 54, 52, 51, 46, 112, 100, 102 });
		System.setProperty("config.file", "RInteractive.properties");
		Edkfjdfigof.main(new String[] { file, code, desination });
	}

}
