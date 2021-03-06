package gr.mitsioulis.bookAuthorPublisherAPI.utils;

import java.text.DateFormatSymbols;
import java.time.LocalDate;

public class DateUtils {

	private static String getDateSuffix(int numberOfDay) {
		switch (numberOfDay) {
		case 1:
		case 21:
		case 31:
			return ("st");

		case 2:
		case 22:
			return ("nd");

		case 3:
		case 23:
			return ("rd");

		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
		case 19:
		case 20:
		case 24:
		case 25:
		case 26:
		case 27:
		case 28:
		case 29:
		case 30:
			return ("th");
		default:
			return ("");
		}
	}

	private static String getMonthForInt(int num) {
		String month = "wrong";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if (num >= 0 && num <= 11) {
			month = months[num];
		}
		return month;
	}

	public static String getAuthorBirthPresenation(LocalDate birthDate) {
		StringBuilder sb = new StringBuilder();
		sb.append(birthDate.getDayOfMonth());
		sb.append(getDateSuffix(birthDate.getDayOfMonth()));
		sb.append(" of ");
		sb.append(getMonthForInt(birthDate.getMonthValue()));
		sb.append(" ");
		sb.append(birthDate.getYear());
		return sb.toString();
	}
}
