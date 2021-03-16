package gr.mitsioulis.bookAuthorPublisherAPI.utils;

public class StringUtils {

	public static String getCleanPhoneNumber(String initial) {
		return initial.replaceAll("[^0-9]", "");
	}

}
