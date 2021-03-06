package gr.mitsioulis.bookAuthorPublisherAPI.utils;

public class ISBNUtils {

	public static String getPresentationISBN(Long isbn) {
		StringBuilder sb = new StringBuilder();
		sb.append(isbn);
		sb.insert(3, '-');
		sb.insert(5, '-');
		sb.insert(8, '-');
		sb.insert(15, '-');
		return sb.toString();
	}

	public static Long getISBNFromPresentation(String isbn) throws NumberFormatException {
		String normal = isbn.replace("-", "");
		return Long.parseLong(normal);
	}

	public static String getStringISBNFromPresentation(String isbn) {
		String normal = isbn.replace("-", "");
		return normal;
	}
}
