package com.mta.utilities;

import java.util.ArrayList;
import java.util.Date;

import com.mta.model.Drug;

public class FindBy {

	private static ArrayList<Drug> result;

	public static ArrayList<Drug> typeKey(ArrayList<Drug> list, Date key, int type) {
		if (type == 1) {
			result = new ArrayList<Drug>();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getId().equals(key)) {
					result.add(list.get(i));
				}
			}
		}
		if (type == 2) {
			result = new ArrayList<Drug>();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getName().equals(key)) {
					result.add(list.get(i));
				}
			}
		}
		if (type == 3) {
			result = new ArrayList<Drug>();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getProducer().equals(key)) {
					result.add(list.get(i));
				}
			}
		}

		if (type == 4) {
			result = new ArrayList<Drug>();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getMfg().toString().equals(key)) {
					result.add(list.get(i));
				}
			}
		}

		if (type == 5) {
			result = new ArrayList<Drug>();
			for (int i = 0; i < list.size(); i++) {
				if (TimeZone.formatDate(list.get(i).getExp()).equals(TimeZone.formatDate(key).toString())) {
					result.add(list.get(i));
				}
			}
		}

		return result;
	}
}
