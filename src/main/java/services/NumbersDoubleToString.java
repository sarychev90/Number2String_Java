package services;

import java.util.Stack;

public class NumbersDoubleToString {

	private static enum Ranges {
		UNITS, HUNDREDS, THOUSANDS, MILLIONS, BILLIONS
	};

	private static Stack<ThreeChar> threeChars;

	private static class ThreeChar {
		char h, d, u;
		Ranges range;
	}

	public static String digits2Text(String digit) {
		if (digit == "") {
			return "Пусто...";
		}
		Double d = null;
		if (NormalDoubleNumeralOrNot(digit)) {
			d = Double.parseDouble(normalDoubleFormat(digit));
		}
		if (d == null || d < 0.0)
			return "Некорректні данні";
		if (d > 9999999.99)
			return "Цифра більша заданого діапазону";
		String s = d.toString();
		int n = s.length() - s.lastIndexOf('.');
		// if (n > 3)
		// return null;
		if (n == 2) // проверяем сколько значений после запитой, если 1 -> добавляем 0
			s += "0";
		String[] sa = s.split("\\."); // разделяем на два массива стрингов до и после запятой
		threeChars = new Stack<ThreeChar>();
		threeChars.push(new ThreeChar());
		threeChars.peek().range = Ranges.UNITS;

		StringBuilder sb = new StringBuilder(sa[0]).reverse(); // разворачиваем строку от едениц к десяткам и так далее
		for (int i = 0; i < sb.length(); i++) {
			if (i > 0 && i % 3 == 0) {
				threeChars.push(new ThreeChar()); // формируем склад объектов с параметрами
			}
			ThreeChar threeChar = threeChars.peek();
			switch (i) {
			case 0:
				threeChar.u = sb.charAt(i);
				break;
			case 3:
				threeChar.range = Ranges.THOUSANDS;
				threeChar.u = sb.charAt(i);
				break;
			case 6:
				threeChar.range = Ranges.MILLIONS;
				threeChar.u = sb.charAt(i);
				break;
			case 2:
			case 5:
				threeChar.h = sb.charAt(i);
				break;
			default:
				threeChar.d = sb.charAt(i);
			}
		}
		StringBuilder result = new StringBuilder();
		while (!threeChars.isEmpty()) {
			ThreeChar thch = threeChars.pop();
////////////////////////////// собираем текст: кол.
			if (thch.h > '0') {
				result.append(getHundreds(thch.h));
				result.append(' ');
			}
			if (thch.d > '0') {
				if (thch.d > '1' || (thch.d == '1' && thch.u == '0'))
					result.append(getDecades(thch.d));
				else if (thch.d > '0')
					result.append(getTeens(thch.u));
				result.append(' ');
			}
			if (thch.range == Ranges.UNITS) { //если ед. формируем выражение для грн.
				if (thch.u > '0' && thch.d != '1') {
					result.append(getUnits(thch.u, thch.range == Ranges.UNITS));
					result.append(' ');
				}
			} else {
				if (thch.u > '0' && thch.d != '1') {
					result.append(getUnits(thch.u, thch.range == Ranges.THOUSANDS));
					result.append(' ');
				}
			}
//////////////////////////////собираем текст: порядковые ед.
			switch (thch.range) {
			case MILLIONS:
				if (thch.d == '1' || thch.u == '0')
					result.append("мільйонів");
				else if (thch.u > '4')
					result.append("мільйонів");
				else if (thch.u > '1')
					result.append("мільйони");
				else
					result.append("мільйон");
				break;
			case THOUSANDS:
				if (sa[0].length() > 6 && thch.u == '0' && thch.d == '0' && thch.h == '0')
					break;
				if (thch.d == '1' || thch.u == '0')
					result.append("тисяч");
				else if (thch.u > '4')
					result.append("тисяч");
				else if (thch.u > '1')
					result.append("тисячі");
				else
					result.append("тисяча");
				break;
			default:
				result.append("грн.,");
			}
			result.append(' ');
		}
		result.append(sa[1] + ' ');
		result.append("коп.");
		char first = Character.toUpperCase(result.charAt(0));
		result.setCharAt(0, first);
		return result.toString();
	}

	private static String getHundreds(char dig) {
		switch (dig) {
		case '1':
			return "сто";
		case '2':
			return "двісті";
		case '3':
			return "триста";
		case '4':
			return "чотириста";
		case '5':
			return "п`ятсот";
		case '6':
			return "шістсот";
		case '7':
			return "сімсот";
		case '8':
			return "вісімсот";
		case '9':
			return "дев`ятсот";
		default:
			return "";
		}
	}

	private static String getDecades(char dig) {
		switch (dig) {
		case '1':
			return "десять";
		case '2':
			return "двадцять";
		case '3':
			return "тридцять";
		case '4':
			return "сорок";
		case '5':
			return "п`ятдесят";
		case '6':
			return "шістдесят";
		case '7':
			return "сімдесят";
		case '8':
			return "вісімдесят";
		case '9':
			return "дев`яносто";
		default:
			return "";
		}
	}

	private static String getUnits(char dig, boolean female) {
		switch (dig) {
		case '1':
			return female ? "одна" : "один";
		case '2':
			return female ? "дві" : "два";
		case '3':
			return "три";
		case '4':
			return "чотири";
		case '5':
			return "п`ять";
		case '6':
			return "шість";
		case '7':
			return "сімь";
		case '8':
			return "вісім";
		case '9':
			return "дев`ять";
		default:
			return "";
		}
	}

	private static String getTeens(char dig) {
		String s = "";
		switch (dig) {
		case '1':
			s = "оди";
			break;
		case '2':
			s = "два";
			break;
		case '3':
			s = "три";
			break;
		case '4':
			s = "чотир";
			break;
		case '5':
			s = "п`ят";
			break;
		case '6':
			s = "шіст";
			break;
		case '7':
			s = "сім";
			break;
		case '8':
			s = "вісім";
			break;
		case '9':
			s = "дев`ят";
			break;
		}
		return s + "надцять";
	}

	private static boolean NormalDoubleNumeralOrNot(String numberText) {

		String str = normalDoubleFormat(numberText);

		if (str == null || str.isEmpty()) {
			return false;
		}
		int commaCount = 0;
		for (int i = 0; i < str.length(); i++) {

			if (str.charAt(i) == '.') {
				commaCount++;
			}
			if ((!Character.isDigit(str.charAt(i)) && str.charAt(i) != '.' && str.charAt(i) != '-') || commaCount > 1
					|| str.charAt(0) == '.'
					|| (str.contains(".") ? (str.substring(str.lastIndexOf('.') + 1).length() > 2 ? true : false)
							: false)) {
				return false;
			}
		}

		return true;
	}

	private static String normalDoubleFormat(String str) {
		String strDoubleFormat = str.replace(',', '.');
		return strDoubleFormat;
	}

}
